package com.lw.oa.action;

import com.lw.oa.pojo.Person;
import com.lw.oa.service.PersonService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by lw on 2017/5/5.
 */
@Scope("prototype")
@Controller
public class PersonAction extends ActionSupport{
    @Resource(name = "personService")
    private PersonService personService ;


    public String savePerson() throws Exception {
        Person person = new Person();
        person.setName("wenge3333333333");
        personService.savePerson(person);
        return null;
    }
}
