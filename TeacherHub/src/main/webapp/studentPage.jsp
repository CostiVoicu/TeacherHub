<%@ page import="com.example.teacherhub.model.Subject" %>
<%@ page import="com.example.teacherhub.model.Grade" %>
<%@ page import="java.util.List" %>
<html>
<head>
  <title>Student Page</title>
</head>
<body>
<h2>Welcome, ${user.firstName} ${user.lastName}</h2>

<h3>Subjects</h3>
<ul>
  <%-- Use scriptlet to loop through subjects --%>
  <%
    List<Subject> subjects = (List<Subject>) request.getAttribute("subjects");
    if (subjects != null) {
      for (Subject subject : subjects) {
  %>
  <li>
    <a href="StudentServlet?subjectId=<%= subject.getSubjectID() %>">
      <%= subject.getSubjectName() %>
    </a>
  </li>
  <%
      }
    }
  %>
</ul>

<%-- If grades are available, display them below the subjects --%>
<%
  List<Grade> grades = (List<Grade>) request.getAttribute("grades");
  if (grades != null && !grades.isEmpty()) {
    int selectedSubjectId = (Integer) request.getAttribute("selectedSubject");
    Subject selectedSubject = null;

    // Find the selected subject for display
    for (Subject subject : subjects) {
      if (subject.getSubjectID() == selectedSubjectId) {
        selectedSubject = subject;
        break;
      }
    }
%>

<h3>Grades for <%= selectedSubject.getSubjectName() %>:</h3>
<ul>
  <%-- Loop through the grades and display them --%>
  <%
    for (Grade grade : grades) {
  %>
  <li><%= grade.getGrade() %> - <%= grade.getDateAssigned() %></li>
  <%
    }
  %>
</ul>

<% } %>

</body>
</html>
