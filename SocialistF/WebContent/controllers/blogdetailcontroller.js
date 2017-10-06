/**
 * 
 */
app.controller('BlogDetailController',function($scope,BlogService,$location,$routeParams){
	var id=$routeParams.id;
	BlogService.getBlogByID(id).then(function(response){
		$scope.blog = response.data
	},function(response){
		$location.path('/login')
		
	})
})
