package com.lw.oa.action;

import com.lw.oa.pojo.Person;
import com.lw.oa.service.PersonService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by lw on 2017/5/5.
 */
public class PersonAction extends ActionSupport{
    private PersonService personService ;

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    public String savePerson() throws Exception {
        Person person = new Person();
        person.setName("wenge222");
        personService.savePerson(person);
        return null;
    }
}
