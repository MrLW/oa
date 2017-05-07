package com.lw.oa.pojo.system;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by lw on 2017/5/6.
 */
public class Department implements Serializable{
    private Long did ; // 表示符
    private String name ; // 部门名
    private String description ;//说明

    private Set<User> users ;

    public Long getDid() {
        return did;
    }

    public void setDid(Long did) {
        this.did = did;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
