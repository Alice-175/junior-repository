<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 白夜
  Date: 2020/6/24
  Time: 13:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="page_nav">

    <c:if test="${requestScope.brrowpage.pageNo>1}">
        <a href="${requestScope.brrowpage.url}&pageNo=1">首页</a>
        <a href="${requestScope.brrowpage.url}&pageNo=${requestScope.brrowpage.pageNo-1}">上一页</a>
    </c:if>
    <c:choose>
        <c:when test="${requestScope.brrowpage.pageTotal<=5}">
            <c:set var="begin" value="1"/>
            <c:set var="end" value="${requestScope.brrowpage.pageTotal}"/>
        </c:when>

        <c:when test="${requestScope.brrowpage.pageTotal>5}">
            <c:choose>
                <c:when test="${requestScope.brrowpage.pageNo<=3}">
                    <c:set var="begin" value="1"/>
                    <c:set var="end" value="5"/>
                </c:when>
                <c:when test="${requestScope.brrowpage.pageNo>requestScope.brrowpage.pageTotal-3}">
                    <c:set var="begin" value="${requestScope.brrowpage.pageTotal-4}"/>
                    <c:set var="end" value="${requestScope.brrowpage.pageTotal}"/>
                </c:when>
                <c:otherwise>
                    <c:set var="begin" value="${requestScope.brrowpage.pageNo-2}"/>
                    <c:set var="end" value="${requestScope.brrowpage.pageNo+2}"/>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>
    <c:forEach begin="${begin}" end="${end}" var="i">
        <c:if test="${requestScope.brrowpage.pageNo==i}">
            【${i}】
        </c:if>
        <c:if test="${requestScope.brrowpage.pageNo!=i}">
            <a href="${requestScope.brrowpage.url}&pageNo=${i}">${i}</a>
        </c:if>
    </c:forEach>

    <c:if test="${requestScope.brrowpage.pageNo<requestScope.brrowpage.pageTotal}">
        <a href="${requestScope.brrowpage.url}&pageNo=${requestScope.brrowpage.pageNo+1}">下一页</a>
        <a href="${requestScope.brrowpage.url}&pageNo=${requestScope.brrowpage.pageTotal}">末页</a>
    </c:if>
    共${requestScope.brrowpage.pageTotal}页，总计${requestScope.brrowpage.pageTotalcount}条记录
    跳转到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页
    <input id="serachPageBtn" type="button" value="确定">
    <script type="text/javascript">
        $(function () {
            $("#serachPageBtn").click(function () {
                var pageNum=$("#pn_input").val();
                location.href="${pageScope.basePath}${requestScope.brrowpage.url}&pageNo="+pageNum;
            });
        });
    </script>
</div>
