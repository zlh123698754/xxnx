<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 2023/12/3
  Time: 8:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="css/index.css" />
    <link rel="icon" href="img/1.gif">
    <script type="text/javascript" src="js/jquery-3.7.1.js"></script>
  <script src="https://webapi.amap.com/maps?v=2.0&key=85fbe120a0a731904bf777fcc2eca9e8&plugin=AMap.Autocomplete"></script>
</head>
<body>

<div class="container">
  <div class="header">

    <H2 id="aa"></H2>
    <div style="display:none" id="w">
      欢迎${user.user_name}
    </div>
 </div>

  <div class="form">

      <div class="form-group">
        <H3 style="color: #707070">周、月报</H3><br>
        <label class="form-label" for="message1">实习工作具体情况及实习任务完成情况</label>
        <textarea class="form-input"  id="message1" name="shixi"  placeholder="为空则使用通用模板(200字左右)" ></textarea>
      </div>
      <div class="form-group">
        <label class="form-label" for="message2">主要收获及工作成绩</label>
        <textarea class="form-input" id="message2" name="shixi2"  placeholder="为空则使用通用模板(150字左右)" ></textarea>
      </div>
      <div class="form-group">
        <label class="form-label" for="message3">工作中的问题及需要老师的指导帮助</label>
        <textarea class="form-input" id="message3" name="shixi3"  placeholder="为空则使用通用模板(150字左右)" ></textarea>
      </div>

      <H3 style="color: #707070">签到</H3><br>
      <div id="container" class="map" style="width:100%;
    height: 400px;"></div>
      <div class="form-group">
        <h4>左击获取经纬度：</h4>
        <div class="form-group">
          <input class="form-input"  type="text" readonly="true" id="lnglat" name="jingweidu" style="border:none">
        </div>
   </div>
      <div class="form-group">
        <label class="form-label" for="message4">公司名称</label>
        <textarea class="form-input" id="message4" name="gsname"  placeholder="默认为——奈雪的茶" ></textarea>
      </div>
      <div class="form-group">
        <label class="form-label" for="message5">地址名称</label>
        <textarea class="form-input" id="message5" name="dzname"  placeholder="默认为——宁夏回族自治区银川金凤区万达广场115号" ></textarea>
      </div>

      <div class="form-group">
        <button class="form-button" type="submit" id="post-btn">提交</button>
      </div>
  </div>
</div>
</body>

<script>



  var index=0;
  var word =$('#w').text();
  console.log(word);
  function type(){
    $('#aa').text(word.substring(0,index++));//从0开始逐一加入，开始打出来
  }
  setInterval(type,100);//定时器

  function makeExpandingArea(el) {
    var timer = null;
    //由于ie8有溢出堆栈问题，故调整了这里
    var setStyle = function(el, auto) {
      if (auto) el.style.height = 'auto';
      el.style.height = el.scrollHeight + 'px';
    }
    var delayedResize = function(el) {
      if (timer) {
        clearTimeout(timer);
        timer = null;
      }
      timer = setTimeout(function() {
        setStyle(el)
      }, 200);
    }
    if (el.addEventListener) {
      el.addEventListener('input', function() {
        setStyle(el, 1);
      }, false);
      setStyle(el)
    } else if (el.attachEvent) {
      el.attachEvent('onpropertychange', function() {
        setStyle(el)
      })
      setStyle(el)
    }
    if (window.VBArray && window.addEventListener) { //IE9
      el.attachEvent("onkeydown", function() {
        var key = window.event.keyCode;
        if (key == 8 || key == 46) delayedResize(el);

      });
      el.attachEvent("oncut", function() {
        delayedResize(el);
      }); //处理粘贴
    }
  }

  var textarea1 = document.getElementById('message1');
  var textarea2 = document.getElementById('message2');
  var textarea3 = document.getElementById('message3');
  makeExpandingArea(textarea1);
  makeExpandingArea(textarea2);
  makeExpandingArea(textarea3);


  var map = new AMap.Map("container", {
    zoom: 11,
    resizeEnable: true,
      center: [106.244123,38.486927]
  })
  //为地图注册click事件获取鼠标点击出的经纬度坐标
  map.on('click', function(e) {
    document.getElementById("lnglat").value = e.lnglat.getLng() + ',' + e.lnglat.getLat()
  });

  var userid =  ${user.user_id};



      $('#post-btn').click(function(){
          $.post("index",{
                  m1:$('#message1').val(),
                  m2:$('#message2').val(),
                  m3:$('#message3').val(),
                  jinwei:$('#lnglat').val(),
                  gsname:$('#message4').val(),
                  dzname:$('#message5').val(),
                  user_id:${user.user_id}
              },
             );
          alert("您已经提交成功");
      });

</script>
</html>
