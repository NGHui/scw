<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="UTF-8">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<%@include file="/WEB-INF/includes/css-file.jsp"%>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	table tbody tr:nth-child(odd){background:#F4F4F4;}
	table tbody td:nth-child(even){color:#C00;}
	</style>
  </head>

  <body>
  <%
	  /*填写Java代码的地方*/
	  /*设置导航条的内容*/
	  pageContext.setAttribute("navinfo","用户维护");
	  pageContext.setAttribute("curUrl","permission/user/list");
  %>
  <%--引入导航条--%>
  <%@include file="/WEB-INF/includes/nav-bar.jsp"%>

    <div class="container-fluid">
      <div class="row">
		  <%--引入菜单--%>
			  <%@include file="/WEB-INF/includes/user_menu.jsp"%>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<div class="panel panel-default">
			  <div class="panel-heading">
				<h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
			  </div>
			  <div class="panel-body">
<form class="form-inline" role="form" style="float:left;">
  <div class="form-group has-feedback">
    <div class="input-group">
      <div class="input-group-addon">查询条件</div>
      <input class="form-control has-success" type="text" placeholder="请输入查询条件">
    </div>
  </div>
  <button type="button" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
</form>
<button type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
<button type="button" class="btn btn-primary" style="float:right;" onclick="window.location.href='add.html'"><i class="glyphicon glyphicon-plus"></i> 新增</button>
<br>
 <hr style="clear:both;">
          <div class="table-responsive">
            <table class="table  table-bordered">
              <thead>
                <tr >
                  <th width="30">#</th>
				  <th width="30"><input type="checkbox"></th>
                  <th>账号</th>
                  <th>名称</th>
                  <th>邮箱地址</th>
                  <th width="100">操作</th>
                </tr>
              </thead>
              <tbody>
			  <%--拿到遍历的集合--%>
			  <c:forEach items="${user_info.list}" var="user">
                <tr>
					<td>${user.id}</td>
					<td><input type="checkbox"></td>
					<td>${user.loginacct}</td>
					<td>${user.username}</td>
					<td>${user.email}</td>
					<td>
						<button type="button" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>
						<button type="button" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>
						<button type="button" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>
					</td>
				</tr>
			  </c:forEach>
              </tbody>
			  <tfoot>
			     <tr >
				     <td colspan="6" align="center">
						<ul class="pagination">
							<li><a href="${ctp}/permission/user/list?pn=1">首页</a></li>
							<c:if test="${user_info.hasPreviousPage}">
								<%--prePage单词含义是前一页--%>
								<li><a href="${ctp}/permission/user/list?pn=${user_info.prePage}">上一页</a></li>
							</c:if>

							<!-- 遍历连续显示的页面 navigatepageNums : int[]-->
							<c:forEach items="${user_info.navigatepageNums}" var="pn">
								<c:if test="${pn == user_info.pageNum }">
									<li class="active"><a href="${ctp}/permission/user/list?pn=${pn}">${pn } <span
											class="sr-only">(current)</span></a></li>
								</c:if>
								<c:if test="${pn != user_info.pageNum }">
									<li><a href="${ctp}/permission/user/list?pn=${pn}">${pn }</a></li>
								</c:if>
							</c:forEach>
							<c:if test="${user_info.hasNextPage}">
								<li><a href="${ctp}/permission/user/list?pn=${user_info.nextPage}">下一页</a></li>
							</c:if>
							<li><a href="${ctp}/permission/user/list?pn=${user_info.pages}">尾页</a></li>
							 </ul>
					 </td>
				 </tr>
			  </tfoot>
            </table>
          </div>
			  </div>
			</div>
        </div>
      </div>
    </div>
  
        <%@include file="/WEB-INF/includes/js-file.jsp"%>
        <!-- 动态显示页面高亮效果的js -->
        <%@include file="/WEB-INF/includes/common-js.jsp" %>
        <script type="text/javascript">
            $(function () {
			    $(".list-group-item").click(function(){
				    if ( $(this).find("ul") ) {
						$(this).toggleClass("tree-closed");
						if ( $(this).hasClass("tree-closed") ) {
							$("ul", this).hide("fast");
						} else {
							$("ul", this).show("fast");
						}
					}
				});
            });
            $("tbody .btn-success").click(function(){
                window.location.href = "assignRole.html";
            });
            $("tbody .btn-primary").click(function(){
                window.location.href = "edit.html";
            });


        </script>
  </body>
</html>
