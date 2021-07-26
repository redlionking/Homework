
# Spring三种方式

​	1、xml

​	配置<bean>

​	2、java

​	@Configuration上@ComponentScan扫描@Component包

​	或者 xml 扫描@Component包

​	3、注解

​	@Configuration @Bean

# 自动装配+starter

```
@Configuration
@ConditionalOnClass(School.class)
@EnableConfigurationProperties(SchoolProperties.class)
@PropertySource("classpath:application.properties")
@ConditionalOnProperty(prefix = "school", value = "enabled", havingValue = "true")
```

property有关的是为了属性的配置

configuration 则是生成bean

测试成功后maven 打包，导入另一个项目。

# JDBC

比较常规，

数据源加载后，给一个getConnection就跟之前调用一样
