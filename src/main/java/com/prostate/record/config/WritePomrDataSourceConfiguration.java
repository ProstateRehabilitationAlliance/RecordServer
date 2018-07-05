package com.prostate.record.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 写操作 数据源
 */
@Configuration
@MapperScan(basePackages = "com.prostate.record.mapper.pomr.write", sqlSessionTemplateRef  = "writePomrSqlSessionTemplate")
public class WritePomrDataSourceConfiguration {

    @Value("${spring.datasource.writePomr.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.writePomr.url}")
    private String url;

    @Value("${spring.datasource.writePomr.username}")
    private String username;

    @Value("${spring.datasource.writePomr.password}")
    private String password;

    @Bean(name = "writePomrDataSource")
    @Primary
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(this.driverClassName);
        dataSource.setUrl(this.url);
        dataSource.setUsername(this.username);
        dataSource.setPassword(this.password);
        return dataSource;
    }

    @Bean(name = "writePomrSqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("writePomrDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapping/pomr/write/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "writePomrTransactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager(@Qualifier("writePomrDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "writePomrSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("writePomrSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}