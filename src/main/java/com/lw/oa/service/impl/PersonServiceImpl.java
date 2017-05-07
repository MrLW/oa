package com.lw.oa.service.impl;

import com.lw.oa.dao.PersonDao;
import com.lw.oa.pojo.Person;
import com.lw.oa.service.PersonService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by lw on 2017/5/5.
 */
@Service("personService")
public class PersonServiceImpl implements PersonService {
    @Resource(name = "personDao")
    private PersonDao personDao;

    @Transactional(readOnly = false)
    public void savePerson(Person person) {
        personDao.saveEntry(person);
    }
}
