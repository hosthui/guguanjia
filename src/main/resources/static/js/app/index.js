let vm = new Vue({
    el:'.main-content',  //选中整个main
    data:{
        pageInfo:{},       //初始化对象     vue建议声明对象同时进行初始化，避免undefinded
        app:{}
    },
    methods:{
        selectAll:function (pageNum=1,pageSize=3) {
            axios({
                url:`manager/app/selectAll/${pageNum}/${pageSize}`   //相对路径同样受到base标签影响
            }).then(
                response=>{  //省略function关键字  自动传递上下文中的this
                    this.pageInfo = response.data;
                }
            ).catch(error=>{
                // console.log(error.message);
            })
        },

        toUpdate:function (data) {
            layer.obj=data;
            layer.open({
                type: 2,
                title:false,
                shadeClose: true,
                shade: 0.1,
                area: ['80%', '80%'],
                content: 'manager/app/toupdate',
                end: ()=> {
                    this.selectAll();
                }
            })
        },
        addApp:function () {
            axios({
                url:"manager/app/insert",
                method:"post",
                data:this.app
            }).then(response=>{
                layer.msg("添加成功")

            }).catch(err=>{

            })
        }
    },
    created:function () {
        this.selectAll();
    }
});