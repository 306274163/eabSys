<<script type="text/javascript">
<!--

//-->
function closeDownLoad(){ 
    $('#downLoadTimesheetDiv').modal('hide');

}
function downLoadTimeSheet(){ 
	  
	var v_dlTsStart =$("#dlTsStart").val();
	var v_dlTsEnd = $("#dlTsEnd").val();
    var v_dlProjec =$("#dlProjec").val();   
    var v_dlWrNumber= $("#dlWrNumber").val();  
    var v_dlStaffName=$("#dlStaffName").val();     
    
    
    var url = 'dowloadTime.eab';
     document.getElementById("downLoadForm").target="downLoad";
	 document.getElementById("downLoadForm").action=url;
	 document.getElementById("downLoadForm").submit();

	 document.getElementById("downLoadForm").target='_self'; 			
	 $('#downLoadTimesheetDiv').modal('hide');          

}
</script>
<div class="modal fade stick-up" id="downLoadTimesheetDiv" tabindex="-1" role="dialog" aria-labelledby="downLoadTimesheetDiv" aria-hidden="true"  >
     
          <div class="modal-dialog">          
            <div class="modal-content">
              <div class="modal-header clearfix ">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="pg-close fs-14"></i></button>
                <h4 class="p-b-5"><span class="semi-bold">DownLoad</span> TimeSheet</h4>
              </div>             
              <div class="modal-body">              
                <p class="small-text">Search Conditions</p>
                <form role="form" action="dowloadTime.eab"  id="downLoadForm" method="POST">               
                   <div class="row">
                    <div class="col-sm-3">
                      <div class="form-group ">                      
                         <label class="">Task Range &nbsp;:</label>                     
                         </div>
                    </div>
                    <div class="col-sm-9">  
                     <div class="input-daterange input-group" id="datepicker-range" style="z-index:1100">  
                      <input name="dlTsStart" id="dlTsStart" class="input-sm form-control" type="text">
                      <span class="input-group-addon">to</span>
                      <input name="dlTsEnd" id="dlTsEnd" class="input-sm form-control" type="text">
                    </div>  
                   </div>
                   </div>
                   <div class="row">
                     <div class="col-sm-3">
                      <div class="form-group ">                      
                         <label class="">Project &nbsp;:</label>                     
                         </div>
                    </div>
                    <div class="col-sm-9">
                      <div class="form-group form-group-default">                      
                         <label class="">Project</label>
                        <select class="full-width" data-placeholder="dowProject" data-init-plugin="select1"  id="dlProjec" name="dlProjec" onChange="updWrDown(3);">
                               <option value="" ></option>
                              <c:forEach items="${sysProject}" var="indPro">
                                  <option value="${indPro.projectCode}">${indPro.projectName}</option>
                              </c:forEach>   
                        </select>  
                      </div>
                    </div>
                  </div>
                  <div class="row">
                     <div class="col-sm-3">
                      <div class="form-group ">                      
                         <label class="">WR# &nbsp;:</label>                     
                         </div>
                    </div>
                    <div class="col-sm-9">
                       <div class="form-group form-group-default">
                        <label>WR#</label>                   
                          <select class="full-width" data-placeholder="you wr number" data-init-plugin="select1"  id="dlWrNumber" name="dlWrNumber">
                           <option value="" ></option>
                          <c:forEach items="${sysTaskGrp}" var="indGrp">
                                   <optgroup label="${indGrp.projectName}">
                                       <c:forEach items="${indGrp.tasks}" var="indTask">
                                      		 <option value="${indTask.taskCode}">${indTask.taskName}</option>
                                       </c:forEach>
                                   </optgroup>                                 
                           </c:forEach> 
                          </select>
                      </div>
                    </div>
                  </div>                  
                    <div class="row">
                    <div class="col-sm-3">
                     <div class="form-group ">         
                      <label>Staff Name# &nbsp;:</label>
                     </div>           
                    </div>
                    <div class="col-sm-9">
                      <div class="form-group form-group-default">
                        <label>Staff Name</label>
                        <input id="dlStaffName" type="text" class="form-control" placeholder="you name">
                      </div>
                    </div>
                  </div>
                  
                </form>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-primary  btn-cons" onclick="downLoadTimeSheet();">downLoad</button>
                <button type="button" id="close" class="btn btn-cons" onclick="closeDownLoad();">Close</button>
              </div>
            </div>
            <!-- /.modal-content -->
          </div>
          <!-- /.modal-dialog -->
        </div>
        