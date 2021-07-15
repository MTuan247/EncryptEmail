<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
     "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Gmail Clone</title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="javascripts/main.js"></script>
  <link rel="stylesheet" href="./css/style.css">
  <link rel="stylesheet" href="./css/all.css">
  
  
</head>
<body>

  <header id="main-nav">
    <div class="left-icons">
      <i class="fas fa-bars"></i>
      <img src="./img/logo.png" alt="logo">
    </div>

    <div class="search-bar">
      <i class="fas fa-search"></i>
      <input type="text" placeholder="Search mail">
      <i class="fas fa-sort-down"></i>
    </div>

    <div class="right-icons">
      <i class="far fa-question-circle"></i>
      <i class="fas fa-cog"></i>
      <i class="fas fa-th"></i>
      <img src="./img/profile.jpg" alt="profile">
    </div>
  </header>

  <div id="main-container">

    <div class="flex-container">
      <!-- Left Navigation -->
      <div class="left-nav">
        <div class="compose">
          <div class="btn">
            <a href="EncryptEmail/SendEmail"><img src="./img/compose.png" alt="create">
            <p>Compose</p></a>
          </div>
        </div>

        <div class="nav-list">
          <a href="#"><i class="fas fa-inbox"></i><span class="header">Inbox</span><span class="count"></span></a>
          <a href="#"><i class="fas fa-star"></i>Starred</a>
          <a href="#"><i class="fas fa-clock"></i>Snoozed</a>
          <a href="#"><i class="fas fa-paper-plane"></i>Sent</a>
          <a href="#"><i class="fas fa-file"></i>Drafts</a>
          <a href="#"><i class="fas fa-box"></i>Call log</a>
          <a href="#"><i class="fas fa-box"></i>Junk</a>
          <a href="#"><i class="fas fa-box"></i>SMS</a>
          <a href="#"><i class="fas fa-box"></i>Unwanted</a>
          <a href="#"><i class="fas fa-sort-down"></i>More</a>
        </div>

        <div class="meet">
          <h2>Meet</h2>
          <a href="#"><i class="fas fa-video"></i> Start a meeting</a>
          <a href="#"><i class="fas fa-keyboard"></i> Join a meeting</a>

        </div>


        <div class="hangouts">
          <h2>Hangouts</h2>
          <div class="user"><img src="./img/profile.jpg" alt="user"> David <i class="fas fa-sort-down"></i>
          
            <a href="#"><i class="fas fa-plus"></i></a>
          </div>

          
        </div>

      </div>

      <!-- Main Content / mails list -->
      <div class="main-content">

        <div class="content-nav">
          <div class="left-icons">
            <div class="select"><input type="checkbox" class="check-all">
              <i class="fas fa-caret-down"></i>
            </div>
            <div class="check-icon">
              <i class="fas fa-archive"></i>
              <i class="fas fa-trash"></i>
              <i class="fas fa-envelope-open"></i>
              <i class="fas fa-clock"></i> 
            </div>
            <i class="fas fa-redo-alt"></i>
            <i class="fas fa-ellipsis-v"></i>
            
          </div>

          <div class="right-icons">
            <span class="order">1-50 0f 2,828</span>
            <i class="fas fa-chevron-left"></i>
            <i class="fas fa-chevron-right"></i>
          </div>
        </div>

        <div class="mail-box">
          
          <!-- mailing lists -->
          <!-- 1st Mail -->
          <c:if test="${mails != null}">
          	<c:forEach items="${mails}" var = "mail">
		          <a href="" class="mail-list link">
		            <div class="mail-list">
		              <div class="mail-icons-left">
		                <input type="checkbox" name="mail" id="mail">
		                <i class="far fa-star"></i>
		                <i class="far fa-bookmark"></i>
		                <h4 class="title">${mail.getSubject()}</h4>
		              
		              </div>
		              <div class="mail-content">
		                <p class="subject">${mail.getContent()}</span></p>
		              </div>
		              <div class="mail-icons-right">
		                <i class="fas fa-archive"></i>
		                <i class="fas fa-trash"></i>
		                <i class="fas fa-envelope-open"></i>
		                <i class="fas fa-clock"></i>                                         
		              </div>
		            </div>
		          </a>
        	</c:forEach>
        </c:if>
          

        <footer class="content-footer">
          <div class="left-info">
            <p>1.53 GB (10%) of 15 GB used</p>
            <a href="#">Manage</a>
          </div>
          <div class="center-info">
            <a href="#">Terms</a>
            <a href="#">Privacy</a>
            <a href="#">Program Policies</a>
          </div>
          <div class="right-info">
            <p>Last account activity: 54 minutes ago</p>
            <a href="#">Details</a>
          </div>
        </footer>
                 
    </div>

  </div>


  </div>
    
</body>
</html>