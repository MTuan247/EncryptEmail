<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
     "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  <meta charset="utf-8">
  <meta content="width=300, initial-scale=1" name="viewport">
  <title>Login</title>
  <link rel="stylesheet" href="css/login.css">
  <style media="screen and (max-width: 800px), screen and (max-height: 800px)">
    .banner h1 {
      font-size: 38px;
      margin-bottom: 15px;
    }

    .banner h2 {
      margin-bottom: 15px;
    }

    .one-google p.create-account,
    .one-google p.switch-account {
      margin-bottom: 30px;
    }

    .signin-card #Email {
      margin-bottom: 0;
    }

    .signin-card #Passwd {
      margin-top: -1px;
    }

    .signin-card #Email.form-error,
    .signin-card #Passwd.form-error {
      z-index: 2;
    }

    .signin-card #Email:hover,
    .signin-card #Email:focus,
    .signin-card #Passwd:hover,
    .signin-card #Passwd:focus {
      z-index: 3;
    }
  </style>
  <style media="screen and (max-width: 580px)">

  </style>
  <style
    media="screen and (-webkit-min-device-pixel-ratio: 1.5), (min--moz-device-pixel-ratio: 1.5), (-o-min-device-pixel-ratio: 3 / 2), (min-device-pixel-ratio: 1.5)">
    .one-google .logo-strip {
      background-image: url(./img/8XITQ2w.png);
    }
  </style>
  <style>
    .jfk-tooltip {
      background-color: #fff;
      border: 1px solid;
      color: #737373;
      font-size: 12px;
      position: absolute;
      z-index: 800 !important;
      border-color: #bbb #bbb #a8a8a8;
      padding: 16px;
      width: 250px;
    }

    .jfk-tooltip h3 {
      color: #555;
      font-size: 12px;
      margin: 0 0 .5em;
    }

    .jfk-tooltip-content p:last-child {
      margin-bottom: 0;
    }

    .jfk-tooltip-arrow {
      position: absolute;
    }

    .jfk-tooltip-arrow .jfk-tooltip-arrowimplbefore,
    .jfk-tooltip-arrow .jfk-tooltip-arrowimplafter {
      display: block;
      height: 0;
      position: absolute;
      width: 0;
    }

    .jfk-tooltip-arrow .jfk-tooltip-arrowimplbefore {
      border: 9px solid;
    }

    .jfk-tooltip-arrow .jfk-tooltip-arrowimplafter {
      border: 8px solid;
    }

    .jfk-tooltip-arrowdown {
      bottom: 0;
    }

    .jfk-tooltip-arrowup {
      top: -9px;
    }

    .jfk-tooltip-arrowleft {
      left: -9px;
      top: 30px;
    }

    .jfk-tooltip-arrowright {
      right: 0;
      top: 30px;
    }

    .jfk-tooltip-arrowdown .jfk-tooltip-arrowimplbefore,
    .jfk-tooltip-arrowup .jfk-tooltip-arrowimplbefore {
      border-color: #bbb transparent;
      left: -9px;
    }

    .jfk-tooltip-arrowdown .jfk-tooltip-arrowimplbefore {
      border-color: #a8a8a8 transparent;
    }

    .jfk-tooltip-arrowdown .jfk-tooltip-arrowimplafter,
    .jfk-tooltip-arrowup .jfk-tooltip-arrowimplafter {
      border-color: #fff transparent;
      left: -8px;
    }

    .jfk-tooltip-arrowdown .jfk-tooltip-arrowimplbefore {
      border-bottom-width: 0;
    }

    .jfk-tooltip-arrowdown .jfk-tooltip-arrowimplafter {
      border-bottom-width: 0;
    }

    .jfk-tooltip-arrowup .jfk-tooltip-arrowimplbefore {
      border-top-width: 0;
    }

    .jfk-tooltip-arrowup .jfk-tooltip-arrowimplafter {
      border-top-width: 0;
      top: 1px;
    }

    .jfk-tooltip-arrowleft .jfk-tooltip-arrowimplbefore,
    .jfk-tooltip-arrowright .jfk-tooltip-arrowimplbefore {
      border-color: transparent #bbb;
      top: -9px;
    }

    .jfk-tooltip-arrowleft .jfk-tooltip-arrowimplafter,
    .jfk-tooltip-arrowright .jfk-tooltip-arrowimplafter {
      border-color: transparent #fff;
      top: -8px;
    }

    .jfk-tooltip-arrowleft .jfk-tooltip-arrowimplbefore {
      border-left-width: 0;
    }

    .jfk-tooltip-arrowleft .jfk-tooltip-arrowimplafter {
      border-left-width: 0;
      left: 1px;
    }

    .jfk-tooltip-arrowright .jfk-tooltip-arrowimplbefore {
      border-right-width: 0;
    }

    .jfk-tooltip-arrowright .jfk-tooltip-arrowimplafter {
      border-right-width: 0;
    }

    .jfk-tooltip-closebtn {
      background: url("ui/v1/icons/common/x_8px.png") no-repeat;
      border: 1px solid transparent;
      height: 21px;
      opacity: .4;
      outline: 0;
      position: absolute;
      right: 2px;
      top: 2px;
      width: 21px;
    }

    .jfk-tooltip-closebtn:focus,
    .jfk-tooltip-closebtn:hover {
      opacity: .8;
      cursor: pointer;
    }

    .jfk-tooltip-closebtn:focus {
      border-color: #4d90fe;
    }
  </style>
  <style media="screen and (max-width: 580px)">
    .jfk-tooltip {
      display: none;
    }
  </style>
  <style>
    .need-help-reverse {
      float: right;
    }

    .remember .bubble-wrap {
      position: absolute;
      padding-top: 3px;
      -o-transition: opacity .218s ease-in .218s;
      -moz-transition: opacity .218s ease-in .218s;
      -webkit-transition: opacity .218s ease-in .218s;
      transition: opacity .218s ease-in .218s;
      left: -999em;
      opacity: 0;
      width: 314px;
      margin-left: -20px;
    }

    .remember:hover .bubble-wrap,
    .remember input:focus~.bubble-wrap,
    .remember .bubble-wrap:hover,
    .remember .bubble-wrap:focus {
      opacity: 1;
      left: inherit;
    }

    .bubble-pointer {
      border-left: 10px solid transparent;
      border-right: 10px solid transparent;
      border-bottom: 10px solid #fff;
      width: 0;
      height: 0;
      margin-left: 17px;
    }

    .bubble {
      background-color: #fff;
      padding: 15px;
      margin-top: -1px;
      font-size: 11px;
      -moz-border-radius: 2px;
      -webkit-border-radius: 2px;
      border-radius: 2px;
      -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
      -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
      box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
    }

    #stay-signed-in {
      float: left;
    }

    #stay-signed-in-tooltip {
      left: auto;
      margin-left: -20px;
      padding-top: 3px;
      position: absolute;
      top: 0;
      visibility: hidden;
      width: 314px;
      z-index: 1;
    }

    .dasher-tooltip {
      position: absolute;
      left: 50%;
      top: 380px;
      margin-left: 150px;
    }

    .dasher-tooltip .tooltip-pointer {
      margin-top: 15px;
    }

    .dasher-tooltip p {
      margin-top: 0;
    }

    .dasher-tooltip p span {
      display: block;
    }
  </style>
  <style media="screen and (max-width: 800px), screen and (max-height: 800px)">
    .dasher-tooltip {
      top: 340px;
    }
  </style>
</head>

<body>
  <div class="wrapper">
    <div class="main content clearfix">
      <div class="banner">
        <h1 style="margin: 40px 0px 20px 0px">
          Đăng Nhập
        </h1>
      </div>
      <div class="card signin-card clearfix">
        <img class="profile-img" src="./img/9r33ioA.png" alt="">
        <p class="profile-name"></p>
        <form novalidate="" id="gaia_loginform" action="Login" method="post">
          <label class="hidden-label" for="Email">Email</label>
          <input id="Email" name="userName" type="email" placeholder="Email" value="" spellcheck="false" class="">
          <label class="hidden-label" for="Passwd">Password</label>
          <input id="Passwd" name="password" type="password" placeholder="Password" class="passwd-label">
          <input id="signIn" name="signIn" class="rc-button rc-button-submit" type="submit" value="Sign in">

        </form>
      </div>
    </div>
   


</body>

</html>