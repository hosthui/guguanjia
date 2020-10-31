let vm = new Vue({
    el: '.main-content',  //选中整个main
    data: {
              //初始化对象     vue建议声明对象同时进行初始化，避免undefinded
        area: {},
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
                }
            })
        }
        /*toDel: function (app) {
            app.delFlag = 1
            layer.msg('确认删除', {
                time: 20000, //20s后自动关闭
                btn: ['确定', '取消'],
                yes: () => {
                    axios({
                        url: "manager/app/update",
                        method: "put",
                        data: app
                    }).then(response => {
                        layer.msg(response.data.message)
                        this.selectAll();
                    }).catch(error => {
                        layer.msg(error.message)
                    })

                }
            });
        }*/
    },
    created: function () {
        this.area=parent.layer.obj
    }
});