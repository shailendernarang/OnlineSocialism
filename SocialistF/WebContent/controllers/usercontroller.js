/**
 * Angular JS userController
 */
app.controller('UserController',function($scope,UserService,$location,$rootScope,$cookieStore){

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
			
			$rootScope.currentUser=response.data; // accessing variable username from here 
			$cookieStore.put('userDetails',response.data);
			$location.path('/home')
			console.log(response.data);
		},function(response){
			$scope.error=response.data;
			$location.path('/login');
		})
	}
	
	if($rootScope.currentUser!=undefined){
	UserService.getUser().then(function(response){
		$scope.user=response.data;
	},function(response){
		
	})
	}

	$scope.updateUser = function(){
		UserService.updateUser($scope.user)
				.then(function(response){
					alert("Updated Successfully");
					$location.path('/home');
				},function(response){
					if(response.status==401)
					{
					$location.path('/login')
					}
				else{
					$scope.error = response.data;
				$location.path('/editProfile')
				}
				})
	}
	
	
	 
});