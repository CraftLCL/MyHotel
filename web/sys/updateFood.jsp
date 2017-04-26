<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <!-- 包含公共的JSP代码片段 -->

    <title>无线点餐平台</title>


    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/sys/style/js/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/sys/style/js/page_common.js"></script>
    <script type="text/javascript">
        /*function putImg() {
            var inputsImg=document.getElementById("inputsImg");
             if(inputsImg.value==""){
             inputsImg.value=document.getElementById("Img").src;
             var s=document.getElementById("Img").src;
             alert(s);
             alert(inputsImg.value);
             }
        }*/
    </script>
    <link href="${pageContext.request.contextPath}/sys/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sys/style/css/index_1.css"/>
</head>
<body>

<!-- 页面标题 -->
<div id="TitleArea">
    <div id="TitleArea_Head"></div>
    <div id="TitleArea_Title">
        <div id="TitleArea_Title_Content">


            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/sys/style/images/title_arrow.gif"/> 更新新菜品


        </div>
    </div>
    <div id="TitleArea_End"></div>
</div>

<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <!-- 表单内容 -->
    <form action="${pageContext.request.contextPath}/food?method=updatedFood" method="post" enctype="multipart/form-data">
        <!-- 本段标题（分段标题） -->
        <div class="ItemBlock_Title">
            <img width="4" height="7" border="0" src="${pageContext.request.contextPath}/sys/style/images/item_point.gif"> 菜品信息&nbsp;
        </div>
        <!-- 本段表单字段 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <div class="ItemBlock2">
                    <table cellpadding="0" cellspacing="0" class="mainForm">
                        <%--
                                            <tr>
                                                    <td width="80px">菜系</td>
                                                    <td>
                                                    <select name="cid" style="width:80px">

                                                               <option value="1"
                                                                   selected="selected"
                                                               >粤菜</option>


                                                               <option value="2"

                                                               >川菜</option>


                                                               <option value="3"

                                                               >湘菜</option>


                                                               <option value="4"

                                                               >东北菜</option>


                                                    </select>
                                                     *<input type="hidden" name="id" value="1" /></td>
                                                </tr>
                                                <tr>
                                                    <td width="80px">菜名</td>
                                                    <td><input type="text" name="foodName" class="InputStyle" value="白灼虾"/> *</td>
                                                </tr>
                                                <tr>
                                                    <td>价格</td>
                                                    <td><input type="text" name="price" class="InputStyle" value="36.0"/> *</td>
                                                </tr>
                                                <tr>
                                                    <td>会员价格</td>
                                                    <td><input type="text" name="mprice" class="InputStyle" value="23.0"/> *</td>
                                                </tr>

                                                <tr>
                                                    <td>简介</td>
                                                    <td><textarea name="introduce" class="TextareaStyle">粤菜白灼虾，大件！</textarea></td>
                                                </tr>
                                                <tr>
                                                    <td width="80px">菜品图片</td>
                                                    <td>

                                                            <img style='max-width:68px;width:68px;width:expression(width>68?"68px":width "px");max-width: 68px;'
                                                            src="${pageContext.request.contextPath}/sys/style/images/baizhuoxia.jpg">
                                                            <input type="hidden" name="image" value="baizhuoxia.jpg">

                                                        <input type="file" name="imageUrl"/> *
                                                    </td>
                                                </tr>
                        --%>
                        <tr>
                            <td width="80px">菜系</td>
                            <td>
                                <%--<c:choose>
                                <c:when test="${not empty requestScope.foodList}">
                                <c:forEach var="food" items="${requestScope.foodList}">

                        </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="3">没有你要找的数据</td>
                            </tr>
                        </c:otherwise>
                        </c:choose>--%>

                                <select name="foodType_id" style="width:80px">
                                    <c:choose>
                                        <c:when test="${not empty requestScope.foodTypeList}">
                                            <c:forEach var="foodType" items="${requestScope.foodTypeList}">
                                                <c:choose>

                                                    <c:when test="${foodType.id}==${requestScope.food.foodType_id}">
                                                        <option value="${foodType.id}"
                                                                selected="selected">${foodType.typeName}</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${foodType.id}">${foodType.typeName}</option>
                                                    </c:otherwise>

                                                </c:choose>

                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="0">请先添加菜系</option>
                                        </c:otherwise>
                                    </c:choose>
                                    <%--<option value="1"

                                               >粤菜</option>--%>
                                </select>
                                *<input type="hidden" name="id" value="${requestScope.food.id}"/></td>
                        </tr>
                        <tr>
                            <td width="80px">菜名</td>
                            <td><input type="text" name="foodName" class="InputStyle"
                                       value="${requestScope.food.foodName}"/> *
                            </td>
                        </tr>
                        <tr>
                            <td>价格</td>
                            <td><input type="text" name="price" class="InputStyle" value="${requestScope.food.price}"/>
                                *
                            </td>
                        </tr>
                        <tr>
                            <td>会员价格</td>
                            <td><input type="text" name="mprice" class="InputStyle"
                                       value="${requestScope.food.mprice}"/> *
                            </td>
                        </tr>

                        <tr>
                            <td>简介</td>
                            <td><textarea name="remark" class="TextareaStyle">${requestScope.food.remark}</textarea>
                            </td>
                        </tr>
                        <tr>
                            <td width="80px">菜品图片</td>
                            <td>
                                <img  style='width: 68px;height: auto' src="${requestScope.food.img}">
                                <input type="hidden" name="oldImg" value="${requestScope.food.img}">
                                <input type="file"  name="img" /> *
                            </td>
                        </tr>

                    </table>
                </div>
            </div>
        </div>


        <!-- 表单操作 -->
        <div id="InputDetailBar">


            <input type="submit" value="修改"   class="FunctionButtonInput">


            <a href="javascript:history.go(-1);" class="FunctionButton">返回</a>
        </div>
    </form>
</div>
</body>
</html>
