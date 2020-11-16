let vm = new Vue({
    el: '.main-content',  //选中整个main
    data: {
        ueditorConfig:{
            UEDITOR_HOME_URL:"static/ueditor/",
            initialFrameWidth:"100%",
            initialFrameHeight:"100%",
            serverUrl:"manager/office/doUeditor"
        },
        company: {},       //初始化对象     vue建议声明对象同时进行初始化，避免undefinded
        wastetypes:{},
        wastes:{},
        comwastes:[],
        oldcomwastes:[],
        type:'0',
        waste:'0',
    },
    methods: {
        init: function () {
            axios({
                url: "manager/office/allWasteType",
            }).then(response => {
                this.wastetypes =response.data.obj
            }).catch(error => {
                layer.msg(error.message)
            })
        },
        allwaste:function(){
            this.wastes={}
            axios({
                url: "manager/office/allWaste",
                params:{'parentId':this.type.id}
            }).then(response => {
                this.wastes =response.data.obj
            }).catch(error => {
                layer.msg(error.message)
            })
        },
        addcomwastes:function(){
            this.comwastes.push({'wasteTypeCode':this.type.code,'name':this.waste.name,'code':this.waste.code,'id':this.waste.id});
        },
        removecomwastes:function(index){
            this.comwastes.splice(index,1)
        },
        companywaste:function(id){
            axios({
                url: "manager/office/companyw",
                params:{'officeId':id}
            }).then(response => {
                let was=response.data.obj
                this.comwastes=was
                for (let wasKey in was) {
                    this.oldcomwastes.push(was[wasKey].id)
                }
            }).catch(error => {
                layer.msg(error.message)
            })
        },
        toSelect:function (aid) {
            layer.id=aid;
            layer.open({
                type: 2,
                title:false,
                shadeClose: true,
                shade: 0.1,
                area: ['100%', '100%'],
                content: 'manager/office/toselect',
                end: ()=> {
                    this.company.areaName=layer.parentName
                    this.company.areaId=layer.id
                }
            })
        },
        doUpdate: function () {
            let _comwas=[];
            for (let comwastesKey in this.comwastes) {
                _comwas.push(this.comwastes[comwastesKey].id)
            }
            axios({
                url: "manager/office/doupdate",
                method:"put",
                data:{'company':this.company,'oldcomwastes':this.oldcomwastes,'comwastes':_comwas}
            }).then(response => {
                let frameIndex = parent.layer.getFrameIndex(window.name);
                parent.layer.msg("更新成功");
                parent.layer.close(frameIndex)
            }).catch(error => {
                layer.msg(error.message)
            })
        }
    },
    created: function () {
        this.company=parent.layer.obj
        this.init();
        this.companywaste(this.company.id)
    },
    components:{
        VueUeditorWrap
    }
});