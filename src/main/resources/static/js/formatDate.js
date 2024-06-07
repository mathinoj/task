"use strict"

let grabCurrentPublishFormat =
document.getElementById("publish").textContent;
let changeCurrentPublishFormat = new Date(grabCurrentPublishFormat);
let newFormat = {
day: "2-digit",
month: "short",
year: "numeric",
};
let formatDate = changeCurrentPublishFormat.toLocaleDateString(
"en-US", {timeZone: 'UTC'},
newFormat
);
// https://blog.enterprisedna.co/how-to-convert-string-to-date-in-javascript-in-dd-mmm-yyyy-format/#:~:text=You%20can%20convert%20strings%20to,or%20the%20date%2Dfns%20libraries.

// console.log("vvvvv: " + formatDate);
let getSpaceToInputNewPublish =
document.getElementById("publishAgain");
let newPublishFormat = document.createTextNode(formatDate);
getSpaceToInputNewPublish.appendChild(newPublishFormat);
// https://stackoverflow.com/questions/41764061/adding-text-to-an-existing-text-element-in-javascript-via-dom


let grabCurrentTaskFormat2 =
document.getElementById("publishTask").textContent;
let changeCurrentTaskFormat2 = new Date(grabCurrentTaskFormat2);
let newTaskFormat = {
day: "2-digit",
month: "short",
year: "numeric",
};
let formatTaskDate = changeCurrentTaskFormat2.toLocaleDateString(
"en-US", {timeZone: 'UTC'},
newTaskFormat
);
// https://stackoverflow.com/questions/32877278/tolocaledatestring-is-subtracting-a-day

let getSpaceToInputNewTaskDate =
document.getElementById("publishNewTask");
let newTaskFormatSet = document.createTextNode(formatTaskDate);
getSpaceToInputNewTaskDate.appendChild(newTaskFormatSet);