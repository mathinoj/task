"use strict"

let grabCurrentPublishFormat =
document.getElementById("publish").textContent;
let changeCurrentPublishFormat = new Date(grabCurrentPublishFormat);
let newFormat = {
day: "2-digit",
month: "short",
year: "numeric",
};
let formatDate = changeCurrentPublishFormat.toLocaleString(
"en-gb",
newFormat
);
// https://blog.enterprisedna.co/how-to-convert-string-to-date-in-javascript-in-dd-mmm-yyyy-format/#:~:text=You%20can%20convert%20strings%20to,or%20the%20date%2Dfns%20libraries.

// console.log("vvvvv: " + formatDate);
let getSpaceToInputNewPublish =
document.getElementById("publishAgain");
let newPublishFormat = document.createTextNode(formatDate);
getSpaceToInputNewPublish.appendChild(newPublishFormat);
// https://stackoverflow.com/questions/41764061/adding-text-to-an-existing-text-element-in-javascript-via-dom


let grabCurrentPublishFormat2 =
document.getElementById("publish2").textContent;
let changeCurrentPublishFormat2 = new Date(grabCurrentPublishFormat2);
let newFormat2 = {
day: "2-digit",
month: "short",
year: "numeric",
};
let formatDate2 = changeCurrentPublishFormat2.toLocaleString(
"en-gb",
newFormat2
);

// console.log("vvvvv: " + formatDate);
let getSpaceToInputNewPublish2 =
document.getElementById("publishAgain2");
let newPublishFormat2 = document.createTextNode(formatDate2);
getSpaceToInputNewPublish2.appendChild(newPublishFormat2);