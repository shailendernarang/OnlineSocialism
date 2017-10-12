/**
 * 
 */
app.controller('BlogDetailController',function($scope,BlogService,$location,$routeParams){
	$scope.isRejected=false
	var id=$routeParams.id;
	$scope.isLiked=false;
	BlogService.getBlogByID(id).then(function(response){
		$scope.blog = response.data
	},function(response){
		$location.path('/login')
		
	})
	
	$scope.approve=function()
	{
		console.log($scope.blog.blogStatus);
		
		BlogService.approve($scope.blog).then(function(response){
			
			$location.path('/getAllBlogs')
			alert("Approved Success")
	},function(response){
		
		console.log(response.data);
		console.log(response.status);
		alert("Error Approving")
		$location.path('/login');
	})
	}
	$scope.showRejectionTxt=function(val)
	{
		$scope.isRejected=val;
	}
	
	$scope.updateLike = function()
	{
		$scope.isLiked=!$scope.isLiked
		if($scope.isLiked)
			{
				$scope.blog.likes =$scope.blog.likes+1;
			}
		else
			{
			$scope.blog.likes = $scope.blog.likes-1;
			}
		BlogService.approve($scope.blog).then(function(response){
		
		},function(response){
			
		})
	}
	
	$scope.addComment = function()
	{
		$scope.blogComment.blog = $scope.blog;
		console.log($scope.blogComment.blog);
		BlogService.addComment($scope.blogComment).then(function(response){
			
		},function(response){
			$location.path('/getBlogByID/'+$scope.blog.blogID)
		})
	}
	
	function getBlogComments()
	{
		BlogService.getBlogComments(id).then(function(response){
			$scope.blogComments = response.data
			console.log($scope.blogComments);
		},function(response){
			$location.path('/getBlogByID/'+$scope.blog.blogID)
		})
	}
	
getBlogComments()
	
})

	