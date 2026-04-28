package com.zyj.hiddendanger.web.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyj.hiddendanger.rpc.response.RpcPageResult;

import java.util.Collections;
import java.util.List;

public class PageUtil {
    private PageUtil() {
    }

    @SuppressWarnings("unchecked")
    public static <E, T> Page<T> convert2Page(Page<E> sourcePage, List<T> newRecords) {
        Page<T> targetPage = (Page<T>) sourcePage;
        targetPage.setRecords(newRecords);
        return targetPage;
    }

    public static <E, T> Page<T> convert2Page(RpcPageResult<E> sourcePage, List<T> newRecords) {
        return convert2Page(convert2Page(sourcePage), newRecords);
    }

    public static <T> RpcPageResult<T> convert2RpcPageResult(Page<T> sourcePage) {
        RpcPageResult<T> targetPage = new RpcPageResult<>();
        targetPage.setRecords(sourcePage.getRecords());
        targetPage.setTotal(sourcePage.getTotal());
        targetPage.setPageSize(sourcePage.getSize());
        targetPage.setCurrent(sourcePage.getCurrent());
        targetPage.setPages(sourcePage.getPages());
        return targetPage;
    }

    public static <T> Page<T> convert2Page(RpcPageResult<T> sourcePage) {
        Page<T> targetPage = new Page<>();
        targetPage.setRecords(sourcePage.getRecords());
        targetPage.setTotal(sourcePage.getTotal());
        targetPage.setSize(sourcePage.getPageSize());
        targetPage.setCurrent(sourcePage.getCurrent());
        targetPage.setPages(sourcePage.getPages());
        return targetPage;
    }


    public static <T> Page<T> emptyPage() {
        Page<T> page = new Page<>();
        page.setCurrent(1);
        page.setSize(10);
        page.setTotal(0);
        page.setPages(0);
        page.setRecords(Collections.emptyList());
        return page;
    }
}
