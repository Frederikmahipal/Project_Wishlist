function passwordVisibility() {
    // pw_ele = password element
    var pw_ele = document.getElementById("passwordInput");
    if (pw_ele.type === "password") {
        pw_ele.type = "text";
    } else {
        pw_ele.type = "password";
    }
}