/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function validateEmail(email) {
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}
function validate() {
    var userEmail = document.getElementById("userEmail").value;
    var userPassword = document.getElementById("userPassword").value;
    if (validateEmail(userEmail)) {
        alert("true");
    } else {
        alert("false");
    }
    var userConfirmPassword = document.getElementById("conPassword").value;
    if (userConfirmPassword)
        confirmPassword(userPassword, userConfirmPassword);
}
function confirmPassword(firstPassword, secondPassword)
{
    if (firstPassword !== secondPassword)
        alert("password not identical");
}
