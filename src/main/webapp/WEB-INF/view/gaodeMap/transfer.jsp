<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
<style type="text/css">
sBMap_cpyCtrl {
	display: none;
}

.anchorBL {
	display: none;
}

#testdiv {
	position: absolute;
	right: 50px;
	bottom: 10px;
}

#dingw {
	height: 25px;
}

#shuaxdiv {
	position: absolute;
	right: 84px;
	bottom: 10px;
}

#shuax {
	height: 25px;
}

#fanhui {
	position: absolute;
	right: 140px;
	top: 0px;
	height: 70px;
	width: 120px;
	z-index: 20;
}

#fanhuiZYM {
	position: absolute;
	right: 5px;
	top: 0px;
	height: 70px;
	width: 120px;
	z-index: 20;
}

#biaotDiv {
	position: absolute;
	top: 0px;
	left: 20px;
	height: 66px;
	z-index: 20;
	font-size: 24px;
	text-align: left;
	line-height: 66px;
	color: #FFFFFF;
	font-family: "微软雅黑";
}

.tangram-suggestion-main {
	max-height: 150px;
}
</style>
<style type="text/css">
body, html {
	width: 100%;
	height: 100%;
	margin: 0;
	font-family: "微软雅黑";
}
/*#allmap{width:100%;height:100%;}*/
.tangram-suggestion-main {
	z-index: 1060;
}

a {
	text-decoration: none;
}

.mainDiv {
	background: url("${cxt}/static/images/gaode_map/tansfer_init.png");
	height: 588px;
	width: 1080px;
}
</style>
<link href="${cxt}/static/plugins/Hui-iconfont/1.0.8/iconfont.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${cxt}/static/js/jquery-1.8.2.min.js"></script>
<link href="${ctx}/static/css/bootstrap.min.css?v=3.3.6"
	rel="stylesheet">
<script type="text/javascript" src="${cxt}/static/js/timeout.js"></script>
   <link rel="stylesheet" href="https://a.amap.com/jsapi_demos/static/demo-center/css/demo-center.css" />
   <script type="text/javascript" src="https://webapi.amap.com/maps?v=1.4.15&key=c2a5aea091553a08c76a1499c30ee9d1&plugin=AMap.Autocomplete"></script>
<!-- <script type="text/javascript" src="https://cache.amap.com/lbs/static/addToolbar.js"></script> -->
<script type="text/javascript" src="https://cache.amap.com/lbs/static/addToolbar.js"></script>
</head>
<body>
	<div style="height: 654px; width: 1080px;">
		<img src="${cxt}/static/images/test/top.png"
			style="height: 66px; width: 1080px;">
		<div id="biaotDiv">主页>换乘信息</div>
		<a id="fanhui" href='javascript:void(0)' onclick="goback()"> </a> <a
			id="fanhuiZYM" href='javascript:void(0)' onclick="gohome()"> </a>
		<div id="allmap" class="mainDiv">
			<div id="container"></div>
			<input type="text" placeholder="请输入起始位置" value="我的位置"
				autocomplete="off" id="tipinput"
				style="margin-left: 294px; margin-top: 155px;height:35px;border-width:0px;width: 445px;background:#F7F7F7;font-size:30px; position: absolute;">

		</div>
	</div>
</body>
<script type="text/javascript">
var map = new AMap.Map("container", {
    resizeEnable: true
});
//输入提示
var auto = new AMap.Autocomplete({
    input: "tipinput"
});
</script>
</html>