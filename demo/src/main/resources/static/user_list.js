window.addEventListener("load", function(){
	let readButton = document.querySelector("form .read-button");
	let resultSection = document.querySelector("table tbody");

	readButton.onclick = async (e)=>{
		e.preventDefault();
		let id = document.querySelector("input[name='id']").value;
		
		let response = await fetch(`/listdb/${id}`);
		let jsonData;
		if(response)
			jsonData = await response.json();
			
		resultSection.innerHTML = "";
		let template = `
			<tr>
                <td>|</td>
                <td>${jsonData.id}</td><td>|</td>
                <td>${jsonData.name}</td><td>|</td>
                <td>${jsonData.email}</td><td>|</td>
            </tr>
		`;
			
		resultSection.insertAdjacentHTML("beforeend", template);
	}
})