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
                check: {
                    enable: true
                }
            },
            nodes: [],
            treeObj: "",
            officeNodes: [],
            officeTreeObj: '',
            params: {
                role: "",
                _resources: [],
                resources: [],
                _offices: [],
                offices: []
            }
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
                    this.officeTreeObj=$.fn.zTree.init($("#select-treetreeSelectOfficeEdit"),this.setting,this.officeNodes)
                    this.officeSelectByRid();
                }).catch(error=>{

                })
            }else{
                $.fn.zTree.destroy("select-treetreeSelectOfficeEdit");
            }
        },
        selectByRid:function(){
            axios({
                url: "manager/role/selectByRid",
                method: 'get',
                params:{rid:this.role.id},
            }).then(response => {
                let transformToArray = this.treeObj.transformToArray(this.treeObj.getNodes());
                let _nodes = response.data.obj;
                this.params._resources=_nodes
                for (let nodesKey in _nodes) {
                    for (let transformToArrayKey in transformToArray) {
                        if (_nodes[nodesKey]==transformToArray[transformToArrayKey].id){
                            transformToArray[transformToArrayKey].checked=true
                        }
                    }
                }
            }).catch(error => {

            })
        },
        officeSelectByRid:function(){
            axios({
                url: "manager/role/officeSelectByRid",
                method: 'get',
                params:{rid:this.role.id},
            }).then(response => {
                let transformToArray = this.officeTreeObj.transformToArray(this.officeTreeObj.getNodes());
                let _nodes = response.data.obj;
                this.params._offices=_nodes
                for (let nodesKey in _nodes) {
                    for (let transformToArrayKey in transformToArray) {
                        if (_nodes[nodesKey]==transformToArray[transformToArrayKey].id){
                            transformToArray[transformToArrayKey].checked=true
                        }
                    }
                }
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
                this.treeObj=$.fn.zTree.init($("#select-treetreeSelectResEdit"), this.setting, this.nodes)
                this.selectByRid()
                this.changeDataScope()
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
        update:function () {
            let checkedNodes = this.treeObj.getCheckedNodes(true);
            if (checkedNodes.length>0&&checkedNodes[0].id==0){
                checkedNodes.splice(0,1);
            }
            for (let i = 0; i < checkedNodes.length; i++) {
                this.params.resources.push(checkedNodes[i].id);
            }
            if (this.role.dataScope=="9") {
                let checkedNodesoffice = this.officeTreeObj.getCheckedNodes(true);
                if (checkedNodesoffice.length>0&&checkedNodesoffice[0].id==0){
                    checkedNodesoffice.splice(0,1);
                }

                for (let i = 0; i < checkedNodesoffice.length; i++) {
                    this.params.offices.push(checkedNodesoffice[i].id);
                }
            }
            this.params.role=this.role
            axios({
                url:"manager/role/update",
                method:"put",
                data:this.params
            }).then(response=>{
                let frameIndex = parent.layer.getFrameIndex(window.name);
                parent.layer.msg("更新成功");
                parent.layer.close(frameIndex)
            })
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