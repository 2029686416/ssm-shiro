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
<style type="text/css">
html, body, #container {
	width: 100%;
	height: 100%;
}
/*---滚动条默认显示样式--*/
 
::-webkit-scrollbar-thumb {     background-color:#018EE8;     height:50px; 
	   outline-offset:-2px;     outline:2pxsolid#fff;   
	 -webkit-border-radius:4px;     border:2pxsolid#fff; 
	
}

  /*---鼠标点击滚动条显示样式--*/  
::-webkit-scrollbar-thumb:hover {     background-color:#FB4446;   
	 height:50px;     -webkit-border-radius:4px; 
	
}

  /*---滚动条大小--*/  
::-webkit-scrollbar {     width:8px;     height:8px; 
	
}

  /*---滚动框背景样式--*/  
::-webkit-scrollbar-track-piece {     background-color:#fff;   
	 -webkit-border-radius:0; 
	
}
 
</style>
<style type="text/css">
#panel {
	position: absolute;
	background-color: white;
	max-height: 50%;
	overflow-y: auto;
	top: 279px;
	left: 0px;
	width: 280px;
}

#panel .amap-call {
	display: none;
	background-color: #009cf9;
	border-top-left-radius: 4px;
	border-top-right-radius: 4px;
}

#panel .amap-lib-transfer {
	border-bottom-left-radius: 4px;
	border-bottom-right-radius: 4px;
	overflow: hidden;
}

.amap-lib-transfer .planTitle ul {
	display: none;
	margin-top: 7px;
}

.hideGaodeUrl {
	display: none;
}

.amap-lib-transfer .planTitle ul li a {
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

#planForm {
	position: absolute;
	padding: 14px;
	padding-bottom: 68px;
	background: #3d93fd;
	border-top-left-radius: 3px;
	border-top-right-radius: 3px;
}

#indexbox {
	position: relative;
	box-sizing: border-box;
}

.amap-lib-infowindow-title a {
	line-height: 22px;
	font-size: 14px;
	border-bottom: 1px #99adce solid;
	padding-right: 15px;
	display: none;
}

.tabs {
	position: relative;
	height: 28px;
	border: 1px solid #e0e0e0;
	border-radius: 2px;
	margin: 0 auto;
	width: 100%;
	white-space: nowrap;
	background-color: #fff;
}

ol, ul {
	list-style: none;
}

ul.tabs.dir_tab li a.icon-car {
	background: url(${ctx}/static/images/gaode_map/icon_car.png) no-repeat
		50%;
	background-size: 19px 19px;
}

ul.tabs.dir_tab li a {
	display: inline-block;
	color: #fff;
	height: 30px;
	width: 30px;
	cursor: pointer;
	opacity: .5;
}

ul.tabs.dir_tab li a.current {
	opacity: 1;
}

ul.tabs.dir_tab {
	width: 260px;
	border: 0;
	border-radius: 0;
	background: none;
	height: 36px;
	margin: 0 auto;
	white-space: nowrap;
}

ul.tabs.dir_tab li {
	height: 30px;
	width: 33.3333%;
	line-height: 30px;
	position: relative;
	display: block;
	float: left;
	text-align: center;
	margin: 0;
	text-transform: uppercase;
	letter-spacing: .8px;
}

ul.tabs.dir_tab li a.icon-walk {
	background: url(${ctx}/static/images/gaode_map/icon_walk.png) no-repeat
		50%;
	background-size: 12px 19px;
}

ul.tabs.dir_tab li a.icon-bus {
	background: url(${ctx}/static/images/gaode_map/icon_bus.png) no-repeat
		50%;
	background-size: 20px 19px;
}

ul.tabs.dir_tab li a {
	display: inline-block;
	color: #fff;
	height: 30px;
	width: 30px;
	cursor: pointer;
	opacity: .5;
}

#platform {
	background: #3d93fd;
	position: relative;
	padding: 14px;
	padding-bottom: 68px;
	background: #3d93fd;
	border-top-left-radius: 3px;
	border-top-right-radius: 3px;
}

