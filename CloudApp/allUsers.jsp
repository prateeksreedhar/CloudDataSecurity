<!DOCTYPE HTML>
<%@page import="com.dsce.pojo.User"%>
<%@page import="java.util.List"%>
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
						All Users <small>List of Users registered in this Portal.</small>
					</h1>
				</div>
				
				<%
				
					String chk = request.getParameter("revoke");
					if (chk!=null && chk.equals("done")) {
						%>
				<div class='alert alert-info'>
					<i class="general foundicon-checkmark"> User has been successfully Revoked</i>
				</div>
						
						<%
					} else if (chk!=null && chk.equals("fail")) {
						 %>
				<div class='alert alert-error'>
					<i class="general foundicon-remove"> Aww ! Something went wrong while revoking an user. Please try again later.</i>
				</div>	 
						 <%
					}
						
				%>
				<%
				List<User> users = (List<User>) request.getAttribute("users");
				if (users == null || users.size()==0) {
					%>
						<div class='well'>
							No user registered yet at this point of time.
						</div>
					<%
				} else {
				%>

				<table cellspacing=4 cellpadding=5 class='table'>
					<tr>
						<th>First name</th>

						<th>Last name</th>

						<th>Email</th>
						<th>Role</th>
						<th></th>
					</tr>
					<%
																						
						for (User u : users) {
					%>
					<tr>
						<td><%=u.getFirstName()%></td>
						<td><%=u.getLastName()%></td>
						<td><%=u.getEmail()%></td>
						<td><%=u.getRole() %></td>
						<td>
						<a href='editUser?email=<%=u.getEmail()%>'>
						<i class="general foundicon-edit"> EDIT</i>
						</a>
						</td>
						
						<td>
						<a href='revoke?email=<%=u.getEmail()%>'>
						<span class='badge badge-inverse'>
						<i class="general foundicon-trash"> REVOKE </i>
						</span>
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

		$('#menu_au').attr({
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