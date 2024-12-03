<%@ page import="com.example.teacherhub.model.Subject" %>
<%@ page import="java.util.List" %>
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
    <%-- Use scriptlet to loop through subjects --%>
    <%
        List<Subject> subjects = (List<Subject>) request.getAttribute("subjects");
        if (subjects != null) {
            for (Subject subject : subjects) {
    %>
    <li>
        <%= subject.getSubjectName() %>
    </li>
    <%
            }
        }
    %>
</ul>
</body>
</html>
