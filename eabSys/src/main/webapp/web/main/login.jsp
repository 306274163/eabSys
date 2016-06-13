<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8" />
    <meta charset="utf-8" />
    <title>EabSys Internal</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    
    <link rel="apple-touch-icon" href="<c:url value="web/images/pages/ico/60.png"/>">
    <link rel="apple-touch-icon" sizes="76x76" href="<c:url value="web/images/ico/76.png"/>">
    <link rel="apple-touch-icon" sizes="120x120" href="<c:url value="web/images/ico/120.png"/>">
    <link rel="apple-touch-icon" sizes="152x152" href="<c:url value="web/images/ico/152.png"/>">
    <link rel="icon" type="image/x-icon" href="<c:url value="web/images/favicon.ico"/>" >
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-touch-fullscreen" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="default">
    <meta content="" name="description" />
    <meta content="" name="author" />
    <link href="<c:url value="web/css/assets/plugins/pace/pace-theme-flash.css"/>" rel="stylesheet" type="text/css" />
    <link href="<c:url value="web/css/assets/plugins/boostrapv3/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css" />
    <link href="<c:url value="web/css/assets/plugins/font-awesome/css/font-awesome.css"/>" rel="stylesheet" type="text/css" />
    <link href="<c:url value="web/css/assets/plugins/jquery-scrollbar/jquery.scrollbar.css"/>" rel="stylesheet" type="text/css" media="screen" />
    <link href="<c:url value="web/css/assets/plugins/bootstrap-select2/select2.css"/>" rel="stylesheet" type="text/css" media="screen" />
    <link href="<c:url value="web/css/assets/plugins/switchery/css/switchery.min.css"/>" rel="stylesheet" type="text/css" media="screen" />
    <link href="<c:url value="web/css/pages/css/pages-icons.css"/>" rel="stylesheet" type="text/css">
    <link class="main-stylesheet" href="<c:url value="web/images/css/pages.css"/>" rel="stylesheet" type="text/css" />
  
    <!--[if lte IE 9]>
        <link href="pages/css/ie9.css" rel="stylesheet" type="text/css" />
    <![endif]-->
    <script type="text/javascript">
    window.onload = function()
    {
      // fix for windows 8
      if (navigator.appVersion.indexOf("Windows NT 6.2") != -1)
        document.head.innerHTML += '<link rel="stylesheet" type="text/css" href="<c:url value="web/iamges/css/windows.chrome.fix.css"/>" />'
    }
    </script>
  </head>
  <body class="fixed-header   ">
    <!-- START PAGE-CONTAINER -->
    <div class="login-wrapper ">
      <!-- START Login Background Pic Wrapper-->
      <div class="bg-pic">
        <!-- START Background Pic-->
        <img src="<c:url value="web/css/assets/img/main/bigstock-Happy-Asian-Family-23852234.jpg"/>" 
        data-src="<c:url value="web/css/assets/img/main/bigstock-Happy-Asian-Family-23852234.jpg"/>" 
        data-src-retina="<c:url value="web/css/assets/img/main/bigstock-Happy-Asian-Family-23852234.jpg"/>" 
        alt="" class="lazy">
        <!-- END Background Pic-->
        <!-- START Background Caption-->
        <div class="bg-caption pull-bottom sm-pull-bottom text-white p-l-20 m-b-20">
          <h2 class="semi-bold text-white">
					iConfigurator make it easy to enjoy what matters the most in the life</h2>
          <p class="small">
            images Displayed are solely for representation purposes only, All work copyright of respective owner, otherwise Â© 2014-2015 EAB.
          </p>
        </div>
        <!-- END Background Caption-->
      </div>
      <!-- END Login Background Pic Wrapper-->
      <!-- START Login Right Container-->
      <div class="login-container bg-white">
        <div class="p-l-50 m-l-20 p-r-50 m-r-20 p-t-50 m-t-30 sm-p-l-15 sm-p-r-15 sm-p-t-40">
          <img src="<c:url value="web/css/assets/img/logo.png"/>" alt="logo" data-src="<c:url value="web/css/assets/img/logo.png"/>" data-src-retina="<c:url value="web/css/assets/img/logo_2x.png"/>" width="78" height="22">
          <p class="p-t-35">Sign into your iConfigurator account</p>
          <!-- START Login Form -->
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
                    <!--    <div class="text-center">
                      Remember Me:  <input id="_spring_security_remember_me" name="_spring_security_remember_me" type="checkbox" value="true"/>
                    </div> --> 
                    <div class="text-center">
                        <%--<a href="../index.html" class="btn btn-primary">Sign in</a>--%>
                        <button type="submit" class="btn btn-primary">Sign in</button>
                    </div>
                </div>
                </form:form>
          <!--END Login Form-->
        </div>
      </div>
      <!-- END Login Right Container-->
    </div>
    <!-- END PAGE CONTAINER -->
    <!-- BEGIN VENDOR JS -->
    <script src="<c:url value="web/css/assets/plugins/pace/pace.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="web/css/assets/plugins/jquery/jquery-1.11.1.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="web/css/assets/plugins/modernizr.custom.js"/>" type="text/javascript"></script>
    <script src="<c:url value="web/css/assets/plugins/jquery-ui/jquery-ui.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="web/css/assets/plugins/boostrapv3/js/bootstrap.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="web/css/assets/plugins/jquery/jquery-easy.js"/>" type="text/javascript"></script>
    <script src="<c:url value="web/css/assets/plugins/jquery-unveil/jquery.unveil.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="web/css/assets/plugins/jquery-bez/jquery.bez.min.js"/>"></script>
    <script src="<c:url value="web/css/assets/plugins/jquery-ios-list/jquery.ioslist.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="web/css/assets/plugins/jquery-actual/jquery.actual.min.js"/>"></script>
    <script src="<c:url value="web/css/assets/plugins/jquery-scrollbar/jquery.scrollbar.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="web/css/assets/plugins/bootstrap-select2/select2.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="web/css/assets/plugins/classie/classie.js"/>"></script>
    <script src="<c:url value="web/css/assets/plugins/switchery/js/switchery.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="web/css/assets/plugins/jquery-validation/js/jquery.validate.min.js"/>" type="text/javascript"></script>
    <!-- END VENDOR JS -->
    <!-- BEGIN CORE TEMPLATE JS -->
    <script src="<c:url value="web/images/js/pages.min.js"/>"></script>
    <!-- END CORE TEMPLATE JS -->
    <!-- BEGIN PAGE LEVEL JS -->
    <script src="<c:url value="web/css/assets/js/scripts.js"/>" type="text/javascript"></script>
    <!-- END PAGE LEVEL JS -->
    <script>
    $(function()
    {
      $('#form-login').validate()
    })
    </script>
  </body>
</html>