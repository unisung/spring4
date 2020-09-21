<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<%@include file="../includes/header.jsp"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">글목록</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				글 목록 페이지
				<button id='regBtn' type="button" class="btn btn-xs pull-right">새글 등록</button>
			</div>

			<!-- /.panel-heading -->
			<div class="panel-body">
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>#번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>조회수</th>
							<th>좋아요/싫어요</th>
							<th>작성일</th>
							<th>수정일</th>
						</tr>
					</thead>

					<c:forEach items="${list}" var="board">
						<tr>
							<td><c:out value="${board.bno}" /></td>
							<%--<td><a href='/board/get?bno=<c:out value="${board.bno}"/>'><c:out value="${board.title}"/></a></td>--%>
                             <%-- 자바스크립트로 get으로 이동 --%>
							<td><a class='move' href='<c:out value="${board.bno}"/>'>	<c:out value="${board.title}" />
							<b>[<c:out value="${board.replyCnt}"></c:out>]</b></a></td>

							<td><c:out value="${board.writer}" /></td>
							<td><c:out value="${board.readCount}"/></td>
							<td>
                             <button class="btn btn-danger btn-circle"  onclick="updateGoodCnt(this)" data-bno='<c:out value="${board.bno }"/>'>
                                <i class="fa fa-heart"><c:out value="${board.good }"/></i>
                              </button>
                              <button class="btn btn-warning btn-circle" onclick="updateBadCnt(this)"  data-bno='<c:out value="${board.bno }"/>'>
                                <i class="fa fa-times"><c:out value="${board.bad }"/></i>
                               </button>
						   </td>
							<td><fmt:formatDate pattern="yyyy-MM-dd"
									value="${board.regdate}" /></td>
							<td><fmt:formatDate pattern="yyyy-MM-dd"
									value="${board.updateDate}" /></td>
						</tr>
					</c:forEach>
				</table>

				<div class='row'>
					<div class="col-lg-12">

						<form id='searchForm' action="/board/list" method='get'>
							<select name='type'>
								<option value=""
									<c:out value="${pageMaker.cri.type == null?'selected':''}"/>>--</option>
								<option value="T"
									<c:out value="${pageMaker.cri.type eq 'T'?'selected':''}"/>>제목</option>
								<option value="C"
									<c:out value="${pageMaker.cri.type eq 'C'?'selected':''}"/>>내용</option>
								<option value="W"
									<c:out value="${pageMaker.cri.type eq 'W'?'selected':''}"/>>작성자</option>
								<option value="TC"
									<c:out value="${pageMaker.cri.type eq 'TC'?'selected':''}"/>>제목
									or 내용</option>
								<option value="TW"
									<c:out value="${pageMaker.cri.type eq 'TW'?'selected':''}"/>>제목
									or 작성자</option>
								<option value="TWC"
									<c:out value="${pageMaker.cri.type eq 'TWC'?'selected':''}"/>>제목
									or 내용 or 작성자</option>
							</select> 
							<input type='text' name='keyword'
								value='<c:out value="${pageMaker.cri.keyword}"/>' /> <input
								type='hidden' name='pageNum'
								value='<c:out value="${pageMaker.cri.pageNum}"/>' /> <input
								type='hidden' name='amount'
								value='<c:out value="${pageMaker.cri.amount}"/>' />
							<button class='btn btn-default'>Search</button>
						</form>
					</div>
				</div>


				<div class='pull-right'>
					<ul class="pagination">

						<%--             <c:if test="${pageMaker.prev}">
              <li class="paginate_button previous"><a href="#">Previous</a>
              </li>
            </c:if>

            <c:forEach var="num" begin="${pageMaker.startPage}"
              end="${pageMaker.endPage}">
              <li class="paginate_button"><a href="#">${num}</a></li>
            </c:forEach>

            <c:if test="${pageMaker.next}">
              <li class="paginate_button next"><a href="#">Next</a></li>
            </c:if> --%>
                        <%-- 이전 페이지 표시 --%>
						<c:if test="${pageMaker.prev}">
							<li class="paginate_button previous">
							<a href="${pageMaker.startPage -1}">Previous</a></li>
						</c:if>

                       <%-- 페이지 리스트 출력 --%>
						<c:forEach var="num" begin="${pageMaker.startPage}"	end="${pageMaker.endPage}">
							<li class="paginate_button  ${pageMaker.cri.pageNum == num ? "active":""} ">
								<a href="${num}">${num}</a>
							</li>
						</c:forEach>

                       <%-- 다음 페이지 표시  --%>
						<c:if test="${pageMaker.next}">
							<li class="paginate_button next">
							<a href="${pageMaker.endPage +1 }">Next</a></li>
						</c:if>


					</ul>
				</div>
				<!--  end Pagination -->
			</div>

			<form id='actionForm' action="/board/list" method='get'>
				<input type='hidden' name='pageNum' value='${pageMaker.cri.pageNum}'>
				<input type='hidden' name='amount' value='${pageMaker.cri.amount}'>

				<input type='hidden' name='type'		value='<c:out value="${ pageMaker.cri.type }"/>'> 
				<input type='hidden' name='keyword' 	value='<c:out value="${ pageMaker.cri.keyword }"/>'>
			</form>


			<!-- Modal  추가 -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">Modal title</h4>
						</div>
						<div class="modal-body">처리가 완료되었습니다.</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
							<button type="button" class="btn btn-primary">Save
								changes</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->


		</div>
		<!--  end panel-body -->
	</div>
	<!-- end panel -->
