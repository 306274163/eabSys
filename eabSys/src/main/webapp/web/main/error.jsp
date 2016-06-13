<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/web/include/taglib.jsp" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script language="javascript" type="text/javascript">
<c:choose>
    <c:when test="${! empty msg}">
        alert('${msg}');
        window.location = "${ctx}/initlogin.eab";
    </c:when>
    <c:otherwise>
        alert('System error, please login again !');
        window.location = "${ctx}/initlogin.eab";
    </c:otherwise>
</c:choose>
</script>
