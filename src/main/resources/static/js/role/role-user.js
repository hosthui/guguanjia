let vm = new Vue({
    el: '#main-container',
    data: function () {
        return {
            role: {},
            yxUsers:[],
            yxIds:[],
            yxBtn:false,
            dxUsers:[],
            dxIds:[],
            dxBtn:false,
            nowcompany:"",
            setting: {
                data: {
                    simpleData: {
                        enable: true,
                        idKey: "id",
                        pIdKey: "parentId"
                    }
                },
                callback: {
                    onClick: this.onclick
                },
                view: {
                    fontCss: this.setfont
                }
            },
            nodes: []
        }
    },
    methods: {
        selectYxuser: function () {
            axios({
                method: 'get',//默认get请求
                url: `manager/role/selectYxuser`,
                params:this.role
            }).then(response => {
                this.yxUsers = response.data.obj;
                for (let i in this.yxUsers) {
                    this.yxUsers[i].checked=false
                }
            }).catch(error => {
            });
        },
        checkYxUsers: function (id) {
            for (let i in this.yxUsers) {
                if (id==this.yxUsers[i].id){
                    this.yxUsers[i].checked=!this.yxUsers[i].checked
                    if (this.yxUsers[i].checked){
                        this.yxBtn=true;
                        this.yxIds.push(id)
                        return;
                    }else{
                        for (let j in this.yxIds){
                            if (id==this.yxIds[j]){
                                this.yxIds.splice(j,1)
                                break;
                            }
                        }
                    }
                }
            }
            if (this.yxIds.length==0){
                this.yxBtn=false;
            }
        },
        checkDxUsers: function (id) {
            for (let i in this.dxUsers) {
                if (id==this.dxUsers[i].id){
                    this.dxUsers[i].checked=!this.dxUsers[i].checked
                    if (this.dxUsers[i].checked){
                        this.dxBtn=true;
                        this.dxIds.push(id)
                        return;
                    }else{
                        for (let j in this.dxIds){
                            if (id==this.dxIds[j]){
                                this.dxIds.splice(j,1)
                                break;
                            }
                        }
                    }
                }
            }
            if (this.dxIds.length==0){
                this.dxBtn=false;
            }
        },
        insertBatch:function(){
            axios({
                url:"manager/role/insertBatch",
                params:{roleId:this.role.id,dxIds:this.dxIds+""}
            }).then(response=>{
                    layer.msg(response.data.msg)
                    this.selectDxuser(this.nowcompany)
                    this.selectYxuser()
                    this.dxIds=[]
                    this.dxBtn=false;
            }).catch(error=>{

            })
        },
        delBatch:function(){
            axios({
                url:"manager/role/delBatch",
                params:{roleId:this.role.id,yxIds:this.yxIds+""}
            }).then(response=>{
                layer.msg(response.data.msg)
                this.selectDxuser(this.nowcompany)
                this.selectYxuser()
                this.yxIds=[]
                this.yxBtn=false;
            }).catch(error=>{

            })
        },
        init: function () {
            axios({
                url: "manager/examine/allOffice",
                method: 'get',
            }).then(response => {
                this.nodes = response.data.obj
                $.fn.zTree.init($("#treeOffice"), this.setting, this.nodes)
            }).catch(error => {

            })
        },
        onclick: function (event, treeId, treeNode) {
            let zTreeObj = $.fn.zTree.getZTreeObj("treeOffice");
            let transformToArray = zTreeObj.transformToArray(zTreeObj.getNodes());
            this.nowcompany=treeNode.id
            this.selectDxuser(treeNode.id)
            for (let i = 0; i < transformToArray.length; i++) {
                transformToArray[i].isHigh = false;
                zTreeObj.updateNode(transformToArray[i])
            }
            treeNode.isHigh=true;
            zTreeObj.updateNode(treeNode)
        },
        selectDxuser:function(id){
            axios({
                url: "manager/role/selectDxuser",
                method: 'get',
                params: map={officeId:id,roleId:this.role.id}
            }).then(response => {
                this.dxUsers = response.data.obj;
                for (let i in this.dxUsers) {
                    this.dxUsers[i].checked=false
                }
            }).catch(error => {

            })
        },
        setfont: function (treeId, treeNode) {
            return treeNode.isHigh ? {"color": "red"} : {"color": "black"}
        }
    },
    created:
        function () {
            //初始化查询显示第1页,3条数据

            this.role.name=parent.layer.name;
            this.role.id=parent.layer.id;
        },
    mounted: function () {
        this.init();
        this.selectYxuser();
    }
})