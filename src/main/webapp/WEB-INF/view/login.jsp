<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
    <%  int name = 7; 
    %>
    <h6><%=name %></h6>
    <form action="${ctx}/shiro/login" method="POST">
       <spring:message code="username" />: <input type="text" name="username"/>
       <br></br>
       <spring:message code="password" />: <input type="text" name="password"/>
       <br></br>
       <input type="submit" value="<spring:message code="login" />"/>
    </form>
    <select id="sel">
        <option><spring:message code="Language" /></option>
        <option value="zh_CN"><spring:message code="Chinese" /></option>
        <option value="en_US"><spring:message code="English" /></option>
    </select>
</body>
<script type="text/javascript">
    $("#sel").change(function(){
    	var options=$("#sel option:selected"); //获取选中的项
    	var language = options.val();
	    location.href = "${ctx}/login?lang="+language; 
    })
</script>
</html>