package com.lw.oa.struts2.result;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;

/**
 * Created by lw on 2017/5/7.
 * struts2的不刷新结果集
 */
public class AjaxResult implements Result {

    private String dataA;
    private String dataB ;

    public String getDataA() {
        return dataA;
    }

    public void setDataA(String dataA) {
        this.dataA = dataA;
    }

    public String getDataB() {
        return dataB;
    }

    public void setDataB(String dataB) {
        this.dataB = dataB;
    }

    public void execute(ActionInvocation actionInvocation) throws Exception {
        System.out.println("dataA:" + dataA );
        System.out.println("dataB:" + dataB );
        ServletActionContext.getResponse().getWriter().print("aaa");

    }
}
