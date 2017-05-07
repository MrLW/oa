package com.lw.oa.dao.base;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by lw on 2017/5/6.
 */
public interface BaseDao<E> {
    /**
     * 获取所有实体
     *
     * @return
     */
    public Collection<E> getAllEntry();

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    public E getEntryById(Serializable id);

    public void saveEntry(E e);

    public void deleteEntry(Serializable id);

    public void updateEntry(E e);

}
