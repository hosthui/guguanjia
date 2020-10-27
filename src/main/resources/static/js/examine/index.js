let vm = new Vue({
    el: '#main-container',
    data:function() {
        return {
            pageInfo: {},
            condition: {
                type: "",
                name:"全部"
            }
        }
    },
    methods: {
        selectAll: function (pageNum = 1, pageSize = 3) {
            axios({
                method: 'get',//默认get请求
                url: `manager/examine/selectAll/${pageNum}/${pageSize}`,
                params: this.condition
            }).then(response => {
                this.pageInfo = response.data.obj;
            }).catch(error => {
                layer.msg(error.message);
            });
        }
    },
    created:
        function () {
            //初始化查询显示第1页,3条数据
            this.selectAll();
        }
})