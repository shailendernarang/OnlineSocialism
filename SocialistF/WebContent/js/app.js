/**
 * Angular JS Module
 */
var app= angular.module("app",['ngRoute','ngCookies']);
app.config(function($routeProvider){
	$routeProvider
		.when('/register',{
			templateUrl:'views/registrationform.html',
			controller:'UserController'
		})
		.when('/home',{
			templateUrl:'views/home.html'
		})
		.when('/login',{
			templateUrl:'views/login.html',
			controller:'UserController'
		})
		.when('/editProfile',{
			templateUrl:'views/editProfile.html',
			controller:'UserController'
		})
		.otherwise({
			templateUrl:'views/home.html'
		})
})
app.run(function($rootScope,$cookieStore,UserService,$location){
	if($rootScope.currentUser==undefined)
	{	
			$rootScope.currentUser=$cookieStore.get('userDetails')
	}	
	$rootScope.logout=function()
	{
		UserService.logout()
				.then(function(response){
			delete $rootScope.currentUser;
			$cookieStore.remove('userDetails')
			$location.path('/login')
		},function(response){
			
			console.log(response.status);
			
		})
	}
})