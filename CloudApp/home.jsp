<!DOCTYPE HTML>
<%@page import="com.dsce.pojo.User"%>
<%@page import="java.util.List"%>
<html>
<head>
<title>AKLC</title>
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
						New Requests <small>Please review these new user
							registrations.</small>
					</h1>
				</div>
				<%
				List<User> newUsers = (List<User>) request.getAttribute("newUsers");
				if (newUsers == null || newUsers.size()==0) {
					%>
						<div class='well'>
							No Pending User requests at this point of time.
						</div>
					<%
				} else {
				%>
				<table cellspacing=4 cellpadding=5 class='table'>
					<tr>
						<th>First name</th>

						<th>Last name</th>

						<th>Email</th>
						<th>Approve / Reject</th>
					</tr>
					<%
																									
						for (User u : newUsers) {
					%>
					<tr>
						<td><%=u.getFirstName()%></td>
						<td><%=u.getLastName()%></td>
						<td><%=u.getEmail()%></td>
						<td>
						
						<a href='approve?email=<%=u.getEmail()%>' style='text-decoration:none;'>
						<i class="general foundicon-checkmark">Approve</i>&nbsp;&nbsp;&nbsp;&nbsp;
						</a>
						<a href='reject?email=<%=u.getEmail()%>' style='text-decoration:none;'>
						<i class="general foundicon-remove">Reject</i>
						</a>
						</td>
					</tr>

					<%
						}
					%>
				</table>
				<% } %>


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

		$('#menu_nr').attr({
			'class' : 'dropdown active'
		})

		$('#reg').validate({
			rules : {
				'password' : 'required'
			},
			messages : {
				'password' : 'Please provide the admin password'
			},
			submitHandler : function(form) {
				form.submit();
			}
		});
	});
</script>