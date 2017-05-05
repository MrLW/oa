package com.lw.oa.factory;

import com.opensymphony.xwork2.ObjectFactory;
import com.opensymphony.xwork2.config.entities.ActionConfig;

import java.util.Date;
import java.util.Map;

/**
 * Created by lw on 2017/5/5.
 * 修改struts2创建对象的方式
 */
public class MyObjectFactory extends ObjectFactory {

    @Override
    public Object buildAction(String actionName, String namespace, ActionConfig config, Map<String, Object> extraContext) throws Exception {
        Date start = new Date() ;
        Object obj = super.buildAction(actionName, namespace, config, extraContext) ;
        Date end = new Date() ;
        return obj;
    }
}
