package com.cj.common.pojo;

import java.util.List;

/**
 * 专门给前端easyUI的datagrid组件返回JSON格式的数据集用的pojo. 
 * 在controller中把这个pojo转成JSON返回给前台,即可.
 * 两个字段的名字是固定的.要符合datagrid的要求.
 * @author 崔健
 * @date 2016年7月31日上午10:21:18
 */
public class EasyUIDataGridResult {
	
	public Long total;
	public List<?> rows; //通用的,所以要注意泛型.
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
	
	

}
