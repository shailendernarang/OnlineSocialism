/**
 * Angular JS userController
 */
app.controller('UserController',function($scope,UserService,$location){

	$scope.registerUser=function()
	{
		/*
		 * call user service by passing data in JSON FORMAT
		 */
		console.log("USER DATA "+$scope.user);
		
		UserService.registerUser($scope.user).then(function(response){
			/*
			 * Redirect user to login with 'registration success'
			 */
			console.log(response.data);
			console.log(response.status);
			$location.path('/home');
			
		},function(response){
			/*
			 * error redirecting back to register with error message
			 */
			console.log(response.data);
			console.log(response.status);
			$scope.error=response.data;
			$location.path('/register');
		})
		
	}
	$scope.login = function()
	{
		console.log ("User Login Data"+$scope.user);
		UserService.login($scope.user).then(function(response){
			console.log(response.data);
			console.log(response.status);
			$location.path('/home');
		},function(response){
			$scope.error=response.data;
			$location.path('/login');
		})
	}
	
});