package com.lw.oa.dao.impl;

import com.lw.oa.dao.PersonDao;
import com.lw.oa.dao.base.BaseDao;
import com.lw.oa.dao.base.impl.BaseDaoImpl;
import com.lw.oa.pojo.Person;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * Created by lw on 2017/5/5.
 */
@Repository("personDao")
public class PersonDaoImpl extends BaseDaoImpl<Person> implements  PersonDao {

    public void savePerson(Person person) {
    }
}
