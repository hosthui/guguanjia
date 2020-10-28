let vm = new Vue({
    el: '#main-container',
    data: function () {
        return {
            up: false,
            pageInfo: {},
            condition: {
                officeid: "",
                status:"",
                startTime:"",
                endTime:"",
                name: "全部"
            },
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
        selectAll: function (pageNum = 1, pageSize = 3) {
            axios({
                method: 'get',//默认get请求
                url: `manager/work/admin/selectAll/${pageNum}/${pageSize}`,
                params: this.condition
            }).then(response => {
                this.pageInfo = response.data.obj;
            }).catch(error => {
                layer.msg(error.message);
            });
        },
        clearCon: function () {
            this.condition = {
                type: "",
                name: "全部"
            }
            this.selectAll();
        },
        init: function () {
            axios({
                url: "manager/work/admin/allOffice",
                method: 'get',
            }).then(response => {
                this.nodes = response.data.obj
                this.nodes[this.nodes.length] = {"id": 0, "name": "全部"}
                $.fn.zTree.init($("#pullDownTreeone"), this.setting, this.nodes)
            }).catch(error => {

            })
        },
        toDetail:function (data) {
            layer.obj=data;
            layer.open({
                type: 2,
                title:false,
                shadeClose: true,
                shade: 0.1,
                area: ['80%', '80%'],
                content: 'manager/work/admin/todetail',
                end: ()=> {
                    this.selectAll(this.pageInfo.pageNum,this.pageInfo.pageSize)
                }
            })
        },
        onclick: function (event, treeId, treeNode) {
            this.condition.name = treeNode.name;
            this.condition.officeid = treeNode.id;
            if (treeNode.id===0){
                this.condition.officeid=null;
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
        this.init();
    }
})