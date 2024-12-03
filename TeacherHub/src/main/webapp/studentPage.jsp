<%@ page import="com.example.teacherhub.model.Subject" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.teacherhub.model.Grade" %>
<%@ page import="com.example.teacherhub.model.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Student Page</title>
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

        <ul class="list-group mb-4">
          <%
            List<Subject> subjects = (List<Subject>) request.getAttribute("subjects");
            if (subjects != null) {
              for (Subject subject : subjects) {
          %>
          <li class="list-group-item">
            <a href="StudentServlet?subjectId=<%= subject.getSubjectID() %>">
              <%= subject.getSubjectName() %>
            </a>
          </li>
          <%
              }
            }
          %>
        </ul>

        <%-- Display Grades for the Selected Subject --%>
        <%
          Integer selectedSubjectId = (Integer) request.getAttribute("selectedSubject");
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

        <ul class="list-group">
          <%
            for (Grade grade : grades) {
          %>
          <li class="list-group-item">
            <strong>Grade:</strong> <%= grade.getGrade() %> |
            <strong>Date:</strong> <%= grade.getDateAssigned() %>
          </li>
          <%
            }
          %>
        </ul>

        <% } } %>
      </div>
    </div>
  </div>
</div>

<!-- Bootstrap JS & Popper.js -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
</body>
</html>
