/* BEGIN VENDOR JS */
document.write('<script src="web/css/assets/plugins/pace/pace.min.js" type="text/javascript"></script>');
document.write('<script src="web/css/assets/plugins/jquery/jquery-1.11.1.min.js" type="text/javascript"></script>');
document.write('<script src="web/css/assets/plugins/modernizr.custom.js" type="text/javascript"></script>');
document.write('<script src="web/css/assets/plugins/jquery-ui/jquery-ui.min.js" type="text/javascript"></script>');
document.write('<script src="web/css/assets/plugins/boostrapv3/js/bootstrap.min.js" type="text/javascript"></script>');
document.write('<script src="web/css/assets/plugins/jquery/jquery-easy.js" type="text/javascript"></script>');
document.write('<script src="web/css/assets/plugins/jquery-unveil/jquery.unveil.min.js" type="text/javascript"></script>');
document.write('<script src="web/css/assets/plugins/jquery-bez/jquery.bez.min.js"></script>');
document.write('<script src="web/css/assets/plugins/jquery-ios-list/jquery.ioslist.min.js" type="text/javascript"></script>');
document.write('<script src="web/css/assets/plugins/imagesloaded/imagesloaded.pkgd.min.js"></script>');
document.write('<script src="web/css/assets/plugins/jquery-actual/jquery.actual.min.js"></script>');
document.write('<script src="web/css/assets/plugins/jquery-scrollbar/jquery.scrollbar.min.js"></script>');
/* END VENDOR JS */
/* BEGIN CORE TEMPLATE JS */
/*document.write('<script src="pages/js/pages.js" type="text/javascript"></script>');*/
/* END CORE TEMPLATE JS */
/* BEGIN PAGE LEVEL JS */
/*document.write('<script src="assets/js/scripts.js" type="text/javascript"></script>');*/
/* END PAGE LEVEL JS */

function LoadAjaxContent(url,obj){
	//$('.preloader').show();
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