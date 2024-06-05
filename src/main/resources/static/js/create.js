"use strict"

const removeHiddenAttribute = document.getElementById("hiddenForm");
const grabTitleInput = document.getElementById("titleInput");
const grabDescriptionInput = document.getElementById("descriptionInput");


removeHiddenAttribute.addEventListener('click', () => {
  if('click'){
    document.getElementById("removeHidden").hidden = false;
    sessionStorage.setItem('title', grabTitleInput.value);
    sessionStorage.setItem('description', grabDescriptionInput.value);
  }
});

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
};

const newCategoryName = document.getElementById('catName');
if(submitCategoryButtonListener){
  newCategoryName.addEventListener('keyup', ()=>{
    const keyUpNewCategory = newCategoryName.value;
    sessionStorage.setItem('newCategory', keyUpNewCategory);
  })
}

const grabAllChildElements = document.getElementById('cici').children;
for(let i = 1; i < grabAllChildElements.length; i++){
  let categoryName = grabAllChildElements[i].children[1].id;
  newCategoryName.value = sessionStorage.getItem('newCategory');
  if(newCategoryName.value == categoryName){
    document.getElementById(categoryName).checked = true;
    document.getElementById('sobmit').disabled = false;


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


const grabCheckboxInput = document.getElementsByName("cater")
for(let singleCheckbox of grabCheckboxInput){
  let boxName = singleCheckbox.id;
  console.log(singleCheckbox)
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
