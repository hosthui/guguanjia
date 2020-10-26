let vm = new Vue({
    el: '#main-container',
    data: {
        pageInfo: {},
        condition:{
            type:"",
            check:""
        }
    },
    methods: {
        selectAll: function (pageNum=1, pageSize=3) {
            axios({
                method:'get',//默认get请求
                url: `manager/qualification/selectAll/${pageNum}/${pageSize}`,
                params:this.condition
            }).then(response => {
                this.pageInfo = response.data.obj;
            }).catch(error => {
                layer.msg(error.message);
            });
        },
        clearcon:function(){
            this.condition={
                type:"",
                check:""
            }
            this.selectAll()
        },
        toUpdate: function (qu) {
            layer.obj = qu;//将appVersion传递到layer对象的obj属性中,obj自动有getter/setter方法(此处就是调用了get方法获取user并set数据)
            layer.success=false
            layer.open({
                type: 2,//样式类型
                title: false,//标题
                shadeClose: true,//是否显示关闭按钮
                shade: 0.1,//设置窗口影影
                area: ["80%", "80%"],//宽高占比(相对于父窗口的比例)
                content: "manager/qualification/toUpdate",//弹出层展示toUpdate视图
                end:  ()=> {
                    if (layer.success){
                        this.selectAll(this.pageInfo.pageNum,this.pageInfo.pageSize)
                    }

                }//关闭弹出层后调用
            })
        }
    },
    created:
        function () {
            //初始化查询显示第1页,3条数据
            this.selectAll();
        }
})