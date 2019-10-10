<%@ page language="java" pageEncoding="UTF-8" %>
<%@page import="java.util.Locale"%>
<%@page
    import="org.springframework.web.servlet.i18n.SessionLocaleResolver"%>
<%@include file="taglibs.jsp" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
//这是获取lang后面的参数，即语言种类
    String LOCALE_SESSION_ATTRIBUTE_NAME = SessionLocaleResolver.class.getName() + ".LOCALE";
    Locale locale = (Locale) session.getAttribute(LOCALE_SESSION_ATTRIBUTE_NAME);
    System.out.println("-->当前语言："+locale);
    String language = "zh_CN";
    if (locale != null) {
        language = locale.toString();
    }
    request.setAttribute("language", language);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<title>Insert title here</title>
</head>
<body>
</body>
<script type="text/javascript" src="${ctx}/static/js/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="${ctx}/static/jquery.i18n/jquery.i18n.properties.js"></script>
<script type="text/javascript">
    $(function(){
    	loadProperties();
    });
    function loadProperties() {  
        var type = "<%=language%>";
        if (!type)
            type = "zh_CN";
        $.i18n.properties({
            name : 'message', //属性文件名     命名格式： 文件名_国家代号.properties
            path : '${ctx}/static/jquery.i18n/i18n/', //注意这里路径是你属性文件的所在文件夹
            mode : 'map',
            language : type, //这就是国家代号 name+language刚好组成属性文件名：strings+zh -> strings_zh.properties
            callback : function() {
			    //执行成功的方法
            }
        });
    }
</script>
</html>