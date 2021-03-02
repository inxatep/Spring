$(document).ready(
    function getForEdit() {
        $('#edit').on('show.bs.modal', function (event) {
            let button = $(event.relatedTarget);
            getUserEdit(); //получим данные пользователя
        });
    });

function getUserEdit(id) {
    var userId = document.getElementById("editId").value;
    var userName = document.getElementById("editName").value;
    var userPassword = document.getElementById("editPassword").value;
    var userRoles = document.getElementById("editRoles").value;
    data: JSON.stringify({id: userId, name: userName, password: userPassword, role: userRoles})
    fetch('http://localhost:8080/rest/user' + id)
        .then((response) => {
            return response.json();
        })
        .then(data => console.log(data))

}