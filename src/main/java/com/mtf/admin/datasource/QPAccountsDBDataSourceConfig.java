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
@MapperScan(basePackages = QPAccountsDBDataSourceConfig.BASE_PACKAGE, sqlSessionFactoryRef = "QPAccountsDBSqlSessionFactory")
public class QPAccountsDBDataSourceConfig {

    public static final String BASE_PACKAGE = "com.mtf.admin.mapper.qpaccountsdb";
    public static final String MAPPER_LOCATION = "classpath:mapper/qpaccountsdb/*.xml";

    @Bean("QPAccountsDBDataSource")
    @ConfigurationProperties(prefix = "dataSource.QPAccountsDB")
    public DataSource QPAccountsDBDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("QPAccountsDBTransactionManager")
    public DataSourceTransactionManager QPAccountsDBTransactionManager() {
        return new DataSourceTransactionManager(QPAccountsDBDataSource());
    }

    @Bean("QPAccountsDBSqlSessionFactory")
    public SqlSessionFactory QPAccountsDBSqlSessionFactory(@Qualifier("QPAccountsDBDataSource") DataSource QPAccountsDBDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(QPAccountsDBDataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(QPAccountsDBDataSourceConfig.MAPPER_LOCATION));
        return sqlSessionFactoryBean.getObject();
    }
}
