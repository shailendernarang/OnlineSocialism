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
blogService.blogsRejected = function()
{
	
	return $http.get(BASE_URL+"/getAllBlogsRejected/"+0);

	
}
blogService.blogsApproved = function()
{

	return $http.get(BASE_URL+"/getAllBlogs/"+1);
}

blogService.getBlogByID = function(id){

	return $http.get(BASE_URL+"/getBlog/"+id)

}

blogService.approve = function(blog)
{
	
	return $http.put(BASE_URL+"/approveBlog",blog);
}

blogService.addComment = function(blogComment)
{
	return $http.post(BASE_URL+"/addBlogComment",blogComment)
}
blogService.getBlogComments = function(id)
{
	return $http.get(BASE_URL+"/getBlogComments/"+id)

}
blogService.upvoteBlog = function(id) {
	console.log('entering upvote service')
	return $http.get(BASE_URL + "/upvote/" + id).then(
			function(response) {
				console.log(response.status)
				return response.status
			}, function() {
				console.log(reponse.status)
			})
};

blogService.downvoteBlog = function(id) {
	console.log('entering downvote service')
	return $http.get(BASE_URL + "/downvote/" + id).then(
			function(response) {
				console.log(response.status)
				return response.status
			}, function() {
				console.log(reponse.status)
			})
};
	return blogService;
})