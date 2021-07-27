<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="security.Utils"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Email - ${mail.subject}</title>
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/all.css">
<style type="text/css">
.mail-title {
  margin: 20px 0px;
}

.sender {
  margin-bottom: 10px;
  font-size: 20px;
  font-weight: 200;
}

.sender-email {
  font-size: 18px;
}

.mail-content-inside {
  margin-top: 10px;
  font-size: 16px;
  line-height: 30px;
}

hr {
  width: 100%;
  margin: 10px 0px;
  height: 5px;
  background-color: #ccc;
  border: none;
}

.mail-action {
  position: fixed;
  bottom: 0;
  left: 50%; 
}

.mail-title > p {
  margin: 0;
  font-weight: bold;
  font-size: 22px;
}
</style>
</head>
<body>

  <header id="main-nav">
    <div class="left-icons">
      <i class="fas fa-bars"></i>
    </div>

    <div class="right-icons">
      <a href="Logout"><i class="fas fa-sign-out-alt"></i></a> <img
        src="./img/profile.jpg" alt="profile">
    </div>
  </header>

  <div id="main-container">

    <div class="flex-container">
      <!-- Left Navigation -->
      <div class="left-nav">
        <div class="compose">
          <div class="btn">
            <a href="SendEmail"><img src="./img/compose.png"
              alt="create">
              <p>Compose</p></a>
          </div>
        </div>

        <div class="nav-list">
          <a href="CheckEmail"><i class="fas fa-inbox"></i><span
            class="header">Inbox</span></a>
        </div>


      </div>

      <!-- Main Content / mails list -->
      <div class="main-content">


        <div class="mail-box">
          <div class="mail-title">
            <p>Subject: ${mail.subject}</p>
          </div>
          <hr>

          <div class="sender">
            <span class="sender-email">From: 
              <c:choose>
                <c:when test="${mail.from == user.getEmail()}">me (${mail.from})</c:when>
                <c:otherwise>${mail.from}</c:otherwise>
              </c:choose>
            </span></i>
          </div>
          
          <hr>
          
          <div class="mail-content">
            <span><b>Content:</b></span>
            <c:choose>
              <c:when
                test="${mail.noticeSecurity.equals(Utils.SUCCESS_VERIFY)}">
                <span class="notice-security"
                  style="color: var(--success-color);">
                  <i class="fas fa-certificate"></i> <span>${mail.noticeSecurity}</span>
                </span>
              </c:when>

              <c:when
                test="${mail.noticeSecurity.equals(Utils.FAILED_VERIFY)}">
                <span class="notice-security"
                  style="color: var(--error-color);">
                  <i class="fas fa-exclamation-circle"></i> <span>${mail.noticeSecurity}</span>
                </span>
              </c:when>

              <c:otherwise>
                <span class="notice-security"
                  style="color: var(--warning-color);">
                  <span>${mail.noticeSecurity}</span>
                </span>
              </c:otherwise>
            </c:choose>

            <div class="mail-content-inside">
              <p style="word-break: break-all;">${mail.content}</p>
            </div>
            <div class="mail-action">
              <a href=""><i class="fas fa-reply"></i></a> <a href=""><i
                class="fas fa-arrow-right"></i></a>
            </div>
          </div>
        </div>

      </div>


    </div>


  </div>

</body>
</html>