window.addEventListener("load", function(){
	let readButton = document.querySelector("form .read-button");
	let resultSection = document.querySelector("table tbody");

	readButton.onclick = async (e)=>{
		e.preventDefault();
		let id = document.querySelector("input[name='id']").value;
		let name = document.querySelector("input[name='name']").value;
		let email = document.querySelector("input[name='email']").value;
		
		let response = await fetch(`/listdb`);
		
		let jsonData;
		
		if(response) {
			jsonData = await response.json();
			console.log(jsonData);
		}
		
	}
	
	function updateResultSection(resultSection, id, name, email) {
	    resultSection.innerHTML = "";
	   	let template = createTableRow(id, name, email);
	    resultSection.insertAdjacentHTML("beforeend", template);
	}
	
	/* function area */
	function createTableRow(id, name, email) {
		return `
	        <tr>
	            <td>|</td>
	            <td>${id}</td><td>|</td>
	            <td>${name}</td><td>|</td>
	            <td>${email}</td><td>|</td>
	        </tr>
        `;
	}
		
})