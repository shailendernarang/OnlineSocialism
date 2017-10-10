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
	function blogsApproved()
	{
		BlogService.blogsApproved().then(function(response){
			
			$scope.listOfBlogsApproved = response.data
		},function(response){
			$location.path('/login')
			
		})
	}
	function blogsWaitingForApproval()
	{
		BlogService.blogsWaitingForApproval().then(function(response){
			$scope.listOfBlogsWaitingForApproval = response.data
		},function(response){
			$location.path('/login')
			
		})
		}
	
	
	
	blogsApproved()
	blogsWaitingForApproval()
	
});