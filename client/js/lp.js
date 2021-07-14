window.onload = async function loadLPJSON() {
    let output = "";
    await fetch(restAPI + "/lps/" + getParameterByName("id")).then((response) => {
        if (response.status >= 400 && response.status < 600) {
            throw new Error("ERROR " + response.status);
        }
        return response;
    }).then((res) => res.json()).then(jsonData => {
        output += ` 
        <h1>${jsonData.name}</h1>
        <a href="artist.html?id=${jsonData.artist.id}"><h6>${jsonData.artist.name}</h6></a>
        <img src="data:image/jpeg;base64, ${jsonData.image.content}" alt="${jsonData.image.name}">
        <p class="mt-5 database-text">${jsonData.text}</p>
        <button type="button" class="delete-btn" onclick="deleteThis()">Delete</button>
    `
    }).catch((error) => {
        output += error.message
    });
    document.getElementById('container').innerHTML+=output;
}

function deleteThis(){
    fetch(restAPI + "/lps/" + getParameterByName("id"), { method: "DELETE" }).then(() =>{
        window.location.href = 'index.html';
    })
}