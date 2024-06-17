"use strict"

const removeHiddenAttribute = document.getElementById("hiddenForm");
const grabTitleInput = document.getElementById("titleInput");
const grabDescriptionInput = document.getElementById("descriptionInput");


removeHiddenAttribute.addEventListener('click', () => {
  if('click'){
    let u = document.getElementById('start')
    // console.log('uuu: ' +u);
    document.getElementById("removeHidden").hidden = false;
    sessionStorage.setItem('title', grabTitleInput.value);
    sessionStorage.setItem('description', grabDescriptionInput.value);
    console.log("Set title inp: " +sessionStorage.setItem('title', grabTitleInput.value));
    console.log("Set desc inp: " +sessionStorage.setItem('description', grabDescriptionInput.value));
  }
});

const x = document.getElementById('start');
x.addEventListener('change', ()=>{
  console.log(x.value)
  sessionStorage.setItem('taskDueDate', x.value)
  console.log("Set date: " +sessionStorage.setItem('taskDueDate', x.value));
})

const addHiddenAttribute = document.getElementById("cancelCreateCat");
addHiddenAttribute.addEventListener('click', () =>{
  if('click'){
    sessionStorage.clear();
    document.getElementById("removeHidden").hidden = true;
  }
});

const submitCategoryButton = document.getElementById("submitCategory");
function submitCategoryButtonListener () {
  submitCategoryButton.addEventListener('click', ()=>{})
};
if(submitCategoryButtonListener){
  grabTitleInput.value = sessionStorage.getItem('title');
  grabDescriptionInput.value = sessionStorage.getItem('description');
  console.log("get title inp: " +sessionStorage.getItem('title'));
  console.log("get desc inp: " +sessionStorage.getItem('description'));
  console.log("get task date: " +sessionStorage.getItem('taskDueDate'));
  x.value = sessionStorage.getItem('taskDueDate')
};

const newCategoryName = document.getElementById('catName');
if(submitCategoryButtonListener){
  newCategoryName.addEventListener('keyup', ()=>{
    const keyUpNewCategory = newCategoryName.value;
    sessionStorage.setItem('newCategory', keyUpNewCategory);
  })
}


const grabCheckboxInput = document.getElementsByName("cater")
for(let singleCheckbox of grabCheckboxInput){
  let boxName = singleCheckbox.id;
  // console.log(singleCheckbox)
 singleCheckbox.addEventListener('change', function(e) {
  if(submitCategoryButtonListener){
    if (this.checked) {
      console.log(`Checkbox ${boxName} is checked..`);
      document.getElementById('sobmit').disabled = false;
    }
    // https://stackoverflow.com/questions/14544104/checkbox-check-event-listener
    // https://codepen.io/simonjvardy/pen/ZEpEgEj
  }
  });
}

const grabAllChildElements = document.getElementById('cici').children;
// console.log(grabAllChildElements);
for(let i = 1; i < grabAllChildElements.length; i++){
  let categoryName = grabAllChildElements[i].children[1].id;
  // console.log(categoryName);

  newCategoryName.value = sessionStorage.getItem('newCategory');
  console.log(newCategoryName.value);
  // console.log(categoryName);

  if(newCategoryName.value == categoryName){
    let b = document.getElementById(categoryName)
    b.checked = true;
    document.getElementById('sobmit').disabled = false;
    document.getElementById("html").setAttribute("checked","checked")



    console.log(document.getElementById(categoryName))
  }
}

const partialsNavBar = document.getElementById('navBar');
function navBarListener() {
  partialsNavBar.addEventListener('click', ()=>{})
};
if(navBarListener){
  sessionStorage.clear();
};

const createTaskSubmitButton = document.getElementById('sobmit');
function createTaskListener() {
  createTaskSubmitButton.addEventListener('click', ()=>{})
  // createFormSubmitButton.addEventListener('click', ()=>{})
};
if(createTaskListener){
  sessionStorage.clear();
}




//This adds current date into calendar when user wants to Create a Task
let getHiddenCurrentDate = document.querySelector('.dateToInsertIntoCalendar').textContent
let setDateIntoCalendar = document.querySelector('input[type="date"]');
setDateIntoCalendar.min = getHiddenCurrentDate
// https://developer.mozilla.org/en-US/play

