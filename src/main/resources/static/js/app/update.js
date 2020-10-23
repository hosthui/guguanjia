let app=new Vue({
    el:".main-content",
    data:{
        app:{}
    },
    methods:{

    },
    created:function () {
        this.app=parent.layer.obj;
    }
})