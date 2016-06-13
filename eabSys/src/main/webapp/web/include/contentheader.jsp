<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <link href="<c:url value="web/css/all.css"/>" rel="stylesheet" type="text/css" />
 

<script src="<c:url value="web/css/assets/plugins/pace/pace.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="web/css/assets/plugins/jquery/jquery-1.11.1.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="web/css/assets/plugins/modernizr.custom.js"/>" type="text/javascript"></script>
<script src="<c:url value="web/css/assets/plugins/jquery-ui/jquery-ui.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="web/css/assets/plugins/boostrapv3/js/bootstrap.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="web/css/assets/plugins/jquery/jquery-easy.js"/>" type="text/javascript"></script>
<script src="<c:url value="web/css/assets/plugins/jquery-unveil/jquery.unveil.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="web/css/assets/plugins/jquery-bez/jquery.bez.min.js"/>"></script>
<script src="<c:url value="web/css/assets/plugins/jquery-ios-list/jquery.ioslist.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="web/css/assets/plugins/imagesloaded/imagesloaded.pkgd.min.js"/>"></script>
<script src="<c:url value="web/css/assets/plugins/jquery-actual/jquery.actual.min.js"/>"></script>
<script src="<c:url value="web/css/assets/plugins/jquery-scrollbar/jquery.scrollbar.min.js"/>"></script>

<script type="text/javascript">

function LoadAjaxContent(url,obj){
	//$('.preloader').show();
	alert(url);
	$.ajax({
		mimeType: 'text/html; charset=utf-8', // ! Need set mimeType only when run from local file
		url: url,
		type: 'GET',
		success: function(data) {
			obj.html(data);
			//$('.preloader').hide();
		},
		error: function (jqXHR, textStatus, errorThrown) {
			alert(errorThrown);
		},
		dataType: "html",
		async: false
	});
}

</script>