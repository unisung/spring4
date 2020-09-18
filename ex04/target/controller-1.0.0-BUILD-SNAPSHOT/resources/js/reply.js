/**
 *  reply.js
 */
 console.log("Reply Module....");

var replyService=(function(){
	  function add(reply, callback){
	  		console.log("reply............");
	  		
	  		$.ajax({
	  		  type:'post',
	  		  url:'/replies/new',
	  		  data:JSON.stringify(reply),
	  		  contentType:"application/json; charset=utf-8",
	  		  success:function(result,status,xhr){
	  		     if(callback){
	  		      callback(result);
	  		     }
	  		},
	  		 error:function(xhr, status, er){
	  		    if(error){error(er);}
	         }
	    })
	  }
	  
	 function getList(param,callback,error){
		var bno=param.bno;
		var page=param.page||1;
		
		$.getJSON("/replies/page/"+bno+"/"+page+".json",
		   function(data){
		       if(callback){
		       //callback(data);
		       callback(data.replyCnt, data.list);//댓글수자와 목록을 가져오는 경우
		       }
		       }).fail(function(xhr,status,err){
		       if(error){
		          error();
		       }
		       }   
		);
}

 function remove(rno, callback, error){
    $.ajax({
 			type:'delete',
 			url:'/replies/'+rno,
 			success:function(deleteResult, 
 		 			                    status, xhr){
 					if(callback){
 					callback(deleteResult);
 					}
 			},
 			error:function(xhr, status, er){
 			  if(error){
 			   error(er);
 			}       
 		}
    });
 }
 
 function update(reply, callback, error){
	$.ajax({
        type:'put',
        url:'/replies/'+reply.rno,
        data:JSON.stringify(reply),
        contentType:'application/json; charset=utf-8',
        success:function(result, status, xhr){
                if(callback){
                    callback(result);
                    }
            },
        error:function(xhr, status, er){
              if(error){
                  error(er);
                  }
            }
		});
}

/*$.get().fail() 
메소드 체이닝 ().()
*/
function get(rno, callback, error){
    $.get("/replies/"+rno+".json",function(result){
				if(callback){
			       callback(result);
					}
        }).fail(function(xhr,status, err){
             if(error){
                  error();
                 }

        });
}

function displayTime(timeValue){
  var today=new Date();//현재날짜시간
  var gap=today.getTime() - timeValue;//
   
   console.log(gap,gap<(1000*60*60*24));

  var dateObj = new Date(timeValue);//

  //등록일자가 당일이면
  if(gap<(1000*60*60*24)){
      var hh=dateObj.getHours();
      var mi=dateObj.getMinutes();
      var ss=dateObj.getSeconds();

      return[(hh>9 ?'':'0')+hh,":",(mi>9?'':'0')+mi,":",(ss>9?'':'0')+ss].join('');
	  }else{
      var yy=dateObj.getFullYear();
      var mm=dateObj.getMonth()+1;
      var dd=dateObj.getDate();

      return [yy,'/',(mm>9?'':'0')+mm,'/',(dd>9?'':'0')+dd].join('');

		  }
}

  return {add:add,
	              getList:getList,
	              remove:remove,
	              update:update,
	              get:get,
	              displayTime:displayTime
	              };
})();/* 즉시 실행 */