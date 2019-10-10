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
</style>
<link href="${cxt}/static/plugins/Hui-iconfont/1.0.8/iconfont.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${cxt}/static/js/jquery-1.8.2.min.js"></script>
<link href="${ctx}/static/css/bootstrap.min.css?v=3.3.6"
	rel="stylesheet">
<script type="text/javascript" src="${cxt}/static/js/timeout.js"></script>
<link rel="stylesheet"
	href="https://a.amap.com/jsapi_demos/static/demo-center/css/demo-center.css" />
<script
	src="https://a.amap.com/jsapi_demos/static/demo-center/js/demoutils.js"></script>
<script type="text/javascript"
	src="https://webapi.amap.com/maps?v=1.4.15&key=c2a5aea091553a08c76a1499c30ee9d1&plugin=AMap.Transfer"></script>
<script type="text/javascript"
	src="https://cache.amap.com/lbs/static/addToolbar.js"></script>
</head>
<body>
	<div style="height: 654px; width: 1080px;">
		<img src="${cxt}/static/images/test/top.png"
			style="height: 66px; width: 1080px;">
		<div id="biaotDiv">主页>换乘信息</div>
		<a id="fanhui" href='javascript:void(0)' onclick="goback()"> </a> <a
			id="fanhuiZYM" href='javascript:void(0)' onclick="gohome()"> </a>
		<div
			style="position: absolute; z-index: 12; width: 35%; height: 1px; margin: 10px 0px;">
			<div id='searchName' style='text-align: left;'>
				<input type="text" id='start' placeholder="请输入或选择起点信息"
					style='width: 70%; height: 40px; margin: 0px 0px 0px 20px;'>
				<br /> <input type="text" id='end' placeholder="请输入或选择止点信息"
					style='width: 70%; height: 40px; margin: 5px 0px 0px 20px;'>
				<button onclick='goSearch()'
					style='width: 80px; height: 43px; background-color: #0071BD'>
					<i class='Hui-iconfont' style='color: #FFFFFF; font-size: 15px'>&#xe665;</i>
				</button>
				<div id="searchResultPanel"
					style="z-index: 12; border: 1px solid #C0C0C0; width: 150px; display: none;"></div>
			</div>
		</div>
		<div
			style="position: absolute; z-index: 1; width: 24.4%; margin: 98px 0px 0px 20px;">
			<a href='javascript:void(0)' id="xiansA" style="display: none;"><div
					style='background-color: #FFFFFF; text-align: center; height: 30px;'>
					<img style="height: 20px; margin-top: 4px"
						src="${cxt}/static/mapImage/xiala.png">
				</div></a> <a href='javascript:void(0)' style="display: none;" id="yingcA"><div
					style='background-color: #FFFFFF; text-align: center; height: 30px;'>
					<img style="height: 20px; margin-top: 4px"
						src="${cxt}/static/mapImage/shangla.png">
				</div></a>
			<ul class="nav nav-tabs" id="chuxul" role="tablist"
				style="display: none; margin-top: 2px">
				<li role="presentation" id="gongjli" class="active"
					style="width: 33.3%; text-align: center; background-color: #F5F5F5;"><a
					href='javascript:void(0)' onclick="goSearch(1)"
					aria-controls="home" role="tab" data-toggle="tab">公交</a></li>
				<li role="presentation" id="buxli"
					style="width: 33.3%; text-align: center; background-color: #F5F5F5;"><a
					href='javascript:void(0)' onclick="goSearch(2)"
					aria-controls="profile" role="tab" data-toggle="tab">步行</a></li>
				<li role="presentation" id="jiacli"
					style="width: 33.3%; text-align: center; background-color: #F5F5F5;"><a
					href='javascript:void(0)' onclick="goSearch(3)"
					aria-controls="profile" role="tab" data-toggle="tab">驾车</a></li>
			</ul>
			<div id="r-result"
				style='background-color: #FFFFFF; max-height: 400px; display: none; overflow: auto;'></div>
		</div>
		<div id="allmap" style="height: 588px; width: 1080px;"></div>
		<div id="testdiv" title="定位当前位置">
			<a href='javascript:void(0)' onclick="dingw2('2');"><img
				src="${cxt}/static/mapImage/dingw.png" id="dingw"></a>
		</div>
		<div id="shuaxdiv" title="地图重置">
			<a href='javascript:void(0)' onclick="dingw2('1');"><img
				src="${cxt}/static/mapImage/shuax.png" id="shuax"></a>
		</div>
	</div>
</body>
<script type="text/javascript">
    var map = new AMap.Map("allmap", {
        resizeEnable: true,
        center: [116.397428, 39.90923],
        zoom: 13 
    });
    var transOptions = {
        map: map,
        city: '北京市',
        panel: 'panel',                           
        policy: AMap.TransferPolicy.LEAST_TIME //乘车策略
    };
    //构造公交换乘类
    var transfer = new AMap.Transfer(transOptions);
    //根据起、终点名称查询公交换乘路线
    transfer.search([
        {keyword: '地震局',city:'北京'},
        //第一个元素city缺省时取transOptions的city属性
        {keyword: '望京西园4区',city:'北京'}
        //第二个元素city缺省时取transOptions的cityd属性
    ], function(status, result) {
        // result即是对应的公交路线数据信息，相关数据结构文档请参考  https://lbs.amap.com/api/javascript-api/reference/route-search#m_TransferResult
        if (status === 'complete') {
            log.success('绘制公交路线完成')
        } else {
            log.error('公交路线数据查询失败' + result)
        }
    });
</script>
</html>