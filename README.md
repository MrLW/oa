# oa 项目笔记

## 事务

spring容器为事务处理提供了一个平台。

**一个总的接口**

```
public interface PlatformTransactionManager {
   TransactionStatus getTransaction(TransactionDefinition var1) throws TransactionException;

   void commit(TransactionStatus var1) throws TransactionException;

   void rollback(TransactionStatus var1) throws TransactionException;
}
 
```

**事务的定义接口**

```
public interface TransactionDefinition {
    // 事务的传播属性
    int PROPAGATION_REQUIRED = 0;
    int PROPAGATION_SUPPORTS = 1;
    int PROPAGATION_MANDATORY = 2;
    int PROPAGATION_REQUIRES_NEW = 3;
    int PROPAGATION_NOT_SUPPORTED = 4;
    int PROPAGATION_NEVER = 5;
    int PROPAGATION_NESTED = 6;
    // 事务的隔离级别
    int ISOLATION_DEFAULT = -1;
    int ISOLATION_READ_UNCOMMITTED = 1;
    int ISOLATION_READ_COMMITTED = 2;
    int ISOLATION_REPEATABLE_READ = 4;
    int ISOLATION_SERIALIZABLE = 8;
    
    int TIMEOUT_DEFAULT = -1;

    int getPropagationBehavior();

    int getIsolationLevel();

    int getTimeout();

    boolean isReadOnly();

    String getName();
}
```

**事务的状态**

```
public interface TransactionStatus extends SavepointManager {
    boolean isNewTransaction();

    boolean hasSavepoint();

    void setRollbackOnly();

    boolean isRollbackOnly();

    void flush();

    boolean isCompleted();
}

```

**事务管理器的抽象类**


对PlatformTransactionManager中的三个方法进行了重写

```
public abstract class AbstractPlatformTransactionManager implements PlatformTransactionManager, Serializable {

}
```

**事务的传播行为和隔离级别**
http://blog.csdn.net/it_wangxiangpan/article/details/24180085

`传播行为`

```
// 通过代码演示
ServiceA {   
       
       
     void methodA() {   
         ServiceB.methodB();   
     }   
  
}   
  
ServiceB {   
       
         
     void methodB() {   
     }   
       
}   
```

- PROPAGATION_REQUIRED：假如当前正要执行的事务不在另外一个事务里，那么就起一个新的事务

- PROPAGATION_SUPPORTS：如果当前在事务中，即以事务的形式运行，如果当前不再一个事务中，那么就以非事务的形式运行
                       这就跟平常用的普通非事务的代码只有一点点区别了。不理这个，因为我也没有觉得有什么区别

- PROPAGATION_MANDATORY：必须在一个事务中运行。也就是说，他只能被一个父事务调用。否则，他就要抛出异常。

- PROPAGATION_REQUIRES_NEW：这个就比较绕口了。 比如我们设计ServiceA.methodA的事务级别为PROPAGATION_REQUIRED，ServiceB.methodB的事务级别为PROPAGATION_REQUIRES_NEW，那么当执行到ServiceB.methodB的时候，ServiceA.methodA所在的事务就会挂起，ServiceB.methodB会起一个新的事务，等待ServiceB.methodB的事务完成以后，他才继续执行。他与PROPAGATION_REQUIRED 的事务区别在于事务的回滚程度了。因为ServiceB.methodB是新起一个事务，那么就是存在两个不同的事务。如果ServiceB.methodB已经提交，那么ServiceA.methodA失败回滚，ServiceB.methodB是不会回滚的。如果ServiceB.methodB失败回滚，如果他抛出的异常被ServiceA.methodA捕获，ServiceA.methodA事务仍然可能提交。

- PROPAGATION_NOT_SUPPORTED：当前不支持事务。比如ServiceA.methodA的事务级别是PROPAGATION_REQUIRED ，而ServiceB.methodB的事务级别是PROPAGATION_NOT_SUPPORTED ，
                            那么当执行到ServiceB.methodB时，ServiceA.methodA的事务挂起，而他以非事务的状态运行完，再继续ServiceA.methodA的事务。


- PROPAGATION_NEVER：不能在事务中运行。假设ServiceA.methodA的事务级别是PROPAGATION_REQUIRED， 而ServiceB.methodB的事务级别是PROPAGATION_NEVER ，
                     那么ServiceB.methodB就要抛出异常了。

- PROPAGATION_NESTED：理解Nested的关键是savepoint。他与PROPAGATION_REQUIRES_NEW的区别是，PROPAGATION_REQUIRES_NEW另起一个事务，将会与他的父事务相互独立，而Nested的事务和他的父事务是相依的，他的提交是要等和他的父事务一块提交的。也就是说，如果父事务最后回滚，他也要回滚的。 
                     而Nested事务的好处是他有一个savepoint。 




`隔离级别`
http://blog.csdn.net/fg2006/article/details/6937413

- Read uncommitted ：读到未提交的数据.                         脏读
- Read committed ：读提交(会出现更新问题,Oracle SqlServer默认). 不可重复读
- Repeatable read :  可以避免不可重复读.(MySQL默认)             幻读
- Serializable ：最高的事务隔离级别，同时代价也花费最高，性能很低，一般很少使用.


## struts2

**struts2加载配置文件的过程**

1、加载web.xml中的StrutsPrepareAndExecuteFilter核心过滤器,进行初始化.

2、执行init方法,在
```
try {
    FilterHostConfig config = new FilterHostConfig(filterConfig);
    init.initLogging(config);
    Dispatcher dispatcher = init.initDispatcher(config);
    init.initStaticContentLoader(config, dispatcher);
    this.prepare = new PrepareOperations(filterConfig.getServletContext(), dispatcher);
    this.execute = new ExecuteOperations(filterConfig.getServletContext(), dispatcher);
    this.excludedPatterns = init.buildExcludedPatternsList(dispatcher);
    this.postInit(dispatcher, filterConfig);
} finally {
    init.cleanup();
}

```

3、执行InitOperations的init方法
```
public Dispatcher initDispatcher(HostConfig filterConfig) {
    Dispatcher dispatcher = this.createDispatcher(filterConfig);
    dispatcher.init();
    return dispatcher;
}
```

4、进入dispatcher的init方法
```
// 初始化org/apache/struts2/default.properties文件
this.init_DefaultProperties();

// 初始化struts-default.xml,struts-plugin.xml,struts.xml
this.init_TraditionalXmlConfigurations();

```

这三种配置文件都是classpath的根目录存放,其中 struts-plugin.xml文件可以有很多个，
而且通常情况下是在lib下的jar包中出现.



## struts2高级特性

当struts2给我们的结果集不满足需求时,则需要我们自定义结果期。

步骤：

* 1、创建一个类继承Result/StrutsResultSupport,重写execute/doExecute方法.
```
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

```
* 2、在struts.xml中创建结果集

```
<package name="ajax" namespace="/" extends="struts-default">
        <!--自定义结果集,实现不刷新-->
        <result-types>
            <result-type name="ajax" class="com.lw.oa.struts2.result.AjaxResult"></result-type>
        </result-types>
</package>
```
* 3、使用
```
<package name="test" namespace="/" extends="ajax">

    <action name="resultAction_*" method="{1}" class="com.lw.oa.struts2.action.ResultAction">
        <result type="ajax">
            <!--给结果集设置数据-->
            <param name="dataA">数据A</param>
            <param name="dataB">数据B</param>
        </result>
    </action>
</package>
```
* Result:无刷新操作时,StrutsResultSupport:有刷新操作时



