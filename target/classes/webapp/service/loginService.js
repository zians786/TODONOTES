
var ToDo = angular.module('TODO');

ToDo.factory('loginService',function($http,$location){
	var login ={};
	
	login.loginUser = function(user){
	
		return $http({
			method :"POST",
			url :'login',
			data : user
		});
	}
	
	
	login.fblogin=function(){
		return $http({
			method:"GET",
			url:'social/fbLogin'
		});
	}
	
	
	login.glogin=function(){
		
	}

	
	
	login.socialLogin=function(){
		return $http({
			method:"POST",
			url:'getToken'
		});
	}
	
	
	login.UserInfo=function(){
		return $http({
			method:"POST",
			url:'getUser',
			headers: {'accToken': localStorage.getItem('token')
			}
		});
	}
	
	return login;
});
