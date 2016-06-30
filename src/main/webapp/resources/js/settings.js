$(function() {

    var SIZE = $('#addEmail').attr("class");

    $('#addEmail').click(function(e) {
        e.preventDefault();
        $(this).before("<input type='hidden' id='emails" + SIZE + ".remove' name='emails[" + SIZE + "].remove' value='false'>\
                        <div id='emails" + SIZE + "'>\
                        <input type='email' id='emails" + SIZE + ".email' name='emails[" + SIZE + "].email'/>\
                        <a href='#' id='" + SIZE + "' class='delEmail'>Remove</a></div>");
        
        
//        $('.delEmail').click(function(e) {
//        e.preventDefault();
//        var prefix = $(this).attr("id");
//        //var email = 'input[name="' + prefix + '.email"]';
//        var remove = 'input[name="emails[' + prefix + '].remove"]';
//        var hide = '#emails' + prefix;
//        //$(this).remove();
//        $(hide).hide();
//        //$(email).attr('type', 'hidden');
//        $(remove).val('true');
//    });
        
        
        SIZE++;
    });
    
    $.listen('click', '.delEmail', function(e) {
        e.preventDefault();
        var prefix = $(this).attr("id");
        //var email = 'input[name="' + prefix + '.email"]';
        var remove = 'input[name="emails[' + prefix + '].remove"]';
        var hide = '#emails' + prefix;
        //$(this).remove();
        $(hide).hide();
        //$(email).attr('type', 'hidden');
        $(remove).val('true');
    });


    $('#formUserData').submit(function() {
        $('statusError').html('');
    });

    $('#formUserData').validate({
        rules: {
            name: {
                required: true,
                rangelength: [2, 16]
            },
            surname: {
                required: true,
                rangelength: [2, 30]
            },
            birth: {
                date: true
            },
            email: {
                email: true
            }
        },
        messages: {
            name: {
                required: window.myLocale.required,
                rangelength: window.myLocale.nameRangelength
            },
            surname: {
                required: window.myLocale.required,
                rangelength: window.myLocale.surnameRangelength
            },
            birth: {
                date: window.myLocale.dateError
            },
            email: {
                email: window.myLocale.emailError
            }
        }

    });



});


