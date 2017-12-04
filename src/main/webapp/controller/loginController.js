var ToDo = angular.module('TODO');

ToDo.controller('loginController',function($scope,loginService,$location){
	
	$scope.loginUser= function(){
		console.log("At the beggining of controller");
		console.log($scope.user);
		var a = loginService.loginUser($scope.user,$scope.error);
		console.log(a);
			a.then(function(response){
				console.log(response.data.responseMessage);
				localStorage.setItem('token',response.data.responseMessage);
				console.log("login success");
				localStorage.removeItem('token');
//				$location.path('home');
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
	
	
	$scope.register=function(){
		$location.path('/register');
		
	}
	
	$scope.reset=function(){
		$location.path('/reset');
	}
	
	
});