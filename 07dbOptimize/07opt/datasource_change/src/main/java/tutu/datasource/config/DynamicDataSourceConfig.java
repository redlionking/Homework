package tutu.datasource.config;
// AbstractRoutingDataSource, 该类充当了DataSource的路由中介, 能有在运行时, 根据某种key值来动态切换到真正的DataSource上。

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


public class DynamicDataSourceConfig extends AbstractRoutingDataSource {

	//@Autowired
	//@Qualifier("selectDataSource")
	//private DataSource selectDataSource;
	//
	//@Autowired
	//@Qualifier("updateDataSource")
	//private DataSource updateDataSource;

	/**
	 * 返回生效的数据源名称
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		String dbType = DataSourceContextHolder.getDbType();
		System.out.println("DataSourceContextHolder:" + dbType);
		return dbType;
	}

	/**
	 * 配置数据源
	 */
	//@Override
	//public void afterPropertiesSet() {
	//	super.afterPropertiesSet();
	//}

	public DynamicDataSourceConfig() {
		//Map<Object, Object> map = new HashMap<>();
		//map.put("selectDataSource", selectDataSource);
		//map.put("updateDataSource", updateDataSource);
		//setTargetDataSources(map);
		//setDefaultTargetDataSource(updateDataSource);
		//super.afterPropertiesSet();
	}
}
