/**
 * 线路导入-线路管理js
 */
Vue.prototype.$http = axios;
var vm = new Vue({
	el:'#dpLTE',
	data: {
		type: 0
	},
	methods : {
		getFile(event) {
            this.file = event.target.files[0];
        },
        /**
		 * 判断文件类型是否合法
		 * 
		 * @param fileType
		 * @param type
		 * @returns {boolean}
		 */  
        is_inType:function(fileType,type) {  
            var len = fileType.length;  
            while (len--){  
                if(fileType[len]==type){  
                    return true;  
                }  
            }  
            return false;  
        },  
        resetFile:function () {  
            $('#file').val('');  
        }, 
		acceptClick: function() {
			if (!$('#form').Validform()) {
		        return false;
		    }
			if(this.type=='0'){
				dialogConfirm($.i18n.prop('addSure'), function() {
					dialogLoading(true);
					commit();
				});
			}else{
				commit();
			}
		}
	}
})
function commit(){
	var fileTypeStr = 'xls xlsx';// 允许的文件类型 首次判断 file的type
    var fileType = ['xls','xlsx'];// 允许的文件类型 二次判断 name的后缀
    var file=vm.file;
    if(file!=null){
    	var fileName = file.name;  
        var theFileTypeStr = file.type;  
        var theFileType = theFileTypeStr.split("\/")[1];// 所选文件类型
        if(!vm.is_inType(fileType,fileName.split('.')[1])){  
                /* alert("请确保文件类型为 jpg jpeg png的其中一种!"); */ 
                dialogMsg($.i18n.prop('ms'),"warn");
                $('#file').val('');
                return false;
        }	
    }
    var form = document.getElementById('form1');  
    let formData = new FormData(form);
    formData.append('file', vm.file);
    formData.append('type',vm.type);
    let config = {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        }
        vm.$http.post(basePath+'/sys/note/import.html?_' + $.now(), formData, config).then(function (res) {
            console.log(res)    
            console.log(res.data);
        	if (res.status == 200) {
        		if(res.data.success){
        			dialogMsg($.i18n.prop('Si'));
        			$.currentIframe().vm.load();
        			//导入成功后关闭导入页面
        			dialogClose();
        		}else if(res.data.error){
        			dialogMsg($.i18n.prop('SiOne'))
    				$.currentIframe().vm.load();	
        		}
            }else{
            	dialogMsg(res.msg)
            }
        });         
}

