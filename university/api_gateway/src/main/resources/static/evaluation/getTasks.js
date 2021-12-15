function loadTasks() {
	fetch("/api/evaluation/tasks?userId=" + sessionStorage.userId, {
		method: "GET",
		mode: "cors"
	}).then(res => res.json()).then(tasks => {
		const table = document.createElement("table");
		const thead = document.createElement("thead");
		let th = document.createElement("th");
		th.innerText = "Course";
		thead.appendChild(th);
		th = document.createElement("th");
		th.innerText = "Task";
		thead.appendChild(th);
		th = document.createElement("th");
		th.innerText = "Due";
		thead.appendChild(th);
		th = document.createElement("th");
		th.innerText = "Weight";
		thead.appendChild(th);
		table.appendChild(thead);
		document.getElementById("tasks").appendChild(table);
		tasks.forEach(task => {
			const tr = document.createElement("tr");
			
			let td = document.createElement("td");
			td.innerText = task.courseName;
			tr.appendChild(td);

			td = document.createElement("td");
			td.innerText = task.description;
			tr.appendChild(td);

			td = document.createElement("td");
			td.innerText = task.dueDate;
			tr.appendChild(td);
			
			td = document.createElement("td");
			td.innerText = task.weight;
			tr.appendChild(td);

			td = document.createElement("td");
			let button = document.createElement("button");
			button.innerText = "Select";
			button.onclick = () => { selectedTask = task.taskId; selectTask(task.taskId); };
			td.appendChild(button);
			tr.appendChild(td);

			table.appendChild(tr);
		})
	}).catch(err => console.error(err));
}
let selectedTask;
document.body.onload = loadTasks();