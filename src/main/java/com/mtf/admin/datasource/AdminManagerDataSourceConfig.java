package com.mtf.admin.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = AdminManagerDataSourceConfig.BASE_PACKAGE, sqlSessionFactoryRef = "adminManagerSqlSessionFactory")
public class AdminManagerDataSourceConfig {

    public static final String BASE_PACKAGE = "com.mtf.admin.mapper.adminmanager";
    public static final String MAPPER_LOCATION = "classpath:mapper/adminmanager/*.xml";

    @Bean("adminManagerDataSource")
    @ConfigurationProperties(prefix = "dataSource.adminManager")
    @Primary
    public DataSource adminManagerDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("adminManagerTransactionManager")
    @Primary
    public DataSourceTransactionManager adminManagerTransactionManager() {
        return new DataSourceTransactionManager(adminManagerDataSource());
    }

    @Bean("adminManagerSqlSessionFactory")
    @Primary
    public SqlSessionFactory adminManagerSqlSessionFactory(@Qualifier("adminManagerDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(AdminManagerDataSourceConfig.MAPPER_LOCATION));
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        sqlSessionFactoryBean.setConfiguration(configuration);
        return sqlSessionFactoryBean.getObject();
    }
}
