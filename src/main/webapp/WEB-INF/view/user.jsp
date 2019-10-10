<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    User Page!
    <button onclick="test()">测试</button>
</body>
<script type="text/javascript" src="${ctx}/static/js/jquery-2.1.3.min.js"></script>
<script type="text/javascript">
function test(){
	$.ajax({
        type: "post",
        url: "${ctx}/other/testInter",
        data: {},
        dataType: "json",
        success: function(result){
            console.log("-----值："+result);
        }
    });
} 
</script>
</html>