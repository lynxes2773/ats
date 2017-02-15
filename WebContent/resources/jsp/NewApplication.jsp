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
					<div class="row" style="padding-top: 15px; padding-bottom: 0px;">
					  <div class="col-lg-8">
					  	<sf:errors path="positionName" cssClass="error" /><br>
					  	<spring:message code="label.add_application.form.position_name"/><br>
					  	<sf:input path="positionName" class="form-control input-sm" aria-describedby="sizing-addon3" size='50' maxlength='255'/>
					  </div>
					</div>
					<div class="row" style="padding-top: 15px; padding-bottom: 0px;">
					  <div class="col-lg-4">
					  	<sf:errors path="positionId" cssClass="error" /><br>
					  	<spring:message code="label.add_application.form.position_id"/><br>
					  	<sf:input path="positionId" class="form-control input-sm" aria-describedby="sizing-addon3" size='20' maxlength='20'/>
					  </div>
					  <div class="col-lg-4">
					  	<sf:errors path="applicationStatus" cssClass="error" /><br>
					  	<spring:message code="label.add_application.form.position_status"/><br>
					  	<sf:select class="form-control input-sm" path="applicationStatus">
					  		<sf:option value="NONE" label="(select)"/>
							<c:forEach items="${applicationStatusTypes}" var="statusType">
						  		<sf:option value="${statusType.applicationStatusTypeName}" label="${statusType.applicationStatusTypeName}"/>
							</c:forEach>						  		
					  	</sf:select>
					  </div>
					  <div class="col-lg-4">
					  	<sf:errors path="positionType" cssClass="error" /><br>
					  	<spring:message code="label.add_application.form.position_type"/><br>
					  	<sf:select class="form-control input-sm" path="positionType">
					  		<sf:option value="NONE" label="(select)"/>
							<c:forEach items="${positionTypes}" var="type">
						  		<sf:option value="${type.positionTypeDescription}" label="${type.positionTypeDescription}"/>
							</c:forEach>						  		
					  	</sf:select>
					  </div>
					</div>
					<div class="row" style="padding-top: 15px; padding-bottom: 0px;">
					  <div class="col-lg-4">
					  	<sf:errors path="endClient" cssClass="error" /><br>
					  	<spring:message code="label.add_application.form.end_client"/><br>
					  	<sf:input path="endClient" class="form-control input-sm" aria-describedby="sizing-addon3" size='25' maxlength='255'/>
					  </div>
					  <div class="col-lg-4">
					  	<sf:errors path="jobSourceName" cssClass="error" /><br>
					  	<spring:message code="label.add_application.form.job_source_name"/><br>
					  	<sf:input path="jobSourceName" class="form-control  input-sm" aria-describedby="sizing-addon3" size='20' maxlength='50'/>
					  </div>
					  <div class="col-lg-4">
					  	<sf:errors path="jobSourceType" cssClass="error" /><br>
					  	<spring:message code="label.add_application.form.jon_source_type"/><br>
					  	<sf:select class="form-control input-sm" path="jobSourceType">
					  		<sf:option value="NONE" label="(select)"/>
							<c:forEach items="${jobSourceTypes}" var="type">
						  		<sf:option value="${type.jobSourceTypeDescription}" label="${type.jobSourceTypeDescription}"/>
							</c:forEach>						  		
					  	</sf:select>
					  </div>
					</div>
					<div class="row" style="padding-top: 10px; padding-bottom: 0px; padding-left: 0px; paddig-right: 0px;">
					  <div class="col-lg-12">
					  	<sf:errors path="jobDescription" cssClass="error" /><br>
					  	<spring:message code="label.add_application.form.job_description"/><br>
					  	<sf:textarea class="form-control input-sm" path="jobDescription" rows='15' maxlength='5000'/>
					  </div>
					</div>
					<div class="row" style="padding-top: 15px;">
						 <div class="col-lg-4">
						 	&nbsp;
						</div>
						 <div class="col-lg-4">
						 	&nbsp;
						</div>
						 <div class="col-lg-4" align='right'>
							<input class="btn btn-default" type="button" value="Cancel"/>
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
			<div id="right-column-narrow-box" class="col-lg-3"> 
				<div id="card-sized-box">
					<div id="card-title-area">
						<div class="row">
							<div class="col-sm-7">
								<span class="heading3"><spring:message code="label.application.card_header.contact_person"/></span>
							</div>
							<div class="col-sm-3">
								&nbsp;
							</div>
							<div class="col-xs-1">
								<span class="card-icon-area"><i class="fa fa-user-o fa-lg" color:#888888;></i></span>
							</div>
						</div>
					</div>
				</div>
				<div id="card-sized-box">
					<div id="card-title-area">
						<div class="row">
							<div class="col-sm-7">
								<span class="heading3"><spring:message code="label.application.card_header.attachments"/></span>
							</div>
							<div class="col-sm-3">
								&nbsp;
							</div>
							<div class="col-xs-1">
								<span class="card-icon-area"><i class="fa fa-file-text-o fa-lg" style="color:#888888;"></i></span>
							</div>
						</div>
					</div>
				</div>
				<div id="card-sized-box">
					<div id="card-title-area">
						<div class="row">
							<div class="col-sm-7">
								<span class="heading3"><spring:message code="label.application.card_header.comments"/></span>
							</div>
							<div class="col-sm-3">
								&nbsp;
							</div>
							<div class="col-xs-1">
								<span class="card-icon-area"><i class="fa fa-comments-o fa-lg" style="color:#888888;"></i></span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>			
<%@ include file="/resources/jsp/footer.jsp" %>