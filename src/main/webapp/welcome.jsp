<html>

<link rel="stylesheet"
  href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<body>

<h2>Welcome to Edlink!</h2>
<a href="index.jsp" onclick="return doLogout();">Logout</a>

<script src="//code.jquery.com/jquery-2.1.4.min.js"></script>
<script>

function doLogout() {
  $.ajax({
    type: "POST",
    url: "<%= request.getContextPath() %>/logout"
  });
}

</script>

</body>
</html>