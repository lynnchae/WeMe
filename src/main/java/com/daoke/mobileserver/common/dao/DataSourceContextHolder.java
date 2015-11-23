package com.daoke.mobileserver.common.dao;

import org.springframework.util.Assert;


/**
 * 在ThreadLocal中保存当前线程需要使用的DataSourceType。
 * @author chenlong
 * @see {@link DynamicRoutingDataSource}
 */
public class DataSourceContextHolder {
	private static final ThreadLocal<DataSourceType> contextHolder = new ThreadLocal<DataSourceType>();

	public static void setDataSourceType(DataSourceType dataSourceType) {
		Assert.notNull(dataSourceType, "必须指定DataSourceType。");
		contextHolder.set(dataSourceType);
	}

	public static DataSourceType getDataSourceType() {
		return contextHolder.get();
	}

	public static void clearDataSourceType() {
		contextHolder.remove();
	}
}
