package com.cj.common.pojo;

/**
 * 用来封装前端上传功能所需要的格式的数据. 前端上传用的是kindEditor的上传功能. 需要三个字段.
 * @author 崔健
 * @date 2016年7月31日下午9:29:50
 */
public class PictureResult {
	
	private int error; //成功时为0,失败是为1.
	private String url; //图片地址
	private String message; //返回的信息.
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
