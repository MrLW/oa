package com.lw.oa.service.impl;

import com.lw.oa.dao.DepartmentDao;
import com.lw.oa.pojo.system.Department;
import com.lw.oa.service.DepartmentService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * Created by lw on 2017/5/7.
 */
@Repository("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    @Resource(name = "departmentDao")
    private DepartmentDao departmentDao;

    public Collection<Department> getAllDepts() {
        return departmentDao.getAllEntry();
    }
}
