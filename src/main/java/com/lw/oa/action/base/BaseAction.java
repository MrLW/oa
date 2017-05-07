package com.lw.oa.action.base;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import java.lang.reflect.ParameterizedType;

/**
 * Created by lw on 2017/5/7.
 */
public class BaseAction<E> extends ActionSupport implements ModelDriven<E> {

    private E e;
    private Class clazz;

    public BaseAction() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.clazz = (Class) pt.getActualTypeArguments()[0];
        try {
            this.e = (E) this.clazz.newInstance();
        } catch (InstantiationException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        }
    }

    public E getModel() {
        return e;
    }


    public static final String LISTACTION = "listAction";

    /**
     * 跳转了列表页面
     */
    public static String listAction = LISTACTION;

    public static final String UPDATE_UI = "updateUI";

    /*
     * 跳转到修改界面
     */
    public static String updateUI = UPDATE_UI;

    public static final String ADD_UI = "addUI";

    /**
     * 跳转到添加的页面
     */
    public static String addUI = ADD_UI;


    public static final String ACTION2ACTION = "action2action";

    /**
     * 由action跳转到action
     */
    public static String action2action = ACTION2ACTION;
}
