<!DOCTYPE HTML>
<html>
<head>
<title>DSCE</title>
<%@include file="resources.jsp"%>
</head>
<body id="pageBody">
	<div id="divBoxed" class="container">

		<div class="transparent-bg"
			style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; z-index: -1; zoom: 1;"></div>

		<div class="divPanel notop nobottom">

			<%@include file="header.jsp"%>


		</div>

		<div class="contentArea">


			<div class="divPanel notop page-content">

				<div class="page-header">
					<h1>
						Login <small>Please Provide your login
							credentials.</small>
					</h1>
				</div>


				<%
					String chk = request.getParameter("valid");
				%>
				<%
					if (chk != null && chk.equals("false")) {
				%>

				<div class='alert alert-error'>
					<i class="general foundicon-remove"> Aww. ! Looks like you
						provided an invalid credentials. <a href='login.jsp'>Try
							again </a>
					</i>
				</div>
				<%
					} else {
				%>


				<form action='login' id='login' method='post'>

					<label>Email</label> <input name='email'
						type="text" placeholder="Email"> <br />
					<label>Password</label> <input name='password'
						type="password" placeholder="Password"> <br /> <input
						type=submit value='LOGIN' class='btn btn-large btn-success' />



				</form>

				<%
					}
				%>


				<div id="footerInnerSeparator"></div>
			</div>
		</div>

		<div id="footerOuterSeparator"></div>

		<%@include file="footer.jsp"%>
	</div>
	<br />
	<br />
	<br />
</body>
</html>

<script>
	$(document).ready(function() {

		$('#menu_login').attr({
			'class' : 'dropdown active'
		})

		$('#login').validate({
			rules : {
				'password' : 'required',
				'email' : 'required'
			},
			messages : {
				'password' : 'Please provide your password',
				'email' : 'Please enter your email ID'
			},
			submitHandler : function(form) {
				form.submit();
			}
		});
	});
</script>