<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- <form id="pagerForm" action="hiUserList.action">
	<input type="hidden" name="pageInfo.currentPage" value="${pageInfo.currentPage}" />
</form> -->

<script>

</script>


<div class="pageHeader">
	<form id="pagerForm" action="/cuser/search" method="post" rel="user"
		onsubmit="return navTabSearch(this)">
		<input type="hidden" name="pageNum" value="${pager.pageNumber }" />
		<!--【必须】value=1可以写死-->

		<input type="hidden" name="numPerPage" value="${pager.pageSize }" />


		<div class="searchBar">
			<table class="searchContent" width="950px">
				<tr>
				<td width="160px"><label>昵称</label> <input type="text" size="15" name="sysCuser.nickname" style="margin-left:-50px"
					value="${sysCuser.nickname }" /></td>
				<td width="150px"><label>真实姓名</label> <input type="text" size="10" name="sysCuser.real_name" style="margin-left:-30px"
					value="${sysCuser.real_name }" /></td>
				<td width="130px"><label>手机号</label> <input type="text" size="10" name="sysCuser.phone" style="margin-left:-45px"
					value="${sysCuser.phone }" /></td>
				<td width="210px"><label>身份证号</label> <input type="text" size="20" name="sysCuser.id_num" style="margin-left:-30px"
					value="${sysCuser.id_num }" /></td>
				<td width="230px">
					<label>状态</label> 
					<select name="enable" id="state" style="margin-left:-50px">
						<option value="">查看全部</option>
						<option value="0">禁用</option>
						<option value="1">可用</option>
					</select>
					<script type="text/javascript">
						$("#state").val(${enable});
					</script>
				</td>
				<td>
				<div class="buttonActive" style="margin-left:-80px">
					<div class="buttonContent">
						<button type="submit">查询</button>
					</div>
				</div>
				</td>
				<td>
				<div class="buttonActive" style="margin-left:-40px">
					<div class="buttonContent">

						<button type="button" onclick="resetSearch(this)">重置</button>
				</div>
				</div>
				</td>
			</tr>
				</table>
		</div>
	</form>
</div>
<div class="pageContent" selector="h1" layoutH="42">
	<div id="userBaseDiv" class="unitBox">
		<!-- <div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="/user/edit?id=-1" target="dialog"
					rel="user"><span>新建</span></a></li>
			</ul>
		</div> -->
		<table class="table" width="101.5%" layoutH="91"
			targetType="${targetType}">
			<thead>
				<tr>
					<th width="22"><input type="checkbox" group="ids"
						class="checkboxCtrl"></th>
					<th>昵称</th>
					<th width="110px">用户头像</th>
					<th width="80px">真实姓名</th>
					<th width="90px">手机号</th>
					<th width="150px">身份证号</th>
					<th width="150px">注册时间</th>
					<th width="200px">邮箱</th>
					<th width="90px">推荐人</th>
					<th width="80px">推荐人数</th>
					<th width="50px">状态</th>
					<th width="90">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${cusers}" varStatus="user">
					<tr>
						<td><input name="ids" value="${item.id}" type="checkbox"></td>
						<td>${item.nickname}</td>

						<td><a
							href="javascript:void(0);" title="图片显示"  onclick="getRealImg(${item.id},${user.count})"> 
							<img id="${user.count}"
								src="${url_st}${item.user_pic}"
								style="width: 20px; height: 20px; vertical-align: middle;">点击查看大图
						</a></td>
						<td>${item.real_name}</td>
						<td>${item.phone}</td>
						<td>${item.id_num}</td>
						<td>${item.reg_time}</td>
						<td>${item.email}</td>
						<td>${item.referrer}</td>
						<td>${item.recommend}</td>
						<td><c:choose>
								<c:when test="${item.enable == 1 }">可用</c:when>
								<c:when test="${item.enable == 0 }">禁用</c:when>
							</c:choose></td>
						<td>
							<c:choose>
								<c:when test="${item.enable == 1 }">
									<a class="btnDel"
										href="/cuser/disable?id=${item.id}&navTabId=cuser"
										target="ajaxTodo" title="确定禁用吗？">禁用</a>
								</c:when>
								<c:when test="${item.enable == 0 }">
									<a class="btnDel"
										href="/cuser/enable?id=${item.id}&navTabId=cuser"
										target="ajaxTodo" title="确定启用吗？">启用</a>
								</c:when>
							</c:choose>
							<%-- <a class="btnDel"
							href="/user/delete?id=${item.id}&navTabId=user"
							target="ajaxTodo" title="删除">删除</a>  --%>
							<a class="btnView"
							href="/cuser/view?id=${item.id}" target="dialog" rel="user" height="400"
							title="查看">查看</a> </td>
							<%-- <a class="btnEdit"
							href="/user/edit?id=${item.id}" target="dialog" rel="user"
							title="编辑">编辑</a></td> --%>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<div class="panelBar">
			<div class="pages">
				<span>显示</span> <select class="combox" name="numPerPage"
					onchange="navTabPageBreak({numPerPage:this.value})">
					<option value="15">15</option>
					<option value="30">30</option>
					<option value="50">50</option>
					<option value="100">100</option>
				</select> <span>条，共${pager.totalRow}条</span>
			</div>
		<script>	
	        $("select[name='numPerPage']").val('${pager.pageSize}');	    
		 </script>
			<div class="pagination" targetType="navTab"
				totalRow="${pager.totalRow }" numPerPage="${pager.pageSize }"
				pageNumShown="1" currentPage="${pager.pageNumber }"></div>
		</div>
	</div>
</div>

<script>
function getRealImg(itemId,id) {
    var real_width,real_height,
    _im         = document.getElementById(id),
    im          = document.createElement('img');
    im.src      = _im.src,
    real_width  = im.width,
    real_height = im.height;
    if(real_height>800){
    	real_height=800;
    }
    $.pdialog.open('/cuser/show/'+itemId, 'goodPic', '图片', {'width':real_width,'height':real_height+50});
}
</script>