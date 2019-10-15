<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="UTF-8">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
    <%@include file="/WEB-INF/includes/css-file.jsp"%>
	<link rel="stylesheet" href="${ctp}/css/login.css">
	<style>

	</style>
  </head>
  <body>
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <div><a class="navbar-brand" href="index.html" style="font-size:32px;">尚筹网-创意产品众筹平台</a></div>
        </div>
      </div>
    </nav>

    <div class="container">

      <form id="regFrom" method="post" action="${ctp}/permission/user/reg" class="form-signin" role="form">
        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-log-in"></i> 用户注册</h2>
		  <div class="form-group has-success has-feedback">
			<input type="text" class="form-control" id="loginacct_input"  name="loginacct"
                   placeholder="请输入登录账号" value="${TUser.loginacct}" autofocus>
			<span class="glyphicon glyphicon-user form-control-feedback"></span>
              <%--回显session域中的错误的信息--%>
              <span class="errorinfo" style="color: red"></span>
              <span style="color: red"> ${regError} </span>
              <%--默认移除所有狱中的值--%>
              <%--scope="session" 这个表示移除session狱中的值--%>
              <c:remove var="regError"   ></c:remove>
		  </div>
		  <div class="form-group has-success has-feedback">
			<input type="password" class="form-control" id="userpswd_input" name="userpswd"
                   placeholder="请输入登录密码"  style="margin-top:10px;">
			<span class="glyphicon glyphicon-lock form-control-feedback"></span>
              <span class="errorinfo" style="color: red"></span>
		  </div>
		  <div class="form-group has-success has-feedback">
			<input type="text" class="form-control" id="email_input" name="email"
                   placeholder="请输入邮箱地址" value="${TUser.email}" style="margin-top:10px;">
			<span class="glyphicon glyphicon glyphicon-envelope form-control-feedback"></span>
              <span class="errorinfo" style="color: red"></span>
		  </div>
		  <div class="form-group has-success has-feedback">
			<select class="form-control" >
                <option>会员</option>
                <option <%--value="1"--%>>管理</option>
            </select>
		  </div>
        <div class="checkbox">
          <label>
            忘记密码
          </label>
          <label style="float:right">
            <a href="${ctp}/login.jsp">我有账号</a>
          </label>
        </div>
        <a class="btn btn-lg btn-success btn-block" id="submitBtn" > 注册</a>
      </form>
    </div><%--引入标签库--%>
  <%@include file="/WEB-INF/includes/js-file.jsp"%>
  <script type="text/javascript">
      //给校验器设置一些策略
      $.validator.setDefaults({
          showErrors:function(map, list) {
              /*java控制台打印list,map的属性的值*/
              //console.log(map);
              //console.log(list);
              //将每个元素自己的错误信息显示在自己的图标下
              //先清除所有错误；清状态
              $(".errorinfo").empty();
              $(".form-group").removeClass("has-success has-error has-warning")

              $.each(list,function(){
                  //当前发生错误的详细信息；
                  //element当前错误元素
                  //错误信息
                  $(this.element).nextAll(".errorinfo").text(this.message);
                  //改变input的状态；
                  $(this.element).parent("div.form-group").addClass("has-error")


              })
          }
      });
      /*$(".class类的名字") 表示类选择器*/
      $("#submitBtn").click(function () {

          /*$("select.form-control")表示获取下拉列表的值,下拉列表的值默认为下拉列表的名称*/
          var select = $("select.form-control").val();
          if(select=="管理"){
              $("#regFrom  ").submit();
          }else {
              alert("对不起这个功能还没有开启");
          }
          /*禁用掉默认行为*/
          return false;
      });

      /*框架验证regFrom表单/原理看
      * G:\黑马视频\02、【【店主高度推荐】】ssm互联网贷款借贷平台项目实战\maven、svn等\scw\尚筹网\框架\前端\jquery-validation-1.13.1\demo
      * demo网页的源代码
      * */
      $("#regFrom").validate({
          /*验证的插件框架的验证方法*/
          rules:{
              loginacct:{
                  required:true,
                  minlength:6,
                  maxlength:12,
              },
              userpswd:{
                  required:true,
                  minlength:6,
                  maxlength:12,
              },
              email:{
                  required:true,
                  email:true
              }
          },
          /*显示的出错的字体*/
          messages : {
              loginacct: {
                  required: "输入的账号你不可以为空!",
                  minlength: "你输入的账号长度必须是6个字符以上!",
                  maxlength: "你输入的账号长度必须是12个字符以下!"
              },
              userpswd:{
                  required:"你输入的密码不可以为空!",
                  minlength: "你输入的密码长度必须是6个字符以上!",
                  maxlength: "你输入的密码长度必须是12个字符以下!"
              },
              email:{
                  required:"你输入的邮箱不可以为空!",
                  email:"情输入正确的邮箱的地址!"
              }
          }
      });

  </script>
  </body>
</html>