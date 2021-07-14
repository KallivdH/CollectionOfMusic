window.onload = async function loadArtistJSON() {
    let output = "";
    await fetch(restAPI + "/artists/" + getParameterByName("id")).then((response) => {
        if (response.status >= 400 && response.status < 600) {
            throw new Error("ERROR " + response.status);
        }
        return response;
    }).then((res) => res.json()).then(jsonData => {
        output += ` 
            <h1>${jsonData.name}</h1>
            <img src="data:image/jpeg;base64, ${jsonData.image.content}" alt="${jsonData.image.name}">
            <p class="mt-5 database-text">${jsonData.text}</p>
            <h2 class="mt-5">Discography</h2>
            <div class="d-flex flex-wrap mb-5" id="lp-container">
        `
        jsonData.discography.forEach(lp => {
            output += `
                <div class="blocks">
                    <img src="data:image/jpeg;base64, ${lp.image.content}" alt="${lp.image.name}">
                    <div class="p-4">
                        <h5 class="mb-2">${lp.name}</h5>
                        <h6 class="text-muted mb-2"></h6>
                        <a href="lp.html?id=${lp.id}">LP Page</a>
                    </div>
                </div>
            `
        })
        output += `</div>`
    }).catch((error) => {
        output += error.message
    });
    document.getElementById('container').innerHTML+=output;
}