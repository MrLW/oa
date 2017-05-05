package com.lw.oa.service.impl;

import com.lw.oa.dao.PersonDao;
import com.lw.oa.pojo.Person;
import com.lw.oa.service.PersonService;

/**
 * Created by lw on 2017/5/5.
 */
public class PersonServiceImpl implements PersonService {
    private PersonDao personDao ;

    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    public void savePerson(Person person) {
        personDao.savePerson(person);
    }
}
