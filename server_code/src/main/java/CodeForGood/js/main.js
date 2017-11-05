var CorrectAnswers = [];
var WrongAnswers = [];

function SetResult(){

}

function goToNextSlide(){

}

function dropboxFuntion(){
    document.getElementById("myDropBox").classList.toggle("show");
}

window.onclick = function(event){
    if(!even.target.matches('.dropbtn')){
        var dropdowns = document.getElementsByClassName("dropdown-content");
        var i;
        for(i=0; i<dropdowns.length; i++){
            var openDropdown = dropdowns[i];
            if(openDropdown.classList.contains('show')){
                openDropdown.classList.remove('show');
            }
        }
    }
}
