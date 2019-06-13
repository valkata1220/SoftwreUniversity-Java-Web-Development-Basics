<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <c:import url="templates/head.jsp" />
</head>
<body>
<div class="container-fluid">
    <header>
        <c:import url="templates/navbar.jsp" />
    </header>
    <main>
        <hr class="my-2"/>
        <div class="text-center mt-3">
            <h4 class="h4 text-info">Welcome, <%=request.getSession().getAttribute("username")%></h4>
        </div>

        <hr class="my-3"/>
    </main>
    <c:import url="templates/footer.jsp" />
</div>
</body>
</html>
