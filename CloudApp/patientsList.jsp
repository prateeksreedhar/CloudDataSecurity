<!DOCTYPE HTML>
<%@page import="com.dsce.pojo.Patient"%>
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
					<h1>Patients Data</h1>
				</div>


				<%
					String chk = request.getParameter("access");
				%>
				<%
					if (chk != null && chk.equals("no")) {
				%>

				<div class='alert alert-error'>
					<i class="general foundicon-remove"> Aww. ! Looks like you
						don't have READ access on Patients Data at this point of time.
						Please contact the Portal Administrator for obtaining the access.
					</i>
				</div>
				<%
					} else {
				%>

				<form class='pull-right' action='initiatePatientWrite' method=post>
					<input type=submit value='WRITE NEW PATIENT DATA' class='btn btn-info btn-large'/>
				</form>
				
				<h1><small>
						Here are the list of Patients data written </small>
				</h1>
				<table class='table'>
					<tr>
						<th>Patient ID</th>
						<th>Name</th>
						<th>Gender</th>
						<th>Mobile</th>
						<th>Age</th>
						<th>Address</th>
						<th></th>
					</tr>
					<%
						List<Patient> decryptedPatientsList = (List<Patient>) request.getAttribute("decryptedPatientsList");
									for (Patient p : decryptedPatientsList) {
					%>
					<tr>
						<td><%=p.getId()%></td>
						<td><%=p.getName()%></td>
						<td><%=p.getGender()%></td>
						<td><%=p.getMobile()%></td>
						<td><%=p.getAge()%></td>
						<td><%=p.getAddress()%></td>
						<td><a href='editPatient?id=<%=p.getId() %>' > <i class="general foundicon-edit"> EDIT	</i></a></td>
					</tr>
					<%
						}
					%>
				</table>

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

		$('#menu_pt').attr({
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