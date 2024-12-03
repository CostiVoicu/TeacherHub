<%@ page import="com.example.teacherhub.model.Subject" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.teacherhub.model.Grade" %>
<%@ page import="com.example.teacherhub.model.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Teacher Page</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-4">
    <div class="row">
        <!-- Sidebar or navigation can be added here -->

        <!-- Main Content -->
        <div class="col-12 col-md-8 offset-md-2">
            <div class="card p-4">
                <h2 class="text-center mb-4">Welcome, ${user.firstName} ${user.lastName}</h2>

                <form action="LogoutServlet" method="post" class="text-end">
                    <button type="submit" class="btn btn-danger">Logout</button>
                </form>

                <h3 class="mt-4">Subjects</h3>

                <form action="TeacherServlet" method="get">
                    <button type="submit" name="sortSubjects" value="name" class="btn btn-primary mb-3">Sort Alphabetically</button>
                </form>

                <ul class="list-group mb-4">
                    <%
                        List<Subject> subjects = (List<Subject>) request.getAttribute("subjects");
                        if (subjects != null) {
                            for (Subject subject : subjects) {
                    %>
                    <li class="list-group-item">
                        <a href="TeacherServlet?subjectId=<%= subject.getSubjectID() %>">
                            <%= subject.getSubjectName() %>
                        </a>
                    </li>
                    <%
                            }
                        }
                    %>
                </ul>

                <%-- Add Grade Form if Teacher is Assigned to the Selected Subject --%>
                <%
                    Integer selectedSubjectId = (Integer) request.getAttribute("selectedSubject");

                    if (selectedSubjectId != null) {
                        Subject selectedSubject = null;

                        // Find the selected subject
                        for (Subject subject : subjects) {
                            if (subject.getSubjectID() == selectedSubjectId) {
                                selectedSubject = subject;
                                break;
                            }
                        }

                        if (selectedSubject != null && selectedSubject.getTeacherID() == ((User) session.getAttribute("user")).getUserID()) {
                            List<User> students = (List<User>) request.getAttribute("students");
                %>

                <h3>Add Grade for Students in <%= selectedSubject.getSubjectName() %></h3>
                <form action="TeacherServlet" method="post" class="mb-4">
                    <input type="hidden" name="action" value="addGrade">
                    <input type="hidden" name="subjectId" value="<%= selectedSubject.getSubjectID() %>">
                    <div class="mb-3">
                        <label for="student" class="form-label">Select Student:</label>
                        <select name="studentId" id="student" class="form-select" required>
                            <%
                                if (students != null) {
                                    for (User student : students) {
                            %>
                            <option value="<%= student.getUserID() %>">
                                <%= student.getLastName() %> <%= student.getFirstName() %>
                            </option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="grade" class="form-label">Grade:</label>
                        <input type="number" name="grade" id="grade" class="form-control" step="0.01" min="0" max="100" required>
                    </div>
                    <button type="submit" class="btn btn-success">Add Grade</button>
                </form>

                <%
                        }
                    }
                %>

                <%-- Display Grades for the Subject --%>
                <%
                    List<Grade> grades = (List<Grade>) request.getAttribute("grades");

                    if (grades != null && !grades.isEmpty() && selectedSubjectId != null) {
                        Subject selectedSubject = null;

                        // Find the selected subject for display
                        for (Subject subject : subjects) {
                            if (subject.getSubjectID() == selectedSubjectId) {
                                selectedSubject = subject;
                                break;
                            }
                        }

                        if (selectedSubject != null) {
                %>

                <% if (selectedSubject != null && selectedSubject.getTeacherID() == ((User) session.getAttribute("user")).getUserID()) {%>
                <div class="card mt-4">
                    <div class="card-body">
                        <h3 id="averageText" style="display: none;" class="card-title">
                            Average Grade for <%= selectedSubject.getSubjectName() %>: ${ averageGrade }
                        </h3>
                        <button type="button" onclick="toggleAverage()" class="btn btn-primary btn-sm">
                            Show Average
                        </button>
                    </div>
                </div>
                <br>
                <% } %>

                <h3>Grades for <%= selectedSubject.getSubjectName() %></h3>

                <form action="TeacherServlet" method="get" class="mb-3">
                    <input type="hidden" name="subjectId" value="<%= selectedSubject.getSubjectID() %>">
                    <button type="submit" name="sortGrades" value="name" class="btn btn-secondary">Sort By Name</button>
                    <button type="submit" name="sortGrades" value="number" class="btn btn-secondary">Sort By Grade</button>
                    <button type="submit" name="sortGrades" value="date" class="btn btn-secondary">Sort By Date</button>
                </form>

                <ul class="list-group">
                    <%
                        for (Grade grade : grades) {
                    %>
                    <li class="list-group-item">
                        <strong>Student:</strong> <%= grade.getStudentName() %> |
                        <strong>Grade:</strong> <%= grade.getGrade() %> |
                        <strong>Date:</strong> <%= grade.getDateAssigned() %>

                        <% if (selectedSubject != null && selectedSubject.getTeacherID() == ((User) session.getAttribute("user")).getUserID()) { %>

                        <!-- Button to toggle update form -->
                        <button type="button" class="btn btn-warning btn-sm" onclick="toggleUpdateForm('<%= grade.getGradeID() %>')">Update</button>

                        <!-- Delete Grade Form -->
                        <form action="TeacherServlet" method="post" style="display:inline;">
                            <input type="hidden" name="action" value="deleteGrade">
                            <input type="hidden" name="gradeId" value="<%= grade.getGradeID() %>">
                            <input type="hidden" name="subjectId" value="<%= selectedSubject.getSubjectID() %>">
                            <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this grade?');">Delete</button>
                        </form>

                        <!-- Hidden update form -->
                        <form id="updateForm-<%= grade.getGradeID() %>" action="TeacherServlet" method="post" style="display:none; margin-top: 10px;">
                            <input type="hidden" name="action" value="updateGrade">
                            <input type="hidden" name="gradeId" value="<%= grade.getGradeID() %>">
                            <input type="hidden" name="subjectId" value="<%= selectedSubject.getSubjectID() %>">
                            <div class="mb-3">
                                <label for="updatedGrade-<%= grade.getGradeID() %>" class="form-label">New Grade:</label>
                                <input type="number" name="updatedGrade" id="updatedGrade-<%= grade.getGradeID() %>" class="form-control" step="0.01" min="0" max="100" value="<%= grade.getGrade()%>" required>
                            </div>
                            <div class="mb-3">
                                <label for="updatedDate-<%= grade.getGradeID() %>" class="form-label">Date:</label>
                                <input type="date" name="updatedDate" id="updatedDate-<%= grade.getGradeID() %>" class="form-control" value="<%= grade.getDateAssigned()%>" required>
                            </div>
                            <button type="submit" class="btn btn-success">Update</button>
                        </form>

                        <% } %>
                    </li>
                    <%
                        }
                    %>
                </ul>
                <%
                        }
                    }
                %>

            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS & Popper.js -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>

<script>
    function toggleAverage() {
        const averageText = document.getElementById("averageText");
        averageText.style.display = averageText.style.display === "none" ? "block" : "none";
    }

    function toggleUpdateForm(gradeId) {
        const formId = "updateForm-" + gradeId;
        const form = document.getElementById(formId);
        form.style.display = form.style.display === "none" ? "block" : "none";
    }
</script>
</body>
</html>
