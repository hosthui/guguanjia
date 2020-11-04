let vm = new Vue({
    el: '.main-content',  //选中整个main
    data: {
        pageInfo: {},       //初始化对象     vue建议声明对象同时进行初始化，避免undefinded
        sysRoleCondition: {
            dataScope:"",
            officeId:"",
            name:""
        },
        actives: false
    },
    methods: {
        selectAll: function (pageNum = 1, pageSize = 3) {
            axios({
                url: `manager/role/selectAll/${pageNum}/${pageSize}`,   //相对路径同样受到base标签影响
                method:"get",
                params:this.sysRoleCondition
            }).then(
                response => {  //省略function关键字  自动传递上下文中的this
                    this.pageInfo = response.data.obj;
                }
            ).catch(error => {
                // console.log(error.message);
            })
        },
        clearCondition:function(){
           this.sysRoleCondition= {
                dataScope:"",
                    officeId:"",
                    name:""
            }
            this.selectAll()
        },
        addApp: function () {
            this.actives = !this.actives
            axios({
                url: "manager/app/insert",
                method: "post",
                data: this.app
            }).then(response => {
                this.actives = !this.actives
                layer.msg("添加成功")
                this.selectAll()
                this.app = {
                    platform: "0",
                    forceUpdate: "0"
                }
            }).catch(error => {
                layer.msg(error.message)
            })
        },
        toAllocation: function (id,name) {
            layer.id = id;
            layer.name=name;
            layer.open({
                type: 2,
                title: false,
                shadeClose: true,
                shade: 0.1,
                area: ['80%', '80%'],
                content: 'manager/role/toallocation',
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
        this.selectAll();
    }
});