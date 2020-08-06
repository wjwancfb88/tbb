var baselocation = "http://61.153.182.58:3395";
function showDetail() {
	$.ajax({
		url : baselocation + '/reservations/detail.do',
		type : 'post',
		success : function(result) {
		}
	});
}
