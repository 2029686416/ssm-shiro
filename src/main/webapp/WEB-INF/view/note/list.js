/**
 * 短信管理js
 */

$(function() {
	initialPage();
	getGrid();
});

function initialPage() {
	$(window).resize(function() {
		$('#dataGrid').bootstrapTable('resetView', {
			height : $(window).height() - 54
		});
	});
}

function getGrid() {
	$('#dataGrid').bootstrapTableEx({
		url : basePath+'/sys/note/list.html?_' + $.now(),
		queryParams : function(params) {
			params.noteName = vm.keyword;
			return params;
		}		,
		columns : [ {
			checkbox : true
		}, {
			field : "noteId",
			title : $.i18n.prop('noteId'),
			visible : false,
			width : "50px"
		}, {
			field : "noteCountry",
			title : $.i18n.prop('noteCountry'),
			width : "200px",
			sortable : true,
			sortName : "noteCountry"
		}, {
			field : "noteCode",
			title : $.i18n.prop('noteCode'),
			width : "200px",
			sortable : true,
			sortName : "noteCode"
		}]
	})
}

var vm = new Vue({
	el : '#dpLTE',
	data : {
		keyword : null
	},
	methods : {
		load : function() {
			$('#dataGrid').bootstrapTable('refresh');
		},
		add:function() {
			dialogOpen({
				title : $.i18n.prop('addNote'),
				url : basePath+'/shsms_areacode/add.html?_' + $.now(),
				width : '600px',
				height : '300px',
				scroll : true,
				yes : function(iframeId) {
					top.frames[iframeId].vm.acceptClick();
				},
			});
		},
	
		edit : function() {
			var ck = $('#dataGrid').bootstrapTable('getSelections');
			if (checkedRow(ck)) {
				dialogOpen({
					title : $.i18n.prop('editNote'),
					url : basePath+'/shsms_areacode/edit.html?_' + $.now(),
					width :  '600px',
					height : '300px',
					success : function(iframeId) {
						top.frames[iframeId].vm.stations.noteId= ck[0].noteId;
						top.frames[iframeId].vm.setForm();
					},
					yes : function(iframeId) {
						top.frames[iframeId].vm.acceptClick();
					}
				});
			}
		},
		remove : function() {
			var ck = $('#dataGrid').bootstrapTable('getSelections'), ids = [];
			if (checkedArray(ck)) {
				$.each(ck, function(idx, item) {
					ids[idx] = item.noteId;
				});
				$.RemoveForm({
					url : basePath+'/sys/note/remove.html?_' + $.now(),
					param : ids,
					success : function(data) {
						vm.load();
					}
				});
			}
		},	
		importExcel : function() {
			dialogOpen({
				title : $.i18n.prop('importNote'),
				url : basePath+'/shsms_areacode/openImportExcel.html?_' + $.now(),
				width : '300px',
				height : '200px',
				scroll : true,
				yes : function(iframeId) {
					top.frames[iframeId].vm.acceptClick();
				},
			});
		}
	}
})