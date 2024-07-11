<%@page contentType="text/html" pageEncoding="UTF-8" import="DAO.*, java.util.*, model.*"%>
<%@ page import="model.QuestionBank" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Collections" %>
<!DOCTYPE html>
<jsp:include page="header.jsp"></jsp:include>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        h1 {
            text-align: center;
        }

        fieldset {
            margin-bottom: 20px;
            border: 2px solid #ccc;
            border-radius: 5px;
            padding: 20px;
        }

        legend {
            font-weight: bold;
            font-size: 1.2em;
            color: #333;
        }

        input[type="radio"]{
            margin-bottom: 10px;
        }


        #image-preview {
            max-width: 400px; /* Adjust max width as needed */
            max-height: 400px; /* Adjust max height as needed */
        }

        #image-preview-wrapper {
            position: relative;
            display: inline-block; /* Ensure it wraps around the image */
        }

        #delete-image {
            position: absolute;
            top: 5px; /* Adjust as needed */
            right: 5px; /* Adjust as needed */
            background: transparent;
            border: none;
            color: white;
            font-size: 18px;
            cursor: pointer;
            display: none; /* Initially hidden */
        }

        #image-preview2 {
            max-width: 400px; /* Adjust max width as needed */
            max-height: 400px; /* Adjust max height as needed */
        }

        #image-preview-wrapper2 {
            position: relative;
            display: inline-block; /* Ensure it wraps around the image */
        }

        #delete-image2 {
            position: absolute;
            top: 5px; /* Adjust as needed */
            right: 5px; /* Adjust as needed */
            background: transparent;
            border: none;
            color: white;
            font-size: 18px;
            cursor: pointer;
            display: none; /* Initially hidden */
        }

    </style>
