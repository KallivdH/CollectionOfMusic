window.onload = async function loadLPs() {
    let output = "";
    await fetch(restAPI+"/lps").then((response) => {
        if (response.status >= 400 && response.status < 600) {
            throw new Error("ERROR " + response.status);
        }
        return response;
    }).then((res) => res.json()).then(jsonData => {
        if(jsonData.length > 0) {
            jsonData.forEach(lp => {
                output += ` 
                <div class="blocks">
                    <img src="data:image/jpeg;base64, ${lp.image.content}" alt="${lp.image.name}">
                    <div class="p-4">
                        <h5>${lp.name}</h5>
                        <h6 class="text-muted">${lp.artist.name}</h6>
                        <a href="lp.html?id=${lp.id}">LP page</a>
                        <a href="artist.html?id=${lp.artist.id}">Artist page</a>
                    </div>
                </div>
            `
            })
        }else {
            output += `<h1>No lp's found</h1>`
        }
    }).catch((error) => {
        output += error.message
    });
    document.getElementById('lp-container').innerHTML=output;
}