let vm = new Vue({
    el: '#main-container',
    data: function () {
        return {
            role: {},
            yxUsers:[],
            yxIds:[],
            yxBtn:false,
            dxUsers:[],
            dxBtn:false,
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
            this.condition.name = treeNode.name;
            this.condition.officeid = treeNode.id;
            if (treeNode.id === 0) {
                this.condition.officeid = null;
            }
            $(".btn-group").removeClass("open")
        },
        search: function () {
            let zTreeObj = $.fn.zTree.getZTreeObj("pullDownTreeone");
            let nodesFuzzy = zTreeObj.getNodesByParamFuzzy("name", this.condition.name, null);
            let transformToArray = zTreeObj.transformToArray(zTreeObj.getNodes());
            for (let i = 0; i < transformToArray.length; i++) {
                transformToArray[i].isHigh = false;
                zTreeObj.updateNode(transformToArray[i])
            }
            for (let i = 0; i < nodesFuzzy.length; i++) {
                nodesFuzzy[i].isHigh = true
                zTreeObj.updateNode(nodesFuzzy[i])
            }
        },
        setfont: function (treeId, treeNode) {
            return treeNode.isHigh ? {"color": "red"} : {"color": "black"}
        },
        // changeUp: function () {
        //     this.up = !this.up
        // }
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