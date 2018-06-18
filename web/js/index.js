// 一些用于判断的变量



$(document).ready(function(){
	//初始化
	manage_init();
});


// 页面初始化
var manage_init = function(){
	// 初始化顶部导航栏
	initNav();
};

// 初始化顶部导航栏
var initNav = function(){
	$(".nav-head-img").click(function(){
		$(".nav-self-info").toggle();
	});
};
