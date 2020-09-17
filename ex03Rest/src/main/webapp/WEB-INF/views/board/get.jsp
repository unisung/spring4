<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../includes/header.jsp" %>    

<div class="row">
	<div class="col-lg-12">
	    <h1 class="page-header">글 상세 보기</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!--  /.row -->

<!-- 본문 -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-info">
			<div class="panel-heading"> 글 상세 보기 페이지</div>
			<!--  /.panel-heading -->
			<div class="panel-body">
				
				<div class="form-group">
					<label>글번호</label>
					<input class="form-control" name="bno" value='<c:out value="${board. bno }"/>' readonly>
				</div>
				
				<div class="form-group">
					<label>제목</label>
					<input class="form-control" name="title" value='<c:out value="${board. title }"/>' readonly>
				</div>
				
				<div class="form-group">
					<label>내용</label>
					<textarea class="form-control" rows="3" name="content" readonly>
					       <c:out value="${board.content }"/>
					 </textarea>
				</div>
				
				<div class="form-group">
					<label>작성자</label>
					<input class="form-control" name="writer" value='<c:out value="${board.writer }"/>' readonly>
				</div>
				
				<button data-oper='modify' class="btn btn-primary">수정</button>
				<button data-oper='list' class="btn btn-default">글목록</button>
 				
				<!--  이동할 폼 생성  -->
				<form id='operForm' action="/board/modify" method="get">
				<input type="hidden" id='bno' name='bno' value='<c:out value="${board.bno}"/>'>
				<input type="hidden" id='pageNum' name='pageNum' value='<c:out value="${cri.pageNum}"/>'>
				<input type="hidden" id='amount' name='amount' value='<c:out value="${cri.amount}"/>'>
				<input type="hidden" id='keyword' name='keyword' value='<c:out value="${cri.keyword}"/>'>
				<input type="hidden" id='type' name='type' value='<c:out value="${cri.type}"/>'>
				</form>
				
			</div><!--  end panel-body -->
		</div><!-- end panel-info -->
	</div><!-- end panel -->

</div><!--  /.row -->

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                            <h4 class="modal-title" id="myModalLabel">댓글 팝업창</h4>
                                        </div>
                                        <div class="modal-body">
                                            <div class="form-group">
                                               <label>답변내용</label>
                                               <input type="text" class="form-control" name='reply' value='새로운 댓글!!!'>
                                            </div>
                                            <div class="form-group">
                                               <label>작성자</label>
                                               <input type="text" class="form-control" name='replyer' value='작성자'>
                                            </div>
                                            <div class="form-group">
                                               <label>작성일자</label>
                                               <input type="text" class="form-control" name='replyDate' value='2020-09-16 15:50'>
                                            </div>
                                            
                                        </div>
                                        <div class="modal-footer">
                                            <button id='modalModBtn' type="button" class="btn btn-warning" >수정</button>
                                            <button id='modalRemoveBtn' type="button" class="btn btn-danger">삭제</button>
                                            <button id='modelRegisterBtn' type="button" class="btn btn-default" >등록</button>
                                            <button id='modalCloseBtn' type="button" class="btn btn-primary">닫기</button>
                                        </div>
                                    </div>
                                    <!-- /.modal-content -->
                                </div>
                                <!-- /.modal-dialog -->
                            </div>
                            <!-- /.modal -->



<div class='row'>
	<div class='col-lg-12'>
		
	<div class='panel panel-default'>
	   <div class='panel-heading'>
	     <i class="fa fa-comments fa-fw"></i>댓글
	<button id='addReplyBtn' class="btn btn-primary btn-xs pull-right">댓글달기</button>
	   </div>
	
	<div class="panel-body">
	        <ul class="chat">
	        
	        </ul>
	      
	</div>
	   <div class="panel-footer"></div>
		
		</div>
		</div>

</div>




<!-- 수정/리스트 페이지로이동 처리 스크립트  -->
<script>
$(document).ready(function(){
	 var operForm=$("#operForm");
	 $("button[data-oper='modify']").on("click",function(e){
			operForm.attr("action","/board/modify").submit();
		 });
     
	 $("button[data-oper='list']").on("click",function(e){
		    operForm.find("#bno").remove();
			operForm.attr("action","/board/list").submit();
		 });
});
</script>
<script>

</script>
<script type="text/javascript" src="/resources/js/reply.js"></script>
<script>
console.log("===============");
console.log("JS TEST");

var bnoValue = '<c:out value="${board.bno}"/>';

//for replyService add test
 replyService.add(
  
  {reply:"JS Test", 
	replyer:"tester", 
	bno:bnoValue}//객체
  ,
  function(result){ //함수
    alert("RESULT: " + result);
  }
); 
</script>
<script>
replyService.getList({bno:bnoValue,page:1},
		                        function(list){
	                   for(var i=0,len=list.length||0;i<len;i++){
                                console.log(list[i]);
		}
});
</script>
<script>
 //24번 댓글 삭제
 replyService.remove(24,
		 function(count){
           console.log(count);

     	  if(count==='success'){
         	      alert("REMOVED");
          }
        },
      function(err){
          alert('ERROR....');
          }
);
</script>
<script>
//43번 댓글 수정
replyService.update(
  {rno:26, bno:bnoValue, reply:"ajax로 댓글 수정...."},
  function(result){
	  alert("수정완료....");
	  }
);
</script>
<script>
replyService.get(26,function(data){
	   console.log(data);
});

