<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
    <!-- 包含公共的JSP代码片段 -->

    <title>无线点餐平台</title>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/sys/style/js/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/sys/style/js/page_common.js"></script>
    <link href="${pageContext.request.contextPath}/sys/style/css/common_style_blue.css" rel="stylesheet"
          type="text/css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sys/style/css/index_1.css"/>
</head>
<body>
<!-- 页面标题 -->
<div id="TitleArea">
    <div id="TitleArea_Head"></div>
    <div id="TitleArea_Title">
        <div id="TitleArea_Title_Content">
            <img border="0" width="13" height="13"
                 src="${pageContext.request.contextPath}/sys/style/images/title_arrow.gif"/> 餐桌列表
        </div>
    </div>
    <div id="TitleArea_End"></div>
</div>


<!-- 过滤条件 -->
<div id="QueryArea">
    <form action="${pageContext.request.contextPath}/dinnerTable?method=findByName" method="post">
        <input type="hidden" name="method" value="search">
        <input type="text" name="tableName" title="请输入餐桌名称">
        <input type="submit" value="搜索">
    </form>
</div>


<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <table class="MainArea_Content" cellspacing="0" cellpadding="0">
        <!-- 表头-->
        <thead>
        <tr align="center" valign="middle" id="TableTitle">
            <td>编号</td>
            <td>桌名</td>
            <td>状态</td>
            <td>预定时间</td>
            <td>操作</td>
        </tr>
        </thead>
        <!--显示数据列表 -->
        <tbody id="TableData">

        <%--			<tr class="TableDetail1">
                        <td align="center">1&nbsp;</td>
                        <td align="center"> 纽约&nbsp;</td>
                        <td align="center">预定</td>
                        <td align="center">2014-12-08 23:31:12</td>
                        <td>
                            <a href="/wirelessplatform/board.html?method=update&id=1&isBook=0" class="FunctionButton">退桌</a>
                            <a href="/wirelessplatform/board.html?method=delete&id=1" onClick="return delConfirm();"class="FunctionButton">删除</a>
                        </td>
                    </tr>--%>
        <c:choose>
            <c:when test="${not empty requestScope.dinnerTableList}">
                <c:forEach var="dinnerTable" items="${requestScope.dinnerTableList}">
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
                        <td>${dinnerTable.id}&nbsp;</td>
                        <td>${dinnerTable.tableName}&nbsp;</td>
                        <td>${dinnerTable.sTabelStatus}</td>
                        <td><fmt:formatDate value="${dinnerTable.orderDate}" type="both"/></td>
                        <td>

                            <a href="${pageContext.request.contextPath}/dinnerTable?method=update&id=${dinnerTable.id}"
                               class="FunctionButton">
                                <c:choose>
                                    <c:when test="${dinnerTable.sTabelStatus == '空闲'}">
                                        预定
                                    </c:when>
                                    <c:otherwise>
                                        退桌
                                    </c:otherwise>
                                </c:choose>

                            </a>
                            <a href="${pageContext.request.contextPath}/dinnerTable?method=delete&id=${dinnerTable.id}"
                               onClick="return delConfirm();" class="FunctionButton">删除</a>
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
        <div class="FunctionButton"><a href="${pageContext.request.contextPath}/sys/saveBoard.jsp">添加</a></div>
    </div>
</div>
</body>
</html>
