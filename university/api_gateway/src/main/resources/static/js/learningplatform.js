const BASE_URL = "/api/learningplatform/"
const STUDENT_URL = BASE_URL + "students/"
const TEACHERS_URL = BASE_URL + "teachers/"

async function getTeacherCourses(teacherId){
    const coursesResponse = await fetch(TEACHERS_URL + teacherId +  "/courses/"); 
	const courses = await coursesResponse.json();
    
    return courses;
}

async function getStudentCourses(studentId){
    const coursesResponse = await fetch(STUDENT_URL + studentId + "/courses/"); 
	if(!coursesResponse.ok){
		return await coursesResponse.body.getReader().read();
	}
	const courses = await coursesResponse.json();
    
    return courses;
}

async function getStudentCourseMaterials(studentId, courseId){
    const courseMaterialsResponse = await fetch(STUDENT_URL + studentId + "/courses/" + courseId + "/materials/"); 
	if(!courseMaterialsResponse.ok){
		return await courseMaterialsResponse.body.getReader().read();
	}
	const courses = await courseMaterialsResponse.json();
    return courses;
}

async function getStudentCourseAnnouncements(studentId, courseId){
    const courseAnnouncementsResponse = await fetch(STUDENT_URL + studentId + "/courses/" + courseId + "/announcements/"); 
	if(!courseAnnouncementsResponse.ok){
		return await coursesResponse.body.getReader().read();
	}
	const courseAnnouncements = await courseAnnouncementsResponse.json();
    return courseAnnouncements;
}

async function addCourseAnnouncements(teacherId, courseId, message){
	data = {message:message};
	
    const courseAnnouncementsResponse = await fetch(
	TEACHERS_URL + teacherId + "/courses/" + courseId + "/announcements/add", 
	{
		  method: 'PUT',
  		  headers: {'Content-Type': 'application/json'},
  		  body: JSON.stringify(data)
	}
	
	).then(res => {
		alert("added course announcement");
	}).catch(error => {
		alert("something went wrong");
		console.log(error);	
	})
}
async function addCourseMaterials(teacherId, courseId, fileName){
	data = {fileName:fileName, fileBase64: "YmVzdGFuZAo="};
    const courseAnnouncementsResponse = await fetch(
	TEACHERS_URL + teacherId + "/courses/" + courseId + "/materials/add", 
	{
		  method: 'PUT',
  		  headers: {'Content-Type': 'application/json'},
  		  body: JSON.stringify(data),
	}
	
	).then(res => {
		alert("added course material");
	}).catch(error => {
		alert("something went wrong");
		console.log(error);	
	})
}
async function makeCourseMaterialsVisible(teacherId, courseId){
    const courseAnnouncementsResponse = await fetch(
	TEACHERS_URL + teacherId + "/courses/" + courseId + "/materials/makevisible", 
	{
		  method: 'PUT',
  		  headers: {'Content-Type': 'application/json'},
	}
	).then(res => {
		alert("all course material is now visible");
	}).catch(error => {
		alert("something went wrong");
		console.log(error);	
	})
}

function uint8arrayToString(myUint8Arr){
   return String.fromCharCode.apply(null, myUint8Arr);
}

function showCourseContents(studentId, courseId){
		let courseContentsDiv = document.getElementById("courseContent");
		const coursesAnnouncementsResponse = getStudentCourseAnnouncements(studentId, courseId);
		coursesAnnouncementsResponse.then(
			coursesResponse => {
				if(uint8arrayToString(coursesResponse.value) == "STUDENT_NOT_FOUND")
					throw new Error("Student not found")
				else if(uint8arrayToString(coursesResponse.value) == "COURSE_ACCESS_DENIED"){
					throw new Error("Course access denied: either invoice was yet not paid or plagiarism was committed...")
				}
				else{
					courseContentsDiv.innerHTML= "<h3>" + coursesResponse.courseName + "</h3>";
					courseContentsDiv.innerHTML+= "<h5>" + "Announcements" + "</h5>";
					coursesResponse.courseAnnouncements.forEach(
							ca => {
								let paragraph = document.createElement("p");
								paragraph.innerHTML = ca.timestamp + "\t" + ca.message;
								courseContentsDiv.appendChild(paragraph);
								courseContentsDiv.appendChild(document.createElement("br"));
								courseContentsDiv.appendChild(document.createElement("br"));
							}
						)
				}				

		}).catch(error => {
			alert(error);
		})
	
		const coursesMaterialsResponse = getStudentCourseMaterials(studentId, courseId);
		coursesMaterialsResponse.then(
			coursesResponse => {
				if(uint8arrayToString(coursesResponse.value) == "STUDENT_NOT_FOUND")
					throw new Error("Student not found")
				else if(uint8arrayToString(coursesResponse.value) == "COURSE_ACCESS_DENIED"){
					throw new Error("Course access denied: either invoice was yet not paid or plagiarism was committed...")
				}
				else{
					courseContentsDiv.innerHTML+= "<h5>" + "Materials" + "</h5>";
					coursesResponse.courseMaterials.forEach(
							ca => {
								let paragraph = document.createElement("p");
								paragraph.innerHTML = ca.timestamp + "\t" + ca.name;
								courseContentsDiv.appendChild(paragraph);
								courseContentsDiv.appendChild(document.createElement("br"));
								courseContentsDiv.appendChild(document.createElement("br"));
							}
						)
				}				

		}).catch(error => {
			alert(error);
		})
}

