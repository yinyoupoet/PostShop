$(document).ready(function(){
	// 点击图片可以放大
	$('.good-img').zoomify({ duration:500});

	//初始化spinner（选择商品数量）
	spinner_init();

	// 商品数量合法性检查
	checkAmount();

});


// 商品数量合法性检查
var checkAmount = function(){
	console.log("进行了检查");
	var select = Number($('.select-amount').val());
	var stock = Number($('.good-stock').html());
	var warning = $('.warning');
	console.log(stock + " " + select);
	if(select > stock){
		warning.show();
		$('#add-to-shop-cart').attr("disabled",true);
	}else{
		warning.hide();
		$('#add-to-shop-cart').attr("disabled",false);
	}
	
}



//初始化spinner（选择商品数量）
var spinner_init = function() {
	$("#spinner")
	.spinner('delay', 200) //delay in ms
    .spinner('changed', function(e, newVal, oldVal) {
	   // trigger lazed, depend on delay option.
	   checkAmount();
	 })
	.spinner('changing', function(e, newVal, oldVal) {
	   // trigger immediately
        console.log("changing check");
        checkAmount();
	});
}
