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
        quUpdate:function () {
            axios({
                url:"manager/statute/staUpdate",
                method:"put",
                data:this.statute
            }).then(response=>{
                parent.layer.success=response.data.success
                let index=parent.layer.getFrameIndex(window.name)
                parent.layer.msg("更新成功")
                parent.layer.close(index)
            }).catch(error=>{

            })
        }
    },
    created: function () {
        this.statute=parent.layer.obj
        console.log(this.statute);
    },
    mounted:function(){
        jeDate({
            dateCell: '#modifydate',
            format: 'YYYY-MM-DD',
            zIndex: 999999999,
            choosefun:(val)=> {
                this.statute.pubDate=val
            }
        });
    },
    components:{
        VueUeditorWrap
    }

})