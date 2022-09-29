<%@page isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
</head>
<body>
    <div class="container mb-4 mt-5">
        <h4>Add data</h4>

        <div id="panel"></div>


        <div>
            <form id="userform">
                <div class="mb-3">
                    <input type="text" name="name" class="form-control" id="name" placeholder="Name">
                </div>
                <div class="mb-3">
                    <input type="text" name="address" class="form-control" id="address" placeholder="Address">
                </div>
                <div class="col-sm-10">
                    <button type="button" id="submitForm" class="btn btn-primary">Submit</button>
                </div>
            </form>
        </div>

        <table class="table mt-5">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Name</th>
                <th scope="col">Address</th>
                <th scope="col">Delete</th>

            </tr>
            </thead>
            <tbody id="tdata">

            </tbody>
        </table>
    </div>


    <script>
        $(document).ready(function() {
            getData();
            // insert data
            $("#submitForm").on('click', function() {
                let form = $("#userform");
                $.ajax({
                    url: "${pageContext.request.contextPath}/adduser",
                    method: "POST",
                    data: form.serialize(),
                    success: function(data) {
                        if(data == "1") {
                            $("#panel").html(`<div class="alert alert-primary" role="alert">User added successfully</div>`)
                            getData();
                        }
                        else {
                            $("#panel").html(`<div class="alert alert-warning" role="alert">User not added</div>`)
                        }
                    }
                })
                $("#name").val("")
                $("#address").val("");
            })

            // delete perticular data
            $(document).on('click', '#del', function() {
                let uid = $(this).data("uid")
                console.log(uid)
            })

            // fetch data
            function getData() {
                $.ajax({
                    url: "${pageContext.request.contextPath}/getdata",
                    method: "GET",
                    success: function (data) {
                        $("#tdata").html(data)
                    }
                })
            }
        })
    </script>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>
</body>
</html>