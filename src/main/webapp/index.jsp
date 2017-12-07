<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1"><!--  -->
<script
	src="bower_components/angular/angular.js"></script>
	
	<script
	src="script/app.js"></script>

<script type="text/javascript"
	src="bower_components/angular-ui-router/release/angular-ui-router.js"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

 
<script src="bower_components/angular-sanitize/angular-sanitize.js"></script>



	  <link rel="stylesheet" href="bower_components/angular-material/angular-material.min.css">
      <script src = "bower_components/angular-animate/angular-animate.min.js"></script>
      <script src = "bower_components/angular-aria/angular-aria.min.js"></script>
      <script src = "bower_components/angular-messages/angular-messages.min.js"></script>
      <script src="bower_components/angular-material/angular-material.min.js"></script> 

 <!-- CSS -->
<link rel="stylesheet" type="text/css" href="css/Mycss.css"> 
<!-- <link rel="stylesheet" type="text/css" href="css/login.css">
 -->
<!-- JAVASCRIPT 
<script type="text/javascript" src="script/formInput.js"></script>

<script type="text/javascript" src="script/app.js"></script>-->
<!-- <script type="text/javascript" src="script/login.js"></script>
 -->


<!-- <script type="text/javascript" src="script/signUp.js"></script> -->


 <!-- CONTROLLERS -->
<script src="controller/loginController.js"></script>
<script src="controller/noteController.js"></script>

 <script src="controller/registerController.js"></script>
  <script src="controller/resetController.js"></script>
 

 <!-- SERVICES -->
<script src="service/loginService.js"></script>
 <script src="service/registerService.js"></script>
 <script src="service/resetService.js"></script>
 <script src="service/noteService.js"></script>
<!-- DIRECTIVE -->
<script src="directive/allDirective.js"></script>

</head>



<body ng-app="TODO">
	<div ui-view></div>
</body>
</html>