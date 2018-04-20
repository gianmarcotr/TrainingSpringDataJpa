
function addSkill() {
    var e = document.getElementById("skl");
    var id = e.value;
    var nome = e.options[e.selectedIndex].text;

    //todo: creo nuova input con valore l'id della skills
    var sk = document.createElement("BUTTON");
    var skHidden = document.createElement("INPUT");

    sk.setAttribute("class", "btn btn-outline-dark btn-sm");
    sk.setAttribute("size", "5");
    sk.setAttribute("onclick", "rmvSkill('"+nome+"', '"+id+"')");
    sk.setAttribute("id", id+"sk");
    sk.innerText = nome;

    skHidden.setAttribute("type", "hidden");
    skHidden.setAttribute("name", "skills");
    skHidden.setAttribute("value", id);
    skHidden.setAttribute("id", id+"hid");


    //todo: elemino dalla lista delle skill quella appena selezionata
    document.getElementById(id).remove();

    document.getElementById("pos").appendChild(sk);
    document.getElementById("pos").appendChild(skHidden);

}

function rmvSkill(nome, id){
    document.getElementById(id+"sk").remove();
    document.getElementById(id+"hid").remove();
    var optSk = document.createElement("OPTION");
    optSk.setAttribute("value", id);
    optSk.setAttribute("id", id);
    optSk.innerText= nome;
    document.getElementById("skl").appendChild(optSk);
}


