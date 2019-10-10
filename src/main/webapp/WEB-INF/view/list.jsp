<%@page import="org.apache.xmlbeans.impl.xb.xsdschema.ImportDocument.Import"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="com.demons.shiro.model.User" %>
<%@include file="common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
User user = new User();
user.setName("小花");
user.setAge(22);
request.setAttribute("user", user); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="${ctx}/static/My97DatePicker/WdatePicker.js"></script>
<body>
    List Page!
    <h4><spring:message code="password" /></h4>
    <br></br>
    <h6><spring:message code="username" />：${user.name}</h6>
   <%--  <h5><%=user %></h5> --%>
    <a href="${ctx}/shiro/logout"><spring:message code="logout" /></a>
    <br/>
    <!-- $dp.$D里面写id，此处表示最大的时间是这个id下所选择的  可以选其他的id  -->
    WdatePicker时间插件：<input type="text" onfocus="WdatePicker({ dateFmt:'yy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'realLeaveTime\')}' })" 
                id="realLeaveTime" class="form-control" isvalid="yes" checkexpession="NotNull">
    <a href="${ctx}/other/bootstrap">bootstrap分页</a>
    <shiro:hasRole name="admin">
	    <a href="${ctx}/admin">To Admin</a>
    </shiro:hasRole>
    <br/>
    <shiro:hasRole name="user">
        <a href="${ctx}/user">To User</a>
    </shiro:hasRole>
    <span></span>
</body>
</html>