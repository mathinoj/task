"use strict"

let oldTaskDateArea = document.querySelectorAll('.getPublishTask')
let newTaskDateArea = document.querySelectorAll('.publishNewTask')

for(let i = 1; i < oldTaskDateArea.length; i++){
  if(i >= 0){
    let indexNumbers = parseInt(i)
    let getOldDateFormat = oldTaskDateArea[indexNumbers].innerHTML
    let changeCurrentTaskFormat = new Date(getOldDateFormat);
    let newTaskFormat = {
      day: "2-digit",
      month: "short",
      year: "numeric",
      };
      let formatTaskDate = changeCurrentTaskFormat.toLocaleDateString(
      "en-US", {timeZone: 'UTC'},
      newTaskFormat
      );

      let findBlankTextContent = newTaskDateArea[indexNumbers].textContent;
      if(findBlankTextContent == ""){
        let getTaskDueSpace = newTaskDateArea[indexNumbers]
        // console.log(getTaskDueSpace);
        let insertFormattedTaskDate = document.createTextNode(formatTaskDate)
        getTaskDueSpace.appendChild(insertFormattedTaskDate)
      }
      }
    }




// }
// x.addEventListener('click', ()=>{
//   console.log(x)
// })

// let grabCurrentPublishFormat =
// document.getElementById("publish").textContent;
// // console.log(grabCurrentPublishFormat);
// let changeCurrentPublishFormat = new Date(grabCurrentPublishFormat);
// let newFormat = {
// day: "2-digit",
// month: "short",
// year: "numeric",
// };
// let formatDate = changeCurrentPublishFormat.toLocaleDateString(
// "en-US", {timeZone: 'UTC'},
// newFormat
// );
// // https://blog.enterprisedna.co/how-to-convert-string-to-date-in-javascript-in-dd-mmm-yyyy-format/#:~:text=You%20can%20convert%20strings%20to,or%20the%20date%2Dfns%20libraries.




// // console.log("vvvvv: " + formatDate);
// let getSpaceToInputNewPublish =
// document.getElementById("publishAgain");
// let newPublishFormat = document.createTextNode(formatDate);
// getSpaceToInputNewPublish.appendChild(newPublishFormat);
// https://stackoverflow.com/questions/41764061/adding-text-to-an-existing-text-element-in-javascript-via-dom



//THIS CHANGES THE FORMATTING FOR TASK DUE DATE!
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