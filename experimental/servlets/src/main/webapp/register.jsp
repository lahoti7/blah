<html>

<link rel="stylesheet"
  href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<body>

<%
  String result = request.getParameter("result");
  if (result == null) result = "";
%>

<h2>Please sign up</h2>

<form action="register" method="post">
    <input type="text" name="firstName"/>
    <input type="text" name="lastName"/>
    <input type="text" name="username"/>
    <input type="password" name="password"/>
    <input type="submit" value="Register"/>
</form>

<div><%= result %></div>

</body>
</html>
