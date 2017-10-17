angular.module('javaee-hol').factory('TaskResource', function($resource){
    var resource = $resource('rest/tasks/:TaskId',{TaskId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});