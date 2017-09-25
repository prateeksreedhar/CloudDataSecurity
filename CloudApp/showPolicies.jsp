<!DOCTYPE HTML>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
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

				<div class="page-header">
					<h1>Access Policies</h1>
				</div>


				<%
					String chk = request.getParameter("error");
				%>
				<%
					if (chk != null && chk.equals("true")) {
				%>

				<div class='alert alert-error'>
					<i class="general foundicon-remove"> Aww. ! Something went
						wrong while fetching the Access Policy granted for you. Please try
						again later.</i>
				</div>
				<%
					} else {
				%>

				<table class='table' cellspacing=5 cellpadding=5>
					<tr>
						<th>Resource Type</th>
						<th>Access</th>
					</tr>
					
					<%
						
					Map<String, Integer> policies = (Map<String, Integer>) request.getAttribute("policies");
					for (Entry<String, Integer> policy : policies.entrySet()) {
						%>
						
							<tr>
								<td>
									<%=policy.getKey() %>
								</td>
								
								
								<td> 
									<%
									
										int p = policy.getValue();
										if (p>=1) {
											%>
												<span class='badge badge-info' style='font-size: 18px; padding:10px;'> <i class="general foundicon-checkmark"> Read </i></span>
											<%
										} else {
											%>
												<span class='badge badge-inverse' style='font-size: 18px; padding:10px;'><i class="general foundicon-remove"> Read </i></span>
											<%
										}
										if (p>=2) {
											%>
												<span class='badge badge-info' style='font-size: 18px; padding:10px;'><i class="general foundicon-checkmark"> Write</i></span>
												<span class='badge badge-info' style='font-size: 18px; padding:10px;'><i class="general foundicon-checkmark"> Edit</i></span>
											<%
										} else {
											%>
												<span class='badge badge-inverse' style='font-size: 18px; padding:10px;'><i class="general foundicon-remove"> Write</i></span>
												<span class='badge badge-inverse' style='font-size: 18px; padding:10px;'><i class="general foundicon-remove"> Edit</i></span>
											<%
										}
									
									%>
									
									
						
									
								</td>
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

		$('#menu_ap').attr({
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