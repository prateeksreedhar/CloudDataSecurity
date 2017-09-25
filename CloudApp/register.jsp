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
						Register a new user <small>Request will be raised to
							Admin.</small>
					</h1>
				</div>


				<%
					String chk = request.getParameter("register");
					if (chk != null && chk.equals("done")) {
				%>
				<div class='alert alert-info'>
					<i class="general foundicon-checkmark"> Registration
						Successful. We have sent a request to the Admin to grant you
						access to the portal.</i>
				</div>
				<%
					} else if (chk != null && chk.equals("fail")) {
				%>

				<div class='alert alert-error'>
					<i class="general foundicon-remove"> Aww. ! Something went
						wrong during registration. Please try again later.</i>
				</div>
				<%
					} else {
				%>


				<form action='register' id='reg' method='post' class='well'>

					<label>Enter your email.</label> <input type="text"
						placeholder="Email" name='email'> <br /> <br /> <label>Enter
						your first name.</label> <input type="text" placeholder="First name"
						name='firstname'> <br /> <br /> <label>Enter
						your last name.</label> <input type="text" placeholder="Last name"
						name='lastname'> <br /> <br /> <label>Chose your
						password.</label> <input name='password' type="password"
						placeholder="Password"> <br /> <br /> <input type=reset
						value='CLEAR ALL' class='btn btn-large btn-secondary' /> <input
						type=submit value='REGISTER' class='btn btn-large btn-success' />



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
		
		$('#menu_register').attr({'class':'dropdown active'})
		
		$('#reg').validate({
			rules : {
				'firstname' : 'required',
				'lastname' : 'required',
				'password' : 'required',
				'email' : {
					'required' : true,
					'email' : true
				}
			},
			messages : {
				'firstname' : 'First name is a mandatory field',
				'lastname' : 'Last name is a mandatory field',
				'password' : 'Password is a mandatory field',
				'email' : {
					'required' : 'Email is a mandatory field',
					'email' : 'Invalid Email Id. Please enter a valid one'
				}
			},
			submitHandler : function(form) {
				form.submit();
			}
		});
	});
</script>