</script>
<script>
var replyUL =$(".chat");//class="chat"

showList(1);

function showList(page){
	 console.log("show list "+page);

	 replyService.getList({bno:bnoValue,page:page||1},
            function(replyCnt, list){
				console.log("replyCnt: "+replyCnt);
				console.log("list: "+list);
				console.log(list);

                //페이지 번호가 -1로 전달되면 전체 댓글수 재계산후, showList()재호출
				if(page==-1){
                     pageNum=Math.ceil(replyCnt/10.0);
                     showList(pageNum);
                     return;
					}

				var str="";

				//결과가 없을 때 리턴
				if(list==null || list.length==0){
                     return;
					}
                 //댓글 리스트가 있으면
           for(var i=0,len=list.length||0; i<len; i++){
                 str+="<li class='left clearfix' data-rno='"+list[i].rno+"'>";
                 str+=" <div><div class='header'><strong class='primary-font'>["
                        +list[i].rno+"]"+list[i].replyer+"</strong>";
                 str+=" <small class='pull-right text-muted'>"
                         +replyService.displayTime(list[i].replyDate) +"</small></div>";
                 str+=" <p>"+list[i].reply+"</p></div></li>";
               }

               //ul에 추가하기
           replyUL.html(str);//InnerHTML(element)
           
           showReplyPage(replyCnt);
	 }         
     
			 );
}
</script>
<script>
var pageNum=1;
var replyPageFooter=$(".panel-footer");//class="panel-footer"
function showReplyPage(replyCnt){
        
	   //끝번호
	var endNum=Math.ceil(pageNum/10.0) *10;
	//시작번호
	var startNum=endNum-9;
   //이전페이지
	var prev=startNum !=1;
	//다음페이지 여부
	var next=false;

	console.log(endNum, startNum, prev,next);

	//페이지 조정
	if(endNum*10 >=replyCnt){
        endNum=Math.ceil(replyCnt/10.0);
	}

	//다음페이지  true로
	if(endNum *10 <replyCnt){
        next=true;
		}

	var str="<ul class='pagination pull-right'>";

	if(prev){
		  str+="<li class='page-item'><a class='page-link' href='"
			    +(startNum-1)+"'>이전페이지</a></li>";
		}

    for(var i=startNum; i<=endNum;i++){
          var active = pageNum==i?"active":"";

          str+="<li class='page-item " +active+"'><a class='page-link' href='"+i+"'>"+i+"</a></li>";


        }

	
	if(next){
		str+="<li class='page-item'><a class='page-link' href='"
			   +(entNum+1)+"'>다음페이지</a></li>";
		}

	str +="</ul></div>";

	console.log(str);

	replyPageFooter.html(str);//innerHTML(str);
	
}

//이벤트 위임-부모요소에 이벤트 리스너 등록 - 동적생성된 자식요소에 이벤트 처리 
replyPageFooter.on("click","li a",function(e){
	e.preventDefault();//기본동작 정지
	console.log("page click");

	var targetPageNum=$(this).attr("href");//this <- li a요소

	console.log("targetPageNum: "+targetPageNum);

	pageNum=targetPageNum;

	showList(pageNum);
	
});
</script>
<script>
//모달 객체 얻기
var modal=$(".modal");
//모달 input태그 얻기
var modalInputReply=modal.find("input[name='reply']");
var modalInputReplyer=modal.find("input[name='replyer']");
var modalInputRepyDate=modal.find("input[name='replyDate']");

//모달내 버튼 객체 얻기
var modalModBtn=$('#modalModBtn');
var modalRemoveBtn=$('#modalRemoveBtn');
var modalRegisterBtn=$('#modelRegisterBtn');


$(".chat").on("click","li",function(e){
   var rno=$(this).data("rno");// this-> 클릭한 li,   data-* 글로벌 속성, data-rno,data-변수명, 값얻기 data(변수명)

   replyService.get(rno,function(reply){
	   modalInputReply.val(reply.reply);// 객체.val()- 값 얻기, 객체.val(값) -값 세팅,
	   modalInputReply.val(reply.replyer);
	   modalInputRepyDate.val(replyService.displayTime(reply.replyDate)).attr("readonly","readonly");
	   modal.data("rno",reply.rno);

	   modal.find("button[id!='modalCloseBtn']").hide();//닫기 버튼만 보여줌
	   modalModBtn.show();
	   modalRemoveBtn.show();

	   $(".modal").modal("show");
	   
	   });
});

</script>

<%@ include file="../includes/footer.jsp"%>