.line-search-submit {
	position: absolute;
	margin-top: 17px;
	border-bottom: none;
	width: 230px;
}

.line-search-submit .line-search-clear {
	color: #fff;
}

.line-search-submit .dir_submit {
	display: inline-block;
	float: right;
	padding: 0 25px;
	height: 32px;
	line-height: 32px;
	background-color: #559ffb;
	border: none;
	border-radius: 2px;
	color: #fff;
	font-size: 14px;
	text-decoration: none;
	box-shadow: 0 1px 1px 0 rgba(0, 0, 0, .21);
	-webkit-box-shadow: 0 1px 1px 0 rgba(0, 0, 0, .21);
	-moz-box-shadow: 0 1px 1px 0 rgba(0, 0, 0, .21);
}

#trafficTab {
	left: -20px;
	cursor: pointer;
}
</style>
<link rel="stylesheet"
	href="https://a.amap.com/jsapi_demos/static/demo-center/css/demo-center.css" />
<link rel="stylesheet" href="${ctx}/static/js/plugins/just-tip.css">
<script
	src="https://a.amap.com/jsapi_demos/static/demo-center/js/demoutils.js"></script>
<script type="text/javascript"
	src="https://webapi.amap.com/maps?v=1.4.15&key=5db187a07a03113c5702d765dd789931&plugin=AMap.Transfer"></script>
<!-- <script type="text/javascript" src="https://cache.amap.com/lbs/static/addToolbar.js"></script> -->

