var stompClient = null;
var chatRoom = null;
var roomId = null;
var username = null;

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#send" ).click(function() { sendMessage(); });
    $( "#findPDF" ).click(function() { findPDF(); });
    $( "#uploadPDF" ).click(function() { uploadPDF(); });
    $( "#changeRoom" ).click(function() { changeRoom(); });
});

function connect() {
    $.ajax({
        type: 'GET',
        enctype: 'multipart/form-data',
        url: 'https://localhost:8443/getUserInfo',
        contentType: false,
        cache: false,
        processData:false,
        success: function(userInfo){
            username = userInfo.username;
            roomId = userInfo.roomId;

            var socket = new SockJS('/chat-room-entry-point');//subscribe to a url
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function (frame) {
                if(roomId === '1')
                {
                    stompClient.subscribe('/topic/chat1', function (sendMessage) {
                        showGreeting(JSON.parse(sendMessage.body).content);
                    });
                    chatRoom = "/app/ChatRoomOne";
                }
                else if(roomId === "2")
                {
                    stompClient.subscribe('/topic/chat2', function (sendMessage) {
                        showGreeting(JSON.parse(sendMessage.body).content);
                    });
                    chatRoom = "/app/ChatRoomTwo";
                }
                else
                {
                    stompClient.subscribe('/topic/chat3',function (sendMessage) {
                        showGreeting(JSON.parse(sendMessage.body).content);
                    });
                    chatRoom = "/app/ChatRoomThree";
                }
            });
        },
        error: function (error) {
            console.log(error);
        }
    });


}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
}

function sendMessage() {
    var today = new Date();
    var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate()+'-'+today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
    stompClient.send(chatRoom, {},
        JSON.stringify({'name': username+'     ',
            'message': $("#message").val()+'     ',
            'date': date}));
    document.getElementById("message").value = "";
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

function findPDF()
{
    disconnect();
    window.location.href = "https://localhost:8443/FindPDF";
}

function uploadPDF()
{
    disconnect();
    window.location.href = "https://localhost:8443/Upload";
}

function changeRoom()
{
    disconnect();
    window.location.href = "https://localhost:8443/ChangeRoom";
}

