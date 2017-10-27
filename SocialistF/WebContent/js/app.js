/**
 * Angular JS Module
 */
var app= angular.module("app",['ngRoute','ngCookies']);
app.config(function($routeProvider){
	$routeProvider
		.when('/register',{
			templateUrl:'views/registrationform.html',
			controller:'UserController'
		})
		.when('/home',{
			templateUrl:'views/home.html'
		})
		.when('/login',{
			templateUrl:'views/login.html',
			controller:'UserController'
		})
		.when('/editProfile',{
			templateUrl:'views/editProfile.html',
			controller:'UserController'
		})
		.when('/createBlog',{
			templateUrl:'views/createBlog.html',
			controller:'BlogController'
		})
		.when('/getAllBlogs',{
			templateUrl:'views/getBlogs.html',
			controller:'BlogController'
		})
		.when('/getBlogByID/:id',{
			templateUrl:'views/blogdetails.html',
			controller:'BlogDetailController'
		})
		.when('/getApprovalForm/:id',{
			templateUrl:'views/approvalBlogForm.html',
			controller:'BlogDetailController'
		})
		.when('/addJob',{
			templateUrl:'views/addJob.html',
			controller : 'JobController'
		})
		.when('/getAllJobs',{
			templateUrl:'views/getAllJobs.html',
			controller:'JobController'
		})
		
		.when('/pendingRequests',{
			templateUrl:'views/pendingRequest.html',
			controller:'FriendController'
		})
		.when('/getSuggestedUsers',{
			templateUrl:'views/suggestedUser.html',
			controller:'FriendController'
			
		})
		.when('/listOfFriends',{
			templateUrl:'views/listoffriends.html',
			controller:'FriendController'
		})
		.otherwise({
			templateUrl:'views/home.html'
		})
})

app.run(function($rootScope,$cookieStore,UserService,$location){
	if($rootScope.currentUser==undefined){
		$rootScope.currentUser=$cookieStore.get('userDetails');
		
	}
	$rootScope.logout = function()
	{
		delete $rootScope.currentUser;
		UserService.logout()
		.then(function(response){
			console.log(" CURRENT USER");
			

	$cookieStore.remove($rootScope.currentUser);
	console.log("REMOVE CURRENT USER");
	
	$location.path('/login');
},function(response){
	if(response.status==401)
		{
		console.log(response.message);
		delete $rootScope.currentUser;
		$cookieStore.remove('userDetails');
		console.log("REMOVE CURRENT USER");

		$location.path('/login');
		}
})
}
})