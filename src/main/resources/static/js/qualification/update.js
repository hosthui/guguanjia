let vm = new Vue({
    el: '#main-container',
    data: {
        qualification:{},
        id:''
    },
    methods: {
        selectOne: function () {
            axios({
                url:`manager/qualification/selectOne/${this.id}`,
                method:"get",
            }).then(response=>{
                this.qualification=response.data.obj
            }).catch(error=>{

            })
        },
        quUpdate:function (check) {
            this.qualification.check=check;
            this.qualification.address=null;
            axios({
                url:"manager/qualification/quUpdate",
                method:"put",
                data:this.qualification
            }).then(response=>{
                parent.layer.success=response.data.success
                let index=parent.layer.getFrameIndex(window.name)
                parent.layer.msg("更新成功")
                parent.layer.close(index)
            }).catch(error=>{

            })
        }
    },
    created:
        function () {
        this.id=parent.layer.obj.id
            this.selectOne()
        }
})