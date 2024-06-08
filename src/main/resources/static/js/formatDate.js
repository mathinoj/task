"use strict"

let g = document.querySelectorAll('.lish')
let bz = document.querySelectorAll('.hey')
// console.log(bz);
// console.log(g)

for(let key = 1; key < g.length; key++){
// for(let key in g){
      // console.log("key: " +key);
  if(key >= 0){
    // console.log("key: " +key);
    let x = parseInt(key)
    // console.log("xx: " +typeof(x));
    let seperate = g[x].innerHTML
    // console.log(seperate);
    let changeCurrentPublishFormat = new Date(seperate);
    let newFormat = {
      day: "2-digit",
      month: "short",
      year: "numeric",
      };
      let formatDate = changeCurrentPublishFormat.toLocaleDateString(
      "en-US", {timeZone: 'UTC'},
      newFormat
      );
      // console.log(formatDate);
      // seperate.replace(seperate, formatDate)
      let you = bz[x].textContent;
      // console.log(bz[x]);
      console.log(you);
      if(you == ""){
        let o = bz[x]
        let b = document.createTextNode(formatDate)
        o.appendChild(b)
        // console.log(bz[x]);

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