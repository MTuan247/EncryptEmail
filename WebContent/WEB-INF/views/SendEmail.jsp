<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Compose</title>
  <link rel="stylesheet" href="./css/style.css">
  <link rel="stylesheet" href="./css/all.css">
  <style>
    #btnSubmit {
      display: inline-block;
      position: relative;
      left: 50%;
      transform: translateX(-50%);
      min-width: 200px;
    }
    
    h2 {
      margin-top: 20px;
      text-align: center; 
      padding: 10px;
    }
    
    input[type=radio] {
      transform: scale(1.5);
      margin-left: 50px;
      margin-right: 10px;
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
            <a href="${pageContext.request.contextPath}/SendEmail"><img src="./img/compose.png" alt="create">
            <p>Compose</p></a>
          </div>
        </div>

        <div class="nav-list">
          <a href="${pageContext.request.contextPath}/CheckEmail"><i class="fas fa-inbox"></i><span class="header">Inbox</span><span class="count"></span></a>
        </div>

      </div>

      <!-- Main Content / mails list -->
      <div class="main-content-send-email">
        <div class="content-nav">
            <h2>Send Email</h2>
        </div>

        <div class="mail-box">
            <form action="SendEmail" method="post">
                <label for="email">To: </label>
                <input type="email" id="email" name="recipient" required />
                <label for="title">Title: </label>
                <input type="text" id="title" name="subject" required />
                <label for="content">Content:</label>
                <textarea id="content" name="content" placeholder="Write something.." style="height:200px" required></textarea>
                <label for="security">Encrypt & Digital Signature:</label>
                <br>
                <label><input type="radio" name="security" value="y" checked>Yes</label>
                <label><input type="radio" name="security" value="n">No</label>
                <br>
                <input type="submit" value="Send" id="btnSubmit"/>
            </form>
        </div>
                 
    </div>
  

  </div>


  </div>
    
</body>
</html>