<%
if(session.getAttribute("subjectID") != null){
int subjectID = (Integer)session.getAttribute("subjectID");
String link = (String)session.getAttribute("backlink");
%>
<body>
    <link
        rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.0-2/css/all.min.css"
        integrity="sha256-46r060N2LrChLLb5zowXQ72/iKKNiw/lAmygmHExk/o="
        crossorigin="anonymous"
        />
    <div class="container">
        <br><br>
        <button class="btn"><a href="<%=link%>" style="text-decoration: none">Back</a></button>
        <form action="AddQuestionBank" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="subjectID" value="<%=subjectID%>"/>
                <fieldset>
                    <label for="question" style="font-weight: bold">Câu hỏi:</label>
                    <textarea
                        type="text"
                        class="form-control"
                        name="context"
                        id="question"
                        placeholder="Câu hỏi"
                        required
                        autofocus
                        rows="1" 
                        style="resize: none; overflow: hidden;"
                        ></textarea>
                    <div id="image-preview-container">
                        <label for="myfile" style="font-weight: bold">Chọn ảnh:</label>
                        <input id="image-upload" type="file" name="image" accept="image/*">
                        <br>
                        <div id="image-preview-wrapper" style="position: relative;">
                            <img id="image-preview" src="#" alt="Preview Image" style="display:none;">
                            <button id="delete-image"><i class="fa fa-times"></i></button>
                        </div>
                    </div>
                    <label for="answerA" style="font-weight: bold">Đáp án A:</label>
                    <textarea
                        type="text"
                        class="form-control"
                        name="A"
                        id="answerA"
                        placeholder="Đáp án A"
                        required
                        autofocus
                        rows="1" 
                        style="resize: none; overflow: hidden;"
                        ></textarea>
                    <label for="answerB" style="font-weight: bold">Đáp án B:</label>
                    <textarea
                        type="text"
                        class="form-control"
                        name="B"
                        id="answerB"
                        placeholder="Đáp án B"
                        required
                        autofocus
                        rows="1" 
                        style="resize: none; overflow: hidden;"
                        ></textarea>
                    <label for="answerC" style="font-weight: bold">Đáp án C:</label>
                    <textarea
                        type="text"
                        class="form-control"
                        name="C"
                        id="answerC"
                        placeholder="Đáp án C"
                        required
                        autofocus
                        rows="1" 
                        style="resize: none; overflow: hidden;"
                        ></textarea>
                    <label for="answerD" style="font-weight: bold">Đáp án D:</label>
                    <textarea
                        type="text"
                        class="form-control"
                        name="D"
                        id="answerD"
                        placeholder="Đáp án D"
                        required
                        autofocus
                        rows="1" 
                        style="resize: none; overflow: hidden;"
                        ></textarea>
                    <label for="rightanswer" style="font-weight: bold">Đáp án đúng:</label>
                    <div id="rightanswer">
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="rightanswer" id="optionA" value="A" checked/>
                            <label class="form-check-label" for="optionA">A</label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="rightanswer" id="optionB" value="B"/>
                            <label class="form-check-label" for="optionB">B</label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="rightanswer" id="optionC" value="C"/>
                            <label class="form-check-label" for="optionC">C</label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="rightanswer" id="optionD" value="D"/>
                            <label class="form-check-label" for="optionD">D</label>
                        </div>
                    </div>
                    <label for="explain" style="font-weight: bold">Giải thích:</label>
                    <textarea
                        type="text"
                        class="form-control"
                        name="explain"
                        id="explain"
                        placeholder="Giải thích"
                        required
                        autofocus
                        rows="1" 
                        style="resize: none; overflow: hidden;"
                        ></textarea>
                    <div id="image-preview-container">
                        <label for="myfile" style="font-weight: bold">Chọn ảnh:</label>
                        <input id="image-upload2" type="file" name="image2" accept="image/*"/>
                        <br>
                        <div id="image-preview-wrapper2" style="position: relative;">
                            <img id="image-preview2" src="#" alt="Preview Image" style="display:none;">
                            <button id="delete-image2"><i class="fa fa-times"></i></button>
                        </div>
                    </div>
                </fieldset>
                <input class="btn btn-primary has-icon btn-block" type="submit" value="Thêm câu hỏi">
                </form>
                <br>
                <button
                    class="btn btn-primary has-icon btn-block"
                    type="button"
                    data-toggle="modal"
                    data-target="#threadModal"
                    >
                    Thêm câu hỏi bằng file .docx
                </button>
                <div class="modal fade" id="threadModal" tabindex="-1" role="dialog" aria-labelledby="threadModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-lg" role="document">
                        <div class="modal-content" style="width: 500px; margin: auto">
                            <form action="DocFileQuestionAdd" method="POST" enctype="multipart/form-data">
                                <input type="hidden" name="subjectID" value="<%=subjectID%>"/>
                                <div class="modal-header d-flex align-items-center bg-primary text-white">
                                    <h6 class="modal-title mb-0" id="threadModalLabel">Thêm câu hỏi bằng file .docx</h6>
                                </div>
                                <div class="modal-body">
                                    <div class="form-group">
                                        <label for="docFile">Upload .docx File</label>
                                        <input type="file" class="form-control" id="docFile" name="docFile" accept=".docx" required>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <input type="button" class="btn btn-light" data-dismiss="modal" style="background-color: red" value="Huỷ">
                                    <input type="submit" class="btn btn-primary" value="Thêm câu hỏi"/>
                                </div>
                            </form>
                        </div> 
                    </div>                        
                </div>
                </div>
                <%
                    }
                %>

                <script>
                    var textarea = document.getElementById("submit-comment");
                    textarea.addEventListener("input", function () {
                        this.style.height = "auto";
                        this.style.height = (this.scrollHeight) + "px";
                    });
                </script>

                <script>
                    // Function to handle file input change event
                    document.getElementById('image-upload').addEventListener('change', function (event) {
                        var file = event.target.files[0];
                        var reader = new FileReader();

                        reader.onload = function (e) {
                            var imgElement = document.getElementById('image-preview');
                            imgElement.src = e.target.result;
                            imgElement.style.display = 'block';

                            // Show delete button
                            document.getElementById('delete-image').style.display = 'inline-block';
                        }

                        reader.readAsDataURL(file);
                    });

                    document.getElementById('image-upload2').addEventListener('change', function (event) {
                        var file = event.target.files[0];
                        var reader = new FileReader();

                        reader.onload = function (e) {
                            var imgElement = document.getElementById('image-preview2');
                            imgElement.src = e.target.result;
                            imgElement.style.display = 'block';

                            // Show delete button
                            document.getElementById('delete-image2').style.display = 'inline-block';
                        }

                        reader.readAsDataURL(file);
                    });

                    // Function to handle delete image button click
                    document.getElementById('delete-image').addEventListener('click', function (event) {
                        event.preventDefault(); // Prevent default behavior (page reload)

                        var imgElement = document.getElementById('image-preview');
                        imgElement.src = '#'; // Clear the preview
                        imgElement.style.display = 'none';


                        // Hide delete button
                        document.getElementById('delete-image').style.display = 'none';

                        // Reset file input
                        document.getElementById('image-upload').value = '';
                    });

                    // Function to handle delete image button click
                    document.getElementById('delete-image2').addEventListener('click', function (event) {
                        event.preventDefault(); // Prevent default behavior (page reload)

                        var imgElement = document.getElementById('image-preview2');
                        imgElement.src = '#'; // Clear the preview
                        imgElement.style.display = 'none';


                        // Hide delete button
                        document.getElementById('delete-image2').style.display = 'none';

                        // Reset file input
                        document.getElementById('image-upload2').value = '';
                    });

                    function removeURL() {
                        var imgElement = document.getElementById('image-preview');
                        imgElement.src = '#'; // Clear the preview
                        imgElement.style.display = 'none';
                        document.getElementById('image-upload').value = '';

                        var imgElement2 = document.getElementById('image-preview2');
                        imgElement2.src = '#'; // Clear the preview
                        imgElement2.style.display = 'none';
                        document.getElementById('image-upload2').value = '';
                    }

                </script>
                <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.bundle.min.js"></script>
                <script type="text/javascript"></script>
                <jsp:include page="footer.jsp"></jsp:include>
                
                <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>
