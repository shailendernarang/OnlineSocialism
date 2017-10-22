/**
 * 
 */
app.controller('JobController',function($scope,JobService,$location,$routeParams){
	var self = this;
	var id=$routeParams.id;
	
	self.job = 
			{'jobTitle':'',
			 'location':'',
			 'companyName':'',
			 'skillsRequired':'',
			 'salary':'',
			 'jobDesc':''};
	var id = $routeParams.id;
	
	self.submit = function()
	{
		
	{
		self.addJob(self.job);
		
	}
	self.reset();
};
	self.reset = function()
	{
		self.job = 
		{'jobTitle':'',
		 'location':'',
		 'companyName':'',
		 'skillsRequired':'',
		 'salary':'',
		 'jobDesc':''};
	}
	self.addJob = function()
	{
		JobService.addJob(self.job).then(function(response){
			self.job = response.data;
			self.job = ''
				alert("Added Successfully")
			$location.path('/getAllJobs')
		},function(response){
			console.log(response.data)
		})
	}
	function getAllJobs()
	{
		JobService.getAllJobs().then(function(response){
			$scope.listOfJobs = response.data;
			console.log($scope.listOfJobs);
			
		},function(response){
			console.log(response.data)
		})
	}
	getAllJobs();
	
	$scope.deleteJob = function(id)
	{
		
		JobService.deleteJob(id).then(function(response){
			alert("Deleted Successfully")
			getAllJobs();
			$location.path('/getAllJobs')
		},function(response){
			console.log(response.status);
			console.log(response.message);
			$scope.error=response.data;
			$location.path('/login');
		})
	}
	
})
