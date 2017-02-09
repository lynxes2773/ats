<%@page import="example.hibernate.Candidate"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ include file="/resources/jsp/header.jsp" %>
		<div id="invisible-placement-container">
			<div id="left-column-wide-box" class="col-lg-9">
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
					<sf:form method="POST" commandName="application" action="http://localhost:8080/ats/addSubmittedApplication.htm">
					<div class="row" style="padding-top: 2px; padding-bottom: 2px;">
					  <div class="col-lg-8">
					  	<sf:errors path="positionName" cssClass="error" /><br>
					  	<spring:message code="label.add_application.form.position_name"/><br>
					  	<sf:input path="positionName" size='50' maxlength='255'/>
					  </div>
					</div>
					<div class="row" style=style="padding-top: 2px; padding-bottom: 2px;">
					  <div class="col-lg-4">
					  	<sf:errors path="positionId" cssClass="error" /><br>
					  	<spring:message code="label.add_application.form.position_id"/><br>
					  	<sf:input path="positionId" size='20' maxlength='20'/>
					  </div>
					  <div class="col-lg-4">
					  	<sf:errors path="applicationStatus" cssClass="error" /><br>
					  	<spring:message code="label.add_application.form.position_status"/><br>
					  	<sf:select path="applicationStatus">
					  		<sf:option value="NONE" label="(select)"/>
							<c:forEach items="${applicationStatusTypes}" var="statusType">
						  		<sf:option value="${statusType.applicationStatusTypeName}" label="${statusType.applicationStatusTypeName}"/>
							</c:forEach>						  		
					  	</sf:select>
					  </div>
					  <div class="col-lg-4">
					  	<sf:errors path="positionType" cssClass="error" /><br>
					  	<spring:message code="label.add_application.form.position_type"/><br>
					  	<sf:select path="positionType">
					  		<sf:option value="NONE" label="(select)"/>
							<c:forEach items="${positionTypes}" var="type">
						  		<sf:option value="${type.positionTypeDescription}" label="${type.positionTypeDescription}"/>
							</c:forEach>						  		
					  	</sf:select>
					  </div>
					</div>
					<div class="row" style=style="padding-top: 2px; padding-bottom: 2px;">
					  <div class="col-lg-4">
					  	<sf:errors path="endClient" cssClass="error" /><br>
					  	<spring:message code="label.add_application.form.end_client"/><br>
					  	<sf:input path="endClient" size='25' maxlength='255'/>
					  </div>
					  <div class="col-lg-4">
					  	<sf:errors path="jobSourceName" cssClass="error" /><br>
					  	<spring:message code="label.add_application.form.job_source_name"/><br>
					  	<sf:input path="jobSourceName" size='20' maxlength='50'/>
					  </div>
					  <div class="col-lg-4">
					  	<sf:errors path="jobSourceType" cssClass="error" /><br>
					  	<spring:message code="label.add_application.form.jon_source_type"/><br>
					  	<sf:select path="jobSourceType">
					  		<sf:option value="NONE" label="(select)"/>
							<c:forEach items="${jobSourceTypes}" var="type">
						  		<sf:option value="${type.jobSourceTypeDescription}" label="${type.jobSourceTypeDescription}"/>
							</c:forEach>						  		
					  	</sf:select>
					  </div>
					</div>
					<div class="row" style="padding-top: 2px; padding-bottom: 2px;">
					  <div class="col-lg-9">
					  	<sf:errors path="jobDescription" cssClass="error" /><br>
					  	<spring:message code="label.add_application.form.job_description"/><br>
					  	<sf:textarea path="jobDescription" rows='15.' cols='90' maxlength='5000'/>
					  </div>
					</div>
					<div class="row" style="padding-top: 5px;">
						 <div class="col-md-2">
						 	&nbsp;<br>
							<input class="btn-default" type="submit" value="Submit Entry"/>
						</div>
					</div>					
					</sf:form>	
				</div>
				<div class="post-content-spacing">
					&nbsp;
				</div>
			</div>
			<div id="right-column-narrow-box" class="col-lg-3"> 
				<div id="card-sized-box">
					Contact Person 
				</div>
				<div id="card-sized-box">
					Attachments
				</div>
				<div id="card-sized-box">
					Comments
				</div>
			</div>
		</div>			
<%@ include file="/resources/jsp/footer.jsp" %>