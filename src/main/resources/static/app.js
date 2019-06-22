var stompClient = null;

function setConnected(connected) {
  $("#connect").prop("disabled", connected);
  $("#disconnect").prop("disabled", !connected);
  if (connected) {
    $("#conversation").show();
  }
  else {
    $("#conversation").hide();
  }
  $("#greetings").html("");
}

function restarted() {
  //alert("Restarted");
}

function endGame(ev) {
  console.log(JSON.stringify(ev));
  $("#result").empty();
  $("#result").append( "<tr><td style=\"background-color: darkgreen;color: #ffffff\"> "+ev.body+" won  !</td></tr>")
  //alert(ev.winnerName+" won.");
}
function connect() {
  var socket = new SockJS('/ns-server');
  stompClient = Stomp.over(socket);
  stompClient.connect({}, function (frame) {
    setConnected(true);
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/login', function (greeting) {
      //alert(ticket.tocken);
      window.greeting = greeting;
      loginResult(JSON.parse(greeting.body).tocken);
    });

    stompClient.subscribe('/topic/refresh-state', function (state) {
      changeState(JSON.parse(state.body));
    });
    stompClient.subscribe('/topic/restart', function (state) {
      restarted();
    });

    stompClient.subscribe('/topic/end-game', function (ev) {
       endGame(ev);
    });

    stompClient.subscribe('/topic/user-action-resp', function (eventResponse) {
      showEventResponse(JSON.parse(eventResponse.body));
    });
    login();
  });
}

function disconnect() {
  if (stompClient !== null) {
    stompClient.disconnect();
  }
  setConnected(false);
  console.log("Disconnected");
}

function sendName() {
  stompClient.send("/app/user-action", {},
      JSON.stringify({'playerName': $("#name").val()}));
}

function sendAction(actionType) {
  stompClient.send("/app/user-action", {},
      JSON.stringify({'playerName': $("#name").val(), "type": actionType}));
}

function restart() {
  stompClient.send("/app/restart", {},
      JSON.stringify({'playerName': $("#name").val()}));
   disconnect();
}

function login() {
  stompClient.send("/app/login", {},
      JSON.stringify({'principal': $("#name").val(), 'credintial': "xxxxx"}));
}

function showEventResponse(resp) {
  //$("#greetings").append( "<tr><td>Event resp: " + resp.content + " " + resp.type + "</td></tr>");
}

function loginResult(message) {
  //$("#greetings").append("<tr><td> LOGIN RES:" + message + "</td></tr>");
}

function restartResult(message) {

}

function changeState(state) {
  window.scene = state;
  drawState(state);
  //$("#state").append("<tr><td> State:" + state.name + "</td></tr>");
}

$(function () {
  $("form").on('submit', function (e) {
    e.preventDefault();
  });
  $("#connect").click(function () {
    connect();
  });
  $("#disconnect").click(function () {
    disconnect();
  });
  $("#send").click(function () {
    sendName();
  });
  $("#restart").click(function () {
    restart();
  });
  $("#login").click(function () {
    login();
  });
  Mousetrap.bind('up', function (e) {
    sendAction("UP");
  });
  Mousetrap.bind('down', function (e) {
    sendAction("DOWN");
  });
  Mousetrap.bind('right', function (e) {
    sendAction("RIGHT");
  });
  Mousetrap.bind('left', function (e) {
    sendAction("LEFT");
  });
  Mousetrap.bind('x', function (e) {
    sendAction("FIRE");
  });

});

//-------------------------------------------
function showDead(s) {
  console.log(s);
}

//-------------------------------------------
function drawState(state) {

  console.log("Draw state " + state);
  var canvas = document.getElementById("myCanvas");
  var ctx = canvas.getContext("2d");
  ctx.clearRect(0, 0, canvas.width, canvas.height);
  console.log("Cleared ");
  //----------------------------------------
  //----------------------------------------
  var players = Object.values(window.scene.players);
  $("#report").empty();
  for (var i = 0; i < players.length; i++) {

    var player = players[i];
    if( player.life==0){
      $("#report").append("<tr><td style=\"background-color: #ff0000;color: #ffffff\"><span STYLE=\"text-decoration: line-through\"> " + player.title + " " + player.life + "</span></td></tr>");
      window.setTimeout(function(){restart();},2000);
    }else {
      $("#report").append("<tr><td> " + player.title + " " + player.life + "</td></tr>");
    }
    if(player.life<=0){
      showDead(player.name+" is dead");
    }
    drawImagex(ctx, "p" + i, player);

    for (var j = 0; j < player.bullets.length; j++) {
      console.log("bullet ");
      var bullet = player.bullets[j];
      if (bullet.active) {
        drawImagex(ctx, "bullet", bullet);
      }else {
        console.log("invisible :"+bullet.active);
      }
    }
  }
}

function drawImagex(ctx, img, movingObject) {
  console.log(" Draw: " + img + "  " + movingObject.location.x + " "
      + movingObject.location.y + "   Angle:" + (movingObject.angle / Math.PI));
  ctx.save();
  ctx.translate(movingObject.location.x, movingObject.location.y); // change origin
  ctx.rotate(movingObject.angle); // rotate
  var image = document.getElementById(img);
  ctx.drawImage(image, -image.width / 2, -image.width / 2);
  ctx.restore();
}
