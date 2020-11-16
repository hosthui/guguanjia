let vm = new Vue({
    el: '.main-content',  //选中整个main
    data: {
        log: {},       //初始化对象     vue建议声明对象同时进行初始化，避免undefinded
    },
    methods: {

    },
    created: function () {
        this.log=parent.layer.obj
    }
});