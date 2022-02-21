function selectAll() {

    //点击表头的复选框,选中所有的下方的复选框标签
    $("#selectAllBtn").click(function () {

        //给下方的所有复选框中,添加name属性
        //通过jquery的标签选择器,加载所有的下方的复选框标签
        var cks = $("input[name=ck]");

        //获取表头的复选框的选中状态
        var flag = $(this).prop("checked");

        //更新所有遍历的复选框的选中状态
        for(var i=0; i<cks.length; i++){
            cks[i].checked = flag;
        }
    })
}


function reverseAll() {

    //根据所有的下方的复选框,添加事件
    $("input[name=ck]").click(function () {

        //获取所有的下方的复选框的数量
        var cks = $("input[name=ck]");

        //获取所有的下方的已选中的复选框的数量
        var ckds = $("input[name=ck]:checked");

        //比对,如果数量相等,证明所有的下方的复选框已全部选中
        if (cks.length == ckds.length) {

            //更新表头复选框的选中状态,为选中状态
            $("#selectAllBtn").prop("checked",true);

        }else{

            //更新表头复选框的选中状态,为取消选中状态
            $("#selectAllBtn").prop("checked",false);

        }
    })
}

function checkCode() {

    //给编码的输入框,添加失去焦点事件
    $("#code").blur(function () {

        //获取编码内容
        var code = $("#code").val();

        if(code == ""){
            //给出提示信息
            $("#msg").html("编码能为不能为空...").css("color","#FF5555");
            return;
        }

        //校验通过,取消提示信息
        $("#msg").html("");

        //发送ajax请求,进行异步查询结果
        $.ajax({
            url:"settings/dictionary/type/checkCode.do",
            data:{
                "code":code
            },
            type:"POST",
            dataType:"json",
            success:function(data) {
                //data: {code:0/1,msg:xxx}
                if(data.code == 0){
                    //代表可以新增数据,编码未重复,显示返回的信息
                    //也可以清空
                    $("#msg").html("");
                    //RGB -> R 红色 red -> G 绿色 green -> b 蓝色 blue
                    //每一个以十六进制两位开头
                    //$("#msg").html(data.msg).css("color","#77FF77");
                }else{
                    //代表不可以新增数据,编码已重复,显示返回的信息
                    $("#msg").html(data.msg).css("color","#FF5555");
                }
            }
        })
    })
}


function saveDictionaryType() {

    //给保存按钮,添加点击事件
    $("#saveDictionaryTypeBtn").click(function () {

        //获取属性信息
        var code = $("#code").val();

        if(code == ""){
            $("#msg").html("编码能为不能为空...").css("color","#FF5555");
            return;
        }

        //校验通过,还需要校验
        var errMsg = $("#msg").html();

        if(errMsg != ""){
            return;
        }

        var name = $("#name").val();

        var description = $("#description").val();

        //校验全部通过,发送ajax请求
        $.ajax({
            url:"settings/dictionary/type/saveDictionaryType.do",
            data:{
                "code":code,
                "name":name,
                "description":description
            },
            type:"POST",
            dataType:"json",
            success:function(data) {
                //data:{code:0/1,msg:xxx}
                if(data.code == 0){
                    //新增成功,跳转到字典类型首页面,加载列表数据
                    window.location.href = "settings/dictionary/type/toIndex.do";
                }else{
                    //给出提示信息
                    $("#msg").html(data.msg).css("color","#FF5555");
                }
            }
        })
    })
}


function toTypeEdit() {

    //给修改按钮,添加点击事件
    $("#toTypeEditBtn").click(function () {

        //获取选中的复选框,并且只能选中一个
        var cks = $("input[name=ck]:checked");

        if(cks.length != 1){
            //要么没有选中,要么选中了多个
            alert("修改操作能选中一条数据...");
            return;
        }

        //选中了一条数据,获取它的value属性(类型编码)
        var code = cks[0].value;

        if(code == ""){
            alert("当前页面加载数据异常,请刷新后再试...");
            return;
        }

        //发送传统请求
        window.location.href = "settings/dictionary/type/toEdit.do?code="+code;

    })
}


function updateDictionaryType() {

    //给字典类型修改页面的更新按钮绑定点击事件
    $("#updateDictionaryTypeBtn").click(function () {

        //获取页面中的属性
        var code = $("#code").val();

        if(code == ""){
            $("#msg").html("当前数据加载异常,请刷新后再试...");
            return;
        }

        var name = $("#name").val();
        var description = $("#description").val();

        //校验通过,清空提示信息
        $("#msg").html("");

        //发送ajax请求,进行更新操作
        $.ajax({
            url:"settings/dictionary/type/updateDictionaryType.do",
            data:{
                "code":code,
                "name":name,
                "description":description
            },
            type:"POST",
            dataType:"json",
            success:function(data) {
                //data : {code:0/1,msg:xxx}
                //修改成功,跳转到字典类型的首页面,查看更新的信息
                if(data.code == 0){
                    window.location.href = "settings/dictionary/type/toIndex.do";
                }else{
                    $("#msg").html(data.msg);
                }
            }
        })
    })
}


