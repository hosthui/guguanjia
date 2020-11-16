let vm = new Vue({
    el: '.main-content',  //选中整个main
    data:function() {
      return {
          up: false,
          pageInfo: {},       //初始化对象     vue建议声明对象同时进行初始化，避免undefinded
          param: {
              phone: "",
              name: "",
              roleId: ""
          },
          user: {},
          actives: false,
          setting:{
              data:{
                  simpleData:{
                      enable:true,
                      idKey:"id",
                      pIdKey:"parentId"
                  }
              },
              callback:{
                  onClick: this.onclick
              },
              view:{
                  fontCss:this.setfont
              }
          },
          nodes:[]
      }
    },
    methods: {
        selectAll: function (pageNum = 1, pageSize = 5) {
            axios({
                url: `manager/sysuser/selectAll/${pageNum}/${pageSize}` ,  //相对路径同样受到base标签影响
                params:this.param
            }).then(
                response => {  //省略function关键字  自动传递上下文中的this
                    this.pageInfo = response.data.obj;
                }
            ).catch(error => {
                // console.log(error.message);
            })
        },
        clearCon:function(){
            this.param={
                phone: "",
                name: "",
                roleId:""
            }
            this.selectAll();
        },
        addApp: function () {
            this.actives = !this.actives
            axios({
                url: "manager/sysuser/insert",
                method: "post",
                data: this.user
            }).then(response => {
                this.actives = !this.actives
                layer.msg("添加成功")
                this.selectAll()
                this.user = {
                }
            }).catch(error => {
                layer.msg(error.message)
            })
        },
        toUpdate: function (data) {
            layer.obj = data;
            layer.success=false;
            layer.open({
                type: 2,
                title: false,
                shadeClose: true,
                shade: 0.1,
                area: ['80%', '80%'],
                content: 'manager/sysuser/toupdate',
                end: () => {
                    if (layer.success){
                        this.selectAll(this.pageInfo.pageNum, this.pageInfo.pageSize)
                    }

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
        },
        init:function () {
            axios({
                url:"manager/examine/allOffice",
                method: 'get',
            }).then(response=>{
                this.nodes=response.data.obj
                this.nodes[this.nodes.length]={"id":0,"name":"全部"}
                $.fn.zTree.init($("#pullDownTreeone"),this.setting,this.nodes)
            }).catch(error=>{

            })
        },
        onclick:function (event,treeId,treeNode) {
            this.user.officeName=treeNode.name;
            this.user.officeId=treeNode.id;
            $(".btn-group").removeClass("open")
        },
        search:function () {
            let zTreeObj = $.fn.zTree.getZTreeObj("pullDownTreeone");
            let nodesFuzzy = zTreeObj.getNodesByParamFuzzy("name", this.param.officeName, null);
            let transformToArray = zTreeObj.transformToArray(zTreeObj.getNodes());
            for (let i = 0; i < transformToArray.length; i++) {
                transformToArray[i].isHigh=false;
                zTreeObj.updateNode(transformToArray[i])
            }
            for (let i = 0; i < nodesFuzzy.length; i++) {
                nodesFuzzy[i].isHigh=true
                zTreeObj.updateNode(nodesFuzzy[i])
            }
        },
        setfont:function (treeId,treeNode) {
            return treeNode.isHigh?{"color":"red"}:{"color":"black"}
        },
        changeUp:function () {
            this.up=!this.up
        }
    },
    created: function () {
        this.selectAll();
    },
    mounted:function () {
        this.init();
    }
});