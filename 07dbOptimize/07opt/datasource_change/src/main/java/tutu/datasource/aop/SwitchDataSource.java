package tutu.datasource.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import tutu.datasource.config.DataSourceContextHolder;

@Aspect
@Component
@Lazy(false)
@Order(0) // Order设定AOP执行顺序 使之在数据库事务上先执行
public class SwitchDataSource {

	@Before("execution(* tutu.datasource.mapper.OrderMapper.*(..))")
	public void process(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		if (methodName.startsWith("get") || methodName.startsWith("count") || methodName.startsWith("find")
				|| methodName.startsWith("list") || methodName.startsWith("select") || methodName.startsWith("check")) {
			// 读
			System.out.println("read");
			DataSourceContextHolder.setDbType("selectDataSource");
		} else {
			// 切换dataSource
			System.out.println("update");
			DataSourceContextHolder.setDbType("updateDataSource");
		}
	}

	//@AfterReturning("execution(* tutu.datasource_change.mapper.OrderMapper.*(..))")
	//public void post(){
	//	System.out.println("数据源为"+DataSourceContextHolder.getDbType());
	//	DataSourceContextHolder.clearDbType();
	//}
}
