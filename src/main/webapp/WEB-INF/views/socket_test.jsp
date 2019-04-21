<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <title>Hello WebSocket</title>
  <link href="<c:url value="/resources/vendor/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">
  <script src="<c:url value="/resources/vendor/websocks/sockjs.min.js" />"></script>
  <script src="<c:url value="/resources/vendor/websocks/stomp.min.js" />"></script>
  <script src="<c:url value="/resources/vendor/jquery/jquery.min.js" />"></script>
  <script src="<c:url value="/resources/application/app.js" />"></script>
</head>
<body>
<noscript><h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websocket relies on Javascript being
  enabled. Please enable
  Javascript and reload this page!</h2></noscript>
<div id="main-content" class="container">
  <div class="row">
    <div class="col-md-6">
      <div id="test"></div>
      <form class="form-inline">
        <div class="form-group">
          <label for="connect">WebSocket connection:</label>
          <button id="connect" class="btn btn-default" type="submit">Connect</button>
          <button id="disconnect" class="btn btn-default" type="submit" disabled="disabled">Disconnect
          </button>
        </div>
      </form>
    </div>
    <div class="col-md-6">
      <form class="form-inline">
        <div class="form-group">
          <label for="name">What is your name?</label>
          <input type="text" id="name" class="form-control" placeholder="Your name here...">
        </div>
        <button id="send" class="btn btn-default" type="submit">Send</button>
      </form>
    </div>
  </div>
  <div class="row">
    <div class="col-md-12">
      <table id="conversation" class="table table-striped">
        <thead>
        <tr>
          <th>Greetings</th>
        </tr>
        </thead>
        <tbody id="greetings">
        </tbody>
      </table>
    </div>
  </div>
</div>
</body>
</html>
