'use strict'

const dateControl = document.querySelector('input[type="date"]');
console.log(dateControl.value);

let getCheckbox = document.querySelector("input[name=checkbox]");
let grabTrueOrFalse = document.getElementById("completeBox").value;
let insertIfComplete = document.getElementById("insertIfComplete");

if (grabTrueOrFalse == "true") {
    console.log(grabTrueOrFalse);
    document.querySelector("input[name=checkbox]").checked = true;
    document.getElementById("insertIfComplete").innerText =
        "Task is Completed!";
    getCheckbox.addEventListener("change", function () {
        if (grabTrueOrFalse == "true" && this.checked) {
            document.getElementById("completeBox").value = "true";
            document.getElementById("insertIfComplete").innerText =
                "Task is Completed!";
            // } else if (this.checked) {
            //     // console.log("Checkbox is checked..");
            //     document.getElementById("completeBox").value = "false";
        } else {
            // console.log("Checkbox is not checked..");
            document.getElementById("completeBox").value = "false";
            document.getElementById("insertIfComplete").innerText =
                "Task not Finished.";
        }
    });
}
if (grabTrueOrFalse == "false") {
    getCheckbox.addEventListener("change", function () {
        if (grabTrueOrFalse == "false" && this.checked) {
            document.getElementById("completeBox").value = "true";
            document.getElementById("insertIfComplete").innerText =
                "Task is Completed!";
            // } else if (this.checked) {
            //     // console.log("Checkbox is checked..");
            //     document.getElementById("completeBox").value = "false";
            // document.getElementById("insertIfComplete").innerText =
            //     "Task is Completed!";
        } else {
            // console.log("Checkbox is not checked..");
            document.getElementById("completeBox").value = "false";
            document.getElementById("insertIfComplete").innerText =
                "Task not Finished.";
        }
    });
}