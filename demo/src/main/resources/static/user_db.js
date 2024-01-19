document.addEventListener("DOMContentLoaded", function() {
	resultSection.addEventListener("click", function(e) {
    	let dataCard = e.target.closest('.data-card');
    	toggleDataCard(dataCard);
  	});
});
      
let resultSection = document.getElementById('json');
  
async function getJsonData() {
	const uid = document.getElementById("input-id").value;
	const uname = document.getElementById("input-name").value;
	const uemail = document.getElementById("input-email").value;
  
	if (uid) {
		const response = await fetch(`http://localhost:8080/user/id/${uid}`);
		const jsonData = await response.json();
		console.log(jsonData);

		renderDataCard(jsonData);
	}
  	else if (uname) {
	  	const response = await fetch(`http://localhost:8080/user/name/${uname}`);
		
      	const jsonData = await response.json();
      	console.log(jsonData);
      
      	renderDataCard(jsonData);
  	}
  	else if (uemail) {
	  	const response = await fetch(`http://localhost:8080/user/email/${uemail}`);
		
      	const jsonData = await response.json();
      	console.log(jsonData);
      
	  	renderDataCard(jsonData);
  	}
  	else {
	  	const response = await fetch(`http://localhost:8080/user/list`);
		
      	const jsonData = await response.json();
      	console.log(jsonData);
      
      	resultSection.innerHTML = '';
      	for (let i = 0; i < jsonData.length; i++) {
        	let number = jsonData[i].id;
        	let template = `
          		<section class="data-card">
            		<h1 class="card-number">number : ${number}</h1>
            		<div class="user-id d:none">id : ${jsonData[i].id}</div>
            		<p class="user-email d:none">
                		email : ${jsonData[i].email}
            		</p>
            		<p class="user-name d:none">
                		name : ${jsonData[i].name}
            		</p>
          		</section>
    		`;

        	resultSection.insertAdjacentHTML("beforeend", template);
      	}
  	}
}

async function postJsonData() {
    const pemail = document.getElementById("input-email").value;
    const pname = document.getElementById("input-name").value;
      
    const response = await fetch("http://localhost:8080/userdb/create", {
		method: "POST",
        headers: {
          "Content-Type" : "application/json"
        },
        body: JSON.stringify({
          "email": pemail,
          "name": pname
        })
	});
      
    const jsonData = await response.json();
    console.log(jsonData);

    renderDataCard(jsonData);
}

async function putJsonData() {
    const pid = document.getElementById("input-id").value;
    const pemail = document.getElementById("input-email").value;
    const pname = document.getElementById("input-name").value;
      
    const response = await fetch(`http://localhost:8080/update/${pid}`, {
		method: "PUT",
        headers: {
          "Content-Type" : "application/json"
        },
        body: JSON.stringify({
          "email": pemail,
          "name": pname,
        })
    });
      
    const jsonData = await response.json();
    console.log(jsonData);

    renderDataCard(jsonData);
}

async function deleteJsonData() {

    const pid = document.getElementById("input-id").value;
      
    const response = await fetch(`http://localhost:8080/delete/${pid}`, {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json"
        },
    });
      
    const jsonData = await response.json();
    console.log(jsonData);

    renderDataCard(jsonData);
}
      
      
      
/* function area */
function createDataCardTemplate(jsonData) {
	return `
		<section class="data-card">
		<h1 class="card-number">number : ${jsonData.id}</h1>
		    <div class="user-id d:none">id : ${jsonData.id}</div>
		    <p class="user-email d:none">
		        email : ${jsonData.email}
		    </p>
		    <p class="user-name d:none">
		        name : ${jsonData.name}
		    </p>
		</section>
		`;
}
	
function renderDataCard(jsonData) {
    resultSection.innerHTML = '';
    const template = createDataCardTemplate(jsonData);
    resultSection.insertAdjacentHTML("beforeend", template);
}

function toggleDataCard(dataCard) {
    let cardNumber = dataCard.querySelector('.card-number');
    let userId = dataCard.querySelector('.user-id');
    let userEmail = dataCard.querySelector('.user-email');
    let userName = dataCard.querySelector('.user-name');
        
    let arrayList = [userId, userEmail, userName];

    if (dataCard.classList.contains('open')) {
		dataCard.classList.remove('open');
        for (let i = 0; i < arrayList.length; i++) {
            arrayList[i].classList.add('d:none');
            arrayList[i].classList.remove('show');
        }
    }
    else {
		dataCard.classList.add('open');
		for (let i = 0; i < arrayList.length; i++) {
		    arrayList[i].classList.remove('d:none');
		    arrayList[i].classList.add('show');
		}
    }
}
