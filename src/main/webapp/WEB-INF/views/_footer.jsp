<header>
<c:if test="${notice == true}">
    <c:forEach var="notif" items="${notification}">
        <script>
            printNotice("${notif.msg}", "${notif.type}", "${notif.timer}")
        </script>
    </c:forEach>
</c:if>
</header>
</html>
