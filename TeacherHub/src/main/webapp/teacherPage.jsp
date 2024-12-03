<%@ page import="com.example.teacherhub.model.Subject" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.teacherhub.model.Grade" %>
<%@ page import="com.example.teacherhub.model.User" %>
<html>
<head>
    <title>Teacher Page</title>
</head>
<body>
<h2>Welcome, ${user.firstName} ${user.lastName}</h2>

<form action="LogoutServlet" method="post">
    <button type="submit">Logout</button>
</form>

<h3>Subjects</h3>
<ul>
    <%
        List<Subject> subjects = (List<Subject>) request.getAttribute("subjects");
        if (subjects != null) {
            for (Subject subject : subjects) {
    %>
    <li>
        <a href="TeacherServlet?subjectId=<%= subject.getSubjectID() %>">
            <%= subject.getSubjectName() %>
        </a>
    </li>
    <%
            }
        }
    %>
</ul>

<%-- Form to Add Grade if Teacher is Assigned to the Selected Subject --%>
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
<form action="TeacherServlet" method="post">
    <input type="hidden" name="subjectId" value="<%= selectedSubject.getSubjectID() %>">
    <label for="student">Select Student:</label>
    <select name="studentId" id="student">
        <%-- Loop through the students and create a dropdown --%>
        <%
            if (students != null) {
                for (User student : students) {
        %>
        <option value="<%= student.getUserID() %>">
            <%= student.getFirstName() %> <%= student.getLastName() %>
        </option>
        <%
                }
            }
        %>
    </select>
    <br>
    <label for="grade">Grade:</label>
    <input type="number" name="grade" id="grade" step="0.01" min="0" max="100" required>
    <br>
    <button type="submit">Add Grade</button>
</form>

<%
        }
    }
%>

<%-- If grades are available, display them below the subjects --%>
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

<h3>Grades for <%= selectedSubject.getSubjectName() %></h3>
<ul>
    <%-- Loop through the grades and display them --%>
    <%
        for (Grade grade : grades) {
    %>
    <li><%= grade.getStudentName() %> | <%= grade.getGrade() %> | <%= grade.getDateAssigned() %></li>
    <%
        }
    %>
</ul>

<%
        }
    }
%>

</body>
</html>
