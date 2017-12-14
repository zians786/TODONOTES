var todo = angular.module('TODO', [ 'ui.router', 'ngSanitize', 'ngAnimate',
		'ngMaterial','tb-color-picker','ngFileUpload','ngMaterialDatePicker','ngTagsInput','toastr']);

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
			
			$stateProvider.state('archive',{
				url:'/archive',
				templateUrl :'template/archive.html',
				controller:'noteController'
			});
			

			$stateProvider.state('trash',{
				url:'/trash',
				templateUrl :'template/trash.html',
				controller:'noteController'
			});
			
			$stateProvider.state('dummy',{
				url:'/dummy',
				templateUrl :'template/dummy.html',
				controller:'dummyController'
			});
			
			$urlRouterProvider.otherwise('login');
			
		} ]);
