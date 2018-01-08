<%@page import="example.hibernate.Candidate"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ include file="/resources/jsp/header.jsp" %>
	<div id="invisible-placement-container">
		<div id="left-column-wide-box">
			<div id="page-title-area">
				<table border=0 cellspacing=0 cellpadding=0>
					<tr>
						<td valign='bottom'><span class="heading2"><spring:message code="label.add_application.header"/></span></td>
						<td width=25>&nbsp;</td>
						<td width=25>&nbsp;</td>
					</tr>
				</table>
			</div>
			<div id="main-content-box">
				<sf:form method="POST" commandName="applicationData" action="${pageContext.servletContext.contextPath}/addSubmittedApplication.htm">
				<div class="row" style="padding-top: 15px; padding-bottom: 0px;">
				  <div class="col-lg-8">
				  	<span class="heading3"><spring:message code="label.application_position_detail.header"/></span>
				  	<sf:errors path="application.positionName" cssClass="error" /><br>
				  	<spring:message code="label.add_application.form.position_name"/><br>
				  	<sf:input path="application.positionName" class="form-control input-sm" aria-describedby="sizing-addon3" size='50' maxlength='255'/>
				  </div>
				</div>
				<div class="row" style="padding-top: 15px; padding-bottom: 0px;">
				  <div class="col-lg-4">
				  	<sf:errors path="application.positionId" cssClass="error" /><br>
				  	<spring:message code="label.add_application.form.position_id"/><br>
				  	<sf:input path="application.positionId" class="form-control input-sm" aria-describedby="sizing-addon3" size='20' maxlength='20'/>
				  </div>
				  <div class="col-lg-4">
				  	<sf:errors path="application.applicationStatus" cssClass="error" /><br>
				  	<spring:message code="label.add_application.form.position_status"/><br>
				  	<sf:select class="form-control input-sm" path="application.applicationStatus">
				  		<sf:option value="NONE" label="(select)"/>
						<c:forEach items="${applicationStatusTypes}" var="statusType">
					  		<sf:option value="${statusType.applicationStatusTypeName}" label="${statusType.applicationStatusTypeName}"/>
						</c:forEach>						  		
				  	</sf:select>
				  </div>
				  <div class="col-lg-4">
				  	<sf:errors path="application.positionType" cssClass="error" /><br>
				  	<spring:message code="label.add_application.form.position_type"/><br>
				  	<sf:select class="form-control input-sm" path="application.positionType">
				  		<sf:option value="NONE" label="(select)"/>
						<c:forEach items="${positionTypes}" var="type">
					  		<sf:option value="${type.positionTypeDescription}" label="${type.positionTypeDescription}"/>
						</c:forEach>						  		
				  	</sf:select>
				  </div>
				</div>
				<div class="row" style="padding-top: 15px; padding-bottom: 0px;">
				  <div class="col-lg-4">
				  	<sf:errors path="application.endClient" cssClass="error" /><br>
				  	<spring:message code="label.add_application.form.end_client"/><br>
				  	<sf:input path="application.endClient" class="form-control input-sm" aria-describedby="sizing-addon3" size='25' maxlength='255'/>
				  </div>
				  <div class="col-lg-4">
				  	<sf:errors path="application.jobSourceName" cssClass="error" /><br>
				  	<spring:message code="label.add_application.form.job_source_name"/><br>
				  	<sf:input path="application.jobSourceName" class="form-control  input-sm" aria-describedby="sizing-addon3" size='20' maxlength='50'/>
				  </div>
				  <div class="col-lg-4">
				  	<sf:errors path="application.jobSourceType" cssClass="error" /><br>
				  	<spring:message code="label.add_application.form.job_source_type"/><br>
				  	<sf:select class="form-control input-sm" path="application.jobSourceType">
				  		<sf:option value="NONE" label="(select)"/>
						<c:forEach items="${jobSourceTypes}" var="type">
					  		<sf:option value="${type.jobSourceTypeDescription}" label="${type.jobSourceTypeDescription}"/>
						</c:forEach>						  		
				  	</sf:select>
				  </div>
				</div>
				<div class="row" style="padding-top: 10px; padding-bottom: 0px; padding-left: 0px; paddig-right: 0px;">
				  <div class="col-lg-12">
				  	<sf:errors path="application.jobDescription" cssClass="error" /><br>
				  	<spring:message code="label.add_application.form.job_description"/><br>
				  	<sf:textarea class="form-control input-sm" path="application.jobDescription" rows='15' maxlength='5000'/>
				  </div>
				</div>
				<div class="row" style="padding-top: 15px; padding-bottom: 0px; padding-left: 0px; padding-right: 0px;">
					<div class="col-lg-12">
						<div class="row">
							<div class="col-lg-6">
								<span class="heading3"><spring:message code="label.add_application-contact.header"/></span>
								<sf:errors path="applicationContact.contactName" cssClass="error" /><br>
								<spring:message code="label.form.add_application_contact.field.contact_name"/><br>
								<sf:input path="applicationContact.contactName" class="form-control input-sm" aria-describedby="sizing-addon3" size='15' maxlength='50'/>
							</div>
						</div>											
						<div class="row">
							<div class="col-lg-6">
								<sf:errors path="applicationContact.contactDescription" cssClass="error" /><br>
	 								<spring:message code="label.form.add_application_contact.field.contact_description"/><br>
								<sf:textarea class="form-control input-sm" path="applicationContact.contactDescription" rows='3' maxlength='255'/>
							</div>
						</div>
					</div>
				</div>
				<div class="row" style="padding-top: 10px;">
					 <div class="col-lg-4">
					 	&nbsp;
					</div>
					 <div class="col-lg-4">
					 	&nbsp;
					</div>
					 <div class="col-lg-4" align='right'>
						<input class="btn btn-default" type="button" value="Cancel" onClick="window.location.href='${pageContext.servletContext.contextPath}/applications.htm';" />
					 	&nbsp;&nbsp;
						<input class="btn btn-primary" type="submit" value="Submit"/>
					</div>
				</div>
				</sf:form>	
			</div>
			<div class="post-content-spacing">
				&nbsp;
			</div>
		</div>
	</div>			
<%@ include file="/resources/jsp/footer.jsp" %>