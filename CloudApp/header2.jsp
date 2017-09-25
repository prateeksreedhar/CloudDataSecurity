
<div class="row-fluid">
	<div class="span12">

		<div id="headerSeparator"></div>

		<div class="row-fluid">
			<div class="span6">

				<div id="divHeaderText" class="page-content">
					<%
						String chk = request.getParameter("logout");
						if (chk != null && chk.equals("true")) {
					%>
					<div class="alert alert-error"><strong>You have been successfully
						logged out from the portal.</strong></div>
					<% } %>
					<div id="divHeaderLine1">Cloud Computing</div>
					<br />
					<div id="divHeaderLine2">Data Security In Clouds Using Decentralized Access Control and Anonymous Authentication</div>
					<br />
					
					<%
						String u = (String) session.getAttribute("loggedInUser");
						if (u == null) {
					%>
					
					<div id="divHeaderLine3">
						<a class="btn btn-large btn-secondary" href="login.jsp"> Existing
							users ! Sign in here</a>   <a class="btn btn-large btn-primary"
							href="register.jsp"> New users ! Register here </a>
					</div>
					
					<% } %>
				</div>

			</div>
			<div class="span6">

				<div id="camera_wrap">
					<div data-src="styles/working-on-keyboard.JPG">
						<div
							style="position: absolute; bottom: 10%; left: 3%; padding: 10px; width: 50%;"
							class="fadeIn camera_effected camera_caption cap1">Lorem
							Ipsum is simply dummy text of the printing and typesetting
							industry.</div>
					</div>
					<div data-src="styles/two-businessmen.jpg">
						<div class="camera_caption fadeFromBottom cap2">Lorem Ipsum
							is simply dummy text of the printing and typesetting industry.</div>
					</div>
				</div>

			</div>
		</div>

		<div id="headerSeparator2"></div>

	</div>
</div>