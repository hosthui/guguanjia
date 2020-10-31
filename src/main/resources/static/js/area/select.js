let vm = new Vue({
    el: '#main-container',
    data: function () {
        return {
            id:'',
            searchname:"",
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
        init: function () {
            axios({
                url: "manager/area/areaAll",
                method: 'get',
            }).then(response => {
                this.nodes = response.data.obj
                this.nodes[this.nodes.length] = {"id": 0, "name": "区域列表"}
                $.fn.zTree.init($("#select-tree"), this.setting, this.nodes)
            }).catch(error => {

            })
        },
        onclick: function (event, treeId, treeNode) {
            if (treeNode.id != 0&&treeNode.id!=this.id) {
                parent.layer.parentName = treeNode.name;
                parent.layer.id = treeNode.id;
                let index=parent.layer.getFrameIndex(window.name)
                parent.layer.close(index)
            }else{
                layer.msg("请选择正确的区域")
            }
        },
        search: function () {
            let zTreeObj = $.fn.zTree.getZTreeObj("select-tree");
            let nodesFuzzy = zTreeObj.getNodesByParamFuzzy("name", this.searchname, null);
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
            if (treeNode.level!=0){
                return treeNode.isHigh ? {"color": "red"} : {"color": "black"}
            }
        },

    },
    created:
        function () {
        this.id=parent.layer.id;
        },
    mounted: function () {
        this.init();
    }
})