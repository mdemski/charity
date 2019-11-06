<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="header.jsp"/>
<c:url var="imagesUrl" value="resources/images"/>

<section class="login-page">
    <h2>Załóż konto</h2>
    <form:form modelAttribute="data" method="post">
        <div class="form-group">
            <form:input type="email" path="email" placeholder="Email" />
        </div>
        <div class="form-group">
            <form:input type="password" path="password" placeholder="Hasło" />
        </div>
        <div class="form-group">
            <label>
                <input type="password" name="password2" placeholder="Powtórz hasło" />
            </label>
        </div>
        <div class="form-group">
            <form:input path="firstName" placeholder="Imię" />
        </div>
        <div class="form-group">
            <form:input path="lastName" placeholder="Nazwisko" />
        </div>
        <div class="form-group">
            <form:hidden path="role" value="Normal user" />
        </div>

        <div class="form-group form-group--buttons">
            <a href="<c:url value="/login"/>" class="btn btn--without-border">Zaloguj się</a>
            <button class="btn" type="submit">Załóż konto</button>
        </div>
    </form:form>
</section>

<jsp:include page="footer.jsp"/>
