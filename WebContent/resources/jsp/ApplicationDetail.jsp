<%@page import="example.hibernate.Candidate"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<script type="text/javascript" src="resources/scripts/pagewise/applicationdetail.js"></script>
<%@ include file="/resources/jsp/header.jsp" %>
		<div id="invisible-placement-container">
			<div id="left-column-wide-box" class="col-lg-9">
				<div id="page-title-area">
					<table border=0 cellspacing=0 cellpadding=0 width='100%'>
						<tr>
							<td width='76%' valign='bottom'>
								<span class="heading2">
								<c:choose>
									<c:when test="${!applicationEditable}">
										${applicationData.application.positionName}
									</c:when>
									<c:otherwise>
										<spring:message code="label.application_detail.header"/>
									</c:otherwise>
								</c:choose>
								</span>
							</td>
							<td width='24%' align='right'>
								<a class="free" href="${pageContext.servletContext.contextPath}/showApplicationEditable.htm?id=${applicationData.application.id}"><spring:message code="label.common.link.edit"/></a>
								&nbsp;&nbsp;&nbsp;
								<i class="fa fa-id-badge" aria-hidden="true"></i>
							</td>
						</tr>
					</table>
				</div>
				<div id="main-content-box">
					<sf:form method="POST" commandName="applicationData" action="${pageContext.servletContext.contextPath}/updateApplication.htm">
					<sf:hidden path="application.id" />
					<sf:hidden path="application.applicationDate" />
				  	<c:if test="${applicationEditable}">
						<div class="row" style="padding-top: 15px; padding-bottom: 0px;">
						  <div class="col-lg-8">
							  	<span style='field-label'><spring:message code="label.add_application.form.position_name"/></span><br>
							  	<sf:errors path="application.positionName" cssClass="error" /><br>	
							  	<sf:input path="application.positionName" class="form-control input-sm" aria-describedby="sizing-addon3" size='50' maxlength='255'/>
						  </div>
						</div>
				  	</c:if>
					<div class="row" style="padding-top: 15px; padding-bottom: 0px;">
					  <div class="col-lg-4">
					  	<span style='field-label'><spring:message code="label.add_application.form.position_id"/></span><br>
					  	<c:if test="${applicationEditable}">
						  	<sf:errors path="application.positionId" cssClass="error" /><br>
						  	<sf:input path="application.positionId" class="form-control input-sm" aria-describedby="sizing-addon3" size='20' maxlength='20'/>
					  	</c:if>
					  	<c:if test="${!applicationEditable}">
					  		<p>${applicationData.application.positionId}</p>
						</c:if>					  	
					  </div>
					  <div class="col-lg-4">
					  	<span style='field-label'><spring:message code="label.add_application.form.position_status"/></span><br>
					  	<c:if test="${applicationEditable}">
						  	<sf:errors path="application.applicationStatus" cssClass="error" /><br>
						  	<sf:select class="form-control input-sm" path="application.applicationStatus">
						  		<sf:option value="NONE" label="(select)"/>
								<c:forEach items="${applicationStatusTypes}" var="statusType">
							  		<sf:option value="${statusType.applicationStatusTypeName}" label="${statusType.applicationStatusTypeName}"/>
								</c:forEach>						  		
						  	</sf:select>
						</c:if>
					  	<c:if test="${!applicationEditable}">
					  		<p>${applicationData.application.applicationStatus}</p>
						</c:if>					  	
					  </div>
					  <div class="col-lg-4">
					  	<span style='field-label'><spring:message code="label.add_application.form.position_type"/></span><br>
					  	<c:if test="${applicationEditable}">
						  	<sf:errors path="application.positionType" cssClass="error" /><br>
						  	<sf:select class="form-control input-sm" path="application.positionType">
						  		<sf:option value="NONE" label="(select)"/>
								<c:forEach items="${positionTypes}" var="type">
							  		<sf:option value="${type.positionTypeDescription}" label="${type.positionTypeDescription}"/>
								</c:forEach>						  		
						  	</sf:select>
					  	</c:if>
					  	<c:if test="${!applicationEditable}">
					  		<p>${applicationData.application.positionType}</p>
						</c:if>					  	
					  </div>
					</div>
					<div class="row" style="padding-top: 15px; padding-bottom: 0px;">
					  <div class="col-lg-4">
					  	<span style='field-label'><spring:message code="label.add_application.form.end_client"/></span><br>
					  	<c:if test="${applicationEditable}">
						  	<sf:errors path="application.endClient" cssClass="error" /><br>
						  	<sf:input path="application.endClient" class="form-control input-sm" aria-describedby="sizing-addon3" size='25' maxlength='255'/>
						</c:if>
					  	<c:if test="${!applicationEditable}">
					  		<p>${applicationData.application.endClient}</p>
						</c:if>					  	
					  </div>
					  <div class="col-lg-4">
					  	<span style='field-label'><spring:message code="label.add_application.form.job_source_name"/></span><br>
					  	<c:if test="${applicationEditable}">
						  	<sf:errors path="application.jobSourceName" cssClass="error" /><br>
						  	<sf:input path="application.jobSourceName" class="form-control  input-sm" aria-describedby="sizing-addon3" size='20' maxlength='50'/>
						</c:if>
					  	<c:if test="${!applicationEditable}">
					  		<p>${applicationData.application.jobSourceName}</p>
						</c:if>					  	
					  </div>
					  <div class="col-lg-4">
					  	<span style='field-label'><spring:message code="label.add_application.form.job_source_type"/></span><br>
					  	<c:if test="${applicationEditable}">
						  	<sf:errors path="application.jobSourceType" cssClass="error" /><br>
						  	<sf:select class="form-control input-sm" path="application.jobSourceType">
						  		<sf:option value="NONE" label="(select)"/>
								<c:forEach items="${jobSourceTypes}" var="type">
							  		<sf:option value="${type.jobSourceTypeDescription}" label="${type.jobSourceTypeDescription}"/>
								</c:forEach>						  		
						  	</sf:select>
						</c:if>
					  	<c:if test="${!applicationEditable}">
					  		<p>${applicationData.application.jobSourceType}</p>
						</c:if>					  	
					  </div>
					</div>
					<div class="row" style="padding-top: 10px; padding-bottom: 0px; padding-left: 0px; paddig-right: 0px;">
					  <div class="col-lg-12">
					  	<span style='field-label'><spring:message code="label.add_application.form.job_description"/></span><br>
					  	<c:if test="${applicationEditable}">
							<sf:textarea class="form-control input-sm" path="application.jobDescription" rows='15' maxlength='5000'/>
						</c:if>
						<c:if test="${!applicationEditable}">
						  	<sf:textarea class="form-control input-sm" path="application.jobDescription" rows='15' maxlength='5000' readonly='true'/>
						</c:if>
					  </div>
					</div>
					<c:if test="${applicationEditable}">
						<div class="row" style="padding-top: 15px;">
							 <div class="col-lg-4">
							 	&nbsp;
							</div>
							 <div class="col-lg-4">
							 	&nbsp;
							</div>
							 <div class="col-lg-4" align='right'>
								<input class="btn btn-default" type="button" value="Cancel" onclick="window.location.href='${pageContext.servletContext.contextPath}/showApplication.htm?id=${applicationData.application.id}'"/>
								&nbsp;&nbsp;
								<input class="btn btn-primary" type="submit" value="Submit"/>
							</div>
						</div>
					</c:if>	
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
							<div class="col-sm-9">
								<span class="heading3"><spring:message code="label.application.card_header.contact_person"/></span>
							</div>
							<c:choose>
								<c:when test="${!showContactForm}">
									<div class="col-xs-1">
										<a href="${pageContext.servletContext.contextPath}/editApplicationContact.htm?id=${applicationData.application.id}" class="card"><spring:message code="label.common.link.edit"/></a>
									</div>
								</c:when>
								<c:otherwise>
									<div class="col-xs-1">
										&nbsp;
									</div>
							</c:otherwise>
							</c:choose>
							<div class="col-xs-1">
								<span class="card-icon-area"><i class="fa fa-user-o fa-lg" color:#888888;></i></span>
							</div>
						</div>
					</div>
					<div id="card-content-area">
						<div class="row">
							<sf:form method="POST" commandName='applicationData' action="${pageContext.servletContext.contextPath}/saveApplicationContact.htm">
							<sf:hidden path="applicationContact.id" />
							<sf:hidden path="application.id" />
							<sf:hidden path="application.positionName" />
							<sf:hidden path="application.jobSourceType" />
						
							<div class="col-sm-12" style="padding-top:2px">
								<c:choose>
									<c:when test="${!showContactForm}">
										<div class="card-sub-header">
											${applicationData.applicationContact.contactName}
										</div>
										<div class="card-sub-content">
											${applicationData.applicationContact.contactDescription}
										</div>
										<!--  
						  				<sf:textarea class="form-control input-sm" path="applicationContact.contactDescription" rows='7' maxlength='255' readonly='true'/>
						  				-->
									</c:when>	
									<c:otherwise>
										<div class="card-form-box">
											<div class="row">
												<div class="col-sm-8">
				  									<spring:message code="label.form.add_application_contact.field.contact_name"/>
				  									<sf:input path="applicationContact.contactName" class="form-control input-sm" aria-describedby="sizing-addon3" size='15' maxlength='50'/>
												</div>
											</div>											
											<div class="row">
												<div class="col-sm-12">
					  								<spring:message code="label.form.add_application_contact.field.contact_description"/><br>
													<sf:textarea class="form-control input-sm" path="applicationContact.contactDescription" rows='3' maxlength='255'/>
												</div>
											</div>
											<div class="row">
												<div class="col-sm-12">
												 	&nbsp;
												</div>
											</div>											
											<div class="row">
												<div class="col-sm-4">
												 	&nbsp;
												</div>
						 						<div class="col-sm-8" align='right'>
													<input class="btn btn-default btn-xs" type="button" value="Cancel" onclick="window.location.href='${pageContext.servletContext.contextPath}/showApplication.htm?id=${applicationData.application.id}'"/>
													&nbsp;&nbsp;
													<input class="btn btn-primary btn-xs" type="submit" value="Save"/>
												</div>
											</div>
										</div>
									</c:otherwise>							
								</c:choose>
							</div>
						</div>
						</sf:form>					
					</div>
				</div>
				<div id="card-sized-box">
					<div id="card-title-area">
						<div class="row">
							<div class="col-sm-9">
								<span class="heading3"><spring:message code="label.application.card_header.attachments"/></span>
							</div>
							<div class="col-xs-1">
								<c:choose>
									<c:when test="${!showNewAttachmentForm}">
										<a href="${pageContext.servletContext.contextPath}/addNewAttachment.htm?id=${applicationData.application.id}" class="card"><spring:message code="label.common.link.add"/></a>
									</c:when>
								</c:choose>
							</div>
							<div class="col-xs-1">
								<span class="card-icon-area"><i class="fa fa-file-text-o fa-lg" style="color:#888888;"></i></span>
							</div>
						</div>
					</div>
					<div id="card-content-area">
						<c:choose>
							<c:when test="${showNewAttachmentForm}">
								<div class="row">
									<div class="col-sm-12" style="padding-top:10px">
										<sf:form id="attachmentForm" method="POST" commandName='applicationData' encType="multipart/form-data" action="${pageContext.servletContext.contextPath}/saveNewAttachment.htm">
										<sf:hidden path="application.id" value="${applicationData.application.id}"/>
										<sf:hidden id="attachmentFilename" path="newAttachment.attachmentFilename" />
											<div class="row">
												<div class="col-sm-10">
													<br>
													<spring:message code="label.card.application_attachments.attachment_type"/>
													<!-- space for attachment types -->
													<sf:select class="form-control input-sm+" path="newAttachment.attachmentType">
														<sf:option value="NONE" label="(select)"/>
														<c:forEach items="${attachmentTypes}" var="type">
															<sf:option value="${type.attachmentTypeName}" label="${type.attachmentTypeName}"/>
														</c:forEach>						  		
													</sf:select>
													<br>
												</div>
											</div>
											<div class="row">
												<div class="col-sm-10">
													<input class="btn btn-default btn-xs" type="file" name="attachmentContent" onchange="javascript:fileUploadModule.init(this,'attachmentFilename');"/>
													<br>
												</div>
											</div>
											<div class="row">
												<div class="col-sm-4">
												 	&nbsp;
												</div>
						 						<div class="col-sm-8" align='right'>
													<input class="btn btn-default btn-xs" type="button" value="Cancel" onclick="window.location.href='${pageContext.servletContext.contextPath}/showApplication.htm?id=${applicationData.application.id}'"/>
													&nbsp;&nbsp;
													<input class="btn btn-primary btn-xs" type="submit" value="Upload" name="submit"/>
												</div>
											</div>
										</sf:form>						
									</div>
								</div>
							</c:when>
							<c:otherwise>
									<c:choose>
										<c:when test="${applicationData.attachments.size()<1}">
											<spring:message code="label.card.application_attachments.no_attachment"/>
										</c:when>
										<c:otherwise>
											<table cellpadding='2' width='95%' style="border: 1px solid #DFDFDF; border-collapse:collapse; background-color:#FAFAFA">
												<c:forEach items="${applicationData.attachments}" var="item" varStatus="loop">
													<tr class="listing" height='20' onmouseover="javascript:removeAttachmentLinkModule.init(${loop.index},'1')" onmouseleave="javascript:removeAttachmentLinkModule.init(${loop.index},'0')">
														<td height='20'><font size='1'>&nbsp;&nbsp;</font></td>
														<td height='20' class='minor-listing' width='60%'>
															<font size='1'>
																<a href="${pageContext.servletContext.contextPath}/downloadAttachment.htm?id=${item.id}&applicationId=${applicationData.application.id}">
																	${fn:substring(item.attachmentFilename,0,30)}
																</a>	
															</font>
														</td>
														<td height='20' class='minor-listing'><font size='1'>&nbsp;&nbsp;</font></td>
														<td height='20' class='minor-listing' width='23%'><font size='1'>${item.attachmentType}</font></td>
														<td height='20' class='minor-listing' width='10%' align='right'>
															<div id='removeColumnRow${loop.index}' style='visibility:hidden'>
															<font size='1'>
																<a href="${pageContext.servletContext.contextPath}/deleteAttachment.htm?id=${item.id}&applicationId=${applicationData.application.id}" data-title='Remove attachment'>
																	<span class="card-icon-area"><i class="fa fa-trash-o fa-lg" style="color:#888888;"></i></span>
																	<!--<spring:message code="label.card.application_attachments.remove_link"/>-->
																</a>
															</font>
															</div>
														</td>
														<td height='20'><font size='1'>&nbsp;&nbsp;</font></td>
													</tr>
												</c:forEach>
											</table>
										</c:otherwise>
									</c:choose>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div id="card-sized-box">
					<div id="card-title-area">
						<div class="row">
							<div class="col-sm-9">
								<span class="heading3"><spring:message code="label.application.card_header.comments"/></span>
							</div>
							<div class="col-xs-1">
								<${pageContext.servletContext.contextPath}/addNewApplication.htm" class="card"><spring:message code="label.common.link.add"/></a>
							</div>
							<div class="col-xs-1">
								<span class="card-icon-area"><i class="fa fa-comments-o fa-lg" style="color:#888888;"></i></span>
							</div>
						</div>
					</div>
					<div id="card-content-area">
					</div>
				</div>
			</div>
		</div>			
<%@ include file="/resources/jsp/footer.jsp" %>