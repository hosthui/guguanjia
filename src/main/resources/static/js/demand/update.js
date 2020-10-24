let vm=new Vue({
    el:".main-content",
    data:{
        demand:{}
    },
    methods:{
        demandUp:function () {
            axios({
                url:"manager/demand/update",
                method:"post",
                data: this.demand
            }).then(response=>{
                let index=parent.layer.getFrameIndex(window.name)
                parent.layer.msg("更新成功")
                parent.layer.close(index)
            }).catch(error=>{
                layer.msg(error.message);
            })
        }
    },
    created:function () {
        this.demand=parent.layer.obj
    }
})