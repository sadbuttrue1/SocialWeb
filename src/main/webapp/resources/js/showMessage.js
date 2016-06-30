$(function() {
    //console.log(new Date(1396209740230));
    //console.log(+"1396209740230");
    //console.log(parseInt("1396209740230", 10));


    var block = document.getElementById("messageList");
    block.scrollTop = block.scrollHeight;

    $('.tdMessageDate').each(function(index) {
        var tdMessageDate = this.innerHTML;
        this.innerHTML = getDate(tdMessageDate);
    });


    $('#inputMessages').submit(function(e) {
        e.preventDefault();
        //var m_method = $(this).attr('method');
        var m_action = $(this).attr('action');
        var text = $('#text').val();
        if (text.length === 0) {
            return false;
        }
        $.ajax({
            url: m_action,
            data: 'text=' + text,
            dataType: "text",
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                alert(textStatus + ' что-то пошло не так. Сообщение не отправлено!');
            },
            success: function(result) {
                if (/good/.test(result))
                    $('#text').val('');
            }
        });
    });

    UserChat = function(profileLink, photo, name, text, messageDate) {
        this.profileLink = profileLink;
        this.photo = photo;
        this.name = name;
        this.text = text;
        this.messageDate = messageDate;
    };

    function convertUserChat(message) {
        var authorId = message.authorId;
        var profileLink = $("input[name='profileLink" + authorId + "']").val();
        var photo = $("input[name='photo" + authorId + "']").val();
        var name = $("input[name='name" + authorId + "']").val();
        var userChat = new UserChat(profileLink, photo, name, message.text, message.messageDate);
        return userChat;
    }

    /*<div class="message">                     
     <div class="avatarSearch">
     <img src="../../avatar/${photo}" width="80" height="80">
     </div>
     <a href="${pageContext.request.contextPath}/profile/${profileLink}">${name}</a>
     <table><tr>
     <td>${message.text}</td>
     <td>${message.messageDate}</td>
     </tr></table>
     </div>
     <div style="clear: both;"></div>*/
    function addMessage(userChat) {
        var messageHtml = '<div class="message">\
        <div class="avatarSearch">\
        <img src="../../avatar/' + userChat.photo + '" width="80" height="80"></div>\
        <a href="/profile/' + userChat.profileLink + '">' + userChat.name + '</a>\
        <table width="80%"><tr>\
         <td width="70%">' + userChat.text + '</td>\
         <td>' + getDate(userChat.messageDate) + '</td>\
         </tr></table></div>\
         <div style="clear: both;"></div>';
        $('#messageList').append(messageHtml);
    }

    function getDate(dateLong) {
        var dateDate = new Date(+dateLong);

        //var formatDate = ([dateDate.getDate(), dateDate.getMonth(), dateDate.getYear()]).join(".");        
        return dateDate.toLocaleDateString() + '<br\>' + dateDate.toLocaleTimeString();
    }

    setInterval(function() {
        var lastUpdate = 'lastUpdate=' + $("input[name='lastUpdate']").val();
        var chatId = '&chatId=' + $("input[name='chatId']").val();
        $.ajax({
            url: 'refreshMessages',
            data: lastUpdate + chatId,
            dataType: "json",
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                alert(textStatus + ' что-то пошло не так. Сообщение не отправлено!');
            },
            success: function(messages) {
                var messageIndex;
                var len = messages.length;
                for (messageIndex = 0; messageIndex < len; messageIndex++) {
                    var userChat = convertUserChat(messages[messageIndex]);
                    addMessage(userChat);
                }
                //если был в самом низу
                block.scrollTop = block.scrollHeight;
                if (len > 0) {
                    var lastUpdate = messages[len - 1].messageDate;
                    $("input[name='lastUpdate']").val(lastUpdate);
                }
            }
        });
    }, 25000);
});



