<%@ include file="/resources/jsp/header.jsp" %>
	<tr>
		<td>
			<table border=0 cellpadding=0 cellspacing=0 width=100%>
				<tr>
					<td align='left'><p class="heading2" width=150>Candidate Details</p></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td align="center">
			<table border=1 bordercolor='#D1D0CE' cellpadding=2 cellspacing=0 width=75%>
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
		</td>
	</tr><%@ include file="/resources/jsp/footer.jsp" %>