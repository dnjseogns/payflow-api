package com.dhwon.payflow_api.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.dhwon.payflow_api.api")
@EnableTransactionManagement
public class DatabaseConfig {
    /**
     * SqlSessionFactory
     * 세팅 경로(mybatis-config.xml) 및 mapping sql 경로 지정
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setConfigLocation(resolver.getResource("classpath:mybatis-config.xml"));
        sessionFactory.setMapperLocations(resolver.getResources("classpath:sql/*.xml"));
        sessionFactory.setVfs(SpringBootVFS.class);
        return sessionFactory.getObject();
    }

    /**
     * SqlSessionTemplate
     * MyBatis SQL 실행을 Spring 방식으로 안전하게 처리해주는 핵심 실행 객체
     * Service -> Mapper (interface) -> SqlSessionTemplate -> SqlSessionFactory -> DB
     */
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
