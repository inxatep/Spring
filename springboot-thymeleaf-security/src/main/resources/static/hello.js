$(document).ready(function () {
    createTable();
    getAllRoles();
    TabUser();

});

    function createTable() {
        fetch('http://localhost:8080/rest/all')
            .then((response) => {
                return response.json();
            })
            .then((data) => {

                const users = JSON.parse(JSON.stringify(data));

                // ITERATING THROUGH OBJECTS
                for (let i = 0; i < users.length; i++) {
                    let listRoles = '';
                    for (let element of users[i].roles) {
                        listRoles += " " + element.name;
                    }

                    //CONSTRUCTION OF ROWS HAVING
                    // DATA FROM JSON OBJECT

                    var tr = $("<tr>").attr("id", users[i].id);
                    tr.append("" +
                        "<td>" + users[i].id + "</td>" +
                        "<td>" + users[i].name + "</td>" +
                        "<td>" + users[i].password + "</td>" +
                        "<td>" + listRoles + "</td>" +

                        "<td><button  onclick='getUserEdit(" + users[i].id + ")'class='btn btn-md btn-info' data-toggle='modal' data-target='#myModal'>Edit</button></td>" +

                        "<td><button onclick='getDeleteEdit(" + users[i].id + ")'  class='btn btn-md btn-danger' data-toggle='modal' data-target='#myModal1'>Delete</button> </td>"

                    );

                    //INSERTING ROWS INTO TABLE
                    $('#myTable').append(tr);
                };
            })
    }

function getAllRoles() {
    // Get All users
    fetch('http://localhost:8080/rest/role')
        .then((response) => {
            return response.json();
        })
        .then((data) =>{
     var select = $('select'),
        list = '';

    $.each(data, function(i, val){
        list += '<option id="'+ val.id +'">'+ val.name +'</option>';
    });

    select.append(list);
        });
}
$(document).ready(
    function getForEdit() {
        $('#editUserModal').on('show.bs.modal', function (event) {
            let button = $(event.relatedTarget);
            getUserEdit(); //здесь получить данные пользователя
        });
    });

function getUserEdit(id) {
    fetch('http://localhost:8080/rest/user/' + id)
        .then((response) => {
            return response.json();
        })
        .then((data) => {
        // TODO FILL THE TEXTAREAS WITH THE VALUES OF THE RESULT.
            document.getElementById('editId').value = data.id;
            document.getElementById('editName').value = data.name;
            document.getElementById('editPassword').value = data.password;

        });
}
function TabUser() {
    fetch('http://localhost:8080/rest/auth')
        .then((response) => {
            return response.json();
        })
        .then((data) => {
            let listRoles = '';
            for (let element of data.roles) {
                listRoles += " " + element.name;
            }
            let tr = $("<tr>").attr("id", data.id);
            tr.append("" +
                "<td>" + data.id + "</td>" +
                "<td>" + data.name + "</td>" +
                "<td>" + data.password + "</td>" +
                "<td>" + listRoles + "</td>"
            );

            //INSERTING ROWS INTO TABLE
            $('#TabUser').append(tr);
        })

}

    function getDeleteEdit(id) {
        fetch('http://localhost:8080/rest/user/' + id)
            .then((response) => {
                return response.json();
            })
            .then((data) => {
                // TODO FILL THE TEXTAREAS WITH THE VALUES OF THE RESULT.
                document.getElementById('delId').value = data.id;
                document.getElementById('delName').value = data.name;
                document.getElementById('delPassword').value = data.password;

                delButton.onclick = function() {
                    deleteUser(data.id)
                }
            });
    }
        // Delete user
        function deleteUser(id) {
            fetch('http://localhost:8080/rest/delete/'+ id, {
                method: "DELETE",
                }).then((data) => {
                $("#myTable #" + id).remove();
            });
        }


$(document).ready(function () {

    $('#addUser').submit(function (event) {
        event.preventDefault()


        let userName = document.getElementById("inputUserName").value;
        let userPassword = document.getElementById("inputUserPassword").value;
        let userRoles = document.querySelector('#inputUserRoles');

        let optionList =  Array.from(userRoles.options).filter(function (option) {
            return option.selected;
        });


        let array =[];

        for(i = 0;  i < optionList.length; i++ ) {
            array.push({id: optionList[i].id, name: optionList[i].value})
        }
                // Create a new user
                fetch('http://localhost:8080/rest/add', {
                        headers: {"Content-Type": "application/json; charset=utf-8"},
                        method: "post",
                    //make sure to serialize your JSON body
                            body: JSON.stringify({name: userName, password: userPassword,
                                roles:  array })

                    })
        location.reload();

                })

})


// Update user with id 3
        function update() {

            let editId = document.getElementById('editId').value;
            let editName = document.getElementById('editName').value;
            let editPassword = document.getElementById('editPassword').value;
            let userRoles = document.querySelector('#editRoles');

            let EditOptionList =  Array.from(userRoles.options).filter(function (option) {
                return option.selected;
            });

            let EditArray =[];


            for(i = 0;  i < EditOptionList.length; i++ ) {
                EditArray.push({id: EditOptionList[i].id, name: EditOptionList[i].value})

            }

            fetch('http://localhost:8080/rest/editSave', {
                headers: {"Content-Type": "application/json; charset=utf-8"},
                method: 'PUT',
                body: JSON.stringify({
                    id: editId, name: editName, password:
                    editPassword, roles: EditArray

                })

            })
            var str = $("<tr>").attr("id", editId);
            let listRoles = '';
            for (let element of EditArray) {
                listRoles += " " + element.name;
            }
            str.append("" +
                "<td>" + editId + "</td>" +
                "<td>" + editName + "</td>" +
                "<td>" + editPassword + "</td>" +
                "<td>" + listRoles + "</td>" +

                "<td><button  onclick='getUserEdit(" + editId + ")'class='btn btn-md btn-info' data-toggle='modal' data-target='#myModal'>Edit</button></td>" +

                "<td><button onclick='getDeleteEdit(" + editId + ")' class='btn btn-md btn-danger' data-toggle='modal' data-target='#myModal1'>Delete</button> </td>"

            );
            $("#myTable #" + editId).replaceWith(str);


        }







