$(function() {
    
    $('#formRegistration').validate({
        rules: {
            name: {
                required: true,
                rangelength: [2, 16]
            },
            surname: {
                required: true,
                rangelength: [2, 30]
            },
            password: {
                required: true,
                minlength: 4
            },
            passwordConfirm: {               
                equalTo: '#password'
            },
            email: {
                required: true,
                email: true
            }
        },
        messages: {
            name: {
                required: window.myLocale.required,
                rangelength: window.myLocale.nameRangelength,
            },
            surname: {
                required: window.myLocale.required,
                rangelength: window.myLocale.surnameRangelength
            },
            password: {
                required: window.myLocale.required,
                minlength: window.myLocale.passwordMinlength
            },
            passwordConfirm: {
              equalTo: window.myLocale.notMatch  
            },
            email: {
                required: window.myLocale.required,
                email: window.myLocale.emailError
            }
        }
    });
});

