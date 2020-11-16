let vm = new Vue({
    el: '.main-content',  //选中整个main
    data: {
        //初始化对象     vue建议声明对象同时进行初始化，避免undefinded
        user: {},
    },
    methods: {
        toSelect:function (aid) {
            layer.id=aid;
            layer.open({
                type: 2,
                title:false,
                shadeClose: true,
                shade: 0.1,
                area: ['100%', '100%'],
                content: 'manager/area/toselect',
                end: ()=> {
                    this.area.parentName=layer.parentName
                    this.area.parentId=layer.id
                    this.area.parentIds=layer.parentIds
                }
            })
        },
        doUpdate:function () {
            axios({
                url:"manager/sysuser/doupdate",
                method:"put",
                data:this.user
            }).then(response=>{
                parent.layer.success=response.data.success
                let index=parent.layer.getFrameIndex(window.name)
                parent.layer.msg("更新成功")
                parent.layer.close(index)
            })

        }

    },
    created: function () {
        this.user=parent.layer.obj
    }
});