let vm = new Vue({
    el: '.main-content',  //选中整个main
    data: {
        company: {},       //初始化对象     vue建议声明对象同时进行初始化，避免undefinded
        wastetypes:{},
        wastes:{},
        comwastes:{},
        type:'0',
        waste:'0',
    },
    methods: {
        init: function () {
            axios({
                url: "manager/office/allWasteType",
            }).then(response => {
                this.wastetypes =response.data.obj
            }).catch(error => {
                layer.msg(error.message)
            })
        },
        allwaste:function(){
            this.wastes={}
            axios({
                url: "manager/office/allWaste",
                params:{'parentId':this.type.id}
            }).then(response => {
                this.wastes =response.data.obj
            }).catch(error => {
                layer.msg(error.message)
            })
        },
        addcomwastes:function(){
            this.comwastes.push({'wasteTypeCode':this.type.code,'name':this.waste.name,'code':this.waste.code,'id':this.waste.id});
        },
        removecomwastes:function(index){
            this.comwastes.splice(index,1)
        },
        companywaste:function(id){
            axios({
                url: "manager/office/companyw",
                params:{'officeId':id}
            }).then(response => {
                this.comwastes =response.data.obj
            }).catch(error => {
                layer.msg(error.message)
            })
        },

        doUpdate: function (data) {
            layer.obj = data;
            layer.open({
                type: 2,
                title: false,
                shadeClose: true,
                shade: 0.1,
                area: ['80%', '80%'],
                content: 'manager/app/toupdate',
                end: () => {
                    this.selectAll(this.pageInfo.pageNum, this.pageInfo.pageSize)
                }
            })
        },
        toDel: function (app) {
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
        }
    },
    created: function () {
        this.company=parent.layer.obj
        this.init();
        this.companywaste(this.company.id)
    }
});