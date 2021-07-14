
async function deleteFetch(event, url) {
    event.preventDefault();
    const data = new FormData(event.target)
    const value = Object.fromEntries(data.entries())
    event.target.firstElementChild.setAttribute("disabled","");
    await fetch(restAPI+url+value.delete_id, {
        method: 'DELETE'
    }).then((response) => {
        if (response.status >= 400 && response.status < 600) {
            throw new Error("ERROR " + response.status);
        }
        return response;
    }).then(r => {
        showMessage("✔", event.target)
        setTimeout(() => { location.reload(); }, 2000);
        console.log(r);
    }).catch(e => {
        showMessage("❌", event.target)
        event.target.firstElementChild.removeAttribute("disabled");
        console.log(e);
    })
}

const LPForm = document.getElementById('LPForm');
LPForm.onsubmit = async (event) => {
    await deleteFetch(event, "/lps/")
}

const ArtistForm = document.getElementById('ArtistForm');
ArtistForm.onsubmit = async (event) => {
    await deleteFetch(event, "/artists/")
}

const imgForm = document.getElementById('imageForm');
imgForm.onsubmit = async (event) => {
    await deleteFetch(event, "/images/")
}

async function fillSelectsFetch(url){
    const response = await fetch(url);
    const jsonData = await response.json();
    let output;
    jsonData.forEach(function(r){
        if(r.discography !== undefined){
            output += `<option value="${r.id}">${r.name} (${r.discography.length})</option>`
        }else if(r.artist !== undefined) {
            output += `<option value="${r.id}">${r.name} (${r.artist.name})</option>`
        }else {
            output += `<option value="${r.id}">${r.name}</option>`
        }
    });
    return output;
}

window.onload = async function fillSelects() {
    document.getElementById('ImageSelect').innerHTML += await fillSelectsFetch(restAPI+"/images/available");
    document.getElementById('EditArtistSelect').innerHTML += await fillSelectsFetch(restAPI+"/artists");
    document.getElementById('EditLPSelect').innerHTML += await fillSelectsFetch(restAPI+"/lps");
}

function showMessage(msg, parent){
    const message = document.createElement("div")
    message.classList.add("message")
    message.innerHTML = msg;
    parent.appendChild(message)
    setTimeout(() => { parent.removeChild(message) }, 2000);
}