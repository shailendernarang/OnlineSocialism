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

blogService.blogsWaitingForApproval = function()
{

	return $http.get(BASE_URL+"/getAllBlogs/"+0);
}

blogService.blogsApproved = function()
{

	return $http.get(BASE_URL+"/getAllBlogs/"+1);
}

blogService.getBlogByID = function(id){

	return $http.get(BASE_URL+"/getBlog/"+id)

}
	return blogService;
})