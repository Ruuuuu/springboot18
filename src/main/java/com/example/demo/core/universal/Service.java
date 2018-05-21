package com.example.demo.core.universal;


import tk.mybatis.mapper.entity.Condition;

import java.util.List;
import java.util.TimerTask;

/**
 * @Description service层基础接口，其他service需继承此接口
 * @author yangr
 * @param <T>
 */
public interface Service<T> {

    /**
     * 持久化
     */
    Integer insert(T model);

    /**
     * 删
     */
    Integer deleteById(String id);

    /**
     * 批量删 ids -> "1,2,3,4"
     */
    Integer deleteByIds(String ids);

    /**
     * 改
     */
    Integer update(T model);

    /**
     * 查
     * @param id
     * @return T
     */
    T selectById(String id);

    /**
     * 通过model中的某个参数查
     */
    T selectBy(String fieldName, Object value);
    List<T> selectListBy(String fieldName, Object value);
    List<T> selectListByIds(String ids);
    List<T> selectByCondition(Condition condition);

    /**
     * 查询
     * @Description 查询
     * @return
     */
    List<T> selectAll();
    List<T> select(T record);
    T selectOne(T record);




}
