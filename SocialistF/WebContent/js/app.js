/**
 * Angular JS Module
 */
var app= angular.module("app",['ngRoute','ngAnimate']);
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
		.otherwise({
			templateUrl:'views/home.html'
		})
})
