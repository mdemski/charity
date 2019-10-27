<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="header.jsp"/>
<c:url var="imagesUrl" value="resources/images"/>
<section class="form">
    <H3>Formularz datków</H3>
    <form:form>
        <form:label path="categories">Kategoria datku</form:label>
        <form:checkboxes path="categories"
                         items="${categories}"/>
        <form:label path="institution">Dla instytucji:</form:label>
        <form:select path="institution" items="${institutions}"/>
        <form:label path="zipCode">Kod pocztowy</form:label>
        <form:input path="zipCode"/>
        <form:label path="street">Ulica</form:label>
        <form:input path="street"/>
        <form:label path="city">Miasto</form:label>
        <form:input path="city"/>
        <form:label path="quantity">Ilość</form:label>
        <form:input path="quantity"/>
        <form:label path="pickUpComment">Uwagi</form:label>
        <form:textarea path="pickUpComment"/>
        <form:label path="pickUpDate">Data</form:label>
        <form:input type="date" path="pickUpDate"/>
        <form:label path="pickUpTime">Godzina</form:label>
        <form:input type="time" path="pickUpTime"/>
    </form:form>
</section>

<jsp:include page="footer.jsp"/>
