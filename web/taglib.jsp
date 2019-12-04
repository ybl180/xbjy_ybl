<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<link href="${path}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="${path}/static/js/jquery-3.3.1.js"></script>
<script src="${path}/static/bootstrap/js/bootstrap.js"></script>
<style>
    li {
        list-style: none;
    }
</style>

<%--<script src="${path}/static/vendor/jquery/jquery.min.js"></script>--%>
<%--<script src="${path}/static/vendor/popper.js/umd/popper.min.js"></script>--%>
<%--<script src="${path}/static/vendor/bootstrap/js/bootstrap.min.js"></script>--%>
<%--<script src="${path}/static/vendor/jquery.cookie/jquery.cookie.js"></script>--%>
<%--<script src="${path}/static/vendor/chart.js/Chart.min.js"></script>--%>
<%--<script src="${path}/static/vendor/jquery-validation/jquery.validate.min.js"></script>--%>
<%--<script src="${path}/static/js/charts-home.js"></script>--%>
<%--<script src="${path}/static/js/front.js"></script>--%>
<%--<script src="${path}/static/js/custom.js"></script>--%>
<%--<!-- Bootstrap CSS-->--%>
<%--<link rel="stylesheet" href="${path}/static/vendor/bootstrap/css/bootstrap.min.css">--%>
<%--<!-- Font Awesome CSS-->--%>
<%--<link rel="stylesheet" href="${path}/static/vendor/font-awesome/css/font-awesome.min.css">--%>
<%--<!-- Custom Font Icons CSS-->--%>
<%--<link rel="stylesheet" href="${path}/static/css/font.css">--%>
<%--<!-- theme stylesheet-->--%>
<%--<link rel="stylesheet" href="${path}/static/css/style.default.css" id="theme-stylesheet">--%>
<%--<!-- Custom stylesheet - for your changes-->--%>
<%--<link rel="stylesheet" href="${path}/static/css/custom.css">--%>