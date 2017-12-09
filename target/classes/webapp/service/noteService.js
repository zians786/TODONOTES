var ToDo = angular.module('TODO');

ToDo.factory('noteService',function($http,$location){
	var userNote ={};
	
	userNote.add = function(note){
	
		return $http({
			method :"POST",
			url :'notes/create',
			data : note,
			headers: { 'accToken': localStorage.getItem('token')
					}
		});
	}
	
	userNote.read=function(){
		return $http({
			method:"POST",
			url:'notes/read',
			headers:{ 'accToken':localStorage.getItem('token')
				
			}
		});
	}

	
	userNote.delete=function(note){
		return $http({
			method:"POST",
			url:"notes/delete",
			data: note,
			headers: { 'accToken': localStorage.getItem('token')
			}
		});
		
		
	}
	
	
	userNote.update=function(note){
		
		return $http({
			method:"POST",
			url:"notes/update",
			data:note,
			headers: {	'accToken':localStorage.getItem('token')		
			}
		});
	}
	
	userNote.trash=function(note){
		return $http({
			method:"POST",
			url:"notes/trash",
			data:note,
			headers: {'accToken': localStorage.getItem('token')
			}
		});
	}
	
	

	userNote.archive=function(note){
		return $http({
			method:"POST",
			url:"notes/archive",
			data:note,
			headers: {'accToken': localStorage.getItem('token')
			}
		});
	}
	
	userNote.color=function(note){
			return $http({
				method:"POST",
				url:"notes/color",
				data:note,
				headers: {'accToken': localStorage.getItem('token')
				}
			});
	}
	
	
	

	return userNote;
});
