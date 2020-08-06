	
	mui.init({
		gestureConfig:{
			tap: true,
			doubletap: true,
			longtap: true,
			hold: true,
			drag: true,
			swipe: true,
			release: true
		}
	});
 function DealDanger(id){
	 window.location.href="../danger/dealDanger?id="+id;
	 console.log(id);
	 //$.ajax({
		// type: 'POST',
		// url: "../danger/dealDanger",
		// data:{id:id},
		// success: function (result) {
     //
     //
		// },
     //
	 //});





 }