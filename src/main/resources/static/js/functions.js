function passwordVisibility() {
    // pw_ele = password element
    var pw_ele = document.getElementById("passwordInput");
    if (pw_ele.type === "password") {
        pw_ele.type = "text";
    } else {
        pw_ele.type = "password";
    }
}

function checkInputs() {
    let name = document.getElementById("nameInput").value
    let email = document.getElementById("emailInput").value
    let password = document.getElementById("passwordInput").value

    if (name === "") {
        alert('Udfyld venligst navn')
    }
    if (email === "") {
        alert('Udfyld venligst email')
    }
    if (password === "") {
        alert('Udfyld venligst password')
    } else
        alert('Registrering færdig! Du bliver viderestillet')
}


function checkLoginInput() {
    let email = document.getElementById("emailInput").value
    let password = document.getElementById("passwordInput").value

    if (email === "") {
        alert('Udfyld venligst email')
    }
    if (password === "") {
        alert('Udfyld venligst password')
    } else
        alert('Login var en success! Du viderestilles nu til dine ønskerlister.')
}


function checkWishInputs() {
    let title = document.getElementById("title").value
    let url = document.getElementById("url").value
    let price = document.getElementById("price").value


    if (title === "") {
        alert('Udfyld venligst en titel på dit ønske')
    }
    if (url === "") {
        alert('Udfyld venligst et link')
    }
    if (price === "") {
        alert('Udfyld venligst en pris')
    } else
        alert('Dit ønske er tilføjet!')
}


