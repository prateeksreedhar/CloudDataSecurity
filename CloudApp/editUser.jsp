<!DOCTYPE HTML>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
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
				<a href='allUsers'> <i class="general foundicon-left-arrow">
						Go Back </i>
				</a>
				<div class="page-header">
					<h1>
						Edit Users <small>Reassign the roles and Policies.</small>
					</h1>
				</div>
				<%
					String chk = request.getParameter("error");
							String chk2 = request.getParameter("edit");
							if (chk!=null && chk.equals("true")) {
				%>
				<div class='alert alert-error'>
					<i class="general foundicon-remove"> Aww. ! Something went
						wrong while fetching the user information. Please try again later.
					</i>
				</div>

				<%
					} else if (chk2!=null && chk2.equals("done")) {
				%>
			
				<div class='alert alert-info'>
					<i class="general foundicon-checkmark"> Successfully Updated
						the User roles and policies. </i>
				</div>
				<%
					} else if (chk2!=null && chk2.equals("fail")) {
				%>
				<div class='alert alert-error'>
					<i class="general foundicon-remove"> Aww. ! Something went
						wrong while updating the user roles and policies. Please try again
						later. </i>
				</div>
				<%
					}
								
							else {
				%>
				<%
					User u = (User) request.getAttribute("user");
							Map<String, Integer> policies = (Map<String, Integer>) request.getAttribute("policies");
							int lab = policies.get(Constants.RESOURCE_LABREPORT);
							int patient = policies.get(Constants.RESOURCE_PATIENT);
							int presc = policies.get(Constants.RESOURCE_PRESCRIPTION);
				%>

				<form action='updateUser' method='post'>
					<table cellspacing=4 cellpadding=5 class='table'>

						<tr>
							<td>Email</td>
							<td><%=u.getEmail()%> <input type=hidden name='email'
								value='<%=u.getEmail()%>' /></td>
						</tr>
						<tr>
							<td>First name</td>
							<td><%=u.getFirstName()%></td>
						</tr>
						<tr>
							<td>Last name</td>
							<td><%=u.getLastName()%></td>
						</tr>

						<tr>
							<td>Role</td>
							<td><select name='role'>
									<option value='<%=Constants.ROLE_DOCTOR%>'
										<%if (u.getRole().equals(Constants.ROLE_DOCTOR)) {%>
										selected <%}%>><%=Constants.ROLE_DOCTOR%></option>
									<option value='<%=Constants.ROLE_NURSE%>'
										<%if (u.getRole().equals(Constants.ROLE_NURSE)) {%> selected
										<%}%>><%=Constants.ROLE_NURSE%></option>
									<option value='<%=Constants.ROLE_LABTECHNICIAN%>'
										<%if (u.getRole().equals(Constants.ROLE_LABTECHNICIAN)) {%>
										selected <%}%>><%=Constants.ROLE_LABTECHNICIAN%></option>
							</select></td>
						</tr>

						<tr>
							<td>Access to <%=Constants.RESOURCE_PATIENT%> Data
							</td>
							<td><select class='form-control'
								name='<%=Constants.RESOURCE_PATIENT%>'>
									<option value='0' <%if (patient==0) {%> selected <%}%>>No
										Access</option>
									<option value='1' <%if (patient==1) {%> selected <%}%>>Read
										only Access</option>
									<option value='2' <%if (patient==2) {%> selected <%}%>>Read
										Write Access</option>
							</select></td>

						</tr>

						<tr>
							<td>Access to <%=Constants.RESOURCE_PRESCRIPTION%> Data
							</td>
							<td><select class='form-control'
								name='<%=Constants.RESOURCE_PRESCRIPTION%>'>
									<option value='0' <%if (presc==0) {%> selected <%}%>>No
										Access</option>
									<option value='1' <%if (presc==1) {%> selected <%}%>>Read
										only Access</option>
									<option value='2' <%if (presc==2) {%> selected <%}%>>Read
										Write Access</option>
							</select></td>

						</tr>

						<tr>
							<td>Access to <%=Constants.RESOURCE_LABREPORT%> Data
							</td>
							<td><select class='form-control'
								name='<%=Constants.RESOURCE_LABREPORT%>'>
									<option value='0' <%if (lab==0) {%> selected <%}%>>No
										Access</option>
									<option value='1' <%if (lab==1) {%> selected <%}%>>Read
										only Access</option>
									<option value='2' <%if (lab==2) {%> selected <%}%>>Read
										Write Access</option>
							</select></td>

						</tr>
						<tr>
							<td></td>
							<td><input type='submit' class='btn btn-large btn-info'
								value='UPDATE USER' /></td>

						</tr>

					</table>
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