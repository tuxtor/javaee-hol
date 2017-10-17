'use strict';

angular.module('javaee-hol',['ngRoute','ngResource'])
  .config(['$routeProvider', function($routeProvider) {
    $routeProvider
      .when('/',{templateUrl:'views/landing.html',controller:'LandingPageController'})
      .when('/Tasks',{templateUrl:'views/Task/search.html',controller:'SearchTaskController'})
      .when('/Tasks/new',{templateUrl:'views/Task/detail.html',controller:'NewTaskController'})
      .when('/Tasks/edit/:TaskId',{templateUrl:'views/Task/detail.html',controller:'EditTaskController'})
      .otherwise({
        redirectTo: '/'
      });
  }])
  .controller('LandingPageController', function LandingPageController() {
  })
  .controller('NavController', function NavController($scope, $location) {
    $scope.matchesRoute = function(route) {
        var path = $location.path();
        return (path === ("/" + route) || path.indexOf("/" + route + "/") == 0);
    };
  });
