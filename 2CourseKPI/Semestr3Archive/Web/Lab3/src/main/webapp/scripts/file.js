task3();


function task3() {
    if (document.cookie !== "" && confirm("Cookies: " + document.cookie + ". After pressing OK, cookies will be deleted")){
        document.getElementById("task3form").setAttribute("class","hidden");
        document.cookie = "biggest=0;max-age=0";
    }
}


function onClickTask3() {
    var num = document.getElementById("inputText").value;
    var max = Number.MIN_SAFE_INTEGER;
    if (!isNaN(num)){
        for (let i = 0; i < num.length; i++) {
            if (num[i] > max){
                max = num[i];
                document.cookie = "biggest="+max;
            }
        }
        alert(document.cookie);
    }
    else alert('String is NaN')
}

var item2 = localStorage.getItem("pos2");
var item4 = localStorage.getItem("pos4");
var item5 = localStorage.getItem("pos5");
if (item2 != null)
{
    document.getElementById("main").style.textAlign = item5;
}
if (item4 != null)
{
    document.getElementById("rightBar").style.textAlign = item4;
}
if (item5 != null)
{
    document.getElementById("leftBar").style.justifyContent = item2;
}


function task4() {
    var pos = document.querySelector('input[name="position"]:checked').value;
    if (pos == "Right"){
        if (document.querySelector('.pos2').checked == true){
            document.getElementById("leftBar").style.justifyContent = "right";
            localStorage.setItem("pos2", "right")
        }
        if (document.querySelector('.pos4').checked == true){
            document.getElementById("rightBar").style.textAlign = "right";
            localStorage.setItem("pos4", "right")
        }
        if (document.querySelector('.pos5').checked == true){
            document.getElementById("main").style.textAlign = "right";
            localStorage.setItem("pos5", "right")
        }
    }
    if (pos == "Center"){
        if (document.querySelector('.pos2').checked == true){
            document.getElementById("leftBar").style.justifyContent = "center";
            localStorage.setItem("pos2", "center")
        }
        if (document.querySelector('.pos4').checked == true){
            document.getElementById("rightBar").style.textAlign = "center";
            localStorage.setItem("pos4", "center")
        }
        if (document.querySelector('.pos5').checked == true){
            document.getElementById("main").style.textAlign = "center";
            localStorage.setItem("pos5", "center")
        }
    }
}

// Task 5


function logSelection(event) {
    const log = document.getElementById('log');
    const selection = event.target.value.substring(event.target.selectionStart, event.target.selectionEnd);
    log.textContent = selection;
}

const input = document.querySelector('input');
input.addEventListener('select', logSelection);

function addelement() {
    var text = document.getElementById('log').textContent;
    var completelist = document.getElementById("numList1");

    completelist.innerHTML += "<li>Item " + text + "</li>";

}

function saveAll() {
    var toStorage = [];
    var values = document.querySelectorAll('li');
    for (var i = 0; i < values.length; i++) {
        toStorage.push(values[i].innerHTML);
    }
    localStorage.setItem('items', JSON.stringify(toStorage));
    document.getElementById(`numListForm`).setAttribute("class","hidden" );
}

window.onunload = function () {
    var pos2 = localStorage.getItem('pos2')
    var pos4 = localStorage.getItem('pos4')
    var pos5 = localStorage.getItem('pos5')
    localStorage.clear();
    localStorage.setItem("pos2", pos2)
    localStorage.setItem("pos4", pos4)
    localStorage.setItem("pos5", pos5)

}
