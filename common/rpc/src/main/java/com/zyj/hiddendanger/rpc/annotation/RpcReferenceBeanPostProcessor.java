package com.zyj.hiddendanger.rpc.annotation;

import com.zyj.hiddendanger.core.exception.sys.RPCInjectionException;
import com.zyj.hiddendanger.core.util.ThrowUtil;
import com.zyj.hiddendanger.rpc.config.RpcProperties;
import jakarta.annotation.Resource;
import lombok.NonNull;
import org.apache.dubbo.config.ReferenceConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class RpcReferenceBeanPostProcessor implements BeanPostProcessor {
    @Resource
    private RpcProperties rpcProperties;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // 扫描所有带 @RpcReference 的字段
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(RpcReference.class)) {
                try {
                    inject(field, bean);
                } catch (Exception e) {
                    throw new RPCInjectionException();
                }
            }
        }
        return bean;
    }

    private void inject(Field field, Object bean) throws Exception {
        field.setAccessible(true);
        RpcReference rpc = field.getAnnotation(
                RpcReference.class);
        Class<?> interfaceClass = field.getType();
        Object target;
        if (rpcProperties.isMock()) {
            // 自动查找 Mock 类：接口名 + Mock
            target = createMockInstance(interfaceClass);
        } else {
            // 注入真实 Dubbo
            target = createDubboReference(interfaceClass, rpc);
        }
        field.set(bean, target);
    }

    // 创建 Dubbo 代理
    private Object createDubboReference(Class<?> interfaceClass, RpcReference rpc) {
        ReferenceConfig<Object> reference = new ReferenceConfig<>();
        reference.setInterface(interfaceClass);
        reference.setVersion(rpc.version());
        reference.setGroup(rpc.group());
        reference.setTimeout(rpc.timeout());
        reference.setCheck(false);
        // 使用 Spring Cloud 的服务发现
        reference.setProtocol("dubbo");
        return reference.get();
    }

    // ===================== 核心：找带@RpcMockService的类 =====================
    private Object createMockInstance(Class<?> interfaceClass) throws Exception {
        List<Class<?>> result = new ArrayList<>();
        ClassPathScanningCandidateComponentProvider scanner = getScanner(interfaceClass);
        Set<BeanDefinition> candidates = scanner.findCandidateComponents(interfaceClass.getPackageName());
        for (BeanDefinition bd : candidates) {
            Class<?> clazz = Class.forName(bd.getBeanClassName());
            result.add(clazz);
        }
        ThrowUtil.throwIf(result.isEmpty(), () -> new RuntimeException("找不到Mock类"));
        ThrowUtil.throwIf(result.size() > 1, () -> new RuntimeException("找到多个Mock类"));
        return result.get(0).getConstructor().newInstance();
    }

    private static @NonNull ClassPathScanningCandidateComponentProvider getScanner(
            Class<?> interfaceClass) {
        ClassPathScanningCandidateComponentProvider scanner =
                new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter((metadataReader, metadataReaderFactory) -> {
            boolean isInterfaceImpl = new AssignableTypeFilter(interfaceClass)
                    .match(metadataReader, metadataReaderFactory);
            boolean hasAnnotation = new AnnotationTypeFilter(RpcMockService.class)
                    .match(metadataReader, metadataReaderFactory);
            return isInterfaceImpl && hasAnnotation;
        });
        return scanner;
    }
}