window.onload = function(){
	// GET COURSES OF STUDENT
	let getAllStudentCoursesButton = document.getElementById("getStudentCoursesButton");
	let myCoursesDiv = document.getElementById("myCoursesDiv");
	
	getAllStudentCoursesButton.addEventListener("click", function(){
		let studentIdInput = document.getElementById("studentIdInput");
		let studentId = studentIdInput.value;
		console.log("GOT " + studentId + " from student id input textbox");
		const coursesResponse = getStudentCourses(studentId);
		coursesResponse.then(
			coursesResponse => {
				if(uint8arrayToString(coursesResponse.value) == "STUDENT_NOT_FOUND")
					throw new Error("Student not found")
				else if(uint8arrayToString(coursesResponse.value) == "COURSE_ACCESS_DENIED"){
					throw new Error("Course access denied: either invoice was yet not paid or plagiarism was committed...")
				}
				else{
					myCoursesDiv.innerHTML= "";

					coursesResponse.forEach(course => {
						let link = document.createElement("a");
						let linkText = document.createTextNode(course.name);
						link.appendChild(linkText);
						link.setAttribute('class', "courseButton");
						link.addEventListener("click", function(){
							console.log(course.id);
							console.log(studentId);
							showCourseContents(studentId, course.id);
						})
			
						myCoursesDiv.appendChild(document.createElement("br"));
						myCoursesDiv.appendChild(link);
						myCoursesDiv.appendChild(document.createElement("br"));
						myCoursesDiv.appendChild(document.createElement("br"));
					});
				}				

		}).catch(error => {
			alert(error);
		})
	});
		// GET COURSES OF TEACHER
	let getTeacherCoursesButton = document.getElementById("getTeacherCoursesButton");
	let teacherCoursesDiv = document.getElementById("teacherCoursesDiv");
	
	getTeacherCoursesButton.addEventListener("click", function(){
		let teacherIdInput = document.getElementById("teacherIdInput");
		let teacherId = teacherIdInput.value;
		console.log("GOT " + teacherId + " from teacher id input textbox");
		const coursesResponse = getTeacherCourses(teacherId);
		coursesResponse.then(
			cr => {
				teacherCoursesDiv.innerHTML= "";
				cr.forEach(course => {
					let courseId = course.id;
					if(course.teacherID == teacherId){
						let paragraph = document.createElement("p");
						paragraph.innerHTML = course.name;
						let addAnnouncementButton = document.createElement("a");
						addAnnouncementButton.innerHTML = "Add announcement";
						addAnnouncementButton.setAttribute('class', "optionButton");
						addAnnouncementButton.addEventListener("click", function(){
							let msg = prompt("What is the announcement message?");
							addCourseAnnouncements(teacherId, courseId, msg);					
						})
						
						let addCourseMaterialButton = document.createElement("a");
						addCourseMaterialButton.innerHTML = "Add course material";
						addCourseMaterialButton.setAttribute('class', "optionButton");
						addCourseMaterialButton.addEventListener("click", function(){
							let fileName = prompt("What is the filename?");
							addCourseMaterials(teacherId, courseId, fileName);				
						})
						
						let makeCourseMaterialVisibleButton = document.createElement("a");
						makeCourseMaterialVisibleButton.innerHTML = "Make material visible"
						makeCourseMaterialVisibleButton.setAttribute('class', "optionButton");
						makeCourseMaterialVisibleButton.addEventListener("click", function(){
							makeCourseMaterialsVisible(teacherId, courseId);				
						})
						
						teacherCoursesDiv.appendChild(paragraph);
						teacherCoursesDiv.appendChild(addAnnouncementButton);
						teacherCoursesDiv.appendChild(addCourseMaterialButton);
						teacherCoursesDiv.appendChild(makeCourseMaterialVisibleButton);
						teacherCoursesDiv.appendChild(document.createElement("br"));
						teacherCoursesDiv.appendChild(document.createElement("br"));
					}
				});
		}).catch(error => {
			alert(error);
		})
	});
}