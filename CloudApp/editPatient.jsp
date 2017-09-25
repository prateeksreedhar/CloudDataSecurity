<!DOCTYPE HTML>
<%@page import="com.dsce.pojo.Patient"%>
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
				<a href='patientList'> <i class="general foundicon-left-arrow">
						Go Back </i>
				</a>
				<div class="page-header">
					<h1>Edit Patients Data</h1>
				</div>


				<%
					String chk = (String) request.getAttribute("access");
					String chk2 = request.getParameter("update");
				%>
				<%
					if (chk != null && chk.equals("no")) {
				%>
				
				<div class='alert alert-error'>
					<i class="general foundicon-remove"> Aww. ! Looks like you
						don't have EDIT/WRITE access on Patients Data at this point of
						time. Please contact the Portal Administrator for obtaining the
						access. </i>
				</div>
				<%
					} else if (chk2 != null && chk2.equals("done")) {
						%>
							<div class='alert alert-info'>
							<i class="general foundicon-checkmark">Successfully updated the Patient's record.</i> <br/>
							<br/>
							<a href='patientList'> <i class="general foundicon-left-arrow">
								Go to Patients' list </i>
							</a>
							</div>
						
						<%
					} else if (chk2!=null && chk2.equals("fail")) {
						%>	<div class='alert alert-error'>
						<i class="general foundicon-remove"> Aww. ! Something went wrong while updating the record. Please try again later. </i>
						</div>
						
						<%
					} else {
				%>
				<%
					Patient p = (Patient) request.getAttribute("patient");
				%>

				<form action='updatePatient' method='post' id='editPatient' method='post'>
					<table class='table'>
						<tr>
							<td>Patient ID</td>
							<td><input type=text name='id' value='<%=p.getId()%>' readonly /></td>
						</tr>
						
						<tr>
							<td>Name</td>
							<td><input type=text name='name' value='<%=p.getName()%>' /></td>
						</tr>
						
						<tr>
							<td>Gender</td>
							<td><input type=text name='gender' value='<%=p.getGender()%>' /></td>
						</tr>
						
						<tr>
							<td>Mobile</td>
							<td><input type=text name='mobile' value='<%=p.getMobile()%>' /></td>
						</tr>
						
						<tr>
							<td>Age</td>
							<td><input type=text name='age' value='<%=p.getAge()%>' /></td>
						</tr>
						
						<tr>
							<td>Address</td>
							<td><textarea name='address' ><%=p.getAddress()%></textarea></td>
						</tr>
						
						
						<tr>
							<td></td>
							<td><input type=submit value='UPDATE' class='btn btn-info'/></td>
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

		$('#menu_pt').attr({
			'class' : 'dropdown active'
		})

		$('#editPatient').validate({
			rules : {
				'name' : 'required',
				'age' : 'required',
				'address' : 'required',
				'mobile' : 'required',
				'gender' : 'required'
			},
			messages : {
				'name' : '* Name is a mandatory field',
				'age' : '* Age is a mandatory field',
				'address' : '* Address is a mandatory field',
				'mobile' : '* Mobile is a mandatory field',
				'gender' : '* Gender is a mandatory field'
			},
			submitHandler : function(form) {
				form.submit();
			}
		});
	});
</script>