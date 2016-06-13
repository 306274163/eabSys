/* ============================================================
 * DataTables
 * Generate advanced tables with sorting, export options using
 * jQuery DataTables plugin
 * For DEMO purposes only. Extract what you need.
 * ============================================================ */
(function($) {

    'use strict';

    var responsiveHelper = undefined;
    var breakpointDefinition = {
        tablet: 1024,
        phone: 480
    };
    var initDispRowCnt =10; //每页显示行数 XH2016-05-30 添加

    // Initialize datatable showing a search box at the top right corner
    var initTableWithSearch = function() {
        var table = $('#tableWithSearch');

        var settings = {
            "sDom": "<'table-responsive't><'row'<p i>>",
            "sPaginationType": "bootstrap",
            "destroy": true,
            "scrollCollapse": true,
            "oLanguage": {
                "sLengthMenu": "_MENU_ ",
                "sInfo": "Showing <b>_START_ to _END_</b> of _TOTAL_ entries"
            },
            "iDisplayLength": initDispRowCnt  //2016-05-30 XHchange
        };

        table.dataTable(settings);

        // search box for table
        $('#search-table').keyup(function() {
            table.fnFilter($(this).val());
        });
    }

    // Initialize datatable with ability to add rows dynamically
    var initTableWithDynamicRows = function() {
        var table = $('#tableWithDynamicRows');
        var settings = {
            "sDom": "<'table-responsive't><'row'<p i>>",
            "sPaginationType": "bootstrap",
            "destroy": true,
            "scrollCollapse": true,
            "oLanguage": {
                "sLengthMenu": "_MENU_ ",
                "sInfo": "Showing <b>_START_ to _END_</b> of _TOTAL_ entries"
            },
            "iDisplayLength": initDispRowCnt  //2016-05-30 XHchange
        };


        table.dataTable(settings);

        $('#show-modal').click(function() {        
            $('#addNewAppModal').modal('show');     
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
        });
        
        $('#add-Taskcode').click(function() {   
        	if($('input[name="addType"]:checked').val()=='Y'){    
        		  //2016-05-30 XHchange      
		        	  
		        	var v_inProject = $("#inProject").val();
		        	var v_inProjectText = $("#inProject").find("option:selected").text();
		        	if(v_inProject==''){
		        		alert('Project is required.');
		        		return ;
		        	}
		            var inWrNumber = $("#inWrNumber").val();
		            if(inWrNumber==''){
		            	alert('you wr number is required.');
		        		return ;
		        	}  
		        	var v_inStart = $("#inStart").val();
		        	if(v_inStart==''){
		        		alert('Start Date is required.');
		        		return ;
		        	}
		        	var v_inEnd = $("#inEnd").val();
		        	if(v_inEnd==''){
		        		alert('End Date is required.');
		        		return ;
		        	}		        	
		        	var timeScope = v_inStart +"-"+v_inEnd;
		            $.ajax({  
		                url: "addTaskCode.eab",
		                data: {
		                	projectCode: v_inProject,
		                	taskName:inWrNumber,
		                	startDate:v_inStart,
		                	endDate:v_inEnd,
		                	taskStatus:'A'
		                	},
		                type:'post',
		                dataType: "json",
		                cache:false,
		                success: function (data) {
		                    if (data.result=='1') {                    	
		                    	table.dataTable().fnAddData([
		                    	 data.lastUpDate,
		                    	 v_inProjectText,
		                         inWrNumber,   
		                         timeScope,
		                         '<INPUT  type="checkbox" name="delTaskCode" id="delTaskCode"  value ="'+data.seq+'" />'
		                         ]);
		                        $('#addNewAppModal').modal('hide');
		                        $("#inTaskGrp").val("");
		                        $("#inWrNumber").val("");                                            
		                        
		                    }
		                },
		                error: function () {
		                    alert("error！");
		                }
		            });  
        	}else{
        		var v_inProjectName = $("#inProjectName").val();	        
	        	if(v_inProjectName==''){
	        		alert('Project name is required.');
	        		return ;
	        	}	          
	            $.ajax({  
	                url: "addProject.eab",
	                data: {
	                	projectName: v_inProjectName,	                	
	                	projectStatus:'A'
	                	},
	                type:'post',
	                dataType: "json",
	                cache:false,
	                success: function (data) {
	                    if (data.result=='1') { 
	                    	jQuery("#inProject").empty();	                   
	                      	jQuery("#projectCode").empty();
	                      	jQuery("#inProject").append("<option value=''>ALL</option>");     
                    		jQuery("#projectCode").append("<option value=''>ALL</option>");  
	                    	$.each(data.grp, function(i, item) {  
	                    		jQuery("#inProject").append("<option value='"+item.projectCode+"'>"+item.projectName+"</option>");     
	                    		jQuery("#projectCode").append("<option value='"+item.projectCode+"'>"+item.projectName+"</option>");            
	                    	 });

	                        $('#addNewAppModal').modal('hide');
	                        $("#inProjectName").val("");
	                                                             
	                        
	                    }
	                },
	                error: function () {
	                    alert("error！");
	                }
	            });
        	}
            
            
            
         });  
        $('#taskClose').click(function() {   
            $('#addNewAppModal').modal('hide');

        });
    }

    // Initialize datatable showing export options
    var initTableWithExportOptions = function() {
        var table = $('#tableWithExportOptions');


        var settings = {
            "sDom": "<'exportOptions'T><'table-responsive't><'row'<p i>>",
            "sPaginationType": "bootstrap",
            "destroy": true,
            "scrollCollapse": true,
            "oLanguage": {
                "sLengthMenu": "_MENU_ ",
                "sInfo": "Showing <b>_START_ to _END_</b> of _TOTAL_ entries"
            },
            "iDisplayLength": initDispRowCnt, //2016-05-30 XHchange
            "oTableTools": {
                "sSwfPath": "assets/plugins/jquery-datatable/extensions/TableTools/swf/copy_csv_xls_pdf.swf",
                "aButtons": [{
                    "sExtends": "csv",
                    "sButtonText": "<i class='pg-grid'></i>",
                }, {
                    "sExtends": "xls",
                    "sButtonText": "<i class='fa fa-file-excel-o'></i>",
                }, {
                    "sExtends": "pdf",
                    "sButtonText": "<i class='fa fa-file-pdf-o'></i>",
                }, {
                    "sExtends": "copy",
                    "sButtonText": "<i class='fa fa-copy'></i>",
                }]
            },
            fnDrawCallback: function(oSettings) {
                $('.export-options-container').append($('.exportOptions'));

                $('#ToolTables_tableWithExportOptions_0').tooltip({
                    title: 'Export as CSV',
                    container: 'body'
                });

                $('#ToolTables_tableWithExportOptions_1').tooltip({
                    title: 'Export as Excel',
                    container: 'body'
                });

                $('#ToolTables_tableWithExportOptions_2').tooltip({
                    title: 'Export as PDF',
                    container: 'body'
                });

                $('#ToolTables_tableWithExportOptions_3').tooltip({
                    title: 'Copy data',
                    container: 'body'
                });
            }
        };


        table.dataTable(settings);

    }

    initTableWithSearch();
    initTableWithDynamicRows();
    initTableWithExportOptions();

})(window.jQuery);
 