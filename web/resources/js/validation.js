
function validateEmail(email) {
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}
function validate() {
    var userEmail = document.getElementById("userEmail").value;
//    var userPassword = document.getElementById("userPassword").value;
    if (!validateEmail(userEmail)) {
        document.getElementById("wrongText").value = "Email Not Valid";
    }
//    var userConfirmPassword = document.getElementById("conPassword").value;
//    confirmPassword(userPassword, userConfirmPassword);

}
//function confirmPassword(firstPassword, secondPassword)
//{
//    alert(firstPassword === secondPassword);
//    if (firstPassword !== secondPassword) {
//     
//        document.getElementById("wrongText").value = "Passwords are not Identical";
//        return false;
//    }
//}
