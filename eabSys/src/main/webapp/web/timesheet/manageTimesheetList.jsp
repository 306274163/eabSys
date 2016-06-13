<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8" />
    <meta charset="utf-8" />
    <title>Manage TimeSheet</title>
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
    
    
    <link href="<c:url value="web/css/assets/plugins/jquery-datatable/media/css/jquery.dataTables.css"/>" rel="stylesheet" type="text/css" />
    <link href="<c:url value="web/css/assets/plugins/jquery-datatable/extensions/FixedColumns/css/dataTables.fixedColumns.min.css"/>" rel="stylesheet" type="text/css" />
    <link href="<c:url value="web/css/assets/plugins/datatables-responsive/css/datatables.responsive.css"/>" rel="stylesheet" type="text/css" media="screen" />
 
    
    <!--[if lte IE 9]>
        <link href="pages/css/ie9.css" rel="stylesheet" type="text/css" />
    <![endif]-->
    <script type="text/javascript">
    window.onload = function()
    {
      // fix for windows 8
      if (navigator.appVersion.indexOf("Windows NT 6.2") != -1)
        document.head.innerHTML += '<link rel="stylesheet" type="text/css" href="pages/css/windows.chrome.fix.css" />'
      <!-- 初使化日历控件-->
      
      $('#datepicker-range').datepicker();
    }
    function search(){
    	$("#searchForm").submit();
    }
    function del_task(){
    	var canRunDel =false;
        var saveDataAry=[];  
    	$("#tableWithDynamicRows input[name=delTaskCode]:checked").each(function(i){  
			var  thirdRow = $(this).closest('tr');    	  
    	    var data1={"taskCode":$(this).val()}; 
    	    saveDataAry.push(data1);
    	    canRunDel =true;
    	});    
    	if(canRunDel){
	    	$.ajax({ 
	            type:"POST", 
	            url:"delTask.eab", 
	            dataType:"json",      
	            contentType:"application/json",               
	            data:JSON.stringify(saveDataAry), 
	            success:function(data){ 
	            	$("#tableWithDynamicRows input[name=delTaskCode]:checked").each(function(i){  
	        			var  thirdRow = $(this).closest('tr');
	        			//thirdRow.remove();		
	        			var oTable = $('#tableWithDynamicRows').dataTable();
			            oTable.fnDeleteRow( thirdRow );
	            	});                      
	            },
	            error: function () {
	                alert("del error！");             
	       	   } 
	        }); 
    	} 
 }
    function clickRadio(){    
    	  if($('input[name="addType"]:checked').val()=='Y'){            	
           	  $('#addTaskRow').show();  
           	  $('#addTaskRow1').show();  
        	  $('#addTaskRow2').show();  
           	  $('#addGrpRow').hide();  
           }else{
           	  $('#addTaskRow').hide();  
           	  $('#addTaskRow1').hide();  
              $('#addTaskRow2').hide();  
           	  $('#addGrpRow').show();  
           }
    }
    </script>
  </head>
  <body class="fixed-header   ">
    <!-- BEGIN SIDEBPANEL-->
    <nav class="page-sidebar" data-pages="sidebar">
      <!-- BEGIN SIDEBAR MENU TOP TRAY CONTENT-->

      <!-- END SIDEBAR MENU TOP TRAY CONTENT-->
      <!-- BEGIN SIDEBAR MENU HEADER-->
      <div class="sidebar-header">
        <img src="<c:url value="web/css/assets/img/logo_white.png"/>" alt="logo" class="brand" data-src="<c:url value="web/css/assets/img/logo_white.png"/>" 
        data-src-retina="<c:url value="web/css/assets/img/logo_white_2x.png"/>" width="78" height="22">
        <div class="sidebar-header-controls">
          <button type="button" class="btn btn-xs sidebar-slide-toggle btn-link m-l-20" data-pages-toggle="#appMenu"><i class="fa fa-angle-down fs-16"></i>
          </button>
          <button type="button" class="btn btn-link visible-lg-inline" data-toggle-pin="sidebar"><i class="fa fs-12"></i>
          </button>
        </div>
      </div>
      <!-- END SIDEBAR MENU HEADER-->
      <!-- START SIDEBAR MENU -->
      <div class="sidebar-menu">
        <!-- BEGIN SIDEBAR MENU ITEMS-->
        <ul class="menu-items">
            <li class="active open">
            <a href="javascript:;"><span class="title">Timesheet</span>
            <span class="active open arrow"></span></a>
            <span class="icon-thumbnail"><i class="pg-tables"></i></span>
            <ul class="sub-menu">
              <li class="">
                <a href="index.eab">Timesheet</a>
                <span class="icon-thumbnail">TS</span>
              </li>
              <li class="active">
                <a href="manageTask.eab">Manage Task</a>
                <span class="icon-thumbnail">MT</span>
              </li>
            </ul>
          </li>         
        </ul>
        <div class="clearfix"></div>
      </div>
      <!-- END SIDEBAR MENU -->
    </nav>
    <!-- END SIDEBAR -->
    <!-- END SIDEBPANEL-->
    <!-- START PAGE-CONTAINER -->
    <div class="page-container">
      <!-- START HEADER -->
      <div class="header ">
        <!-- START MOBILE CONTROLS -->
        <!-- LEFT SIDE -->
        <div class="pull-left full-height visible-sm visible-xs">
          <!-- START ACTION BAR -->
          <div class="sm-action-bar">
            <a href="#" class="btn-link toggle-sidebar" data-toggle="sidebar">
              <span class="icon-set menu-hambuger"></span>
            </a>
          </div>
          <!-- END ACTION BAR -->
        </div>
        <!-- RIGHT SIDE -->
        <div class="pull-right full-height visible-sm visible-xs">
          <!-- START ACTION BAR -->
          <div class="sm-action-bar">
            <a href="#" class="btn-link" data-toggle="quickview" data-toggle-element="#quickview">
              <span class="icon-set menu-hambuger-plus"></span>
            </a>
          </div>
          <!-- END ACTION BAR -->
        </div>
        <!-- END MOBILE CONTROLS -->
        <div class=" pull-left sm-table">
          <div class="header-inner">
            <div class="brand inline">
              <img src="<c:url value="web/css/assets/img/logo.png"/>" alt="logo" data-src="<c:url value="web/css/assets/img/logo.png"/>" 
              data-src-retina="<c:url value="web/css/assets/img/logo_2x.png"/>" width="78" height="22">
            </div>
            <!-- START NOTIFICATION LIST -->
            <ul class="notification-list no-margin hidden-sm hidden-xs b-grey b-l b-r no-style p-l-30 p-r-20">
              <li class="p-r-15 inline">
                <div class="dropdown">
                  <a href="javascript:;" id="notification-center" class="icon-set globe-fill" data-toggle="dropdown">
                    <span class="bubble"></span>
                  </a>
                  <!-- START Notification Dropdown -->
                  <div class="dropdown-menu notification-toggle" role="menu" aria-labelledby="notification-center">
                    <!-- START Notification -->
                    <div class="notification-panel">
                      <!-- START Notification Body-->
                      <div class="notification-body scrollable">
                        <!-- START Notification Item-->
                        <div class="notification-item unread clearfix">
                          <!-- START Notification Item-->
                          <div class="heading open">
                            <a href="#" class="text-complete">
                              <i class="pg-map fs-16 m-r-10"></i>
                              <span class="bold">Carrot Design</span>
                              <span class="fs-12 m-l-10">David Nester</span>
                            </a>
                            <div class="pull-right">
                              <div class="thumbnail-wrapper d16 circular inline m-t-15 m-r-10 toggle-more-details">
                                <div><i class="fa fa-angle-left"></i>
                                </div>
                              </div>
                              <span class=" time">few sec ago</span>
                            </div>
                            <div class="more-details">
                              <div class="more-details-inner">
                                <h5 class="semi-bold fs-16">âAppleâs Motivation - Innovation <br> 
                                                            distinguishes between <br>
                                                            A leader and a follower.â</h5>
                                <p class="small hint-text">
                                  Commented on john Smiths wall.
                                  <br> via pages framework.
                                </p>
                              </div>
                            </div>
                          </div>
                          <!-- END Notification Item-->
                          <!-- START Notification Item Right Side-->
                          <div class="option" data-toggle="tooltip" data-placement="left" title="mark as read">
                            <a href="#" class="mark"></a>
                          </div>
                          <!-- END Notification Item Right Side-->
                        </div>
                        <!-- START Notification Body-->
                        <!-- START Notification Item-->
                        <div class="notification-item  clearfix">
                          <div class="heading">
                            <a href="#" class="text-danger">
                              <i class="fa fa-exclamation-triangle m-r-10"></i>
                              <span class="bold">98% Server Load</span>
                              <span class="fs-12 m-l-10">Take Action</span>
                            </a>
                            <span class="pull-right time">2 mins ago</span>
                          </div>
                          <!-- START Notification Item Right Side-->
                          <div class="option">
                            <a href="#" class="mark"></a>
                          </div>
                          <!-- END Notification Item Right Side-->
                        </div>
                        <!-- END Notification Item-->
                        <!-- START Notification Item-->
                        <div class="notification-item  clearfix">
                          <div class="heading">
                            <a href="#" class="text-warning-dark">
                              <i class="fa fa-exclamation-triangle m-r-10"></i>
                              <span class="bold">Warning Notification</span>
                              <span class="fs-12 m-l-10">Buy Now</span>
                            </a>
                            <span class="pull-right time">yesterday</span>
                          </div>
                          <!-- START Notification Item Right Side-->
                          <div class="option">
                            <a href="#" class="mark"></a>
                          </div>
                          <!-- END Notification Item Right Side-->
                        </div>
                        <!-- END Notification Item-->
                        <!-- START Notification Item-->
                        <div class="notification-item unread clearfix">
                          <div class="heading">
                            <div class="thumbnail-wrapper d24 circular b-white m-r-5 b-a b-white m-t-10 m-r-10">
                              <img width="30" height="30" data-src-retina="assets/img/profiles/1x.jpg" data-src="assets/img/profiles/1.jpg" alt="" src="assets/img/profiles/1.jpg">
                            </div>
                            <a href="#" class="text-complete">
                              <span class="bold">Revox Design Labs</span>
                              <span class="fs-12 m-l-10">Owners</span>
                            </a>
                            <span class="pull-right time">11:00pm</span>
                          </div>
                          <!-- START Notification Item Right Side-->
                          <div class="option" data-toggle="tooltip" data-placement="left" title="mark as read">
                            <a href="#" class="mark"></a>
                          </div>
                          <!-- END Notification Item Right Side-->
                        </div>
                        <!-- END Notification Item-->
                      </div>
                      <!-- END Notification Body-->
                      <!-- START Notification Footer-->
                      <div class="notification-footer text-center">
                        <a href="#" class="">Read all notifications</a>
                        <a data-toggle="refresh" class="portlet-refresh text-black pull-right" href="#">
                          <i class="pg-refresh_new"></i>
                        </a>
                      </div>
                      <!-- START Notification Footer-->
                    </div>
                    <!-- END Notification -->
                  </div>
                  <!-- END Notification Dropdown -->
                </div>
              </li>
              <li class="p-r-15 inline">
                <a href="#" class="icon-set clip "></a>
              </li>
              <li class="p-r-15 inline">
                <a href="#" class="icon-set grid-box"></a>
              </li>
            </ul>
            <!-- END NOTIFICATIONS LIST -->
            <a href="#" class="search-link" data-toggle="search"><i class="pg-search"></i>Type anywhere to <span class="bold">search</span></a> </div>
        </div>
        <div class=" pull-right">
          <div class="header-inner">
           <a href="<c:url value="/logout.eab" />" class="clearfix">
                    <span class="pull-left">Logout</span>
                    <span class="pull-right"><i class="pg-power"></i></span>
          </div>
        </div>
        <div class=" pull-right">
          <!-- START User Info-->
          <div class="visible-lg visible-md m-t-10">
         
            <div class="dropdown pull-right">              
              <ul class="dropdown-menu profile-dropdown" role="menu">
                <li><a href="#"><i class="pg-settings_small"></i> Settings</a>
                </li>
                <li><a href="#"><i class="pg-outdent"></i> Feedback</a>
                </li>
                <li><a href="#"><i class="pg-signals"></i> Help</a>
                </li>
                <li class="bg-master-lighter">
                  <a href="#" class="clearfix">
                    <span class="pull-left">Logout</span>
                    <span class="pull-right"><i class="pg-power"></i></span>
                  </a>
                </li>
              </ul>
            </div>
          </div>
          <!-- END User Info-->
        </div>
      </div>
      
      <!-- END HEADER -->
      <!-- START PAGE CONTENT WRAPPER -->
      <div class="page-content-wrapper">
        <!-- MODAL STICK UP  -->
        <div class="modal fade stick-up" id="addNewAppModal" tabindex="-1" role="dialog" aria-labelledby="addNewAppModal" aria-hidden="true"  >
     
          <div class="modal-dialog">          
            <div class="modal-content">
              <div class="modal-header clearfix ">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="pg-close fs-14"></i></button>
                <h4 class="p-b-5"><span class="semi-bold">New</span> Task</h4>
              </div>             
              <div class="modal-body">              
                <p class="small-text">Create a new Task</p>
                <form role="form">
	                  <div class="row">   
		                  <div class="col-sm-6">
				                <div class="radio radio-success">
				                      <input type="radio" value="Y" checked="checked" name="addType"  id="yes"  onClick="clickRadio();" >
				                      <label for="yes">add WR#</label>
				                      <input type="radio"  value="N" name="addType" id="no"  onClick="clickRadio();" >
				                      <label for="no">add Project</label>
				                    </div>
		                    </div>
	                </div>
                   <div class="row" id="addTaskRow">
                    <div class="col-sm-3">
                      <div class="form-group ">                      
                         <label class="">Project &nbsp;:</label>                     
                         </div>
                    </div>                   
                    <div class="col-sm-6">
                      <div class="form-group form-group-default">                      
                         <label class="">Project</label>                   
                        <select class="full-width" data-placeholder="Select Task Grp" data-init-plugin="select1"  id="inProject" name="inProject">
                          <option value="">All</option>                          
                          </optgroup>                          
                           <c:forEach items="${proList}" var="inPro">                                 
                                <option value="${inPro.projectCode}">${inPro.projectName}</option>                               
                           </c:forEach> 
                        </select>                  
                      </div>
                    </div>
                  </div>
                    <div class="row" id="addTaskRow1"> 
                    <div class="col-sm-3">
                      <div class="form-group ">                      
                         <label class="">WR# &nbsp;:</label>                     
                         </div>
                    </div>
                    <div class="col-sm-6">
                      <div class="form-group form-group-default">
                        <label>WR#</label>                  
                       <input id="inWrNumber" name='inWrNumber' type="text" class="form-control" placeholder="you Task Code">
                      </div>
                    </div>                 
                  </div>   
                    <div class="row"  id="addTaskRow2"> 
                    <div class="col-sm-3">
                      <div class="form-group ">                      
                         <label class="">Time Scope &nbsp;:</label>                     
                         </div>
                    </div>
                    <div class="col-sm-9">  
                     <div class="input-daterange input-group" id="datepicker-range" style="z-index:1100">  
                      <input name=""inStart"" id="inStart" class="input-sm form-control" type="text">
                      <span class="input-group-addon">to</span>
                      <input name="inEnd" id="inEnd" class="input-sm form-control" type="text">
                    </div>  
                   </div>
                   </div>                
                   <div class="row" id="addGrpRow"> 
                    <div class="col-sm-6">
                      <div class="form-group form-group-default">
                        <label>Project</label>                  
                       <input id="inProjectName" name='inProjectName' type="text" class="form-control" placeholder="you Task Grp">
                      </div>
                    </div>                 
                  </div>   
                </form>
              </div>
              <div class="modal-footer">
                <button id="add-Taskcode" type="button" class="btn btn-primary  btn-cons">Add</button>
                <button type="button" id="taskClose" class="btn btn-cons">Close</button>
              </div>
            </div>
            <!-- /.modal-content -->
          </div>
          <!-- /.modal-dialog -->
        </div>
        <!-- END MODAL STICK UP  -->
        
        
        <!-- START PAGE CONTENT -->
        <div class="content">
          <!-- START JUMBOTRON -->
          <div class="jumbotron" data-pages="parallax">
            <div class="container-fluid container-fixed-lg sm-p-l-20 sm-p-r-20">
              <div class="inner">
                <!-- START BREADCRUMB -->
                <ul class="breadcrumb">
                  <li>
                    <a href="#">Timesheet</a>
                  </li>
                  <li><a href="#" class="active">Manage Timesheet</a>
                  </li>
                </ul>
                <!-- END BREADCRUMB -->
               </div>
            </div>
          </div>
          <!-- END JUMBOTRON -->
      <div class="container-fluid container-fixed-lg">
            <!-- START PANEL -->
            <div class="panel panel-transparent">
              <div class="panel-heading">                
              <form:form  role="form" action="manageTask.eab" method="post"  id="searchForm">
                   <div class="row">                  
                    <div class="col-sm-3">
                      <div class="form-group form-group-default">
                        <label>Project</label>                 
                        <select class="full-width" data-placeholder="Select task Grp" data-init-plugin="select1"  id="projectCode" name="projectCode">
                         <option value="">All</option> 
                          <c:forEach items="${proList}" var="indPro">
                                <option value="${indPro.projectCode}" ${indPro.projectCode==task.projectCode?'selected':''} >${indPro.projectName}</option>
                           </c:forEach> 
                        </select>                       
                        
                      </div>
                    </div>                
                  </div>              
                </form:form>
               
              </div>
             
            </div>
            <!-- END PANEL -->
          </div>
          <!-- START CONTAINER FLUID -->
          <div class="container-fluid container-fixed-lg">
            <!-- START PANEL -->
            <div class="panel panel-transparent">
              <div class="panel-heading">
                <div class="panel-title">Table with Dynamic Rows
                </div>
                <div class="pull-right">
                  <div class="col-xs-12">
                   <button id="search" class="btn btn-primary btn-cons" onclick="search();">Search</button>
                   <button id="show-modal" class="btn btn-primary btn-cons"><i class="fa fa-plus"></i> Add row</button>
                    <button id="search" class="btn btn-primary btn-cons" onclick="del_task();">del</button>
                  </div>
                </div>
                <div class="clearfix"></div>
              </div>
              <div class="panel-body">
                <table class="table table-hover demo-table-dynamic" id="tableWithDynamicRows">
                  <thead>
                    <tr>
                      <th>last updDate</th>
                      <th>Project</th>                                  
                      <th>WR#</th>
                      <th>Time Scope</th>
                       <th> &nbsp;</th>
                    </tr>
                  </thead>
                  <tbody>
                      <c:forEach items="${grpList}" var="curGrp">
                         <c:forEach items="${curGrp.tasks}" var="curTask">
                       <tr>
                       <td class="v-align-middle">
                        <p><spring:eval expression="curTask.updDate"></spring:eval></p>
                      </td>
                       <td class="v-align-middle">
                       <p>${curGrp.projectName}</p>
                      </td>
                      <td class="v-align-middle">
                    	<p>${curTask.taskName}</p>
                      </td> 
                      <td class="v-align-middle">
                    	<p>
                    	<spring:eval expression="curTask.startDate"></spring:eval>
                    	 - <spring:eval expression="curTask.endDate"></spring:eval></p>
                      </td>
                       <td class="v-align-middle">                        
                       	<p>                    
                       	<INPUT  type="checkbox" name="delTaskCode" id="delTaskCode" value ="${curTask.taskCode}" />
                         </p>
                       </td>
                       </tr>
                    	  </c:forEach>
                      </c:forEach>
                  </tbody>
                </table>
              </div>
            </div>
            <!-- END PANEL -->
          </div>
          <!-- END CONTAINER FLUID -->
          <!-- START CONTAINER FLUID -->
          
          <!-- END CONTAINER FLUID -->
        </div>
        <!-- END PAGE CONTENT -->
        <!-- START COPYRIGHT -->
        <!-- START CONTAINER FLUID -->
        <div class="container-fluid container-fixed-lg footer">
          <div class="copyright sm-text-center">
            <p class="small no-margin pull-left sm-pull-reset">
              <span class="hint-text">Copyright Â© 2014 </span>
              <span class="font-montserrat">REVOX</span>.
              <span class="hint-text">All rights reserved. </span>
              <span class="sm-block"><a href="#" class="m-l-10 m-r-10">Terms of use</a> | <a href="#" class="m-l-10">Privacy Policy</a></span>
            </p>
            <p class="small no-margin pull-right sm-pull-reset">
              <a href="#">Hand-crafted</a> <span class="hint-text">&amp; Made with Love Â®</span>
            </p>
            <div class="clearfix"></div>
          </div>
        </div>
        <!-- END COPYRIGHT -->
      </div>
      <!-- END PAGE CONTENT WRAPPER -->
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
    
     <script src="<c:url value="web/css/assets/plugins/jquery-datatable/media/js/jquery.dataTables.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="web/css/assets/plugins/jquery-datatable/extensions/TableTools/js/dataTables.tableTools.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="web/css/assets/plugins/jquery-datatable/extensions/Bootstrap/jquery-datatable-bootstrap.js"/>" type="text/javascript"></script>
    <script type="text/javascript" src="<c:url value="web/css/assets/plugins/datatables-responsive/js/datatables.responsive.js"/>"></script>
    <script type="text/javascript" src="<c:url value="web/css/assets/plugins/datatables-responsive/js/lodash.min.js"/>"></script>
    <!-- 日历相关的JS -->
    <script src="<c:url value="web/css/assets/plugins/bootstrap-daterangepicker/daterangepicker.js"/>"></script>
    <script src="<c:url value="web/css/assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"/>" type="text/javascript"></script>
    
    <!-- END VENDOR JS -->
    <!-- BEGIN CORE TEMPLATE JS -->
    <script src="<c:url value="web/images/js/pages.min.js"/>"></script>
    <!-- END CORE TEMPLATE JS -->
    <!-- BEGIN PAGE LEVEL JS -->
        <script src="<c:url value="web/css/assets/js/datatables_manageTimesheet.js"/>" type="text/javascript"></script>
    <script src="<c:url value="web/css/assets/js/scripts.js"/>" type="text/javascript"></script>
    <!-- END PAGE LEVEL JS -->
    
  
       </body>
</html>