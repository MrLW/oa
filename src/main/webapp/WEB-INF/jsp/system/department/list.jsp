<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html>
<head>
    <title>部门列表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/css/images/title_arrow.gif"/>
            部门管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">

        <!-- 表头-->
        <thead>
        <tr align=center valign=middle id=TableTitle>
            <td width="150px">部门名称</td>
            <td width="200px">职能说明</td>
            <td>相关操作</td>
        </tr>
        </thead>

        <!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="deptList">
        <s:iterator value="#deptList">
            <tr class="TableDetail1 template">
                <td><s:property value="name"/></td>
                <td><s:property value="description"/></td>
                <td><a onClick="return window.confirm('这将删除所有的下级部门，您确定要删除吗？')" href="#">删除</a>
                    <a href="saveUI.html">修改</a>
                </td>
            </tr>
        </s:iterator>

        <s:iterator value="#list">
            <%--不写value默认遍历栈顶--%>
            <s:iterator>
                <s:property value="key"/>
                <s:iterator value="value">
                    <s:property value="name"/>
                </s:iterator>
            </s:iterator>
        </s:iterator>


        <s:iterator value="#map">
            大map的key:<s:property value="key"/>
            <s:iterator value="value">
                <s:iterator>
                    小map的key:<s:property value="key"/>
                    部门:<s:property value="dept.name"/>
                </s:iterator>
            </s:iterator>
        </s:iterator>
        </tbody>
    </table>

    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
            <a href="saveUI.html"><img src="${pageContext.request.contextPath}/css/images/createNew.png"/></a>
        </div>
    </div>
</div>

<!--说明-->
<div id="Description">
    说明：<br/>
    1，列表页面只显示一层的（同级的）部门数据，默认显示最顶级的部门列表。<br/>
    2，点击部门名称，可以查看此部门相应的下级部门列表。<br/>
    3，删除部门时，同时删除此部门的所有下级部门。
</div>

</body>
</html>
