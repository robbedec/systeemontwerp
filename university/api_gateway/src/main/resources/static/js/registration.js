 const REGISTRATION_URL = "api/registration/"
const FACULTY_URL = "api/faculty/"
const INVOICE_URL = "api/invoice/"

const facultyMap = new Map();
const registrationMap = new Map();

const facultylist = []

async function getRegistrationOverview(studentId){ //for students --> readonly
    const registrationsResponse = await fetch(REGISTRATION_URL + "student/" + studentId + "/");
    return registrationsResponse;
}

async function acceptRegistrationCall(registrationId) {
	var xhr = new XMLHttpRequest();
	xhr.open("PUT", REGISTRATION_URL + registrationId + "/accept/");
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.onreadystatechange = function () {
   	if (xhr.readyState === 4) {
		if(xhr.status == 200){
			window.alert(xhr.responseText)
			console.log("Accept registration call:");
      		console.log(xhr.responseText);	
		}
   	}};
   	xhr.send();
}

async function rejectRegistrationCall(registrationId) {
	var xhr = new XMLHttpRequest();
	xhr.open("PUT", REGISTRATION_URL + registrationId + "/reject/");
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.onreadystatechange = function () {
   	if (xhr.readyState === 4) {
		if(xhr.status == 200){
			window.alert(xhr.responseText)
			console.log("Reject registration call");
      		console.log(xhr.responseText);	
		}
   	}};
   	xhr.send();
}

async function payInvoicesCall(studentNumber){
	var xhr = new XMLHttpRequest();
	xhr.open("PUT", INVOICE_URL + studentNumber + "/pay/");
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.onreadystatechange = function () {
   	if (xhr.readyState === 4) {
		console.log(xhr);
		if(xhr.status == 200){
			window.alert(xhr.responseText)
			console.log("Reject registration call");
      		console.log(xhr.responseText);	
		}
   	}};
   	xhr.send();	
}

async function expireInvoiceCall(studentNumber){
	var xhr = new XMLHttpRequest();
	xhr.open("PUT", INVOICE_URL + studentNumber + "/expire/");
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.onreadystatechange = function () {
   	if (xhr.readyState === 4) {
		if(xhr.status == 200){
			window.alert(xhr.responseText)
			console.log("Reject registration call");
      		console.log(xhr.responseText);	
		}
   	}};
   	xhr.send();	
}

async function getFaculties(){
    const response = await fetch(FACULTY_URL);    
    return response;
}

function acceptRegistration(){	
	registrationId = document.getElementById("registrationIdInput").value;
	const response = acceptRegistrationCall(registrationId).then((response) => {
		console.log(response);
	})
}

function rejectRegistration(){	
	registrationId = document.getElementById("registrationIdInput").value;
	const response = rejectRegistrationCall(registrationId).then((response) => {
		console.log(response);
	})
}



