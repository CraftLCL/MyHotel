<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<!-- 包含公共的JSP代码片段 -->
	
<title>无线点餐平台</title>



<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/sys/style/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/sys/style/js/page_common.js"></script>
<link href="${pageContext.request.contextPath}/sys/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sys/style/css/index_1.css" />
</head>
<body>
<!-- 页面标题 -->
<div id="TitleArea">
	<div id="TitleArea_Head"></div>
	<div id="TitleArea_Title">
		<div id="TitleArea_Title_Content">
			<img border="0" width="13" height="13" src="${pageContext.request.contextPath}/sys/style/images/title_arrow.gif"/> 菜品列表
		</div>
    </div>
	<div id="TitleArea_End"></div>
</div>


	<!-- 过滤条件 -->
	<div id="QueryArea">
		<form action="${pageContext.request.contextPath}/food?method=findByName" method="post">
			<input type="hidden" name="method" value="search">
			<input type="text" name="foodName" title="请输入菜品名称">
			<input type="submit" value="搜索">
		</form>
	</div>
<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <table class="MainArea_Content" align="center" cellspacing="0" cellpadding="0">
        <!-- 表头-->
        <thead>
            <tr align="center" valign="middle" id="TableTitle">
				<td>菜编号</td>
				<td>菜名</td>
				<td>所属菜系</td>
				<td>价格</td>
                <td>会员价格</td>
				<td>操作</td>
			</tr>
		</thead>	
		<!--显示数据列表 -->
        <tbody id="TableData">
		<c:choose>
			<c:when test="${not empty requestScope.foodList}">
				<c:forEach var="food" items="${requestScope.foodList}">
<%--
					<tr>
						<td>${foodType.id}</td>
						<td>${foodType.typeName}</td>
						<td>
							<a href="${pageContext.request.contextPath}/foodType?id=${foodType.id}&method=viewUpdate" class="FunctionButton">更新</a>
							<a href="${pageContext.request.contextPath}/foodType?id=${foodType.id}&method=delete" class="FunctionButton">删除</a>
						</td>
					</tr>
--%>
					<tr class="TableDetail1">
						<td>${food.id}&nbsp;</td>
						<td>${food.foodName}&nbsp;</td>
						<td>${food.foodType.typeName}&nbsp;</td>
						<td>${food.price}&nbsp;</td>
						<td>${food.mprice}&nbsp;</td>
						<td>
							<a href="${pageContext.request.contextPath}/food?method=updateFood&id=${food.id}"  class="FunctionButton">更新</a>
							<a href="${pageContext.request.contextPath}/food?method=deleteFood&id=${food.id}" onClick="return delConfirm();"class="FunctionButton">删除</a>
						</td>
					</tr>

				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="3">没有你要找的数据</td>
				</tr>
			</c:otherwise>
		</c:choose>



        </tbody>
    </table>
	
   <!-- 其他功能超链接 -->
	<div id="TableTail" align="center">
		<div class="FunctionButton"><a href="${pageContext.request.contextPath}/food?method=beforeFoodSave">添加</a></div>
    </div> 
</div>
</body>
</html>
