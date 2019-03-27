//==========获取系统时间==========
var newDate = '';
getLangDate();
function getLangDate() {
    //表示当前系统时间的Date对象
    var dateObj = new Date();
    //当前系统时间的完整年份值
    var year = dateObj.getFullYear();
    //当前系统时间的月份值
    var month = dateObj.getMonth() + 1;
    //当前系统时间的月份中的日
    var date = dateObj.getDate();
    //当前系统时间中的星期值
    var day = dateObj.getDay();
    var weeks = ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"];
    //根据星期值,从数组中获取对应的星期字符串
    var week = weeks[day];
    //当前系统时间的小时值
    var hour = dateObj.getHours();
    //当前系统时间的分钟值
    var minute = dateObj.getMinutes();
    //当前系统时间的秒钟值
    var second = dateObj.getSeconds();
    //当前时间属于上午、晚上还是下午
    var timeValue = "" + ((hour >= 12) ? (hour >= 18) ? "晚上" : "下午" : "上午");
    newDate = dateFilter(year) + "年" + dateFilter(month) + "月" + dateFilter(date) + "日 " + " " + dateFilter(hour) + ":" + dateFilter(minute) + ":" + dateFilter(second);
    document.getElementById("nowTime").innerHTML = "<span style='color: black;'>" + timeValue + "好,当前时间为: " + newDate + " " + week + "</span>";
    setTimeout("getLangDate()", 1000);
}
//值小于10时,在前面补0
function dateFilter(date) {
    if (date < 10) {
        return "0" + date;
    }
    return date;
}
//==========获取系统时间==========
layui.use(['form','element','layer','jquery'],function(){
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		element = layui.element,
		$ = layui.jquery;

	//上次登录时间(此处应该从接口获取,实际使用中请自行更换)
    $(".loginTime").html(newDate.split("日")[0] + "日</br>" + newDate.split("日")[1]);
    //icon动画
    $(".panel a").hover(function () {
        $(this).find(".layui-anim").addClass("layui-anim-scaleSpring");
    }, function () {
        $(this).find(".layui-anim").removeClass("layui-anim-scaleSpring");
    });
    $(".panel a").click(function () {
        parent.addTab($(this));
    });

	//数字格式化
	$(".panel span").each(function(){
		$(this).html($(this).text()>9999 ? ($(this).text()/10000).toFixed(2) + "<em>万</em>" : $(this).text());
	});

});
