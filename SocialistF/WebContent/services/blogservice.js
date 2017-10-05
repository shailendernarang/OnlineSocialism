/**
 * 
 */
app.factory('BlogService',function($http)
{	
var blogService={};

var BASE_URL="http://localhost:8087/SocialistM"	;	

	blogService.createBlog = function(blog)
	{
	
		return $http.post(BASE_URL+"/createBlog",blog);
	}

blogService.blogWaitingForApproval = function()
{

	$http.get(BASE_URL+"/getAllBlogs/"+0);
}

blogService.blogApproved = function()
{

	$http.get(BASE_URL+"/getAllBlogs/"+1);
}
	return blogService;
})