function batchDeleteDictionaryType() {

    //给删除按钮,绑定点击事件
    $("#batchDeleteDictionaryTypeBtn").click(function () {

        //获取勾选的复选框的数量
        var cks = $("input[name=ck]:checked");

        //遍历所有的复选框
        //获取复选框中的value属性,进行拼接成参数
        //值得注意的是,拼接的&,分隔符,是要比遍历的次数少一个
        //http://localhost:8080/crm/xxx?codes=xxx&codes=xxx
        var params = "";
        for (var i=0; i<cks.length; i++){

            params += "codes="+cks[i].value;

            //拼接分隔符
            if(i < cks.length -1)
                params += "&";

        }

        //console.log("params",params);

        //由于删除是一个非常危险的动作,给出提示信息
        if(confirm("您确定要删除这些数据嘛?")){
            //发送ajax请求,进行批量删除操作
            $.ajax({
                //批量删除,字典类型
                //url:"settings/dictionary/type/batchDeleteDictionaryType.do?"+params,
                //批量删除,考虑一对多的关联关系
                url:"settings/dictionary/type/batchDeleteDictionaryTypeCondition.do?"+params,
                data:{
                },
                type:"POST",
                dataType:"json",
                success:function(data) {
                    //data : {code:0/1,msg:xxx}
                    if(data.code == 0){
                        //删除成功,刷新页面
                        //跳转到字典类型首页面,就是查询列表操作
                        window.location.href = "settings/dictionary/type/toIndex.do";
                    }else{
                        //删除失败,弹出提示信息
                        alert("当前数据,包含关联关系[ "+data.data+" ],请解除关联关系再在删除");
                        //跳转到字典类型首页面,就是查询列表操作
                        window.location.href = "settings/dictionary/type/toIndex.do";
                    }
                }
            })
        }
    })
}


function getDictionaryValueList() {

    //发送ajax请求,获取字典值列表数据
    $.ajax({
        url:"settings/dictionary/value/getDictionaryValueList.do",
        data:{
        },
        type:"POST",
        dataType:"json",
        success:function(data) {
            //data:{code:0/1,msg:xxx,data:[{字典值}...]}
            if(data.code == 0){
                //数据获取成功,异步加载

                //异步加载步骤
                //1. 定义字符串标签
                var html = "";

                //2. 遍历集合
                //参数1,data.data获取返回值中的集合数据
                //参数2,遍历时,执行的方法,i代表遍历的索引值,n代表遍历的对象
                $.each(data.data,function (i, n) {

                    //3. 将页面中需要加载的标签封装到字符串标签中
                    //4. 将字符串标签中的数据,替换为动态的数据
                    html += '<tr class="'+(i%2==0?'active':'')+'">';
                    html += '<td><input type="checkbox" name="ck"/></td>';
                    html += '<td>'+(i+1)+'</td>';
                    html += '<td>'+n.value+'</td>';
                    html += '<td>'+n.text+'</td>';
                    html += '<td>'+n.orderNo+'</td>';
                    html += '<td>'+n.typeCode+'</td>';
                    html += '</tr>';

                })

                //5. 将字符串标签加载到页面容器中
                $("#dictionaryValueListBody").html(html);

            }else{
                //数据查询失败,弹出提示信息
                alert(data.msg);
            }
        }
    })
}


function selectValueAll() {

    $("#selectValueAll").click(function () {

        //获取所有的复选框,设置它们的选中状态
        //jquery方式获取选中状态并设置
        // $("input[name=ck]").prop("checked",$(this).prop("checked"))
        //dom方式获取选中状态并设置
        $("input[name=ck]").prop("checked",this.checked)

    })
}


function reverseValueAll() {

    //之前我们通过给所有的复选框进行绑定点击事件来实现的
    //现在就无法实现了,因为这种方式需要通过页面中的标签来绑定
    // $("input[name=ck]").click(function () {
    //     alert("123")
    // })
    //但是现在标签,在js代码中进行异步加载完成
    //通过它的页面中的父标签来给子标签绑定事件完成
    //父标签通过on方法来给子标签绑定事件
    //参数1,事件名称
    //参数2,绑定的对象(子标签)
    //参数3,点击后执行的方法
    $("#dictionaryValueListBody").on("click","input[name=ck]",function () {
        $("#selectValueAll").prop(
            "checked",
            $("input[name=ck]").length == $("input[name=ck]:checked").length)
    })
}



function f() {
    $.ajax({
        url:"",
        data:{

        },
        type:"POST",
        dataType:"json",
        success:function(data) {

        }
    })
}