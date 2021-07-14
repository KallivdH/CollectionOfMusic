const LPForm = document.getElementById('LPForm');
LPForm.onsubmit = async (event) => {
    event.preventDefault();
    const data = new FormData(event.target)
    let value = Object.fromEntries(data.entries())
    const imageId = value.image_id
    const artistId = value.artist_id
    delete value.image_id
    delete value.artist_id
    value.id = document.getElementById('EditLPSelect').value
    event.target.firstElementChild.setAttribute("disabled","");
    await fetch(restAPI+'/lps/'+document.getElementById('EditLPSelect').value+'?imgId='+imageId+'&artistId='+artistId, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(value)
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
        LPForm.firstElementChild.removeAttribute("disabled");
        console.log(e);
    })
}

const ArtistForm = document.getElementById('ArtistForm');
ArtistForm.onsubmit = async (event) => {
    event.preventDefault();
    const data = new FormData(event.target);
    let value = Object.fromEntries(data.entries());
    const imgID = value.image_id
    delete value.image_id
    value.id = document.getElementById('EditArtistSelect').value
    event.target.firstElementChild.setAttribute("disabled","");
    await fetch(restAPI+'/artists/'+ document.getElementById('EditArtistSelect').value +'?imgId='+imgID, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(value)
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
        LPForm.firstElementChild.removeAttribute("disabled");
        console.log(e);
    })
}

const imgForm = document.getElementById('imageForm');
imgForm.onsubmit = async (event) => {
    event.preventDefault();
    const data = new FormData(event.target);
    let value = Object.fromEntries(data.entries());
    value.id = document.getElementById('ImageSelect').value;
    event.target.firstElementChild.setAttribute("disabled","");
    await fetch(restAPI + "/images/" + document.getElementById('ImageSelect').value, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(value)
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
        LPForm.firstElementChild.removeAttribute("disabled");
        console.log(e);
    })
}

/**
 * when no object is given all object wil be loaded and added to do dom as option tags
 * fillSelectsFetch(url)
 *
 * if an imageObject is given the same actions happen like just like fillSelectsFetch(url) + imageObject wil be added as extra option and gets a selected attribute.
 * fillSelectsFetch(url, imageObject)
 *
 * if an artistObject is given the same actions happen like just like fillSelectsFetch(url) + there wil be a check for the currentArtist, if theres a match that particular option gets the selected attribute
 * fillSelectsFetch(url, artistObject)
 **/
async function fillSelectsFetch(url, object){
    let hasMatch = false;
    const response = await fetch(url);
    const jsonData = await response.json();
    let output;
    jsonData.forEach(function(r){
        if(object !== undefined && object.id === r.id) {
            output += `<option selected value="${r.id}">${r.name}</option>`
            hasMatch = true
        }else {
            output += `<option value="${r.id}">${r.name}</option>`
        }
    });

    if(object !== undefined && !hasMatch){
        output += `<option value="${object.id}" selected>${object.name}</option>`
    }
    return output;
}

window.onload = async function fillSelects() {
    document.getElementById('ImageSelect').innerHTML += await fillSelectsFetch(restAPI+"/images");
    document.getElementById('EditArtistSelect').innerHTML += await fillSelectsFetch(restAPI+"/artists");
    document.getElementById('EditLPSelect').innerHTML += await fillSelectsFetch(restAPI+"/lps");
}

document.getElementById('ImageSelect').onchange = async function () {
    const response = await fetch(restAPI+"/images/" + document.getElementById('ImageSelect').value);
    const jsonData = await response.json();
    document.getElementById('imgUploadName').value = jsonData.name;
    document.getElementById('imgUpload').value = jsonData.content;

}

document.getElementById('EditArtistSelect').onchange = async function () {
    const response = await fetch(restAPI+"/artists/" + document.getElementById('EditArtistSelect').value);
    const jsonData = await response.json();
    document.getElementById('ArtistImageSelect').innerHTML = await fillSelectsFetch(restAPI+"/images/available", jsonData.image);
    document.getElementById('ArtistName').value = jsonData.name;
    document.getElementById('ArtistText').value = jsonData.text;
}

document.getElementById('EditLPSelect').onchange = async function () {
    const response = await fetch(restAPI+"/lps/" + document.getElementById('EditLPSelect').value);
    const jsonData = await response.json();
    document.getElementById('LPImageSelect').innerHTML = await fillSelectsFetch(restAPI+"/images/available", jsonData.image);
    document.getElementById('ArtistSelect').innerHTML = await fillSelectsFetch(restAPI+"/artists", jsonData.artist);
    document.getElementById('LPName').value = jsonData.name;
    document.getElementById('LPText').value = jsonData.text;
}

function showMessage(msg, parent){
    const message = document.createElement("div")
    message.classList.add("message")
    message.innerHTML = msg;
    parent.appendChild(message)
    setTimeout(() => { parent.removeChild(message) }, 2000);
}