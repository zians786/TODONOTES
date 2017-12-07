var todo=angular.module("TODO");
todo.controller('noteController',function($scope,noteService,$location){


	//for fetching all the User notes 
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
	
	
	//funtion to be called after any operation on notes
	var getNotes=function(){
	var read=noteService.read();
	read.then(function(response){
		console.log(response.data);
		$scope.readNotes=response.data;
});
	}
	
	
	//for deleting notes
	$scope.delete=function(note){
		console.log(note);
		var noteDelete=noteService.delete(note);
		noteDelete.then(function(response){
			console.log("note deleted..");
			getNotes();
		});
	}
	
	
	
	//for trash
	$scope.trash=function(note){
		console.log(note);
		var trashNote=noteService.trash(note);
		trashNote.then(function(response){
			console.log("note Trashed..");
			getNotes();
		});
	}
	
	
	//for archived
	$scope.archive=function(note){
		console.log(note);
		var archiveNote=noteService.archive(note);
		archiveNote.then(function(response){
			console.log("note Trashed..");
			getNotes();
		});
	}
	
	
	//for logout
	$scope.logout=function(){
		localStorage.clear();
		$location.path('login');
		
	}
	
	
	
	
	
	//for adding user notes
	$scope.addNote=function(){
		var add=noteService.add($scope.note,$scope.error);
		add.then(function(response){
			console.log(response.data);
			getNotes();
			console.log("Note Added Successfully");
			$scope.showTitle=false;
			$scope.note.description="";
			$scope.note.title="";
			
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
	
	
	
	
	//for expanding 
	$scope.showTitle=false;
	$scope.expandDiv=function(){
		$scope.showTitle=true;
	}
	
		
	
	//for list and grid view
	$scope.view="true";

	$scope.flex="30";
	$scope.changeView=function(){

		if($scope.view){
			$scope.flex="65";
			$scope.view=!$scope.view;
		}else
		{
			$scope.flex="30";
			$scope.view=!$scope.view;	
		}
		
		
	}
	
	
	
	
});