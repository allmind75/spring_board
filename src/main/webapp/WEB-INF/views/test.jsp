<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	#modDiv {width: 300px; height: 100px; background: gray; position: absolute; display: none;
	top: 50%; left: 50%; margin-top: -50px; margin-left: -150px; padding: 10px; z-index: 1000;
	}
</style>
</head>
<body>
	<h2>Ajax Test Page</h2>
	
	<div>
		<div>
			REPLYER <input type="text" name="replyer" id="newReplyWriter">
		</div>
		<div>
			REPLY TEXT	<input type="text" name="replytext" id="newReplyText">
		</div>
		<button id="replyAddBtn">ADD REPLY</button>
	</div>
	
	
	<ul id="replies">
	</ul>
	
	<ul class="pagination">
	</ul>
	
	<div id="modDiv">
		<div class="modal-title"></div>
		<div>
			<input type="text" id="replytext">
		</div>
		<div>
			<button type="button" id="replyModBtn">Modify</button>
			<button type="button" id="replyDelBtn">DELETE</button>
			<button type="button" id="closeBtn">CLOSE</button>
		</div>
	</div>
	
	
	<!-- jQuert 2.1.4 -->
	<script src="/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
	<script>
		var bno = 6127;
		var replyPage = 1;
		
		
		//댓글 list 출력
		function getAllList() {
			//json 형식으로 데이터 받기
			$.getJSON("/replies/all/" + bno, function(data) {
				console.log(data.length);
				
				var str = "";
				
				$(data).each(
					function() {
						str += "<li data-rno='" + this.rno + "' class='replyLi'>" +
						this.rno + ":" + this.replytext +
						"<button>MOD</button></li>"
					});
				
				$("#replies").html(str);
			});
		}
		
		//댓글 페이지 처리
		function getPageList(page) {
			
			$.getJSON("/replies/" + bno + "/" + page, function(data) {
				console.log(data.list.length);
				
				var str = "";
				
				$(data.list).each(function() {
					str += "<li data-rno='" + this.rno + "' class='replyLi'>" +
					this.rno + ":" + this.replytext +
					"<button>MOD</button></li>";
				});
				
				$("#replies").html(str);
				
				printPaging(data.pageMaker);
			});
		}
		
		function printPaging(pageMaker) {
			
			var str = "";
			
			if(pageMaker.prev) {
				str += "<li><a href='" + (pageMaker.startPage-1) + "'> << </a></li>";
			}
			
			for(var i=pageMaker.startPage, len = pageMaker.endPage; i <= len; i++) {
				
				var strClass = pageMaker.cri.page == i? 'class=active' : '';
				str += "<li " + strClass + "><a href='" + i + "'>" + i + "</a></li>";
			}
			
			if(pageMaker.next) {
				str += "<li><a href='" + (pageMaker.endPage + 1) + "'> >> </a></li>";
			}
			
			$(".pagination").html(str);
		}
		
		//댓글 등록, JSON.stringify() : JSON 데이터를 구성
		$("#replyAddBtn").on("click", function() {
				
			var replyer = $("#newReplyWriter").val();
			var replytext = $("#newReplyText").val();
			
			$.ajax({
				type: "post",
				url : "/replies",
				headers: {
					"Content-Type": "application/json",
					"X-HTTP-Method-Override": "POST"
				},
				dataType: "text",
				data: JSON.stringify({
					bno: bno,
					replyer: replyer,
					replytext: replytext
				}),
				success: function(result) {
					
					if(result == "SUCCESS") {
						alert("댓글 등록 완료");
						//getAllList();
						getPageList(1);
					}
				}
				
			});
		});
		
		//댓글 수정 버튼 replyLi button에 이벤트 위임(delegation)
		$("#replies").on("click", ".replyLi button", function() {
			
			var reply = $(this).parent();
			var rno = reply.attr("data-rno");	//댓글 번호 가져옴
			var replytext = reply.text();		//댓글 내용 가져옴
			
			$(".modal-title").html(rno);
			$("#replytext").val(replytext);
			$("#modDiv").show("slow");
		});
		
		//댓글 삭제 버튼
		$("#replyDelBtn").on("click", function() {
			
			var rno = $(".modal-title").html();
			var replytext = $("#replytext").val();
			
			$.ajax({
				type: "delete",
				url: "/replies/" + rno,
				headers: {
					"Content-Type": "application/json",
					"X-HTTP-Method-Override": "DELETE"
				},
				dataType: "text",
				success: function(result) {
					console.log("result: " + result);
					if(result == "SUCCESS") {
						alert("댓글 삭제 완료");
						$("#modDiv").hide("slow");
						getPageList(replyPage);
					}
				}
			});
		});
			
		$("#replyModBtn").on("click", function() {
			
			var rno = $(".modal-title").html();
			var replytext = $("#replytext").val();
			
			$.ajax({
				type: "put",
				url: "/replies/" + rno,
				headers: {
					"Content-Type": "application/json",
					"X-HTTP-Method-Override": "PUT"
				},
				data: JSON.stringify({replytext:replytext}),
				dataType:"text",
				success: function(result) {
					console.log("result: " + result);
					if(result == "SUCCESS") {
						alert("댓글 수정 완료");
						$("#modDiv").hide("slow");
						//getAllList();
						getPageList(replyPage);
					}
				}
			});
		});
		
		//페이지 링크 처리
		$(".pagination").on("click", "li a", function(event){
					
			event.preventDefault();
			
			replyPage = $(this).attr("href");
			
			getPageList(replyPage);
		});
		
	</script>
</body>
</html>