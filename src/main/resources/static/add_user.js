function submitForm(e) {
    e.preventDefault();
    let form = e.target;
    let formData = new FormData(form);
    let jsonData = {};
    formData.forEach(function(value, key){
        jsonData[key] = value;
    });
    fetch('/users', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(jsonData)
    }).then(response => response.json())
      .then(data => {
    	  console.log(data);
    	  window.location.href = '/user';
      })
      .catch(error => console.error('Error:', error));
}