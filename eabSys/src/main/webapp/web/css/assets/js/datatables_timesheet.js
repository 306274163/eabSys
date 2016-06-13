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
        });
        
        $('#add-app').click(function() {    //2016-05-30 XHchange      
        	var v_inTaskDate = $("#inTaskDate").val();
        	if(v_inTaskDate==''){
        		alert('Date is required.');
        		return ;
        	}
            var v_inProjec = $("#inProjec").val();
            if(v_inProjec==''){
        		alert('Project is required.');
        		return ;
        	}
            var v_inProjecText =$("#inProjec").find("option:selected").text();
            var v_inWrNumber=  $("#inWrNumber").val();
            var v_inWrNumberText = $("#inWrNumber").find("option:selected").text();
            var v_inProjec = $("#inProjec").val();
            if(v_inWrNumber==''){
        		alert('you wr number is required.');
        		return ;
        	}
            var v_inStaffName=  $("#inStaffName").val();
            if(v_inStaffName==''){
        		alert('staff Name is required.');
        		return ;
        	}
            var v_inHourse=  $("#inHourse").val();
            if(v_inHourse==''){
        		alert('Task hourse is required.');
        		return ;
        	}
            var v_inStage=  $("#inStage").val();
            var v_inStageText = $("#inStage").find("option:selected").text();
            var v_inComment =   $("#inComment").val()  ;  
            $.ajax({
                url: "addTimesheet.eab",
                data: {
                	task_date: v_inTaskDate,
                	project:v_inProjec,
                	wrNumber:v_inWrNumber,
                	staffName:v_inStaffName,
                	taskHours:v_inHourse,
                	stage:v_inStage,
                	taskComment:v_inComment
                	},
                type:'post',
                dataType: "json",
                cache:false,
                success: function (data) {
                    if (data.result=='1') {                    	
                    	table.dataTable().fnAddData([
                         $("#inTaskDate").val(),
                         v_inProjecText,
                         v_inWrNumberText,
                         $("#inStaffName").val(),
                         $("#inHourse").val(),
                         v_inStageText, 
                         $("#inComment").val(),
                         '<input type="hidden" name="delStaffName" id="delStaffName" value = "'+ $("#inStaffName").val()+'"/><INPUT  type="checkbox" name="delTimeSheet" value ="'+data.seq+'" />'
                         ]);
                        $('#addNewAppModal').modal('hide');
                       // $("#inTaskDate").val("");
                        $("#inProjec").val("");
                        $("#inWrNumber").val("");
                       // $("#inStaffName").val("");
                        $("#inHourse").val("");
                        $("#inStage").val("");
                        $("#inComment").val("")  ;                          
                        
                    }
                },
                error: function () {
                    alert("error！");
                }
            });            
            
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
 