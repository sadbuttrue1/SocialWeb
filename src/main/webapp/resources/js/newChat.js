$(function() {
    $('#newChatForm').submit(function (e){
       if($('#addresseeUserId').val() === '0'){
           $('#addresseeUserId').click();
            e.preventDefault();
            return false;            
       }else if($('#text').val().length === 0){
            e.preventDefault();
            return false;            
       } 
    });
});



