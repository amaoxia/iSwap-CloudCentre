<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="core" prefix="c"%>
<%@ taglib uri="fn" prefix="fn"%>
<<c:set value="${fn:length(menu.childrenPermission)}" var="len"></c:set>
<c:if test="${not empty  menu.childrenPermission}">
	<c:forEach var="menu" items="${menu.childrenPermission}">
		<c:set var="menu" value="${menu}" scope="request" />
		<c:choose>
			<c:when test="${len gt 0}">
			<ul>
				<li url="${menu.url}" isexpand="false"><span>${menu.menuName}</span>
					<jsp:include page="recursive.jsp" />
				</li>
				</ul>
			</c:when>
			<c:when test="${ len lt 0}">
					<li url="${menu.url}" isexpand="false"><span>${menu.menuName}</span>
						<jsp:include page="recursive.jsp" /></li>
			</c:when>
		</c:choose>
	</c:forEach>
</c:if>

