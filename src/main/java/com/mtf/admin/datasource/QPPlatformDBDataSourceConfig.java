package com.mtf.admin.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = QPPlatformDBDataSourceConfig.BASE_PACKAGE, sqlSessionFactoryRef = "QPPlatformDBSqlSessionFactory")
public class QPPlatformDBDataSourceConfig {

    public static final String BASE_PACKAGE = "com.mtf.admin.mapper.qpplatformdb";
    public static final String MAPPER_LOCATION = "classpath:mapper/qpplatformdb/*.xml";

    @Bean("QPPlatformDBDataSource")
    @ConfigurationProperties(prefix = "dataSource.QPPlatformDB")
    public DataSource QPPlatformDBDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("QPPlatformDBTransactionManager")
    public DataSourceTransactionManager QPPlatformDBTransactionManager() {
        return new DataSourceTransactionManager(QPPlatformDBDataSource());
    }

    @Bean("QPPlatformDBSqlSessionFactory")
    public SqlSessionFactory QPPlatformDBSqlSessionFactory(@Qualifier("QPPlatformDBDataSource") DataSource QPPlatformDBDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(QPPlatformDBDataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(QPPlatformDBDataSourceConfig.MAPPER_LOCATION));
        return sqlSessionFactoryBean.getObject();
    }
}
