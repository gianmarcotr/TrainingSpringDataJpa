function deleteConfirm(id) {
    var aElement =  document.getElementById("a"+id);
    var x = confirm("Are u sure?");
    if(x==true){
        aElement.setAttribute("href", "/delete-"+id);
    }else {
        aElement.setAttribute("href", "");
    }

}