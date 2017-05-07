package com.lw.oa.dao.impl;

import com.lw.oa.dao.DepartmentDao;
import com.lw.oa.dao.base.impl.BaseDaoImpl;
import com.lw.oa.pojo.system.Department;
import org.springframework.stereotype.Repository;

/**
 * Created by lw on 2017/5/7.
 */
@Repository("departmentDao")
public class DepartmentDaoImpl extends BaseDaoImpl<Department> implements DepartmentDao {
}
