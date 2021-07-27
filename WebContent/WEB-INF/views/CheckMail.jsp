<%@page import="security.Utils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
     "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Inbox</title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="javascripts/main.js"></script>
  <link rel="stylesheet" href="./css/style.css">
  <link rel="stylesheet" href="./css/all.css">
  <style>
    .mail-icons-right {
      display: flex;
      align-items: center;
      justify-content: right !important;
      gap: 10px;
    }
    
    .mail-icons-right * {
      color: inherit;
    }
  </style>
  
</head>
<body>

  <header id="main-nav">
    <div class="left-icons">
      <i class="fas fa-bars"></i>
    </div>

    <div class="right-icons">
      <a href="Logout"><i class="fas fa-sign-out-alt"></i></a>
      <img src="./img/profile.jpg" alt="profile">
    </div>
  </header>

  <div id="main-container">

    <div class="flex-container">
      <!-- Left Navigation -->
      <div class="left-nav">
        <div class="compose">
          <div class="btn">
            <a href="SendEmail"><img src="./img/compose.png" alt="create">
            <p>Compose</p></a>
          </div>
        </div>

        <div class="nav-list">
          <a href="CheckEmail"><i class="fas fa-inbox"></i><span class="header">Inbox</span><span class="count"></span></a>
        </div>

      </div>

      <!-- Main Content / mails list -->
      <div class="main-content">
        <div class="mail-box">
          
          <!-- mailing lists -->
          <!-- 1st Mail -->
          <c:if test="${mails != null}">
          	<c:forEach items="${mails}" var = "mail" varStatus="loop" >
		          <a href="/EncryptEmail/Mail?mail=${loop.index}" class="mail-list link">
		            <div class="mail-list">
		            
		              <div class="mail-icons-left">
		                <h4 class="title">
                          <c:choose>
                            <c:when test="${mail.getFrom() == user.getEmail()}">me</c:when>
                            <c:otherwise>${mail.getFrom()}</c:otherwise>
                          </c:choose>
                        </h4>
		              </div>
		              
		              <div class="mail-content">
		                <p class="subject">${mail.getSubject()}</p>
		              </div>
                  
                      <c:choose>
                      
                        <c:when test="${mail.noticeSecurity.equals(Utils.SUCCESS_VERIFY)}">
                          <div class="mail-icons-right" style="color: var(--success-color);">
                            <i class="fas fa-certificate"></i>
                            <span>${mail.noticeSecurity}</span>
                          </div>
                        </c:when>
                        
                        <c:when test="${mail.noticeSecurity.equals(Utils.FAILED_VERIFY)}">
                           <div class="mail-icons-right" style="color: var(--error-color);">
                              <i class="fas fa-exclamation-circle"></i>
                              <span>${mail.noticeSecurity}</span>
                           </div>
                        </c:when>
                        
                        <c:otherwise>
                           <div class="mail-icons-right" style="color: var(--warning-color);">
                              <span>${mail.noticeSecurity}</span>
                           </div>
                        </c:otherwise>
                      </c:choose>                                        

		              
		            </div>
		          </a>
        	</c:forEach>
        </c:if>
                 
    </div>

  </div>


  </div>
    
</body>
</html>