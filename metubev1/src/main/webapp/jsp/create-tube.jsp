<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <c:import url="templates/head.jsp"/>
</head>
<body>
<div class="container">
    <main>
        <div class="jumbotron">
            <form action="/tubes/create" method="post">
                <div class="row">
                    <div class="col col-md-12 d-flex justify-content-center">
                        <h1>Create Tube</h1>
                    </div>
                </div>
                <hr/>
                <div class="row">
                    <div class="col col-md-12 ">
                        <div class="row d-flex justify-content-center">
                            <label for="nameInput">Title</label>
                        </div>
                        <div class="row d-flex justify-content-center">
                            <input type="text" id="nameInput" name="name"/>
                        </div>
                    </div>
                </div>
                <hr/>
                <div class="row">
                    <div class="col col-md-12 ">
                        <div class="row d-flex justify-content-center">
                            <label for="descriptionTextArea">Description</label>
                        </div>
                        <div class="row d-flex justify-content-center">
                            <textarea type="text" id="descriptionTextArea" name="description" cols="22"
                                      rows="4"></textarea>
                        </div>
                    </div>
                </div>
                <hr/>
                <div class="row">
                    <div class="col col-md-12 ">
                        <div class="row d-flex justify-content-center">
                            <label for="inputLink">YouTube Link</label>
                        </div>
                        <div class="row d-flex justify-content-center">
                            <input type="text" id="inputLink" name="youTubeLink"/>
                        </div>
                    </div>
                </div>
                <hr/>
                <div class="row">
                    <div class="col col-md-12 ">
                        <div class="row d-flex justify-content-center">
                            <label for="inputUploader">Uploader</label>
                        </div>
                        <div class="row d-flex justify-content-center">
                            <input type="text" id="inputUploader" name="uploader"/>
                        </div>
                    </div>
                </div>
                <hr/>
                <div class="row mt-3">
                    <div class="col col-md-12 d-flex justify-content-center">
                        <button class="btn btn-primary" type="submit">Create Tube</button>
                    </div>
                </div>
            </form>
            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <a href="/">Back to home</a>
                </div>
            </div>
        </div>
        <footer>
            <c:import url="templates/footer.jsp"/>
        </footer>
    </main>
</div>
</body>
</html>
