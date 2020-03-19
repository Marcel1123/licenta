function build_card(object_){
    var div = document.getElementById("card-row-line");

    var col_sm_3 = document.createElement("DIV");
    col_sm_3.setAttribute("class", "col-sm-3");
    col_sm_3.setAttribute("id", "card-row-component");
    div.appendChild(col_sm_3);

    var card = document.createElement("div");
    card.setAttribute("class", "card");
    card.setAttribute("id", "card" );
    col_sm_3.appendChild(card);

    var card_body = document.createElement("div");
    card_body.setAttribute("class", "card-body padding-15");
    card_body.setAttribute("id", "card-body");
    card.appendChild(card_body);

    var text = document.createElement("h3");
    text.setAttribute("class","card-title");
    text.textContent = object_['name'];
    card_body.appendChild(text);

    var link = document.createElement("a");
    link.setAttribute("class", "btn btn-primary");
    link.setAttribute("id", "goup-info");
    link.textContent = "Group info";
    card_body.appendChild(link);

    var remove = document.createElement("a");
    remove.style.margin = "5px";
    remove.setAttribute("class", "btn btn-danger");
    remove.setAttribute("id", "goup-remove");
    remove.textContent = "Remove group";
    card_body.appendChild(remove);
}

window.onload = function(){
    if(sessionStorage.getItem("teacherId") === null){
        sessionStorage.clear();
        localStorage.clear();
        window.location.replace("http://localhost:8080/teacher-app/faces/xhtml/index.xhtml");
    } else {
        var data = sessionStorage.getItem("teacherGroups");
        var json_obj = JSON.parse(data);

        json_obj.map(elemet => build_card(elemet));
    }
};