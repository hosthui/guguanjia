let vm = new Vue({
    el: '#main-container',
    data: function () {
        return {
            role: {},
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
                },
                check:{
                    enable:true
                }
            },
            nodes: [],
            officeNodes:[]
        }
    },
    methods: {
        changeDataScope:function(){
            if (this.role.dataScope=="9"){
                axios({
                    url:"manager/examine/allOffice",
                    method: 'get',
                }).then(response=>{
                    this.officeNodes=response.data.obj
                    this.officeNodes[this.officeNodes.length]={"id":0,"name":"机构列表"}
                    $.fn.zTree.init($("#select-treetreeSelectOfficeEdit"),this.setting,this.officeNodes)
                }).catch(error=>{

                })
            }else{
                $.fn.zTree.destroy("select-treetreeSelectOfficeEdit");
            }
        },
        selectByRid:function(){
            axios({
                url: "manager/role/allRole",
                method: 'get',
            }).then(response => {
            }).catch(error => {

            })
        },
        init: function () {
            axios({
                url: "manager/role/allRole",
                method: 'get',
            }).then(response => {
                this.nodes = response.data.obj
                this.nodes[this.nodes.length] = {"id": 0, "name": "权限列表"}
                $.fn.zTree.init($("#select-treetreeSelectResEdit"), this.setting, this.nodes)
                this.selectByRid()

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
        changeUp: function () {
            this.up = !this.up
        }
    },
    created:
        function () {
            //初始化查询显示第1页,3条数据
            this.selectAll();

        },
    mounted: function () {
        this.role=parent.layer.obj
        this.init();
    }
})