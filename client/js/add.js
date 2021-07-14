const LPForm = document.getElementById('LPForm');
LPForm.onsubmit = async (event) => {
    event.preventDefault();
    const data = new FormData(event.target)
    let value = Object.fromEntries(data.entries())
    const imageId = value.image_id
    const artistId = value.artist_id
    delete value.image_id
    delete value.artist_id
    event.target.firstElementChild.setAttribute("disabled","");
    await fetch(restAPI+'/lps?imgId='+imageId+'&artistId='+artistId, {
        method: 'POST',
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
        event.target.firstElementChild.removeAttribute("disabled");
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
    event.target.firstElementChild.setAttribute("disabled","");
    await fetch(restAPI+'/artists?imgId='+imgID, {
        method: 'POST',
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
imgForm.onsubmit = async (e) => {
    e.preventDefault();
    const imgForm = e.currentTarget;
    const url = restAPI + "/images";
    try {
        const formData = new FormData(imgForm);
        e.target.firstElementChild.setAttribute("disabled", "");
        const response = await fetch(url, {
            method: 'POST',
            body: formData,
            mode: 'no-cors'
        });
        showMessage("✔", imgForm)
        setTimeout(() => {
            location.reload();
        }, 2000);
        console.log(response);
    } catch (error) {
        showMessage("❌", imgForm)
        e.target.firstElementChild.removeAttribute("disabled");
        console.error(error.message);
    }
    //
}

const uploadField = document.getElementById("imgUpload");
uploadField.onchange = function() {
    const error = ` <span class="text-danger">Max file size 5Mb</span>`
    if(this.files[0] == null)
        return;
    if(this.files[0].size > 5242880){
        this.value = "";
        document.getElementById("imgUploadLabel").innerHTML = "Upload" + error;
    }else {
        document.getElementById("imgUploadLabel").innerHTML = "Upload";

    }
};

window.onload = async function fillSelects() {
    const response = await fetch(restAPI+"/images/available");
    const jsonData = await response.json();

    let output;
    jsonData.forEach(function(image){
        output += ` 
      <option value="${image.id}">${image.name}</option>
      `
    });
    document.getElementById('LPImageSelect').innerHTML=output;
    document.getElementById('ArtistImageSelect').innerHTML=output;

    const response_artist = await fetch(restAPI+"/artists");
    const jsonData_artist = await response_artist.json();

    let output_artist;
    jsonData_artist.forEach(function(artist){
        output_artist += ` 
      <option value="${artist.id}">${artist.name}</option>
      `
    });
    document.getElementById('ArtistSelect').innerHTML=output_artist;
}

function showMessage(msg, parent){
    const message = document.createElement("div")
    message.classList.add("message")
    message.innerHTML = msg;
    parent.appendChild(message)
    setTimeout(() => { parent.removeChild(message) }, 2000);
}