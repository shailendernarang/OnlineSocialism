/**
 * 
 */
app.controller('BlogDetailController',function($scope,BlogService,$location,$routeParams,$window){
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
	

	
	$scope.addComment = function()
	{
		$scope.blogComment.blog = $scope.blog;
		console.log($scope.blogComment.blog);
		BlogService.addComment($scope.blogComment).then(function(response){
			$scope.blogComment.blogCommentText = "";
			getBlogComments();
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
	$scope.upvoteBlog = function(blogId) {
		console.log('entering upvote controller')
		BlogService.upvoteBlog(blogId).then(function() {
			console.log('Upvoted')
			$window.location.reload().notify(false);
		}, function() {
			console.log('unable to upvote')
		})
	};

	$scope.downvoteBlog = function(blogId) {
		console.log('entering upvote controller')
		BlogService.downvoteBlog(blogId).then(function() {
			console.log('downvoted')
						$window.location.reload().notify(false);

			
		}, function() {
			console.log('unable to downvote')
		})
	};
getBlogComments()
	
})

	