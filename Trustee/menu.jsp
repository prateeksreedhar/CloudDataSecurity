
<%
	String chk = (String) session.getAttribute("loggedIn");
	if (chk != null && chk.equals("yes")) {
%>
<div id="sidebar-wrapper">
	<ul class="sidebar-nav">
		<li class="sidebar-brand"><span style='color: white;'>TRUSTEE</span></li>
		<li class='active'><a href="#">HOME</a></li>
		<li><a href="flow.jsp">FLOW OF TRSUTEE</a></li>
		<li><a href="design.jsp">DESIGN OF TRSUTEE</a></li>
		<li><a href="tokens">TOKENS</a></li>
		<li><a href="logout">LOGOUT</a></li>
	</ul>
</div>
<%
	} else {
%>
<div id="sidebar-wrapper">
	<ul class="sidebar-nav">
		<li class="sidebar-brand"><span style='color: white;'>TRUSTEE</span></li>
		<li class='active'><a href="#">HOME</a></li>
		<li><a href="flow.jsp">FLOW OF TRSUTEE</a></li>
		<li><a href="design.jsp">DESIGN OF TRSUTEE</a></li>

		<li><a href="login.jsp">ADMIN LOGIN</a></li>
	</ul>
</div>


<%
	}
%>