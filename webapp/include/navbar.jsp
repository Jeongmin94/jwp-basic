<%--
  Created by IntelliJ IDEA.
  User: 김정민
  Date: 2022-08-27
  Time: 오전 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8" %>
<div class="navbar navbar-default" id="subnav">
    <div class="col-md-12">
        <div class="navbar-header">
            <a href="#" style="margin-left:15px;" class="navbar-btn btn btn-default btn-plus dropdown-toggle"
               data-toggle="dropdown"><i class="glyphicon glyphicon-home" style="color:#dd1111;"></i> Home <small><i
                    class="glyphicon glyphicon-chevron-down"></i></small></a>
            <ul class="nav dropdown-menu">
                <li><a href="../user/profile.jsp"><i class="glyphicon glyphicon-user" style="color:#1111dd;"></i>
                    Profile</a></li>
                <li class="nav-divider"></li>
                <li><a href="#"><i class="glyphicon glyphicon-cog" style="color:#dd1111;"></i> Settings</a></li>
            </ul>
        </div>
        <div class="collapse navbar-collapse" id="navbar-collapse2">
            <ul class="nav navbar-nav navbar-right">
                <li class="active"><a href="index.jsp">Posts</a></li>
                <c:choose>
                    <c:when test="${not empty sessionScope.user}">
                        <li><a href="#" role="button">로그아웃</a></li>
                        <li><a href="../user/update" role="button">개인정보수정</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="../user/login.jsp" role="button">로그인</a></li>
                        <li><a href="../user/form.jsp" role="button">회원가입</a></li>
                    </c:otherwise>
                </c:choose>
                <!--
                <li><a href="#loginModal" role="button" data-toggle="modal">로그인</a></li>
                <li><a href="#registerModal" role="button" data-toggle="modal">회원가입</a></li>
                -->
            </ul>
        </div>
    </div>
</div>
