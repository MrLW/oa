package com.lw.oa.dao.base.impl;

import com.lw.oa.dao.base.BaseDao;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

/**
 * Created by lw on 2017/5/6.
 */
public class BaseDaoImpl<E> implements BaseDao<E> {

    private Class clazz;

    @Resource(name = "hibernateTemplate")
    public HibernateTemplate hibernateTemplate;

    public BaseDaoImpl() {
        /**
         * 1、this代表子类或者本类.
         * 2、不能把BaseDaoImpl在spring容器中实例化,因为如果实例化就不是泛型了.
         */
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.clazz = (Class) pt.getActualTypeArguments()[0];
    }

    public Collection<E> getAllEntry() {
        return hibernateTemplate.find("from " + this.clazz.getName());
    }

    public E getEntryById(Serializable id) {
        // 获取持久化类的数据字典
        ClassMetadata cm = hibernateTemplate.getSessionFactory().getClassMetadata(this.clazz);//
        String sid = cm.getIdentifierPropertyName();
        return (E) this.hibernateTemplate.
                find("from " + this.clazz.getName()
                                +
                                " where "
                                + cm.getIdentifierPropertyName()
                                + "=?",
                        id).get(0);
    }

    public void saveEntry(E e) {
        hibernateTemplate.save(e);
    }

    public void deleteEntry(Serializable id) {
        E e = this.getEntryById(id) ;
        hibernateTemplate.delete(e);
    }

    public void updateEntry(E e) {
        hibernateTemplate.update(e);
    }
}
