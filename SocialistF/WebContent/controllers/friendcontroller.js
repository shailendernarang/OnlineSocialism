app.controller('FriendController',function($scope,$location,FriendService){

	function listOfSuggestedUsers(){
	FriendService.listOfSuggestedUsers().then(function(response){
		$scope.suggestedUsers= response.data;
	},function(response){
		$location.path('/login')
	})

	}
	function pendingRequests(){
		FriendService.pendingRequests().then(function(response){
			$scope.pendingRequest= response.data;
		},function(response){
			$location.path('/login')
		})

		}
	$scope.sendFriendRequest=function(toID){
		FriendService.sendFriendRequest(toID).then(function(response){
			alert("SENT Successfully");
			listOfSuggestedUsers()
			$location.path('/getSuggestedUsers')
		},function(response){
			$location.path('/login')
		})

		}
	$scope.updatePendingRequest = function(request,statusValue){
		request.status=statusValue;
		FriendService.updatePendingRequest(request).then(function(response){
			pendingRequests()
			$location.path('/pendingRequests')
		},function(response){
			
		})
	
	}
	function listOfFriends()
	{
		FriendService.listOfFriends().then(function(response){
			$scope.listOfFriends=response.data;
		},function(response){
			
		})
	}
	listOfSuggestedUsers()
	pendingRequests()
	listOfFriends()
})