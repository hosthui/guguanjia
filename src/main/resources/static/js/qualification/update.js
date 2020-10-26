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
        quUpdate:function () {
            this.qualification.address=null;
            axios({
                url:"",
                method:"put",
                data:this.qualification
            }).then(response=>{

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