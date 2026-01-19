package com.zyj.hiddendanger.aop.util;

import com.zyj.hiddendanger.aop.exception.SpELException;
import com.zyj.hiddendanger.aop.exception.SpELExceptionCode;
import com.zyj.hiddendanger.core.util.ThrowUtil;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParseException;
import org.springframework.expression.spel.SpelCompilerMode;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class SpELValidatorAndParser {
    public Expression validateAndParse(String expression) {
        SpelParserConfiguration config = new SpelParserConfiguration(SpelCompilerMode.OFF, this
                .getClass()
                .getClassLoader());
        ExpressionParser parser = new SpelExpressionParser(config);
        return ThrowUtil.supplyWithExceptionTranslation(() -> parser.parseExpression(expression), ParseException.class,
                                                        e -> new SpELException(SpELExceptionCode.SPEL_PARSE_EXCEPTION));
    }
}
