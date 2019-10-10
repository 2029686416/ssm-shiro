<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="${ctx}/static/js/vue.js"></script>
    <link rel="stylesheet" href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.css">
    <title>父子组件通信版todolist</title>
</head>
 
<body>
    <div style="width: 500px;margin:0 auto;" id="app">
       <!--  <h1>待办事项</h1> -->
        <myh1></myh1>
        <!-- 绑定的list为mytable的list提供 -->
        <mytable :list="list" @delitem="delitem"></mytable>
        <myadd :length="this.list.length" @additem="additem"></myadd>
        
    </div>
    <!-- 每一个组件必须有一个根元素，要不然会报错 -->
    <template id="myadd">
        <div>
            <div class="form-group">
                <label for="exampleInputEmail1">添加待办事项</label>
                <input v-model="title" type="text" class="form-control" id="exampleInputEmail1" placeholder="请输入待办事项">
            </div>
            <button @click="additem()" type="submit" class="btn btn-default">添加</button>
        </div>
    </template>
    <template id="mytable">
        <table class="table table-hover">
            <tr>
                <th>id</th>
                <th>标题</th>
                <th>操作</th>
            </tr>
            <tr :data-id="item.id" v-for="(item,index) in list">
                <td>{{item.id}}</td>
                <td>{{item.title}}</td>
                <td>
                    <button @click="delitem(item.id)" class="btn btn-info">删除</button>
                </td>
            </tr>
        </table>
    </template>
    
</body>
 
<script>
    //vm (ViewModel 的缩写) 
    //创建组件，每一个组件必须有一个根元素
    var myh1 = Vue.extend({
        //组件的模板
        template: "<h1>我是h1</h1>",
        data: function () {
            return {
 
            }
        }
    })
    var mytable = Vue.extend({
        props: ['list'],//此处用的list是#mytable这个template中对应的list,获取到分组件的list
        template: "#mytable",
        data: function () {
            return {
 
            }
        },
        methods: {
            delitem: function (id) {
                this.$emit("delitem", id);
            }
        }
    })
    var myadd = Vue.extend({
        props:['length'],
        template: "#myadd",
        data: function () {
            return {
                title: "默认值"
            }
        },
        methods:{
            additem:function(){
            	console.log("1->>>>>"+this.title);
                this.$emit("additem",{id:this.length,title:this.title});
            }
        }
    })
    var vm = new Vue({
        el: "#app",
        data: function () {
            return {
                list: [
                    { id: 0, title: "吃饭" },
                    { id: 1, title: "睡觉" },
                    { id: 2, title: "写代码" },
                    { id: 3, title: "打游戏" },
                ]
            }
        },
        methods: {
            delitem: function (id) {
                for (var key in this.list) {
                    if (this.list[key].id == id) {
                        this.list.splice(key, 1);
                    }
                }
            },
            additem:function(obj){
            	//先进入子组件的additem方法，在到这父组件中
                console.log("值："+obj);
                this.list.push(obj);
            }
        },
        //注册组件
        components: {
            myh1,
            mytable,//当注册了组件之后，
            myadd
        }
    })
 
</script>
 
</html>