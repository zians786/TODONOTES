
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   <style type="text/css">
        .tabData
        {
            width: 100%;
            background-color: Yellow;
            overflow: auto;
        }
    </style>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<script src="bower_components/angular/angular.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script type="text/javascript"
	src="bower_components/angular-ui-router/release/angular-ui-router.js"></script>

<script src="bower_components/angular-sanitize/angular-sanitize.js"></script>

		
		
		
		
	  <link rel="stylesheet" href="bower_components/angular-material/angular-material.min.css">
      <script src = "bower_components/angular-animate/angular-animate.min.js"></script>
      <script src = "bower_components/angular-aria/angular-aria.min.js"></script>
      <script src = "bower_components/angular-messages/angular-messages.min.js"></script>
      <script src="bower_components/angular-material/angular-material.min.js"></script> 
     <script src="bower_components/angular-sanitize/angular-sanitize.js"></script>
      <script src="bower_components/angular-sanitize/angular-sanitize.min.js"></script>
     
      <link rel="stylesheet" href="bower_components/ui-navbar/css/ui-navbar.css">
      <script src="bower_components/ui-navbar/src/navbar.js"></script>
     
    <!--  <script src="bower_components/tinycolor/dist/tinycolor-min.js"></script> -->
     <script src="bower_components/colorpicker/dist/colorPicker.js"></script> 
     
	 <link rel="stylesheet" href="bower_components/colorpicker/dist/colorPickerStyle.css">
	 
	 <!--==============DATE PICKER  -->
	 <script src="bower_components/angular-material-datetimepicker/dist/angular-material-datetimepicker.min.js"></script>
	<script src="bower_components/angular-material-datetimepicker/dist/angular-material-datetimepicker.min.js.map"></script>
	 <link rel="stylesheet" href="bower_components/angular-material-datetimepicker/dist/material-datetimepicker.min.css">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.1/moment.min.js"></script>
		
		
		 <!--==============Toster==================================  -->
	
	<link rel="stylesheet" href="bower_components/angular-toastr/dist/angular-toastr.css">
	<script src="bower_components/angular-toastr/dist/angular-toastr.tpls.js"></script>
	
	<!--==============FileUpload==================================  -->
	
	<script src="bower_components/ng-file-upload/ng-file-upload-shim.min.js"></script>
	<script src="bower_components/ng-file-upload/ng-file-upload.min.js"></script>
		
		
 <!-- CSS -->
<!-- <link rel="stylesheet" type="text/css" href="css/signup.css"> -->
<link rel="stylesheet" type="text/css" href="css/login.css">
<!-- <link rel="stylesheet" type="text/css" href="css/signup.css"> -->
<link rel="stylesheet" type="text/css" href="css/card.css">
<link rel="stylesheet" type="text/css" href="css/navbar.css">
<link rel="stylesheet" type="text/css" href="css/urlLink.css">

<!-- JAVASCRIPT -->
<script type="text/javascript" src="script/app.js"></script>


<script type="text/javascript" src="script/formInput.js"></script>


<!-- <script type="text/javascript" src="script/signUp.js"></script> -->


 <!-- CONTROLLERS -->
<script src="controller/loginController.js"></script>
<script src="controller/registerController.js"></script>
<script src="controller/homeController.js"></script>
<script src="controller/dummyController.js"></script>

 <!-- SERVICES -->
<script type="text/javascript" src="service/loginService.js"></script>
<script type="text/javascript" src="service/userRegisterService.js"></script>
<script type="text/javascript" src="service/noteService.js"></script>
<script type="text/javascript" src="service/tokenservice.js"></script>

<!-- DIRECTIVE -->
<script type="text/javascript" src="directive/CustomDirective.js"></script>

<script src="bower_components/angular-base64/angular-base64.js"></script>
<script src="bower_components/angular-base64-upload/dist/angular-base64-upload.js"></script>
<script src="bower_components/angular-base64-upload/dist/angular-base64-upload.min.js"></script>
</head>


<body ng-app="ToDo">
	<div ui-view ></div>
</body>
</html>