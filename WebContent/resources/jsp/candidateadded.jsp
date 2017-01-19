<%@ include file="/resources/jsp/header.jsp" %>
		<div id="content-area">
			<div id="page-title-area">
				<table border=0 cellspacing=0 cellpadding=0>
					<tr>
						<td align='left'><p class="heading2" width=150>Candidate Details</p></td>
					</tr>
				</table>
			</div>
			<div class="main-content-box">			
				<table class="entity-detail" border=1 bordercolor='#D1D0CE' cellpadding=2 cellspacing=0 width=75%>
						<tr>
							<td>Candidate ID</td><td>${candidate.candidateId}</td>
						</tr>
						<tr>
							<td>Name</td><td>${candidate.firstName} ${candidate.lastName}</td>
						</tr>
						<tr>
							<td>Gender</td><td>${candidate.gender}</td>
						</tr>
						<tr>	
							<td>Email Address</td><td>${candidate.email}</td>
						</tr>
				</table>
			</div>	
			<div class="post-content-spacing">
				&nbsp;
			</div>
		</div>
<%@ include file="/resources/jsp/footer.jsp" %>