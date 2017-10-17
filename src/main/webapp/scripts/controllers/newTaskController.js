
angular.module('javaee-hol').controller('NewTaskController', function ($scope, $location, locationParser, flash, TaskResource ) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.task = $scope.task || {};
    
    $scope.finishedList = [
        "true",
        "false"
    ];


    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            flash.setMessage({'type':'success','text':'The task was created successfully.'});
            $location.path('/Tasks');
        };
        var errorCallback = function(response) {
            if(response && response.data) {
                flash.setMessage({'type': 'error', 'text': response.data.message || response.data}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Something broke. Retry, or cancel and start afresh.'}, true);
            }
        };
        TaskResource.save($scope.task, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Tasks");
    };
});