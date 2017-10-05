/**
 * 
 */
app.controller('BlogController',function($scope,BlogService,$location){
	
	$scope.createBlog = function()
	{
		console.log("Blog DATA "+$scope.blog);
		
		BlogService.createBlog($scope.blog).then(function(response){
			console.log("sahi hai");
			alert("Added Successfully,Waiting For approval");
			$location.path('/createBlog');
			
		},function(response){
			console.log(response.status);
			console.log(response.message);
			$scope.error=response.data;
			$location.path('/login');
		})
			
	}
});