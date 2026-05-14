package com.dhwon.payflow_api.interceptor;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Matcher;

@Intercepts({
        @Signature(type = Executor.class, method = "update",
                args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class MybatisLogInterceptor implements Interceptor {

    static private final Logger logger = LoggerFactory.getLogger(MybatisLogInterceptor.class);
    private Properties properties;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameter = invocation.getArgs().length > 1 ? invocation.getArgs()[1] : null;

        String sqlId = mappedStatement.getId();
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        Configuration configuration = mappedStatement.getConfiguration();

        Object returnValue;

        try {
            long start = System.currentTimeMillis();

            returnValue = invocation.proceed();

            long end = System.currentTimeMillis();
            long time = end - start;

            if (time > 1) {
                String sql = getSql(configuration, boundSql, sqlId, time);
                logger.debug(sql);
            }

            return returnValue;

        } catch (Exception ex) {
            String sql = getSql(configuration, boundSql, sqlId, 0);
            logger.debug(sql);
            throw ex;
        }
    }

    public static String getSql(Configuration configuration,
                                BoundSql boundSql,
                                String sqlId,
                                long time) {

        String sql = showSql(configuration, boundSql);

        StringBuilder sb = new StringBuilder();
        sb.append("\n<SQL LOG ID   > ")
                .append(sqlId)
                .append("(")
                .append(time)
                .append(" ms)\n")
                .append("<SQL LOG QUERY> ")
                .append(sql);

        return sb.toString();
    }

    public static String showSql(Configuration configuration, BoundSql boundSql) {

        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();

        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");

        if (parameterObject == null || parameterMappings.isEmpty()) {
            return sql;
        }

        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();

        for (ParameterMapping parameterMapping : parameterMappings) {

            String propertyName = parameterMapping.getProperty();
            Object value;

            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                value = parameterObject;

            } else {
                MetaObject metaObject = configuration.newMetaObject(parameterObject);

                if (metaObject.hasGetter(propertyName)) {
                    value = metaObject.getValue(propertyName);

                } else if (boundSql.hasAdditionalParameter(propertyName)) {
                    value = boundSql.getAdditionalParameter(propertyName);

                } else {
                    value = null;
                }
            }

            sql = sql.replaceFirst(
                    "\\?",
                    Matcher.quoteReplacement(getParameterValue(value))
            );
        }

        return sql;
    }

    private static String getParameterValue(Object obj) {

        if (obj == null) {
            return "null";
        }

        if (obj instanceof String) {
            return "'" + obj + "'";
        }

        if (obj instanceof Date) {
            DateFormat formatter =
                    DateFormat.getDateTimeInstance(DateFormat.DEFAULT,
                            DateFormat.DEFAULT,
                            Locale.KOREA);
            return "'" + formatter.format(obj) + "'";
        }

        return obj.toString();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}