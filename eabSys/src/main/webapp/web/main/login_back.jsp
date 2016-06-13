<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="author" content="yadong.shao">
    <title><spring:message code="label.system.name" /></title>
    <!-- The fav icon -->
    <%--<link rel="shortcut icon" href="<c:url value="web/img/cn.png"/>">--%>

    <!-- The styles -->
    <link href="<c:url value="web/plugins/bootstrap/bootstrap.css"/>" rel="stylesheet">
    <link href="<c:url value="web/css/font-awesome.css"/>" rel="stylesheet">
    <link href="<c:url value="web/css/style.css"/>" rel="stylesheet">

</head>

<body>
<div class="container-fluid">
    <div id="page-login" class="row">
        <div class="col-xs-12 col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3">
            <div class="text-right">
                <a href="#" class="txt-default">Need an account?</a>
            </div>
            <div class="box">
                <form:form action="login.eab" modelAttribute="login" method="post">
                <div class="box-content">
                    <div class="text-center">
                        <h3 class="page-header">Eab One To One Login Page</h3>
                    </div>
                    <div class="form-group">
                        <spring:message code="label.userid" var="i18nUserid"/>
                        <label for="username" class="control-label">${i18nUserid}</label>
                        <form:input path="username" id="username" cssClass="form-control" placeholder="${i18nUserid}" required="required" autofocus="autofocus" />
                    </div>
                    <div class="form-group">
                        <spring:message code="label.password" var="i18nPassword"/>
                        <label for="password" class="control-label">${i18nPassword}</label>
                        <form:password path="password" id="password" cssClass="form-control" placeholder="${i18nPassword}" required="required" />
                    </div>
                    <div class="text-center">
                        <%--<a href="../index.html" class="btn btn-primary">Sign in</a>--%>
                        <button type="submit" class="btn btn-primary">Sign in</button>
                    </div>
                </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
<script src="<c:url value="web/plugins/html/html5shiv.min.js"/>"></script>
<script src="<c:url value="web/plugins/html/respond.min.js"/>"></script>
<![endif]-->
</body>
</html>
