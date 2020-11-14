let vm = new Vue({
    el: '#main-container',
    data: function () {
        return {
            pageInfo: {},
            params: {
                conName: ""
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
                    beforeEditName: this.editclick,
                    beforeRemove: this.removeclick,
                    onClick: this.selectbyid
                },
                view: {
                    addHoverDom: this.setaddHoverDom,
                    removeHoverDom: this.setremoveHoverDom
                },
                edit: {
                    enable: true,
                    showRemoveBtn: this.setBtn,
                    showRenameBtn: this.setBtn
                }
            },
            nodes: []
        }
    },
    methods: {
        selectAll: function (pageNum = 1, pageSize = 3) {
            axios({
                method: 'get',//默认get请求
                url: `manager/office/officePage/${pageNum}/${pageSize}`,
                params: this.params
            }).then(response => {
                this.pageInfo = response.data.obj;
            }).catch(error => {
                layer.msg(error.message);
            });
        },
        clearCon: function () {
            this.params = {
                conName: ""
            }
            this.selectAll();
        },
        init: function () {
            axios({
                url: "manager/examine/allOffice",
                method: 'get',
            }).then(response => {
                this.nodes = response.data.obj
                this.nodes[this.nodes.length] = {"id": 0, "name": "全部"}
                $.fn.zTree.init($("#treeMenu"), this.setting, this.nodes)
            }).catch(error => {

            })
        },
        setBtn: function (treeId, treeNode) {
            if (treeNode.level == 0) {
                return false;
            } else {
                return true;
            }
        },
        editclick: function (treeId, treeNode) {
            this.toUpdate(treeNode)
            return false;
        },
        removeclick: function (treeId, treeNode) {
            this.doDelete(treeNode)
            return false;

        },
        selectbyid: function (event, treeId, treeNode) {
            this.params = {
                id: treeNode.id
            }
            this.selectAll();
        },
        setaddHoverDom: function (treeId, treeNode) {
            let tid = treeNode.tId;
            let spanobj = $(`#${tid}_add`);
            if (spanobj.length > 0) {
                return;
            }

            let dom = `<span class="button add" id="${tid}_add" title="rename" treenode_add="" style=""></span>`

            $(`#${tid}_a`).append(dom);
            //    <span class="button edit" id="treeMenu_2_edit" title="rename" treenode_edit="" style=""></span>
            $(`#${tid}_add`).on("click", this.areaadd)
        },
        setremoveHoverDom: function (treeId, treeNode) {
            $(`#${treeNode.tId}_add`).unbind().remove()
        },
        areaadd: function () {
            alert("添加")
        },
        toUpdate: function (data) {
            layer.obj = data;
            layer.success = false;
            layer.open({
                type: 2,
                title: false,
                shadeClose: true,
                shade: 0.1,
                area: ['80%', '80%'],
                content: 'manager/office/toupdate',
                end: () => {
                    if (layer.success) {
                        this.selectAll(this.pageInfo.pageNum, this.pageInfo.pageSize)
                        this.init()
                    }
                }
            })
        },
        doDelete: function (area) {
            layer.msg('确定删除？', {
                time: 20000 //不自动关闭
                , btn: ['确定', '取消']
                , yes: () => {
                    area.delFlag = 1
                    axios({
                        url: "manager/area/doupdate",
                        method: "put",
                        data: area
                    }).then(request => {
                        layer.msg(request.data.msg)
                        this.selectAll(this.pageInfo.pageNum, this.pageInfo.pageSize)
                        this.init()
                    })
                }
            });
        },
        download: function () {
            window.location.href = "manager/area/download"
        },
        upload: function (e) {
            let file = e.target.files[0]
            let formData = new FormData;
            formData.append("file", file);
            axios({
                url: "manager/area/upload",
                method: "post",
                data: formData,
                headers: {
                    "content-type": "multipart/form-data"
                }
            }).then(request => {
                layer.msg(request.data.msg)
                this.selectAll()
            })
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