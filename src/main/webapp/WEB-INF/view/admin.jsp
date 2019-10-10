<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    Admin Page! 
    <h5></h5>
</body>
<script type="text/javascript">
$(function(){
    $("h5").text($.i18n.prop('test'));
});
var mess = {"id":1,"name":"小花","age":12,"salt":1};
$.ajax({
	url : "${ctx}/other/test",
	data : JSON.stringify(mess),
	type : "post",
	dataType : "json",
	async : false,
	contentType : 'application/json',
	success : function(mes){
		console.log("666:"+mes[0].name);
	},
	error : function(){
		alert("失败！");
	}
})
$.post("${ctx}/other/te",mess,function(data,st,x){
	console.log("post方法1:"+JSON.stringify(data));
	for(var i =0;i<data.length; i++){
		console.log(data[i]);
	}
},"json")
</script>
</html>