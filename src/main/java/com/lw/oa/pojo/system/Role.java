package com.lw.oa.pojo.system;

import java.util.Set;

/**
 * Created by lw on 2017/5/7.
 */
public class Role {
    private Long rid;
    private String name;
    private String description;
    private Set<User> users;

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
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
