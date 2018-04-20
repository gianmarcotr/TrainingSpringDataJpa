function printNotice(msg, type, timer){

    var div = document.createElement("DIV");
    var button = document.createElement("BUTTON");
    var span = document.createElement("SPAN");

    div.setAttribute("class", "alert alert-"+type+" alert-dismissible fade show");
    div.setAttribute("role", "alert");

    div.setAttribute("data-notify", "container");
    div.setAttribute("data-notify-position", "top-right");

    button.setAttribute("type", "button");
    button.setAttribute("class", "close");
    button.setAttribute("data-dismiss", "alert");
    button.setAttribute("aria-label", "Close");

    div.innerText = msg;
    span.innerHTML = "&times;";

    button.appendChild(span);
    div.appendChild(button);

    $(div).appendTo(document.getElementById("idDivNotice")).slideUp(1).slideDown(500);
    var  x = parseInt(timer);
    $(div).fadeTo(x, 1).slideUp(500);


}


function prova() {
    alert("messagiooo");
}