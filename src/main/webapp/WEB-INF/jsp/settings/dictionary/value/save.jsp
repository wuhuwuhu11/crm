<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
<meta charset="UTF-8">

<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />

<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>

	<script>

		$(function () {

			$("#typeCode").change(function () {

				var typeCode = $("#typeCode").val();

				if(typeCode!=""){

					$("#msg1").html("");

				}

			})

			$("#value").focus(function () {

				$("#msg2").html("");

			})


			$("#saveValueBtn").click(function () {

				var typeCode = $("#typeCode").val();

				if(typeCode==""){

					$("#msg1").html("字典类型编码不能为空");

					return false;

				}

				var value = $.trim($("#value").val());

				if(value==""){

					$("#msg2").html("字典值不能为空");

					return false;

				}

				$.ajax({

					url : "settings/dictionary/value/checkValueAndCode.do",
					data : {

						"typeCode" : typeCode,
						"value" : value

					},
					type : "get",
					dataType : "json",
					success :function (data) {

						if(data.success){

							$("#valueForm").submit();

						}else{

							$("#msg2").html("字典值重复");

							return false;

						}

					}

				})


			})



		})

	</script>

</head>
<body>

	<div style="position:  relative; left: 30px;">
		<h3>新增字典值</h3>
	  	<div style="position: relative; top: -40px; left: 70%;">
			<button type="button" class="btn btn-primary" id="saveValueBtn">保存</button>
			<button type="button" class="btn btn-default" onclick="window.history.back();">取消</button>
		</div>
		<hr style="position: relative; top: -40px;">
	</div>
	<form id="valueForm" method="post" action="settings/dictionary/value/saveValue.do" class="form-horizontal" role="form">
					
		<div class="form-group">
			<label for="create-dicTypeCode" class="col-sm-2 control-label">字典类型编码<span style="font-size: 15px; color: red;">*</span></label>
			<div class="col-sm-10" style="width: 300px;">
				<select class="form-control" id="typeCode" style="width: 200%;" name="typeCode">
				  <option></option>
				  <c:forEach items="${dtList}" var="dt">

					  <option value="${dt.code}">${dt.name}</option>

				  </c:forEach>
				</select>
				<span id="msg1"></span>
			</div>
		</div>
		
		<div class="form-group">
			<label for="create-dicValue" class="col-sm-2 control-label">字典值<span style="font-size: 15px; color: red;">*</span></label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="value" style="width: 200%;" name="value">
				<span id="msg2"></span>
			</div>
		</div>
		
		<div class="form-group">
			<label for="create-text" class="col-sm-2 control-label">文本</label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="text" style="width: 200%;" name="text">
			</div>
		</div>
		
		<div class="form-group">
			<label for="create-orderNo" class="col-sm-2 control-label">排序号</label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="orderNo" style="width: 200%;" name="orderNo">
			</div>
		</div>
	</form>
	
	<div style="height: 200px;"></div>
</body>
</html>