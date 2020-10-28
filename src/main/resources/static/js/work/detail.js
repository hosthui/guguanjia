let app=new Vue({
    el:".main-content",
    data:{
        id:"",
        all:{}
    },
    methods:{
        toDetail:function () {
            axios({
                url:`manager/work/admin/alldetail/${this.id}`,
                method:"get"
            }).then(response=>{
                this.all=response.data
            }).catch(err=>{
                layer.msg("失败")
            })
        }

    },
    created:function () {
        this.id=parent.layer.obj;
        this.toDetail()
    },
    filters:{
        formatTime:function (number,format) {
            let formateArr  = ['Y','M','D','h','m','s'];
            let returnArr   = [];

            let date = new Date(number);
            returnArr.push(date.getFullYear())//在字符数组的末尾插入一个元素
            returnArr.push(formatNumber(date.getMonth() + 1));
            returnArr.push(formatNumber(date.getDate()));

            returnArr.push(formatNumber(date.getHours()));
            returnArr.push(formatNumber(date.getMinutes()));
            returnArr.push(formatNumber(date.getSeconds()));

            for (let i in returnArr)
            {
                format = format.replace(formateArr[i], returnArr[i]);
            }
            return format;
        }
    }
});
function formatNumber(n) {
    n = n.toString();
    return n[1] ? n : '0' + n
}
