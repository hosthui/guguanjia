let vm=new Vue({
    el:".main-content",
    data:{
        pageInfo:{}
    },
    methods:{
        selectAll:function (pageNum=1,pageSize=3) {
            axios({
                url:"manager/demand/selectAll",
                method:"get",
                params:{pageNum,pageSize}
            }).then(response=>{
                this.pageInfo=response.data
            }).catch(error=>{

            })
        },
        toUpdate:function (data) {
            layer.obj=data
            layer.open({
                type:2,
                title:false,
                shadeClose: true,
                shade: 0.1,
                area: ['80%', '80%'],
                content: 'manager/demand/toupdate',
                end:()=>{
                    this.selectAll()
                }
            })
        },
        toDetails:function (data) {
            layer.obj=data
            layer.open({
                type:2,
                title:false,
                shadeClose: true,
                shade: 0.1,
                area: ['80%', '80%'],
                content: 'manager/demand/todetails',
                end:()=>{
                }
            })
        }
    },
    created:function () {
        this.selectAll()
    }
})