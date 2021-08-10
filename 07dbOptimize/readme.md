
# 作业1插入1000000条数据	

## 	java方式

​			1、PreparedStarement 很久

​			2、rewriteBatchedStatements=true + preparedStatement.addBatch();  //590s

​			3、setAutocommit=0//1328s

# 作业2 动态切换

###### 首先 在docker 里配一主一从（好多坑。。）

参考https://blog.csdn.net/abcde123_123/article/details/106244181



然后在java配置

命令行结果如下：

```she
DataSourceContextHolder:selectDataSource
2021-08-10 10:37:53.966  INFO 4364 --- [nio-8080-exec-2] com.alibaba.druid.pool.DruidDataSource   : {dataSource-1} inited
Order(goodId=111, userId=11, count=123)
read           //读
DataSourceContextHolder:selectDataSource
update			//写
DataSourceContextHolder:updateDataSource
2021-08-10 10:40:53.801  INFO 4364 --- [nio-8080-exec-7] com.alibaba.druid.pool.DruidDataSource   : {dataSource-2} inited
update		//写
DataSourceContextHolder:updateDataSource
```



# 作业3 使用框架

使用ShardingSphere-jdbc 5.0.0-alpha 实现读写分离配置

使用starter配置yml就好了





