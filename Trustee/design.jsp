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

						<h1>DESIGN OF TRUSTEE</h1>

						<img src='images/trustee2.jpg' width=800 class='img-thumbnail img-rounded'/>
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
