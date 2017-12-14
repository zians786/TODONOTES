var todo=angular.module("TODO");
todo.controller('noteController',function(toastr,$scope,noteService,$location,$mdDialog,$mdToast,$interval,$filter){

	
	
	$scope.user={};
	$scope.user.userName=localStorage.getItem('userName');
	$scope.user.email=localStorage.getItem('email');
	$scope.user.picture=localStorage.getItem('picture');
	
	console.log($scope.user);


    // for Notification using toastr
    var reminderCheck=function(note){
    	
    	$interval(function(){
    		for(var i=0;i<note.length;i++){
    			if(note[i].reminder){
    				var date=new Date(note[i].reminder);
    				var currentDate=new Date();
    			
	            	if (((date.getTime()-10)<currentDate.getTime())&&(date.getTime()+10)>currentDate.getTime()) {
	            		console.log(note[i]);
	            		 toastr.success(note[i].title, note[i].description,{
	                		 closeButton: true	
	                	 });
	            		 note[i].reminder="";
	            		 noteService.update(note[i]);
	            	}	
    			}	
    
    		}	
    });
    }
	// for fetching all the User notes
	var read=noteService.read();
	read.then(function(response){
		console.log(response.data);
		$scope.readNotes=response.data;
		  reminderCheck($scope.readNotes);
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

  
	// funtion to be called after any operation on notes
	var getNotes=function(){
	var read=noteService.read();
	read.then(function(response){
		console.log(response.data);
		$scope.readNotes=response.data;
		 reminderCheck($scope.readNotes);
});
	}
	

	
	// for deleting notes
	$scope.delete=function(note){
		console.log(note);
		var noteDelete=noteService.delete(note);
		noteDelete.then(function(response){
			console.log("note deleted..");
			getNotes();
		});
	}
	
	
	
	// for trash
	$scope.trash=function(note){
		console.log(note);
		var trashNote=noteService.trash(note);
		trashNote.then(function(response){
			console.log("note Trashed..");
			getNotes();
		});
	}
	
	
	// for restore from trash
	$scope.restore=function(note){
		console.log(note);
		var trashNote=noteService.trash(note);
		trashNote.then(function(response){
			console.log("note unTrashed..");
			getNotes();
		});
	}
	
	// for archived
	$scope.archive=function(note){
		console.log(note);
		var archiveNote=noteService.archive(note);
		archiveNote.then(function(response){
			console.log("note Trashed..");
			getNotes();
		});
	}
	
	

	// for pin
	    $scope.pin=function(note){
			console.log(note);
			var pinResp=noteService.pin(note);
				pinResp.then(function(response){
				console.log("note Pinned..");
				getNotes();
			});
		}
		    
	
	
	
	// for unArchived
	$scope.unArchive=function(note){
		console.log(note);
		var archiveNote=noteService.archive(note);
		archiveNote.then(function(response){
			console.log("note unArchived..");
			getNotes();
		});
	}
	
	
	// for logout
	$scope.logout=function(){
		localStorage.clear();
		$location.path('login');
		
	}
	
	
	
	
	
	// for adding user notes
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
	
	
	// for editing notes inside Dialog
	$scope.showDialog=function(editNote){
		console.log("inside showDialog ");
		$scope.notes= angular.copy(editNote);
		  $mdDialog.show({
			    contentElement: '#myStaticDialog',
			    parent: angular.element(document.body),
		      clickOutsideToClose:true
			  });
			}
	// for updating notes
		    $scope.updateNote = function(note) {
		    	console.log("inside dialog controller");
		    	var updateResponse=noteService.update(note);
		    	updateResponse.then(function(response){
		    		$mdDialog.hide();
		    		getNotes();
		    	});
		      
		    };

	// for uploading image
		    $scope.uploadImage = function(type) {
				$scope.type = type;
				$('#image').trigger('click');
				return false;
		    }
		    
		    $scope.stepsModel = [];
		    $scope.imageUpload = function(element){
			    var reader = new FileReader();
			    console.log("ele"+element);
			    reader.onload = $scope.imageIsLoaded;
			    reader.readAsDataURL(element.files[0]);
			    console.log(element.files[0]);
			}
		
			$scope.imageIsLoaded = function(e){
			    $scope.$apply(function() {
			        $scope.stepsModel.push(e.target.result);
			        console.log(e.target.result);
			        var imageSrc=e.target.result;
			        if($scope.type ==='input')
		        	{
			        	$scope.addImg= imageSrc;
		        	}else if($scope.type ==='user'){
		        		$scope.user.picUrl=imageSrc;
		        		updateUser($scope.user);
		        	}else if($scope.type ==='update'){
		        		$scope.changeIamge.image=imageSrc;
		        		update($scope.changeIamge);
		        	}
			        else{
			        	
		        		$scope.type.image=imageSrc;
// console.log(e.target.result);
// console.log(imageSrc);
		        		console.log($scope.type)
		        		var updateResponse=noteService.update($scope.type);
		        		updateResponse.then(function(response){
		        			console.log(response);
		        			getNotes();	
		        		},
		        		function(response){
		        			console.log(response);
		        		});
//		        		
			        }
			    });
	};
		    
		    
		    
		    
	
	// for expanding
	$scope.showTitle=false;
	$scope.expandDiv=function(){
		$scope.showTitle=true;
	}
	
		
	
	// for list and grid view
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
	
	// for color-picker
		$scope.options = ['transparent','#FF8A80', '#FFD180', '#FFFF8D', '#CFD8DC', '#80D8FF', '#A7FFEB', '#CCFF90'];
	    $scope.color = '#FF8A80';

	    $scope.colorChanged = function(newColor,note) {
	    	note.color=newColor;
	    	var colorResponse=noteService.color(note);
	    	colorResponse.then(function(response){
	    		console.log(response.data);
	    	});
	    }
	    
	
	    
	
	// for checking reminder
	    
	    
	// toggle navbar
	    $scope.showNav=true;
	    $scope.hideNav=function(){
	    	$scope.showNav=!$scope.showNav;
	    	}	    
	    
	// for getting all archived notes
	    $scope.archivedNotes=function(){
	    	$location.path('archive');
	    }
	    
	// for getting all trash notes
	    $scope.trashNotes=function(){
	    	$location.path('trash');
	    }
	  
	    
	// for home notes
	    $scope.home=function(){
	    	$location.path('home');
	    }
	    
	    
	 // for setting reminder
	    $scope.reminder=function(note,remind){
	    	note.reminder=remind;
	    	console.log(note.reminder);
	    	var date=Date.parse(note.reminder);
	    	note.reminder=date;
	    	console.log(note);
	    	var updateResponse=noteService.update(note);
	    	updateResponse.then(function(response){
	    		getNotes();
	    	});
	    
	    }    
	
	


	    
 
});