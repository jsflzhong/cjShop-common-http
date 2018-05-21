package com.cj.common.pojo;

/**
 * 专用来封装前端easyUI的tree组件需要的数据格式.再在controller中封转换成JSON.
 * @author 崔健
 * @date 2016年7月31日上午11:11:12
 */
public class EasyUITreeNode {
	private long id;
	private String text;
	//只有"closed"和"open"两个状态. 代表树的节点是关闭的还是打开的.
	//这个字段在库中是没有的.是我根据表的字段"isParent"来判断的.如果是父节点,状态就是关闭的.
	private String state;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}
