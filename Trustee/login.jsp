<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>DSCE | Trustee</title>

<link href="css/bootstrap.min.css" rel="stylesheet">

<link href="css/simple-sidebar.css" rel="stylesheet">
</head>

<body>

	<div id="wrapper">

		<%@include file='menu.jsp'%>

		<div id="page-content-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<a href="#menu-toggle" class="btn btn-default" id="menu-toggle">
							TOGGLE </a>

						<h1>ADMIN LOGIN</h1>
						<br />
						<div class='col-md-5'>
							<%
								String chk1 = request.getParameter("error");
								if (chk1 != null && chk1.equals("true")) {
							%>
							<div style='font-size: 16px; color: red;'
								class='alert alert-danger'>
								<span class='glyphicon glyphicon-remove'></span> Invalid
								Credentials
							</div>
							<br>
							<%
								} else if (chk1 != null && chk1.equals("dh")) {
							%>
							<div style='font-size: 16px; color: red;'
								class='alert alert-danger'>
								<span class='glyphicon glyphicon-remove'></span> Please login
								first.
							</div>
							<br>
							<%
								}
							%>
							<form class='well' action='login' method=post>
								<label> Enter the Admin Password </label> <input type=password
									name='pwd' placeholder='Password' class='form-control' /> <br />

								<input type=submit class='btn btn-primary' value='LOGIN' />

							</form>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>
	<script src="js/jquery.js"></script>

	<script src="js/bootstrap.min.js"></script>

	<script>
		$("#menu-toggle").click(function(e) {
			e.preventDefault();
			$("#wrapper").toggleClass("toggled");
		});
	</script>

</body>

</html>
