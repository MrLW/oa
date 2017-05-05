package com.lw.oa.dao.impl;

import com.lw.oa.dao.PersonDao;
import com.lw.oa.pojo.Person;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Created by lw on 2017/5/5.
 */
public class PersonDaoImpl extends HibernateDaoSupport implements PersonDao {

    public void savePerson(Person person) {
        HibernateTemplate ht = this.getHibernateTemplate();
        ht.save(person);
    }
}
