package com.mmm.develop.common.service;

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
	 * @param t
	 * @return int
	 * */
	JSONObject deleteOneService(T t);

	/**
	 * 查询一个对象(名称或id)
	 * 
	 * @param paramMap
	 * @return T
	 * */
	T findOneService(Map<String, Object> paramMap);

	/**
	 * 查询多个对象
	 * 
	 * @paramMap paramMap
	 * @return List<T>
	 * */
	List<T> findMultiService(Map<String, Object> paramMap);
}
