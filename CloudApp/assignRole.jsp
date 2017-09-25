<!DOCTYPE HTML>
<%@page import="com.dsce.util.Constants"%>
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

				<%
					String email = (String) request.getAttribute("email");
				%>
				<div class="page-header">
					<h1>
						<small>Assign roles and define the Policy for <%=email %>.</small>
					</h1>
				</div>
		
				<%
				
					String chk = request.getParameter("policy");
					if (chk!=null && chk.equals("done")) {
						%>
						<div class='alert alert-info'>
								<i class="general foundicon-checkmark"> 
										Successfully assigned the role and Policies.
								</i>
						</div>
						<%
					} else if (chk!=null && chk.equals("fail")) {
						%>
						<div class='alert alert-info'>
								<i class="general foundicon-remove"> 
										Aww ! Something went wrong while assigning roles and defining Policies. <a href='assignRole.jsp?email=<%=email%>'>Try again</a>
								</i>
						</div>
						<%
					} else {
						%>
			<form action='assignRoles' method=post id='role'>
				<table class='table' cellspacing=4 cellpadding=4>
					<tr>
						<td>User</td>
						<td>
						<input type=text name='email' readonly value='<%=email%>' /></td>
					</tr>

					<tr>
						<td>Role</td>
						<td><select class='form-control' name='role'>
								<option>Select One</option>
								<option value='<%=Constants.ROLE_DOCTOR%>'><%=Constants.ROLE_DOCTOR%>
								</option>
								<option value='<%=Constants.ROLE_NURSE%>'><%=Constants.ROLE_NURSE%>
								</option>
								<option value='<%=Constants.ROLE_LABTECHNICIAN%>'><%=Constants.ROLE_LABTECHNICIAN%>
								</option>
						</select></td>
					</tr>

					<tr>
						<td>Access to <%=Constants.RESOURCE_PATIENT%> Data
						</td>
						<td><select class='form-control' name='<%=Constants.RESOURCE_PATIENT%>'>
								<option>Select One</option>
								<option value='0'>No Access</option>
								<option value='1'>Read only Access</option>
								<option value='2'>Read Write Access</option>
						</select></td>

					</tr>

					<tr>
						<td>Access to <%=Constants.RESOURCE_PRESCRIPTION%> Data
						</td>
						<td><select class='form-control' name='<%=Constants.RESOURCE_PRESCRIPTION%>'>
								<option>Select One</option>
								<option value='0'>No Access</option>
								<option value='1'>Read only Access</option>
								<option value='2'>Read Write Access</option>
						</select></td>

					</tr>

					<tr>
						<td>Access to <%=Constants.RESOURCE_LABREPORT%> Data
						</td>
						<td><select class='form-control' name='<%=Constants.RESOURCE_LABREPORT%>'>
								<option>Select One</option>
								<option value='0'>No Access</option>
								<option value='1'>Read only Access</option>
								<option value='2'>Read Write Access</option>
						</select></td>

					</tr>
					
					<tr>
						<td>
						</td>
						<td align="right">
							<input type='reset' value='CLEAR' class='btn btn-secondary'/>&nbsp;&nbsp;&nbsp;&nbsp;
							<input type='submit' value='ASSIGN' class='btn btn-success'/>
						</td>

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

		$('#menu_nr').attr({
			'class' : 'dropdown active'
		})

		$('#role').validate({
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