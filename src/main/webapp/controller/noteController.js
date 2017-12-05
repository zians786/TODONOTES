var todo=angular.module("TODO");
todo.controller('noteController',function($scope,noteService,$location){


	
	var read=noteService.read();
	read.then(function(response){
		console.log(response.data);
		$scope.readNotes=response.data;
},function(response){
		if(response.status==409)
			{
				$scope.error=response.data.responseMessage;
			}
		else
			{
				console.log("fail");
				$scope.error="Enter valid data";
			}
	});
	
	var getNotes=function(){
	var read=noteService.read();
	read.then(function(response){
		console.log(response.data);
		$scope.readNotes=response.data;
});
	}
	
	$scope.addNote=function(){
		var add=noteService.add($scope.note,$scope.error);
		add.then(function(response){
			console.log(response.data);
			getNotes();
			console.log("Note Added Successfully");
	},function(response){
			if(response.status==409)
				{
					$scope.error=response.data.responseMessage;
				}
			else
				{
					console.log("fail");
					$scope.error="Enter valid data";
				}
		});
	}
	
	
	$scope.title="Title";
	$scope.descript="Take a note";
	
	
	
	
});