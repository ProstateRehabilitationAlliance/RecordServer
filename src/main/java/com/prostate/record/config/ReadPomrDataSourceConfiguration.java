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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


/**
 * 读操作数据源
 */
@Configuration
@MapperScan(basePackages = "com.prostate.record.mapper.pomr.read", sqlSessionTemplateRef  = "readPomrSqlSessionTemplate")
public class ReadPomrDataSourceConfiguration {

    @Value("${spring.datasource.readPomr.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.readPomr.url}")
    private String url;

    @Value("${spring.datasource.readPomr.username}")
    private String username;

    @Value("${spring.datasource.readPomr.password}")
    private String password;


    @Bean(name = "readPomrDataSource")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(this.driverClassName);
        dataSource.setUrl(this.url);
        dataSource.setUsername(this.username);
        dataSource.setPassword(this.password);
        return dataSource;
    }

    @Bean(name = "readPomrSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("readPomrDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapping/pomr/read/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "readPomrTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("readPomrDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "readPomrSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("readPomrSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}