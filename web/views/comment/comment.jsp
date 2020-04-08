<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.empty.comment.model.vo.Comment,java.util.List"%>

<%
	List<Comment> commentList = (List)request.getAttribute("commentList");
%>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/storeComment/storeComment.css?ver=0" type="text/css">


<!-- 댓글 -->
<div id="comment">
	<th>댓글</th>
	</tr>
	<tr>
		<form id="insertForm" action="<%=request.getContextPath()%>/comment/storeCommentInsert" method="post"> 
			<td>
				<input type="text" id="commentInput" name="userComment" />
			</td>
			<td>
				<input type="hidden" name="commentNo" id="commentNo" />
			</td>
			<td>
				<button type="submit" id="commentBtn" name="commentBtn" >등록</button>
			</td>
			<td>
				<input type="hidden" name="commentWriter"  id="commentWriter" value="<%=loginMember != null ? loginMember.getUserId() : ""%>" />
			<td>
				<input type="hidden" name="commentLevel" id="commentLevel" value="1" /> 
				<input type="hidden" name="commentRef" id="commentRef" value="0" />
	</tr>
	</form>
</div>
<script>
//로그인 안할시 댓글작성방지
 	$(function(){                                
		$("#commentInput").focus(function(){
			if(<%=loginMember == null%>){
				alert("로그인 후 이용하세요");
				$("#userId").focus();
			}
		});
	})
</script>


<table class="userTable">
	<%
			if (commentList != null && !commentList.isEmpty()) {
				for (Comment c : commentList) {
					if (c.getCommentLevel() == 1) {
	%>
	<tr>
		<sub><%=c.getCommentWriter()%></sub>
		<div style="text-align: right;">
			<sub><%=c.getCommentDate()%></sub>
		</div>
		<%=c.getUserComment()%>
	</tr>
	<%if (loginMember!=null&&loginMember.getUserId().equals("ooze")) {%>
	<button class="btn123" onclick="reB()">답글</button>
	<%} %>
</table>
<div id="my-dialog">
	<form id="updateForm" action="<%=request.getContextPath()%>/comment/storeCommentUpdateEnd" method="post">
		<table>
			<tr>
				<td>
					<input type="hidden" id="commNo" name="commentNo" value="<%=c.getCommentNo() %>" />
				</td>
				<td>
					<input type="text" id="userComment" name="userComment" value="<%=c.getUserComment()%>" 
					style="width: 200px"/>
				</td>
				<td>
					<input type="submit" class="btn123" value="수정" />
				</td>
			</tr>
		</table>
	</form>
</div>

<div class="reBtn" style="text-align: right;">
	<%if (loginMember!=null&&loginMember.getUserId().equals("ooze")) {%>
	<button id="updateBtn"  class="btn123"onclick="fnModify(this,<%=c.getCommentNo()%>)">수정</button>
	</br>
	<button id="deleteBtn" class="btn123" onclick="deleteBtn(<%=c.getCommentNo()%>)">삭제</button>
	<%} %>
</div>

		<%
			} else {
		%>
<table class="storeTable">		
	<tr>
		<tr>
		<sub><%=c.getCommentWriter()%></sub>
		<div style="text-align: right;">
			<sub><%=c.getCommentDate()%></sub>
		</div>
		<%=c.getUserComment()%>
		</tr>
	</tr>

	<%
		}	
	} //for
} //if
	%>
</table>

<script>
//대댓글 



//댓글 
	$(function(){
		$("#commentBtn").keyup(function(e){
		  
		})
	})

//댓글수정모달 
function fnModify(e,no){
	const target=$(e).parent().prev();
	console.log(target);
	$('#commNo').val(no);
		 $(target).toggle();
		 
}
//댓글삭제
function deleteBtn(no){
	location.replace("<%=request.getContextPath()%>/comment/storeCommentDelete?no="+no);	
}

//ajax

//댓글리스트 함수호출 

	<%--  $(document).ready(function(){
		commentList();
	}); 	

	function commentList(){
		$.ajax({
			type="POST",
			url:"<%=request.getContextPath()%>/storeView",
			success: function(data){
				$("#div33").html(data);
			},
			error:function(){
				alert("통신실패");
			}
				
		})
	} --%>
<%-- 	//댓글쓰기 
	function enrollBtn(){
		var formData = $('#insertForm').serialize();
		$.ajax({
			type: 'POST',
			url:"<%=request.getContextPath()%>/comment/storeCommentInsert",
			data: formData,
			success:function(data){
				console.log(data);
				 alert('댓글등록성공.');
				 $('#commNo').val('');
				 $('#commentInput').val('');
				 $('#commentWriter').val('');
				 $('#commentLevel').val('');
				 $('#commentRef').val('');
				 commentList();
			},
			error: function(){
				alert('통신실패');
			}
		})
	}
	//댓글수정
	function fnModify(){
			var formData= $('#updateForm').serialize();
			$.ajax({
				type: "POST",
				url:"<%=request.getContextPath()%>/comment/storeCommentUpdateEnd",
				data: formData,
				success:function(data){
					alert('댓글수정성공!!!');
					commentList();
				},
				erro: function(){
					alert("통신실패");
				}
			})
		}
	}
	
	//댓글수정
	function deleteBtn(){
			var formData= $('').serialize();
			$.ajax({
				type: "POST",
				url:"<%=request.getContextPath()%>/comment/storeCommentDelete",
				data: formData,
				success:function(data){
					alert('댓글삭제성공!!!');
					commentList();
				},
				erro: function(){
					alert("통신실패");
				}
			})
		}
	} --%>
	
</script>



<!-- 댓글 -->

<!--  뎃글 페이징  -->
<%-- 
<div id='pageBar'>
	<%=request.getAttribute("pageBar")%>
</div> --%>

<!--  뎃글 페이징  -->

<script>


</script>




<!-- 댓글 -->
