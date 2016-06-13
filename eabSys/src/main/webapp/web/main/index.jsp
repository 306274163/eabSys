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
    <div class="row">
        <div id="breadcrumb" class="col-md-12">
            <ol class="breadcrumb">
                <li><a href="index.html">Quotation</a></li>
            </ol>
        </div>
    </div>
    <div class="well">
        <h3 id="grid-options">David</h3>
        <p>Male, 27 years old, non-smoker, account manager, lives in Hong Kong, Permanent Resident.</p>
    </div>
    <div class="row">
        <div class="col-sm-12">

            <div class="box">
                <div class="box-header">
                    <div class="box-name">
                        <i class="fa fa-user"></i>
                        <span>POLICY DETAILS</span>
                    </div>
                </div>
                <div class="box-content">
                    <div class="row">
                        <div class="col-sm-6">
                            <h3>Currency</h3>
                        </div>
                        <div class="col-sm-6">
                            <h3>Payment Mode *</h3>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6">
                            <select class="form-control">
                                <option>Hong Kong Dollars</option>
                                <option>USD</option>
                            </select>
                        </div>
                        <div class="col-sm-6">
                            <select class="form-control">
                                <option>Yearly</option>
                                <option>Monthly</option>
                                <option>Quarterly</option>
                                <option>Half-Yearly</option>
                                <option>Single Premium</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>

            <div class="box">
                <div class="box-header">
                    <div class="box-name">
                        <i class="fa fa-user"></i>
                        <span>WHOLE LIFE PROTECTION</span>
                    </div>
                </div>
                <div class="box-content">
                    <div class="row">
                        <div class="col-sm-1">
                            <h3>Image</h3>
                        </div>
                        <div class="col-sm-11">
                            <div class="row">
                                <div class="col-sm-4">
                                    <p>Policy Term</p>
                                </div>
                                <div class="col-sm-4">
                                    <p>Premium Term</p>
                                </div>
                                <div class="col-sm-4">
                                    <p>Class</p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-4">
                                    <select class="form-control">
                                        <option>Whole Life</option>
                                    </select>
                                </div>
                                <div class="col-sm-4">
                                    <select class="form-control">
                                        <option>Whole Life</option>
                                    </select>
                                </div>
                                <div class="col-sm-4">
                                    <select class="form-control">
                                        <option>-</option>
                                        <option>1</option>
                                        <option>2</option>
                                    </select>
                                </div>
                            </div>
                            <p class="page-header">&nbsp;</p>
                            <div class="row">
                                <div class="col-sm-4">
                                    <p>Sum Assured *</p>
                                </div>
                                <div class="col-sm-4">
                                    <p>Premium</p>
                                </div>
                                <div class="col-sm-4">&nbsp;</div>
                            </div>
                            <div class="row">
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" placeholder="Sum Assured" onblur="ppscalculate('yadong.shao test1');">
                                </div>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" placeholder="Premium" onblur="ppscalculate('yadong.shao test2');">
                                </div>
                                <div class="col-sm-4">&nbsp;</div>
                            </div>
                        </div>
                    </div>
                    <p class="page-header">&nbsp;</p>
                    <div class="row">
                        <div class="col-sm-1">
                            &nbsp;
                        </div>
                        <div class="col-sm-11">
                            <p>Sum assured must be $24,500 - $999,999,500</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <button type="button" class="btn btn-danger btn-block" onclick="getRider('WLP6');">Add Supplementary Plans</button>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">Supplementary Plans</h4>
            </div>
            <div class="modal-body">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                <button type="button" class="btn btn-primary" onclick="addSuppPlan();">Add</button>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="<c:url value="web/plugins/jquery/jquery.min.js"/>"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<c:url value="web/plugins/bootstrap/bootstrap.min.js"/>"></script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
<script src="<c:url value="web/plugins/html/html5shiv.min.js"/>"></script>
<script src="<c:url value="web/plugins/html/respond.min.js"/>"></script>
<![endif]-->

<script type="text/javascript">
    function getRider(planCode){
        $.getJSON("<c:url value="getRider.eab"/>", { planCode: planCode } )
            .done(function(json) {
                $("#myModal").find(".modal-body").empty();
                $.each(json.suppPlans, function(idx, obj) {
                    var table = $("<table/>");
                    var caption = $("<caption/>");
                    var tbody = $("<tbody/>");
                    var tr = $("<tr/>");
                    var td1 = $("<td/>");
                    var td2 = $("<td/>");
                    var img = $("<img/>");
                    var ridio = $("<input type=\"radio\" name=\"radio\" />");

                    $.each(obj.riderList, function(i, r) {
                        var tr = $("<tr/>");
                        var td1 = $("<td/>");
                        var td2 = $("<td/>");
                        var img = $("<img/>");
                        var ridio = $("<input type=\"radio\" name=\"radio\" />");

                        img.addClass("img-rounded").attr("src","http://i.forbesimg.com/media/lists/people/bill-gates_50x50.jpg");
                        td1.append(img);
                        td1.append(r.riderName);
                        ridio.val(r.riderCode);
                        //ridio.attr("onClick", "addThisPlan('" + r.riderCode + "')");
                        td2.append(ridio);
                        tr.append(td1);
                        tr.append(td2);
                        tbody.append(tr);

                    });
                    caption.text(obj.riderTypeDesc);
                    table.addClass("table");
                    table.append(caption);
                    table.append(tbody);
                    table.appendTo($("#myModal").find(".modal-body"));
                });
                $("#myModal").modal("show");
            })
            .fail(function(jqxhr,textStatus,error) {
                var err = textStatus + ", " + error;
                console.log( "Request Failed: " + err );
            });
    }
    function addSuppPlan(){
        alert("add the select plan");
    }
    function ppscalculate(parameter){
        $.getJSON("<c:url value="ppscalculate.eab"/>", { parameter: parameter } )
            .done(function(json) {
                console.log( "system : " + json.suppPlans );
            })
            .fail(function(jqxhr,textStatus,error) {
                var err = textStatus + ", " + error;
                console.log( "Request Failed: " + err );
            });
    }
</script>
</body>
</html>
