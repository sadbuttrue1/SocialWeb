$(function(){
    
    $('#formSearchUserData').submit(function(){
       var min = $('#ageFrom').val();
       var max = $('#ageTo').val();
       if(min > max){
           ('#age').html(window.myLocale.ageError);
            return false;
        }
    });
    
    $('#formSearchUserData').validate({
        rules: {
            ageFrom: {
                min: 6
            },
            ageTo: {
                max: 120
            },           
            email: {
                email: true
            }
        },
        messages: {
            ageFrom: {
                min: window.myLocale.minError
            },
            ageTo: {
                max: window.myLocale.maxError

            },           
            email: {
                email: window.myLocale.emailError 
            }
        }

    }); 
});
