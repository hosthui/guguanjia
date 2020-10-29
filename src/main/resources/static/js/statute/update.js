let vm = new Vue({
    el: '#main-container',
    data: {
        ueditorConfig:{
            UEDITOR_HOME_URL:"static/ueditor/",
            initialFrameWidth:"100%",
            initialFrameHeight:"100%",
            serverUrl:"manager/statute/doUeditor"
        },
        statute:{}
    },
    methods: {

    },
    created: function () {
        this.statute=parent.layer.obj
        console.log(this.statute);
    },
    components:{
        VueUeditorWrap
    }

})