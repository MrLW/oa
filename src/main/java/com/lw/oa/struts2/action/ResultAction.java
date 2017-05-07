package com.lw.oa.struts2.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by lw on 2017/5/7.
 * 测试异步不刷新结果集
 */
public class ResultAction extends ActionSupport{

    public String test(){
        return SUCCESS ;
    }
}
