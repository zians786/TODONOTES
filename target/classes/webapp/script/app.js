var todo = angular.module('TODO', [ 'ui.router', 'ngSanitize', 'ngAnimate',
		'ngMaterial','tb-color-picker','ngFileUpload']);

todo.config([ '$stateProvider', '$urlRouterProvider',
		function($stateProvider, $urlRouterProvider) {
			
			$stateProvider.state('login', {
				url : '/login',
				templateUrl : 'template/login.html',
				controller : 'loginController'
			});
			
			
			
			$stateProvider.state('register',{
				
				url:'/register',
				templateUrl : 'template/register.html',
				controller : 'registerController'
			});
			
			
			$stateProvider.state('forgot',{
				url:'/forgot',
				templateUrl :'template/forgot.html',
				controller:'forgotController'
				});
		
			
			$stateProvider.state('reset',{
				url:'/reset',
				templateUrl :'template/reset.html',
				controller:'resetController'
				});
			
			$stateProvider.state('home',{
				url:'/home',
				templateUrl :'template/home.html',
				controller:'noteController'
				});
			
			$stateProvider.state('social/dummy',{
				url:'/dummy',
				templateUrl :'template/dummy.html',
				
			});
			
			$urlRouterProvider.otherwise('login');
			
		} ]);
