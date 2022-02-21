function selectAll() {
    //点击表头的复选框，选中所有的下方复选框
    $("#selectAllBtn").click(function () {

        //下方的所有复选框中，添加name属性
        //通过jquery的标签选择器，加载所有的下方的复选框标签
        var cks = $("input[name=ck]");
        //获取表头的复选框的选中状态
        var flag = $(this).prop("checked");

        //更新所有遍历的复选框的选中状态
        for (var i = 0; i < cks.length; i++) {
            cks[i].checked = flag;
        }
    })
}

function reverseAll() {
    //根据所有下方复选框添加事件
    $("input[name=ck]").click(function () {

        //获取所有的下方的复选框的数量
        var cks = $("input[name=ck]");

        //获取所有的下方的已选中的复选框的数量
        var ckds = $("input[name=ck]:checked");

        //比对，如果数量相等，证明所有的 下方复选框已全选中
        if (cks.length == ckds.length) {
            //更新表头复选框的选中状态
            $("#selectAllBtn").prop("checked", true);
        } else {
            $("#selectAllBtn").prop("checked", false);

        }

    })
}

function checkCode() {
    //给编码的输入框，添加失去焦点的事件
    $("#code").blur(function () {

        //获取编码内容
        var code = $("#code").val();

        if (code == "") {
            //给出提示信息
            $("#msg").html('ni yao xie dong xi').css("color", "#FF5555");
            return;
        }

        //发送ajax请求，进行异步请求

        $.ajax({
            url: "settings/dictionary/type/checkCode.do",
            data: {
                "code": code
            },
            type: "POST",
            dataType: "json",
            success: function (data) {
                if (data.code == 0) {
                    //显示返回信息，编码未重复 可以新增数据
                    //也可以清空
                    // $("#msg").html(data.msg).css("color","#33ff33");
                    $("#msg").html("")
                } else {
                    //显示返回信息
                    $("#msg").html(data.msg).css("color", "#ff5555");
                }
            }
        })
    })
}

function saveDictionaryType() {

    //给保存按钮，添加事件
    $("#saveDictionaryTypeBtn").click(function () {

        //获取属性信息
        var code = $("#code").val();

        if (code == "") {
            $("#msg").html("编码不能为空").css("color", "#33ff33");
            return
        }

        //校验通过，还需要进行校验
        var errMsg = $("#msg").html();

        if (errMsg != "") {
            return;

            var name = $("#name").val();

            var description = $("#description").val();

            //校验全部通过，发送ajax请求
            $.ajax({
                url: "settings/dictionary/type/saveDictionaryType.do",
                data: {
                    "code": code,
                    "name": name,
                    "description": description
                },
                type: "POST",
                dataType: "json",
                success: function (data) {
                    //data:{code:0/1,msg:xxx}
                    if (data.code == 0) {
                        //新增成功,跳转到字典类型首页面,加载列表数据
                        window.location.href = "settings/dictionary/type/toIndex.do";
                    } else {
                        //给出提示信息
                        $("#msg").html(data.msg).css("color", "#fa1b1b");
                    }
                }
            })
        }
    })
}

function  toTypeEdit() {

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