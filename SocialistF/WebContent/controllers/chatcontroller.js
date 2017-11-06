/**
 * Created by ikism on Dec 19, 2016
 */
// var a = angular.module('myApp.chatController');
app.controller('ChatController', function($scope, $rootScope, ChatService) {

	console.log('starting chatController')
	$scope.messages = [];
	$scope.message = "";
	$scope.max = 140;

	$scope.addMessage = function() {
		console.log('AddMessage method');
		ChatService.send($rootScope.currentUser.firstName + " : " + $scope.message);
		$scope.message = "";
	};

	ChatService.receive().then(null, null, function(message) {
		console.log('recieve Method')
		$scope.messages.push(message);
	});
});