</head>
<body>
	<div style="height: 654px; width: 1080px;">
		<img src="${cxt}/static/images/test/top.png"
			style="height: 66px; width: 1080px;">
		<div id="biaotDiv">主页>换乘信息</div>
		<a id="fanhui" href='javascript:void(0)' onclick="goback()"> </a> <a
			id="fanhuiZYM" href='javascript:void(0)' onclick="gohome()"> </a>
		<div
			style="position: absolute; z-index: 12; width: 280px; margin: 10px 0px;"
			id="platform">
			<ul class="tabs dir_tab col3 z-depth-1" id="trafficTab">
				<li><a id="carTab" class="palntype_tab icon-car icondirtip"
					href="javascript:void(0)" data-type="car"></a></li>
				<li><a id="busTab"
					class="palntype_tab icon-bus icondirtip current"
					href="javascript:void(0)" data-type="bus"></a></li>
				<li><a id="walkTab" class="palntype_tab icon-walk icondirtip"
					href="javascript:void(0)" data-type="walk"></a></li>
			</ul>
			<div id='searchName' style='text-align: left;'>
				<input type="text" id='start' placeholder="请输入或选择起点信息"
					style='width: 222px; height: 40px; margin: 0px 0px 0px 10px;'>
				<br /> <input type="text" id='end' placeholder="请输入或选择止点信息"
					style='width: 222px; height: 40px; margin: 5px 0px 0px 10px;'>
				<!-- <button onclick='goSearch()'
					style='width: 44px; height: 43px; top: 20px; position: absolute; background-color: #0071BD'>
					<i class='Hui-iconfont' style='color: #FFFFFF; font-size: 15px'>&#xe665;</i>
				</button> -->
				<p class="line-search-submit"
					data-spm-anchor-id="0.0.0.i0.6d041c4erM9VvB">
					<!-- <a href="javascript:void(0)" class="line-search-clear">清除路线</a>  --><a
						href="javascript:void(0)" onclick="toSearch();" class="dir_submit">坐公交</a>
					<span class="fa-spin"></span>
				</p>
				<div id="searchResultPanel"
					style="z-index: 12; border: 1px solid #C0C0C0; width: 150px; display: none;"></div>
			</div>
		</div>
		<div id="container" style="height: 588px; width: 1080px;"></div>
		<div id="panel"></div>
	</div>
	<script type="text/javascript"
		src="${ctx}/static/js/plugins/justTools.js"></script>
	<script type="text/javascript">
		var infoWindow;
		var POINT_CODE = '${pointGps.POINT_CODE}';
		var POINT_NAME = '${pointGps.POINT_NAME}';
		var STATION_LON = '${pointGps.POINT_LON}';
		var STATION_LAT = '${pointGps.POINT_LAT}';
		var pointName;
		var dangqwz = POINT_NAME;
		var dingwIndex = 0;
		//map类    map类方法参考文档：https://lbs.amap.com/api/javascript-api/reference/map
		var map = new AMap.Map("container", {//参考文档的MapOptions（map中选项）map.get...是方法
			resizeEnable : true,//是否监控地图容器尺寸变化，默认值为false
			center : [ 120.5649500000, 30.6302500000 ],//地图中心点坐标值
			zoom : 13//缩放级别
		});
		if (STATION_LON && STATION_LAT) {
			map.setZoomAndCenter(13, [ STATION_LON, STATION_LAT ]);//地图缩放至指定级别并以指定点为地图显示中心点
		} else {
			map.setZoomAndCenter(13, [ 120.5649500000, 30.6302500000 ]);
		}
		var center = map.getCenter();//   获取地图中心点经纬度坐标值
		console.log(center.lng)//获取经纬度坐标值
		console.log(center.lat)
		dingw2(1);
		var transOptions = {
			map : map,
			city : '桐乡市',
			panel : 'panel',
			//extensions : 'all',
			nightflag : true,
			autoFitView : true,
			policy : AMap.TransferPolicy.LEAST_TIME
		//乘车策略
		};
		//异步同时加载多个插件     文档： https://lbs.amap.com/api/javascript-api/guide/abc/plugins/
		//Autocomplete这个插件的方法：https://lbs.amap.com/api/javascript-api/reference/search#m_AMap.Autocomplete
		AMap.plugin([ 'AMap.Autocomplete' ], function() {
			var autoOptions = {
			          city: '桐乡市'
			}
			//输入提示
			var autostart = new AMap.Autocomplete({
				input : "start"
			},autoOptions);
			var autoend = new AMap.Autocomplete({
				input : "end"
			},autoOptions);
		});

		function dingw2(str) {
			if (str == 1) {
				map.clearMap();
				$("#start").val('');
				$("#start").attr("placeholder", "当前位置");
				tabFlag = 0;
				if (dingwIndex == 0) {
					var marker = new AMap.Marker(
							{
								icon : "https://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
								position : center
							});
					map.add(marker);//添加覆盖物/图层
					//鼠标点击marker弹出自定义的信息窗体
					AMap.event.addListener(marker, 'click', function() {
						//infoWindow.open(map, marker.getPosition());
						var infoWindow = new AMap.InfoWindow({
							anchor : 'top-left',
							content : dangqwz,
						});
						infoWindow.open(map, [ center.lng, center.lat ])
					});
				}
			}
		}
		function goback() {
			//document.getElementById("form").action = "/LCDWeb/queryRoute.do?page=init";
			//document.getElementById("form").target = "_self";
			//document.getElementById("form").submit();
			//window.KeyEvent.return();
			window.KeyEvent.returnup();
		}
		function gohome() {
			//window.KeyEvent.exit();
			window.KeyEvent.returnup();
		}
		//出行
		function goSearch(str) {
			var p1 = $("#start").val();
			var p2 = $("#end").val();
			if (!p1 || !p2) {
				log.error('请输入起始点和终止点')
				return;
			}
			$("#panel").empty();
			map.clearMap();
			//var way = $("#wayselect").val();
			if (str == 2) {//步行
				walkingSearch(p1, p2);
			} else if (str == 3) {//驾车
				drivingSearch(p1, p2);
			} else {//公交
				transitSearch(p1, p2);
			}
		}
		function transitSearch(start, end) {
			//API:https://lbs.amap.com/api/javascript-api/reference/route-search#m_AMap.Transfer
			//构造公交换乘类
			var transfer = new AMap.Transfer(transOptions);
			//根据起、终点名称查询公交换乘路线
			
			//使用这些keyword这些关键字，文档没有描绘很清楚，可以查看案例      文档中都会有"相关示例"链接
			//https://lbs.amap.com/api/javascript-api/example/bus-search/plan-route-according-to-name
			transfer.search([ {
				keyword : start,
				city : '桐乡市'
			},
			//第一个元素city缺省时取transOptions的city属性
			{
				keyword : end,
				city : '桐乡市'
			}
			//第二个元素city缺省时取transOptions的cityd属性
			], function(status, result) {
				// result即是对应的公交路线数据信息，相关数据结构文档请参考   https://lbs.amap.com/api/javascript-api/reference/route-search#m_TransferResult

				if (status === 'complete') {
					log.success('绘制公交路线完成')
					//$(".clearfix").addClass("hideGaodeUrl")
				} else {
					log.error('公交路线数据查询失败:' + result.info)
				}
			});
		}
		//都与公交路线相似    自行查询API
		function walkingSearch(start, end) {
			AMap.plugin([ 'AMap.Walking' ], function() {//异步同时加载多个插件
				//步行导航
				var walking = new AMap.Walking({
					map : map,
					panel : "panel"    //此处设置的是id=panel的
				});
				walking.search([ {
					keyword : start,
					city : '桐乡市'
				}, {
					keyword : end,
					city : '桐乡市'
				} ], function(status, result) {
					// result即是对应的步行路线数据信息，相关数据结构文档请参考  https://lbs.amap.com/api/javascript-api/reference/route-search#m_WalkingResult
					if (status === 'complete') {
						log.success('绘制步行路线完成')
					} else {
						log.error('步行路线数据查询失败:' +result.info)
					}
				});
			});
		}
		function drivingSearch(p1, p2) {
			$("#panel").empty();
			AMap.plugin([ 'AMap.Driving' ], function() {//异步同时加载多个插件
				//构造路线导航类
			    var driving = new AMap.Driving({
			        map: map,
			        panel: "panel"
			    }); 
			    // 根据起终点名称规划驾车导航路线
			    driving.search([
			        {keyword: p1,city:'桐乡市'},
			        {keyword: p2,city:'桐乡市'}
			    ], function(status, result) {
			        // result 即是对应的驾车导航信息，相关数据结构文档请参考  https://lbs.amap.com/api/javascript-api/reference/route-search#m_DrivingResult
			        if (status === 'complete') {
			            log.success('绘制驾车路线完成')
			        } else {
			            log.error('获取驾车数据失败:' + result.info)
			        }
			    });
			});
		}

		$("#carTab").click(function() {
			$("#carTab").addClass("current");
			$("#busTab").removeClass("current");
			$("#walkTab").removeClass("current");
			$(".dir_submit").html("开车去");
			dingwIndex=3;
			toSearch();
		});
		$("#busTab").click(function() {
			$("#busTab").addClass("current");
			$("#carTab").removeClass("current");
			$("#walkTab").removeClass("current");
			$(".dir_submit").html("坐公交");
			dingwIndex=0;
			toSearch();
		});
		$("#walkTab").click(function() {
			$("#walkTab").addClass("current");
			$("#carTab").removeClass("current");
			$("#busTab").removeClass("current");
			$(".dir_submit").html("走路去");
			dingwIndex=2;
			toSearch();
		});
		$("#busTab").mouseover(function(event) {
			var _this = $(this);
			_this.justToolsTip({
				events : event,
				animation : "moveInBottom",
				//width:"300px",
				contents : '公交',
				gravity : 'bottom'
			});
		})
		$("#carTab").mouseover(function(event) {
			var _this = $(this);
			_this.justToolsTip({
				events : event,
				animation : "moveInBottom",
				//width:"300px",
				contents : '驾车',
				gravity : 'bottom'
			});
		})
		$("#walkTab").mouseover(function(event) {
			var _this = $(this);
			_this.justToolsTip({
				events : event,
				animation : "moveInBottom",
				//width:"300px",
				contents : '走路',
				gravity : 'bottom'
			});
		})
		
		function toSearch(){
			goSearch(dingwIndex);
		}
	</script>

</body>
</html>