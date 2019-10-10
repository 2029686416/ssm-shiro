/**
 * 新增-短信管理js
 */

var vm = new Vue({
	el:'#dpLTE',
	data: {
		stationType:0,
		stations: {
			noteId: 0,
			noteCountry: null,
			noteCode:null,
		},
	},
	methods : {
		acceptClick: function() {
			if (!$('#form').Validform()) {
		        return false;
		    }
		    $.SaveForm({
		    	url: basePath+'/sys/note/save.html?_' + $.now(),
		    	param: vm.stations,
		    	success: function(data) {
		    		$.currentIframe().vm.load();
		    	}
		    });
		}
	}
})
