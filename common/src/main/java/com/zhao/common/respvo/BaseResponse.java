package com.zhao.common.respvo;

import com.alibaba.fastjson.JSONObject;

/**
 * 统一响应基类
 * @author zhaolianqi
 * @param <T>
 */
public class BaseResponse<T> {

	private int code;
	private T content;
	private String msg;
	
	private BaseResponse() {}
	
	private BaseResponse(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public static BaseResponse<?> getError() {
		return new BaseResponse<Object>();
	}
	
	public static <T> BaseResponse<T> ERROR(String msg) {
		return new BaseResponse<T>(500, msg);
	}

	public static <T> BaseResponse<T> ERROR(int code, String msg) {
		return new BaseResponse<T>(code, msg);
	}

	public static <T> BaseResponse<T> SUCCESS(T t) {
		BaseResponse<T> res = new BaseResponse<T>();
		res.code = 0;
		res.content = t;
		res.msg = "success";
		return res;
	}
	
	public static <T> BaseResponse<T> SUCCESS(int code, T t) {
		BaseResponse<T> res = new BaseResponse<T>();
		res.code = code;
		res.content = t;
		res.msg = "error";
		return res;
	}
	
	public static <T> BaseResponse<T> SUCCESS() {
		return new BaseResponse<T>(0, "success");
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getContent() {
		return content;
	}
	public void setContent(T content) {
		this.content = content;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}
