/**
 * 编辑-站点管理js
 */
var vm = new Vue({
	el:'#dpLTE',
	data: {
			stations: {
				stationId: 0,
				stationName: null
		}
	},
	methods : {
		setForm: function() {
			$.SetForm({
				url: basePath+'/sys/note/info.html?_' + $.now(),
		    	param: vm.stations.noteId,
		    	success: function(data) {
		    		console.log(data);
		    		vm.stations = data;
		    	}
			});
		},		
		acceptClick: function() {
			if (!$('#form').Validform()) {
		        return false;
		    }
		    $.ConfirmForm({
		    	url: basePath+'/sys/note/update.html?_' + $.now(),
		    	param: vm.stations,
		    	success: function(data) {
		    		$.currentIframe().vm.load();
		    	}
		    });
		}
	}
})