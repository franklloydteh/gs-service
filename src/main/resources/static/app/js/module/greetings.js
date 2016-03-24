define([
    'jquery',
    'angular',
    'angularResource',
    'angularRoute'
], function ($, angular) {

    angular.module('GreetingsModule', ['ngRoute', 'ngResource'])
        .config(['$routeProvider', function ($routeProvider) {
            $routeProvider
                .when('/greetings', {
                    templateUrl: 'view/greeting/greeting_list.html',
                    controller: 'GreetingListCtrl',
                    resolve: {
                        greetings: ['GreetingResource', function (GreetingResource) {
                            return GreetingResource.query().$promise;
                        }]
                    }
                })
                .when('/greeting/form', {
                    templateUrl: 'view/greeting/greeting_form.html',
                    controller: 'GreetingSaveCtrl',
                    resolve: {
                        greeting: [function () {
                            return {};
                        }]
                    }
                })
                .when('/greeting/edit/:id', {
                    templateUrl: 'view/greeting/greeting_form.html',
                    controller: 'GreetingSaveCtrl',
                    resolve: {
                        greeting: ['$route', 'GreetingResource', function ($route, GreetingResource) {
                            return GreetingResource.get({id: $route.current.params.id}).$promise;
                        }]
                    }
                })
                .when('/greeting/:id', {
                    templateUrl: 'view/greeting/greeting_detail.html',
                    controller: 'GreetingDetailsCtrl'
                });
        }])
        .controller('GreetingSaveCtrl', ['$scope', '$location', '$routeParams', 'GreetingResource', 'greeting',
            function (a, $location, $routeParams, GreetingResource, greeting) {
                $scope.greeting = greeting;
                $scope.saveGreeting = function () {
                    GreetingResource.save($scope.greeting, function (data) {
                        alert("Saved with ID: " + data.id);
                        $location.path('/greetings');
                    });
                };
            }])
        .controller('GreetingListCtrl', ['$scope', 'GreetingResource', 'greetings',
            function ($scope, GreetingResource, greetings) {
                $scope.greetings = greetings;
                $scope.removeGreeting = function (id, index) {
                    GreetingResource.remove({id: id});
                    $scope.greetings.splice(index, 1);
                }
            }])
        .controller('GreetingDetailsCtrl', ['$scope', '$routeParams', 'GreetingResource',
            function ($scope, $routeParams, GreetingResource) {
                $scope.greeting = GreetingResource.get({id: $routeParams.id});
            }])
        .factory('GreetingResource', ['$resource', function ($resource) {
            return $resource('/hello/:id');
        }]);

});