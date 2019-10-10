<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="initial-scale=1.0, user-scalable=no, width=device-width">
<title>地点关键字 + 公交路线规划</title>
<link href="${cxt}/static/plugins/Hui-iconfont/1.0.8/iconfont.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript" src="${cxt}/static/js/timeout.js"></script>
<script type="text/javascript"
	src="${cxt}/static/js/jquery-1.8.2.min.js"></script>
<link href="${ctx}/static/css/bootstrap.min.css?v=3.3.6"
	rel="stylesheet">

<style>
html, body, #container {
	width: 100%;
	height: 100%;
}

#panel {
	position: fixed;
	background-color: white;
	max-height: 90%;
	overflow-y: auto;
	top: 10px;
	right: 10px;
	width: 280px;
}

#panel .amap-call {
	background-color: #009cf9;
	border-top-left-radius: 4px;
	border-top-right-radius: 4px;
}

#panel .amap-lib-transfer {
	border-bottom-left-radius: 4px;
	border-bottom-right-radius: 4px;
	overflow: hidden;
}
</style>
<title>位置经纬度 + 获取公交规划数据</title>
<link rel="stylesheet"
	href="https://a.amap.com/jsapi_demos/static/demo-center/css/demo-center.css" />
<script
	src="https://webapi.amap.com/maps?v=1.4.15&key=5db187a07a03113c5702d765dd789931&plugin=AMap.Transfer"></script>
<script
	src="https://a.amap.com/jsapi_demos/static/demo-center/js/demoutils.js"></script>
</head>
<body>
	<div id="container"></div>
	<div id="panel"></div>
	<script type="text/javascript">
		var map = new AMap.Map("container", {
			center : [ 116.397428, 39.90923 ],
			zoom : 14
		});

		var transferOption = {
			map : map,
			nightflag : true, // 是否计算夜班车
			city : '桐乡市',
			panel : 'panel',
			outlineColor : '#ffeeee',
			autoFitView : true,
			policy : AMap.TransferPolicy.LEAST_TIME
		// 其它policy取值请参照 https://lbs.amap.com/api/javascript-api/reference/route-search#m_TransferPolicy
		}

		var transfer = new AMap.Transfer(transferOption)

		//根据起、终点坐标查询公交换乘路线
		transfer.search([ {
			keyword : '桐乡中医院',
			city : '桐乡市'
		},
		//第一个元素city缺省时取transOptions的city属性
		{
			keyword : '桐乡市政府',
			city : '桐乡市'
		}
		//第二个元素city缺省时取transOptions的cityd属性
		],
				function(status, result) {
					// result即是对应的公交路线数据信息，相关数据结构文档请参考  https://lbs.amap.com/api/javascript-api/reference/route-search#m_TransferResult
					if (status === 'complete') {
						log.success('公交路线数据查询成功')
					} else {
						log.error('公交路线数据查询失败' + result)
					}
				});
	</script>
</body>
</html>