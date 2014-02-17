<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="core" prefix="c"%>
<%@ taglib uri="fn" prefix="fn"%>
<c:forEach var="s" items="${menu.childrenPermission}">
	<c:if test="${not empty s.childrenPermission}">
		<c:set var="falg" value="1"></c:set>
	</c:if>
</c:forEach>