<%@page import="example.hibernate.Candidate"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ include file="/resources/jsp/header.jsp" %>
		<div id="content-area">
			<div id="page-title-area">
				<table border=0 cellspacing=0 cellpadding=0>
					<tr>
						<td valign='bottom'><span class="heading2"><spring:message code="label.add_candidate.header"/></span></td>
						<td width=25>&nbsp;</td>
						<td width=25>&nbsp;</td>
					</tr>
				</table>
			</div>
			<div class="main-content-box">
				<sf:form method="POST" commandName="candidate" action="http://localhost:8080/ats/addSubmittedCandidate.htm">
				<table class="form" border=0 cellpadding=2 cellspacing=2>
					<tr>
						<td>
							<sf:errors path="firstName" cssClass="error" />
						</td>
						<td>
							<sf:errors path="lastName" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td>
							<spring:message code="label.add_candidate.form.firstName"/>
						</td>
						<td>
							<spring:message code="label.add_candidate.form.lastName"/>
						</td>
					</tr>
					<tr>
						<td>
							<sf:input path="firstName" size='20'/>
						</td>
						<td>
							<sf:input path="lastName" size='20' />
						</td>
					</tr>
					<tr>
						<td>
							<sf:errors path="email" cssClass="error" />
						</td>
						<td width=10>
							&nbsp;
						</td>
					</tr>
					<tr>
						<td>
							<spring:message code="label.add_candidate.form.email"/>
						</td>
						<td>
							<spring:message code="label.add_candidate.form.gender"/>
						</td>
					</tr>
					<tr>
						<td>
							<sf:input path="email" size='35' />
						</td>
						<td>
							<sf:select path="gender">
								<sf:option value="M"><spring:message code="label.add_candidate.form.gender.male"/></sf:option>
								<sf:option value="F"><spring:message code="label.add_candidate.form.gender.female"/></sf:option>
							</sf:select>
						</td>
					</tr>
					<tr><td><input type="submit" value="Submit Entry"/></td></tr>
				</table>
				</sf:form>
			</div>
			<div class="post-content-spacing">
				&nbsp;
			</div>			
		</div>
<%@ include file="/resources/jsp/footer.jsp" %>