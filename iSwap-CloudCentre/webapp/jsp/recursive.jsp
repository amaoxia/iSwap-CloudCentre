<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="core" prefix="c"%>
<%@ taglib uri="fn" prefix="fn"%>
<c:if test="${not empty  menu.childrenPermission }">
	<c:forEach var="menu" items="${menu.childrenPermission}" varStatus="i">
		<c:set var="menu" value="${menu}" scope="request" />
		${menu.permission.permission.id}
		<c:if test="${empty menu.permission.permission.id  }">
			<li url="${menu.url}" isexpand="false">
				<span>${menu.menuName}</span>
				<jsp:include page="recursive.jsp" />
			</li>
		</c:if>
		<c:if test="${not empty menu.permission.permission.id  }">
			<ul>
				<li url="${menu.url}" isexpand="false">
					<span>${menu.menuName}</span>
					<jsp:include page="recursive.jsp" />
				</li>
			</ul>
		</c:if>
	</c:forEach>
</c:if>

