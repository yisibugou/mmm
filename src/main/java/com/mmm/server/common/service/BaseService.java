package com.mmm.server.common.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

public interface BaseService<T> {
	/**
	 * 新增一个对象
	 * 
	 * @param t
	 * @return int
	 * */
	JSONObject insertOneService(T t);

	/**
	 * 修改一个对象
	 * 
	 * @param t
	 * @return int
	 * */
	JSONObject updateOneService(T t);

	/**
	 * 删除一个对象
	 * 
	 * @param paramMap
	 * @return int
	 * */
	JSONObject deleteOneService(Map<String, Object> paramMap);

	/**
	 * 查询一个对象(名称或id)
	 * 
	 * @param t
	 * @return T
	 * */
	T findOneService(T t);
}
