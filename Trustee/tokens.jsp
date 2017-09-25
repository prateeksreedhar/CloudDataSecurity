<!DOCTYPE html>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.dsce.servlet.TokenServlet"%>
<%@page import="com.dsce.pojo.Token"%>
<%@page import="java.util.List"%>
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
	<%
		String login = (String) session.getAttribute("loggedIn");
		if (login != null && login.equals("yes")) {
	%>
	<div id="wrapper">

		<%@include file='menu.jsp'%>

		<div id="page-content-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<a href="#menu-toggle" class="btn btn-default" id="menu-toggle">
							TOGGLE </a>

						<h1>TOKENS</h1>

						<table class='table table-hover'>
							<tr>
								<th>Requester Email</th>
								<th>Requested time</th>
								<th>Token Generated</th>
								<th>Status</th>
								<th>Action</th>


							</tr>
							<%
								List<Token> tokens = (List<Token>) request.getAttribute("tokens");
								int i=1;
								for (Token t : tokens) {
							%>
							<tr>
								<td id='email<%=i%>'><%=t.getEmail()%></td>
								<td>
									<%
										SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy 'at' hh:mm");
									%> <%=sdf.format(t.getRequestTime())%></td>
								<td><%=t.getToken()%></td>
								<td id='status<%=i%>'><%=t.getStatus()%></td>
								<td id='action<%=i%>'>
									<%
										if (t.getStatus().equals("NEW")) {
									%> <span class='glyphicon glyphicon-ok'></span> <input
									type=button value='APPROVE' class='btn btn-success btn-xs'
									onclick='approve(<%=i%>);' /> &nbsp;&nbsp;&nbsp;&nbsp; <span
									class='glyphicon glyphicon-remove'></span> <input type=button
									value='REJECT' class='btn btn-danger btn-xs'
									onclick='reject(<%=i%>);' /> <%
 	} else {
 %> N/A <%
 	}
 %>

								</td>

							</tr>
							<%
								i++; }
							%>
						</table>
					</div>
				</div>
			</div>
		</div>

	</div>
	<%
		} else {
			response.sendRedirect("login.jsp?error=dh");
		}
	%>

	<script src="js/jquery.js"></script>

	<script src="js/bootstrap.min.js"></script>

	<script>
		$("#menu-toggle").click(function(e) {
			e.preventDefault();
			$("#wrapper").toggleClass("toggled");
		});
		
		function approve (i) {
			var email = $('#email'+i).html();
			$('#action'+i).html("Processing.. Please wait .. ");
			var req = $.ajax({
				'url':'grant',
				'method' : 'post',
				'data' : {'email' : email, 'action' : 'approve'}
			});
			
			req.done(function() {
				$('#action'+i).html("<span class='glyphicon glyphicon-ok'> </span> Token Granted");
				$('#status'+i).html("GRANTED");
			});
			req.fail(function() {
				$('#action'+i).html('! Something went wrong');
			});
		}
		
		function reject(i) {
			var email = $('#email'+i).html();
			$('#action'+i).html("Processing.. Please wait .. ");
			var req = $.ajax({
				'url':'grant',
				'method' : 'post',
				'data' : {'email' : email, 'action' : 'reject'}
			});
			
			req.done(function() {
				$('#action'+i).html("<span class='glyphicon glyphicon-remove'> </span> Token Rejected");
				$('#status'+i).html("REJECTED");
			});
			req.fail(function() {
				$('#action'+i).html('! Something went wrong');
			})


		}
	</script>

</body>

</html>
