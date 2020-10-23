let app=new Vue({
    el:".main-content",
    data:{
        app:{}
    },
    methods:{
        toUpdate:function () {
            axios({
                url:"manager/app/update",
                method:"put",
                data:this.app,
            }).then(response=>{
                let frameIndex = parent.layer.getFrameIndex(window.name);
                parent.layer.msg("更新成功");
                parent.layer.close(frameIndex)
            }).catch(err=>{
                layer.msg(err.message)
                })
        }

    },
    created:function () {
        this.app=parent.layer.obj;
    }
})