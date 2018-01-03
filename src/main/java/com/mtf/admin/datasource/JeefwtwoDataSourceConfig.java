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
@MapperScan(basePackages = JeefwtwoDataSourceConfig.BASE_PACKAGE, sqlSessionFactoryRef = "jeefwtwoSqlSessionFactory")
public class JeefwtwoDataSourceConfig {

    public static final String BASE_PACKAGE = "com.mtf.admin.mapper.jeefwtwo";
    public static final String MAPPER_LOCATION = "classpath:mapper/jeefwtwo/*.xml";

    @Bean("jeefwtwoDataSource")
    @ConfigurationProperties(prefix = "dataSource.jeefwtwo")
    public DataSource jeefwtwoDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("jeefwtwoTransactionManager")
    public DataSourceTransactionManager jeefwtwoTransactionManager() {
        return new DataSourceTransactionManager(jeefwtwoDataSource());
    }

    @Bean("jeefwtwoSqlSessionFactory")
    public SqlSessionFactory jeefwtwoSqlSessionFactory(@Qualifier("jeefwtwoDataSource") DataSource jeefwtwoDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(jeefwtwoDataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(JeefwtwoDataSourceConfig.MAPPER_LOCATION));
        return sqlSessionFactoryBean.getObject();
    }
}
