let vm=new Vue({
    el:".main-content",
    data:{},
    methods:{
        selectAll:function () {
            axios({
                url:""
            })
        }
    },
    created:function () {
        this.selectAll()
    }
})