package com.xors.home.center.configuration;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

/**
 * @author qingzhi
 */
@Configuration
@MapperScan(basePackages = "com.xors.home.center.dao", sqlSessionTemplateRef = "rdsSqlSessionTemplate")
public class DataSourceRdsConfiguration {

    @Bean(name = "rdsDataSource")
    @Qualifier("rdsDataSource")
    public DataSource rdsDataSource(Environment env) {
        return AbstractDruidConfiguration.initDruidDataSource(env.getProperty("spring.datasource.rds.url"), env, "spring.datasource.rds.");
    }

    @Bean(name = "rdsSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("rdsDataSource") DataSource dataSource,
                                               Environment env) throws Exception {
        return AbstractDruidConfiguration.getSqlSessionFactory(dataSource, "classpath:mybatis/mapper/*.xml");
    }

    @Bean(name = "rdsSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(
            @Qualifier("rdsSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
