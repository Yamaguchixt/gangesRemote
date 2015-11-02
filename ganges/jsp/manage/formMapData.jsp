<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<%=request.getContextPath() %>/public/css/bootstrap.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/public/css/common.css" rel="stylesheet">
<script src="<%=request.getContextPath() %>/public/js/jquery-1.11.3.min.js"></script>
<script src="<%=request.getContextPath() %>/public/js/bootstrap.js"></script>
<title>MAPデータ入力</title>
<%

%>
</head>
<body>
<div class="container">
<p>URI:<%= request.getRequestURI() %></p>
<p>URL:<%= request.getRequestURL() %></p>
<p>2</p>

<form method="post" action="<%= request.getContextPath() %>/EnchantManageController">
	<input type="hidden" value="mapCreate" name="action">
	<div class="form-group">
		<label for="mapPoint" class="control-label">MAP座標</label>
		<input  class="form-control" type="text" name="mapPoint" id="point" placeholder="例: 2.3">
	</div>
	<div class="form-group">
		<label for="category" class="control-label">imagePath</label>
		<input class="form-control" type="text" name="imagePath" id="imagePath" placeholder="例(絶対pathで):　/pubic/images/map1.png">
	</div>
	<div class="form-group">
		<label for="drawData" class="control-label">drawData</label>
		<textarea class="form-control textarea" name="drawData" id="drawData" placeholder="[[1,1,3],[1,2,3]]"></textarea>
	<div class="form-group">
		<label for="objectData" class="control-label">objectData</label>
		<textarea class="form-control textarea" name="objectData" id="objectData"></textarea>
	</div><div class="form-group">
		<label for="collisionData" class="control-label">collisionData</label>
		<textarea class="form-control textarea" name="collisionData" id="collisionData"></textarea>
	</div></div>
		<input type="submit" value="送信" class="btn btn-primary">
</form>
</div>
<script>
$(".textarea").height(30);//init
$(".textarea").css("lineHeight","20px");//init

$(".textarea").on("input",function(evt){
    if(evt.target.scrollHeight > evt.target.offsetHeight){
        $(evt.target).height(evt.target.scrollHeight);
    }else{
        var lineHeight = Number($(evt.target).css("lineHeight").split("px")[0]);
        while (true){
            $(evt.target).height($(evt.target).height() - lineHeight);
            if(evt.target.scrollHeight > evt.target.offsetHeight){
                $(evt.target).height(evt.target.scrollHeight);
                break;
            }
        }
    }
});
</script>
</body>
</html>