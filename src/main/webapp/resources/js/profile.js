$(function() {
    $('#addFriend').click(function(e) {
        e.preventDefault();
        var m_action = $(this).attr('href');
        $.ajax({
            url: m_action,
            dataType: "text",
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                alert(textStatus + 'что-то пошло не так.<br> Заявка не принета:-(');
            },
            success: function(result) {
                $('#statusAddFriend').html(result);                
            }
        });
    });
});

