﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="keys" content="">
<meta name="author" content="">
<%@include file="/WEB-INF/includes/css-file.jsp"%>
<link rel="stylesheet" href="${ctp}/css/login.css">
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<div>
					<a class="navbar-brand" href="${ctp}/index.html"
						style="font-size: 32px;">尚筹网-创意产品众筹平台</a>
				</div>
			</div>
		</div>
	</nav>

	<div class="container">

		<form class="form-signin" role="form" id="loginFrom" action="${ctp}/permission/user/login">
			<h2 class="form-signin-heading">
				<i class="glyphicon glyphicon-log-in"></i> 用户登录
			</h2>
			<div class="form-group has-success has-feedback">
				<input type="text" class="form-control" id="loginacct_input" name="loginacct"
				   value="${errorUser.loginacct}"placeholder="请输入登录账号" autofocus>
				<span class="glyphicon glyphicon-user form-control-feedback"></span>
				<span style="color: red">${loginError}</span>
				<%--移除狱中的数据--%>
				<c:remove var="loginError" scope="session"/>
				<c:remove var="loginUser"/>
			</div>
			<div class="form-group has-success has-feedback">
				<input type="password" class="form-control" id="userpswd_input" name="userpswd"
					placeholder="请输入登录密码" style="margin-top: 10px;"> <span
					class="glyphicon glyphicon-lock form-control-feedback"></span>
			</div>
			<div class="form-group has-success has-feedback">
				<select class="form-control">
					<option value="member">会员</option>
					<option value="manager">管理</option>
				</select>
			</div>
			<div class="checkbox">
				<label> <input type="checkbox" value="remember-me">
					记住我
				</label> <br> <label> 忘记密码 </label> <label style="float: right">
					<a href="${ctp}/reg.jsp">我要注册</a>
				</label>
			</div>
			<a class="btn btn-lg btn-success btn-block" onclick="dologin()">
				登录</a>
		</form>
	</div>

	<%@include file="/WEB-INF/includes/js-file.jsp"%>

	<script>
		function dologin() {
			var type = $(":selected").val();
			if (type == "manager") {
				$("#loginFrom").submit()
				/*window.location.href = "main.html";*/
			} else {
				window.location.href = "${ctp}/index.jsp";
			}
			return false;
		}
	</script>
</body>
</html>