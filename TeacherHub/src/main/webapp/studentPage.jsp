<%@ page import="com.example.teacherhub.model.Subject" %>
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
  <li><%= subject.getSubjectName() %></li>
  <%
      }
    }
  %>
</ul>

<%-- Optionally, for Grades, use a similar approach --%>
</body>
</html>
