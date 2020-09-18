<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="../includes/header.jsp" %>
    
 <div class="row">
 	<div class="col-lg-12">
 		<h1 class="page-header">글 수정</h1>
 	</div>
 	<!-- /.col-lg-12 -->
 </div>   
  <!-- /.row -->
  
 <!-- 본문  -->
 <div class="row">
  <div class="col-lg-12">
  		<div class="panel panel-default">
  			<div class="panel-heading"> 글 수정하기	</div>
  			<!-- /. panel-heading -->
  		
  			<div class="panel-body">
  			<form role="form" action="/board/modify" method="post">
  				<input type="hidden" name="pageNum" value='<c:out value="${cri.pageNum }"/>'>		
  				<input type="hidden" name="amount" value='<c:out value="${cri.amount }"/>'>		
  				<input type="hidden" name="type" value='<c:out value="${cri.type }"/>'>		
  				<input type="hidden" name="keyword" value='<c:out value="${cri.keyword }"/>'>		
  				
  				
  				
  				<div class="form-group">
  					<label>글 번호</label>
  					<input class="form-control" name="bno" value='<c:out value="${board.bno}"/>' readonly>
  				</div>
  				
  				<div class="form-group">
  					<label>글 제목</label>
  					<input class="form-control" name="title" value='<c:out value="${board.title}"/>' >
  				</div>
  				
  				<div class="form-group">
  					<label>글 내용</label>
  					<textarea rows="3" class="form-control"  name='content' ><c:out value="${board.content}"/></textarea>
  				</div>
  				
  				
  				<div class="form-group">
  					<label>작성자</label>
  					<input class="form-control" name="writer" value='<c:out value="${board.writer}"/>' readonly >
  				</div>
  				
  				<div class="form-group">
  					<label>등록일자</label>
  					<input class="form-control" name="regdate" value='<fmt:formatDate value="${board.regdate}" pattern="yyyy/MM/dd"/>'  readonly >
  				</div>
  				
  				<div class="form-group">
  					<label>수정일자</label>
  					<input class="form-control" name="updateDate" value='<fmt:formatDate value="${board.updateDate}" pattern="yyyy/MM/dd"/>'  readonly >
  				</div>
                                        
  				<!-- 수정,삭제,리스트가기 버튼  -->
  				
  				<button type="submit" data-oper='modify' class="btn btn-default">수정</button>
  				<button type="submit" data-oper='remove' class="btn btn-danger">삭제</button>
  				<button type="submit" data-oper='list' class="btn btn-info">글목록</button>
  				
  				</form>

  			</div>
  			<!-- /.panel-body -->
  		</div>
  		<!-- /. panel  -->
  </div>
  <!-- /.col-lg-12 -->
 </div>
 <!-- /.row -->
 
 <!-- submit버튼 클릭시 처리 자바스크립트 -->
<script>
$(document).ready(function(){
	var formObj=$("form");// form객체 얻기
	
     //submit 버튼 클릭시 처리
	$('button').on('click',function(e){
			e.preventDefault();//기본동작(submit은 페이지 이동)을 중지,

			var operation=$(this).data("oper");// data-oper값 얻기
			if(operation==='remove'){
				//삭제 버튼 클릭시 처리
				formObj.attr("action","/board/remove");

				//alert('remove');
				}else if(operation==='list'){
				formObj.attr("action","/board/list").attr("method","get");
				//alert('list');
				
				//
				
				var pageNumTag=$("input[name='pageNum']").clone();//객체 복제
				var amountTag=$("input[name='amount']").clone();
				var keywordTag=$("input[name='keyword']").clone();
				var typeTag=$("input[name='type']").clone();

				formObj.empty();//초기화 

				//동적으로 생성된 form에 input tag 추가
				formObj.append(pageNumTag);  
				formObj.append(amountTag);
				formObj.append(keywordTag);
				formObj.append(typeTag);
				//<form action='/board/list' method='post'>
					//<input type="hidden" name="pageNum" value='<c:out value="${cri.pageNum }"/>'>		
  				   //<input type="hidden" name="amount" value='<c:out value="${cri.amount }"/>'>		
  				   //<input type="hidden" name="type" value='<c:out value="${cri.type }"/>'>		
  				   //<input type="hidden" name="keyword" value='<c:out value="${cri.keyword }"/>'>		
				   //</form>
				}
             // alert('modify');
		     formObj.submit();//url로 이동처리
		});
});



</script>  
<%@ include file="../includes/footer.jsp" %>    