</div>
</div>
<!-- /.row -->






<script type="text/javascript">
	$(document)	.ready(	function() {

						var result = '<c:out value="${result}"/>';//입력,수정,삭제 결과 rttr.addFlashAttribute("result","success");

						checkModal(result);

						//html5에서 추가된 함수
						history.replaceState({}, null, null);//false

						function checkModal(result) {

							if (result === '' || history.state) {// 글목록으로 바로 진입했거나, history.back으로 이동시 모달창을 띄우지 않음.
								return;
							}

                            //새글 입력후 결과값이 넘어오면 처리 
							if (parseInt(result) > 0) {
								$(".modal-body").html(	"게시글 " + parseInt(result)+ " 번이 등록되었습니다.");
							}

							$("#myModal").modal("show");
						}//

						$("#regBtn").on("click", function() {

							self.location = "/board/register";

						});

						var actionForm = $("#actionForm");

						$(".paginate_button a").on(
								"click",
								function(e) {

									e.preventDefault();// 페이지 이동 막기

									console.log('click');

									actionForm.find("input[name='pageNum']").val($(this).attr("href"));//이동 페이지 설정
									actionForm.submit();
								});

						$(".move").on("click",function(e) {

											e.preventDefault();//<a태그 클릭시 자동으로 이동 방지 처리
											actionForm
													.append("<input type='hidden' name='bno' value='"+ $(this).attr("href")+ "'>");
											actionForm.attr("action","/board/get");
											actionForm.submit();
										});

						var searchForm = $("#searchForm");

						$("#searchForm button").on("click",	function(e) {
									if (!searchForm.find("option:selected").val()) {
										alert("검색종류를 선택하세요");
										return false;
									}

									if (!searchForm.find(	"input[name='keyword']").val()) {
										alert("키워드를 입력하세요");
										return false;
									}

									searchForm.find("input[name='pageNum']").val("1");//pageNum에 값을 1로 세팅
									e.preventDefault();

									searchForm.submit();
								});
/* 
              $('#goodBtn').on("click",function(e){
            	  var bno = $(this).data("bno");
                   alert(bno);
                  });
                   */			
					});
</script>
<script>
function updateGoodCnt(obj){
	var x=$(obj);
	var bno=$(obj).data("bno");
		$.ajax({
	        type:'put',
	        url:'/board/good/'+bno+".json",
	        data:JSON.stringify(bno),
	        contentType:'application/json; charset=utf-8',
	        success:function(result, status, xhr){
	               x.html('<i class="fa fa-heart">'+result.good+'</i>');
	            },
	        error:function(xhr, status, er){  	 alert(status); }
			});
}

function updateBadCnt(obj){
	var x=$(obj);
	var bno=$(obj).data("bno");
		$.ajax({
	        type:'put',
	        url:'/board/bad/'+bno+".json",
	        data:JSON.stringify(bno),
	        contentType:'application/json; charset=utf-8',
	        success:function(result, status, xhr){
	               x.html('<i class="fa fa-times">'+result.bad+'</i>');
	            },
	        error:function(xhr, status, er){    alert(status); }
			});
}
</script>



<%@include file="../includes/footer.jsp"%>
