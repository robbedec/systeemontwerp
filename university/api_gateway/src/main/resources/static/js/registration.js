const REGISTRATION_URL = "/api/registration/"
const FACULTY_URL = "api/faculty/"

const facultyMap = new Map();
const registrationMap = new Map();

const facultylist = []

async function getRegistrationOverview(studentId){ //for students --> readonly
    const registrationsResponse = await fetch(REGISTRATION_URL + "student/" + studentId + "/");
    return registrationsResponse;
}

async function getUnhandledRegistrationList(){
    const registrationResponse = await fetch(BASE_URL); 
	if(!registrationResponse.ok){
		return await registrationResponse.body.getReader().read();
	}
	const registrations = await registrationResponse.json();    
    return registrations;
}

async function acceptRegistration(registrationId) {
    const response = await fetch(BASE_URL + registrationId);
    if(!response.ok){
        return await response.body.getReader().read()
    }
}

async function rejectRegistration(registrationId) {
    const response = await fetch(BASE_URL + registrationId);
    
}

function showRegistrationsList(){
    const registrations = getUnhandledRegistrationList();
    
}

async function getFaculties(){
    const response = await fetch(FACULTY_URL);    
    return response;
}

function sendRegistration(event){	
	var xhr = new XMLHttpRequest();
	xhr.open("POST", REGISTRATION_URL, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.onload = function() {  //request succesful
		window.alert("Registraiton succesful");
	};
	
	xhr.onerror = function(){
		window.alert("Registration failed");
	}
	
	xhr.send(new FormData(event.target));	
	event.preventDefault();
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
	getRegistrationOverview(studentId).then(response => response.json()).then(data => {
		data.forEach(registration => {
			registrationMap.set(registration.registrationId, registration)
			const parent = document.getElementById("overview");
			const btn = document.createElement("button");
			btn.className = "collapsible";
			btn.value = registration.registrationDate + " --- " + "Status: " + registration.status;
			const divEl = document.createElement("div");
			divEl.className="overview_content";
			parent.after(btn, divEl)
			
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
			degreeInput.type = "text";
			degreeInput.value = registration.faculty;
			degreeInput.readOnly = true;
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



