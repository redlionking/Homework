package tutu.datasource.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.core.io.support.PathMatchingResourcePatternResolver;


import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {
    // 可读数据源

    @Bean(name = "selectDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.select") // application.properteis中对应属性的前缀
    public DataSource dataSource1() {
        return DruidDataSourceBuilder.create().build();
    }

    // 可写数据源
    @Bean(name = "updateDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.update") // application.properteis中对应属性的前缀
    public DataSource dataSource2() {
        return DruidDataSourceBuilder.create().build();
    }


    /**
     * 将多个数据源注入到DynamicDataSource
     * @param dataSource1
     * @param dataSource2
     * @return
     */
    @Bean(name = "dynamicDataSource")
    public DynamicDataSourceConfig DataSource(@Qualifier("selectDataSource") DataSource dataSource1,
                                        @Qualifier("updateDataSource") DataSource dataSource2) {
        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put("selectDataSource", dataSource1);
        targetDataSource.put("updateDataSource", dataSource2);
        DynamicDataSourceConfig dataSource = new DynamicDataSourceConfig();
        dataSource.setTargetDataSources(targetDataSource);
        dataSource.setDefaultTargetDataSource(dataSource1);
        dataSource.afterPropertiesSet();
        return dataSource;
    }


    /**
     * 将动态数据源注入到SqlSessionFactory
     * @param dynamicDataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "SqlSessionFactory")
    public SqlSessionFactory getSqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dynamicDataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dynamicDataSource);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        bean.setConfiguration(configuration);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath*:mapping/*.xml"));
        bean.setTypeAliasesPackage("tutu.datasource_change.pojo");
        return bean.getObject();
    }
}
