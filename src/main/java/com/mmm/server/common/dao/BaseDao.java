package com.mmm.server.common.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface BaseDao<T> {
	/**
	 * 新增一个对象
	 * 
	 * @param t
	 * @return int
	 * */
	JSONObject insertOne(T t);

	/**
	 * 修改一个对象
	 * 
	 * @param t
	 * @return int
	 * */
	JSONObject updateOne(T t);

	/**
	 * 删除一个对象
	 * 
	 * @param paramMap
	 * @return int
	 * */
	JSONObject deleteOne(Map<String, Object> paramMap);

	/**
	 * 查询一个对象(名称或id)
	 * 
	 * @param t
	 * @return T
	 * */
	T findOne(T t);
}
