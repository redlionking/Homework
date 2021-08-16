# 作业二（待解决）：

 一开始在docker上部署两个mysql，都能连通

​	1、用docker配置

​			run是可run起来，但不知道错误在哪，日志找不到，连接不上

​    2、用压缩包配置

​			用navicat测试连接是成功的，但连不上

	[ERROR] 15:52:09.194 [ShardingSphere-Command-3] o.a.s.p.f.c.CommandExecutorTask - Exception occur: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'SHOW VARIABLES LIKE 'sql_mode'; SELECT COUNT(*) AS support_ndb FROM information_' at line 1

​	估计：mysql版本是8.0的问题 

# 作业四：

借鉴了大佬的作业。

```
docker run -p 3311:3306 --name mysql11 -e MYSQL_ROOT_PASSWORD=123456 -d mysql:5.7
docker run -p 3312:3306 --name mysql12 -e MYSQL_ROOT_PASSWORD=123456 -d mysql:5.7
```


