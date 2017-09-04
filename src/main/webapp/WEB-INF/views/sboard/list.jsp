<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../include/header.jsp"%>
<script>
	var result = "${msg}";

	if (result == "success") {
		alert("처리가 완료되었습니다.");
	}
	
	$(document).ready(function() {
		
		$("#searchBtn").on("click", function(event) {
			
			self.location = "list" + 
			"${pageMaker.makeQuery(1)}" + 
			"&searchType=" + $("select option:selected").val() +
			"&keyword=" + encodeURIComponent($("#keywordInput").val());
		});
		
		$("#newBtn").on("click", function(evt) {
			self.location = "register";
		});
	});
</script>
<style>
	.pagination > li.active > a {background: blue; color: #fff;}
</style>
<!-- left column -->
<div class="col-md-12">
	<!-- general form elements -->
	
	<!-- search -->
	<div class="box">
		<div class="box-header with-border">
			<h3 class="box-title">Board List</h3>
		</div>
		<div class="box-body">
			<select name="searchType">
				<option value="n" <c:out value="${cri.searchType == null? 'selected':''}"/> >---</option>
				<option value="t" <c:out value="${cri.searchType == 't'? 'selected':''}"/> >Title</option>
				<option value="c" <c:out value="${cri.searchType == 'c'? 'selected':''}"/> >Content</option>
				<option value="w" <c:out value="${cri.searchType == 'w'? 'selected':''}"/> >Writer</option>
				<option value="tc" <c:out value="${cri.searchType == 'tc'? 'selected':''}"/> >Title OR Content</option>
				<option value="cw" <c:out value="${cri.searchType == 'cw'? 'selected':''}"/> >Content OR Writer</option>
				<option value="tcw" <c:out value="${cri.searchType == 'tcw'? 'selected':''}"/> >Title OR Content OR Writer</option>
			</select>
			
			<input type="text" name="keyword" id="keywordInput" value="${cri.keyword }">
			<button id="searchBtn">Search</button>
			<button id="newBtn">New Board</button>
		</div>
	</div>
	
	
	<div class="box">
		<div class="box-header with-border">
			<h3 class="box-title">LIST PAGING</h3>
		</div>
		<div class="box-body">
			<table class="table table-bordered">
				<tr>
					<th style="width: 10px">BNO</th>
					<th>TITLE</th>
					<th>WRITER</th>
					<th>REGDATE</th>
					<th style="width: 40px">VIEWCNT</th>
				</tr>

				<c:forEach items="${list}" var="boardVO">
					<tr>
						<td>${boardVO.bno}</td>
						<td><a href="/sboard/readPage${pageMaker.makeSearch(pageMaker.cri.page)}&bno=${boardVO.bno}">${boardVO.title}</a></td>
						<td>${boardVO.writer}</td>
						<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
								value="${boardVO.regdate}" /></td>
						<td><span class="badge bg-red">${boardVO.viewcnt}</span></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>
<!-- /.col (left) -->

<div class="text-center">
	<ul class="pagination">

		<c:if test="${pageMaker.prev}">
			<li><a href="list${pageMaker.makeSearch(pageMaker.startPage - 1)}">&laquo;</a></li>
		</c:if>

		<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}"
			var="idx">
			<li
				<c:out value="${pageMaker.cri.page == idx? 'class = active':''}" />> 
				<a href="list${pageMaker.makeSearch(idx)}">${idx}</a>
			</li>
		</c:forEach>

		<c:if test="${pageMaker.next && pageMaker.endPage > 0 }">
			<li><a href="list${pageMaker.makeSearch(pageMaker.endPage + 1)}">&raquo;</a>
			</li>
		</c:if>
	</ul>
</div>

<%@ include file="../include/footer.jsp"%>