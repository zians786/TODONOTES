var ToDo = angular.module('TODO');

ToDo.controller('loginController',function($scope,loginService,$location){
	
	$scope.loginUser= function(){
		console.log("At the beggining of controller");
		console.log($scope.user);
		var a = loginService.loginUser($scope.user,$scope.error);
		console.log(a);
			a.then(function(response){
				
				localStorage.setItem('token',response.data.token);
				/*
				localStorage.setItem('userName',response.data.userName);
				
				localStorage.setItem('email',response.data.email);
				localStorage.setItem('picture',response.data.profilePicture);*/
				console.log("login success");
				$location.path('home');
		},function(response){
				if(response.status==409)
					{
						$scope.error=response.data.responseMessage;
					}
				else
					{
						console.log("fail");
						$scope.error="Enter valid data";
					}
			});
	}
	
	$scope.fbLogin=function(){
		var fbResponse=loginService.fblogin();
		fbResponse.then(function(response){
			console.log("working");
		});
	}
	
	
	$scope.register=function(){
		$location.path('/register');
		
	}
	
	$scope.reset=function(){
		$location.path('/reset');
	}
	
	
});