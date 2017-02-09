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
			<div id="main-content-box">
				<sf:form method="POST" commandName="candidate" action="http://localhost:8080/ats/addSubmittedCandidate.htm">
				<!-- Stack the columns on mobile by making one full-width and the other half-width -->
					<div class="row" style="padding-top: 5px;">
					  <div class="col-md-3">
					  	<sf:errors path="firstName" cssClass="error" /><br>
					  	<spring:message code="label.add_candidate.form.firstName"/><br>
					  	<sf:input path="firstName" size='25' maxlength='20'/>
					  </div>
					  <div class="col-md-3">
					  	<sf:errors path="lastName" cssClass="error" /><br>
					  	<spring:message code="label.add_candidate.form.lastName"/><br>
					  	<sf:input path="lastName" size='25' maxlength='20'/>
					  </div>
					</div>
					<div class="row" style="padding-top: 5px;">
					  <div class="col-md-4">
					  	<sf:errors path="email" cssClass="error" /><br>
					  	<spring:message code="label.add_candidate.form.email"/><br>
					  	<sf:input path="email" size='55' maxlength='50'/>
					  </div>
					</div>
					<div class="row" style="padding-top: 5px;">
					  <div class="col-md-2">
					  		&nbsp;<br>
					  		<spring:message code="label.add_candidate.form.gender"/><br>
							<sf:select path="gender">
								<sf:option value="M"><spring:message code="label.add_candidate.form.gender.male"/></sf:option>
								<sf:option value="F"><spring:message code="label.add_candidate.form.gender.female"/></sf:option>
							</sf:select>
					  </div>
					</div>
					<div class="row" style="padding-top: 5px;">
						 <div class="col-md-2">
						 	&nbsp;<br>
							<input class="btn btn-default" type="submit" value="Submit Entry"/>
						</div>
					</div>
				</sf:form>	
			</div>
			<div class="post-content-spacing">
				&nbsp;
			</div>			
		</div>			
<%@ include file="/resources/jsp/footer.jsp" %>