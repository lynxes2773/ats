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
				<div class="main-content-box">
					<sf:form method="POST" commandName="application" action="http://localhost:8080/ats/addSubmittedApplication.htm">
					<div class="row" style="padding-top: 5px;">
					  <div class="col-md-3">
					  	<sf:errors path="positionName" cssClass="error" /><br>
					  	<spring:message code="label.add_application.form.position_name"/><br>
					  	<sf:input path="positionName" size='50' maxlength='255'/>
					  </div>
					</div>
					<div class="row" style="padding-top: 5px;">
					  <div class="col-md-3">
					  	<sf:errors path="applicationStatus" cssClass="error" /><br>
					  	<spring:message code="label.add_application.form.position_status"/><br>
					  	<sf:select path="applicationStatus">
					  		<sf:option value="NONE" label="(Select)"/>
							<c:forEach items="${applicationStatusTypes}" var="statusType">
						  		<sf:option value="${statusType.id}" label="${statusType.applicationStatusTypeName}"/>
							</c:forEach>						  		
					  	</sf:select>
					  </div>
					</div>
					<div class="row" style="padding-top: 5px;">
					  <div class="col-md-3">
					  	<sf:errors path="positionType" cssClass="error" /><br>
					  	<spring:message code="label.add_application.form.position_type"/><br>
					  	<sf:select path="positionType">
					  		<sf:option value="NONE" label="(Select)"/>
							<c:forEach items="${positionTypes}" var="type">
						  		<sf:option value="${type.id}" label="${type.positionTypeDescription}"/>
							</c:forEach>						  		
					  	</sf:select>
					  </div>
					</div>
					</sf:form>	
				</div>
				<div class="post-content-spacing">
					&nbsp;
				</div>
			</div>
			<div id="right-column-narrow-box">
				<div id="card-sized-box">
					Test 1
				</div>
				<div id="card-sized-box">
					Test 2
				</div>
				<div id="card-sized-box">
					Test 3
				</div>
				<div id="card-sized-box">
					Test 4
				</div>
			</div>
		</div>			
<%@ include file="/resources/jsp/footer.jsp" %>