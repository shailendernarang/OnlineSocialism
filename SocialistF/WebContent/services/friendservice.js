app.factory('FriendService',function($http){
	var friendService={}
	var BASE_URL="http://localhost:8087/SocialistM"
		
		friendService.listOfSuggestedUsers = function()
		{
			return $http.get(BASE_URL+"/getSuggestedUsers")
		}
	friendService.sendFriendRequest = function(toID)
	{
		return $http.get(BASE_URL+"/friendRequest/"+toID)
	}

	friendService.pendingRequests= function()
	{
		return $http.get(BASE_URL+"/pendingRequests")
	}
	friendService.updatePendingRequest= function(request)
	{
		return $http.put(BASE_URL+"/updatePendingRequests",request)
	}
	friendService.listOfFriends=function()
	{
		return $http.get(BASE_URL+"/listOfFriends")
	}
	
	return friendService;
})