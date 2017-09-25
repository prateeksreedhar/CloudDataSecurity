<%@page import="com.dsce.util.Constants" %>
<div class="row-fluid">
	<div class="span12">

		<div id="divLogo">
			<a href="index.html" id="divSiteTitle"> <%
 	String user = (String) session.getAttribute("loggedInUser");
 	String role = (String) session.getAttribute("loggedInRole");
 	if (user != null) {
 %> <%=role%></a><br /> <a href="index.html" id="divTagLine"><%=user%>
			</a> <span class="label"><a href='logout'
				style='text-decoration: none; color: white;'>Logout</a></span>
			<%
				} else {
			%>
			Welcome Guest </a><br /> <a href="index.html" id="divTagLine">You
				haven't logged in.</a>

			<%
				}
			%>
		</div>

	</div>
</div>
<div class="row-fluid">
	<div class="span12">

		<div id="divMenuRight" class="pull-right">
			<div class="navbar">
				<button type="button"
					class="btn btn-navbar-highlight btn-large btn-primary"
					data-toggle="collapse" data-target=".nav-collapse">
					NAVIGATION <span class="icon-chevron-down icon-white"></span>
				</button>
				<div class="nav-collapse collapse">
					<ul class="nav nav-pills ddmenu">
					
					<%
						if (role!=null && role.equals("Admin") ) {
							%>
							
								<li id='menu_home' class="dropdown"><a href="index.jsp">Home</a></li>
								<li id='menu_nr' class="dropdown"><a href="adminHome">New Requests</a></li>
								<li id='menu_au' class="dropdown"><a href="allUsers">All Users</a></li>
								<li id='menu_ad' class="dropdown"><a href="adminHome">All Data</a></li>
								
							<% 
						} 
						else if (role !=null && role.equals(Constants.ROLE_DOCTOR)) {
							%>
								<li id='menu_home' class="dropdown"><a href="index.jsp">Home</a></li>
								<li id='menu_ap' class="dropdown"><a href="getPolicy?email=<%=user%>">Access Policy</a></li>
								<li id='menu_pt' class="dropdown"><a href="patientList">Patients</a></li>
								<li id='menu_lr' class="dropdown"><a href="adminHome">Lab Reports</a></li>
								<li id='menu_ps' class="dropdown"><a href="adminHome">Prescription</a></li>					
							
							<%
						} 
						else if (role != null && role.equals(Constants.ROLE_NURSE)) {
							%>								
								<li id='menu_home' class="dropdown"><a href="index.jsp">Home</a></li>
								<li id='menu_ap' class="dropdown"><a href="getPolicy?email=<%=user%>">Access Policy</a></li>
								<li id='menu_pt' class="dropdown"><a href="patientList">Patients</a></li>
								<li id='menu_lr' class="dropdown"><a href="adminHome">Lab Reports</a></li>
								<li id='menu_ps' class="dropdown"><a href="adminHome">Prescription</a></li>																			
							<%
						}
						else if (role != null && role.equals(Constants.ROLE_LABTECHNICIAN)) {
							%>								
							<li id='menu_home' class="dropdown"><a href="index.jsp">Home</a></li>
							<li id='menu_ap' class="dropdown"><a href="getPolicy?email=<%=user%>">Access Policy</a></li>
							<li id='menu_pt' class="dropdown"><a href="patientList">Patients</a></li>
							<li id='menu_lr' class="dropdown"><a href="adminHome">Lab Reports</a></li>
							<li id='menu_ps' class="dropdown"><a href="adminHome">Prescription</a></li>																			
						<%
					}
						
						else {
					%>
					
						<li id='menu_home' class="dropdown"><a href="index.jsp">Home</a></li>
						<li id='menu_site' class="dropdown"><a href="#"
							class="dropdown-toggle">Enter site <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li class="dropdown"><a href="#" class="dropdown-toggle">Users
										&nbsp;&raquo;</a>
									<ul class="dropdown-menu sub-menu">
										<li><a href="login.jsp">Existing Users</a></li>
										<li><a href="register.jsp">New Users</a></li>
									</ul></li>
								<li><a href="admin.jsp">Admin</a></li>
							</ul></li>
						<li id='menu_admin' class="dropdown"><a href="admin.jsp">Admin</a></li>
						<li id='menu_login' class="dropdown"><a href="login.jsp">Login</a></li>
						<li id='menu_register' class="dropdown"><a
							href="register.jsp">Register</a></li>
							
					<% } %>
					</ul>
				</div>
			</div>
		</div>

	</div>
</div>