function sendRegistration(event){	
	var xhr = new XMLHttpRequest();
	xhr.open("POST", REGISTRATION_URL, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	if (xhr.readyState === 4) {
		if(xhr.status == 200){
			window.alert(xhr.responseText)
			console.log("Send new registration call:");
      		console.log(xhr.responseText);	
		}
   	}
	facultySelect = document.getElementById("facultySelect");	
	degreeSelect = document.getElementById("degreeSelect");
	const body = {
		email: document.getElementById("emailInput").value,
		name: document.getElementById("nameInput").value,
		firstName: document.getElementById("firstNameInput").value,
		dateOfBirth: document.getElementById("dateOfBirthInput").value,
		faculty: facultySelect.value,
		degree: degreeSelect.value		
	};
	console.log(JSON.stringify(body));
	xhr.send(JSON.stringify(body));	
	event.preventDefault();
	
}



function payInvoices(){
	let accountId = document.getElementById("accountIdInvoiceInput").value;
	const response = payInvoicesCall(accountId).then((response) => {
		console.log("Invoices paid")
		console.log(response);
	})
}

function expireInvoices(){
	let accountId = document.getElementById("accountIdInvoiceInput").value;
	const response = expireInvoiceCall(accountId).then((response) => {
		console.log("Invoices expired")
		console.log(response);
	})
}

function fillFacultySelect(){    
    const select = document.getElementById("facultySelect");
    getFaculties().then(response => response.json()).then(data => {
		console.log(data);
		data.forEach(faculty => {
			facultyMap.set(faculty.facultyName, faculty.degrees)
        	const optionNode = document.createElement("option");
        	optionNode.value = faculty.facultyName;
        	optionNode.innerHTML = faculty.facultyName;
        	select.append(optionNode);
        	console.log(facultyMap.get(faculty.facultyName));	
		});
		
    });
}

function showDegrees(faculty){
    const degreeSelect = document.getElementById("degreeSelect");    
    const degreeList = facultyMap.get(faculty);
    
    //empty select
    var i, L = degreeSelect.options.length - 1;
    for(i = L; i>=0; i--) {
        degreeSelect.remove(i);
    }

    //fill select
    degreeList.forEach(el => {
        const optionNode = document.createElement("option");
        optionNode.value = el.degreeName;
        optionNode.innerHTML = el.degreeName;
        degreeSelect.append(optionNode);
    });
}

function showOverview() {
	const studentId = document.getElementById("studentIdInput").value;
	const parent = document.getElementById("overview");
	//remove existing list (if there is one)	
	while(parent.firstChild) {
		parent.removeChild(parent.lastChild);
	}
	//make new list
	getRegistrationOverview(studentId).then(response => response.json()).then(data => {
		data.forEach(registration => {
			registrationMap.set(registration.registrationId, registration);
			const btn = document.createElement("button");
			btn.className = "collapsible";
			btn.value = registration.registrationDate + " --- " + "Status: " + registration.status;
			btn.innerHTML = registration.registrationDate + " --- " + "Status: " + registration.status;
			const divEl = document.createElement("div");
			divEl.className="overview_content";
			
			//studentnr
			const studentNrLabel = document.createElement("label");
			const studentNrInput = document.createElement("input");
			studentNrInput.type = "text";
			studentNrInput.value = registration.accountId;
			studentNrInput.readOnly = true;
			//firstname
			const firstNameLabel = document.createElement("label");
			const firstNameInput = document.createElement("input");
			firstNameInput.type = "text";
			firstNameInput.value = registration.firstName;
			firstNameInput.readOnly = true;
			//name
			const nameLabel = document.createElement("label");
			const nameInput = document.createElement("input");
			nameInput.type = "text";
			nameInput.value = registration.name;
			nameInput.readOnly = true;
			//email
			const emailLabel = document.createElement("label");
			const emailInput = document.createElement("input");
			emailInput.type = "text";
			emailInput.value = registration.email;
			emailInput.readOnly = true;
			//faculty
			const facultyLabel = document.createElement("label");
			const facultyInput = document.createElement("input");
			facultyInput.type = "text";
			facultyInput.value = registration.faculty;
			facultyInput.readOnly = true;
			//degree 
			const degreeLabel = document.createElement("label");
			const degreeInput = document.createElement("input");
			degreeInput.type = "text";
			degreeInput.value = registration.degree;
			degreeInput.readOnly = true;
			//status
			const statusLabel = document.createElement("label");
			const statusInput = document.createElement("input");
			statusInput.type = "text";
			statusInput.value = registration.status;
			statusInput.readOnly = true;
			
			divEl.append(studentNrLabel, studentNrInput, firstNameLabel, firstNameInput, nameLabel, nameInput, emailLabel, nameInput, facultyLabel, facultyInput, degreeLabel, degreeInput, statusLabel, statusInput);
			parent.append(btn, divEl)
			
		});		
	});
	
	
	//collapsible content
	var coll = document.getElementsByClassName("collapsible");
	var i;

	for (i = 0; i < coll.length; i++) {
  		coll[i].addEventListener("click", function() {
    	this.classList.toggle("active");
    	var content = this.nextElementSibling;
    	if (content.style.display === "block") {
      		content.style.display = "none";
    	} else {
      		content.style.display = "block";
    	}
  		});
	}
}

function attachEventListeners(){
	document.getElementById("newRegistrationForm").addEventListener("submit", sendRegistration);
}

function init() {
    fillFacultySelect();
    attachEventListeners();
}




