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


	return userNote;
});
