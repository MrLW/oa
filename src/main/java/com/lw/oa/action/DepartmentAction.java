package com.lw.oa.action;

import com.lw.oa.action.base.BaseAction;
import com.lw.oa.pojo.system.Department;
import com.lw.oa.service.DepartmentService;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by lw on 2017/5/7.
 */
@Repository("departmentAction")
@Scope("prototype")
public class DepartmentAction extends BaseAction<Department> {

    @Resource(name = "departmentService")
    private DepartmentService departmentService;

    public String showAllDept() throws Exception {
        Collection<Department> deptList = this.departmentService.getAllDepts();
        ActionContext.getContext().put("deptList", deptList);
        return listAction;
    }

    public String showListMapList() throws Exception {

        List<Map<String, List<Department>>> list = new ArrayList<Map<String, List<Department>>>();

        Department department = new Department();
        department.setName("文哥");

        List<Department> list2 = new ArrayList<Department>();
        list2.add(department);

        Map<String, List<Department>> map = new HashMap<String, List<Department>>();
        map.put("map1", list2);

        list.add(map);

        // 将List存入值栈
        ActionContext.getContext().put("list", list);

        return listAction;
    }


    public String showMapListMap(){
        Department department = new Department();
        department.setName("文哥");
        Map<String,List<Map<String,Department>>> map1 = new HashMap<String, List<Map<String,Department>>>() ;

        Map<String,Department> map2 = new HashMap<String, Department>() ;
        map2.put("dept",department) ;

        List<Map<String,Department>> list = new ArrayList<Map<String, Department>>() ;
        list.add(map2) ;


        map1.put("map1",list) ;

        // 将Map存入值栈
        ActionContext.getContext().put("map", map1);

        return listAction;
    }
}
