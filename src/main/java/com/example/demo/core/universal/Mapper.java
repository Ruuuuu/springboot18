package com.example.demo.core.universal;


import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.base.insert.InsertMapper;

/**
 * @author yangr
 * @Description 定制版的mybatis mapper插件接口
 * BaseMapper 接口继承了 select insert update delete
 */
public interface Mapper<T> extends BaseMapper<T>, ConditionMapper<T>, IdsMapper<T>, InsertMapper<T> {
}
