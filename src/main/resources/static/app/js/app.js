define([
    'jquery',
    'angular',
    'angularRoute',
    'module/greetings'
], function ($, angular) {

    angular.module('Application', ['ngRoute', 'GreetingsModule'])

        .config(['$routeProvider', function ($routeProvider) {
            $routeProvider.
                otherwise({
                    redirectTo: '/greetings'
                });
        }]);

    angular.element(document).ready(function () {
        console.log('document ready');
        angular.bootstrap(document, ['Application']);
    });

});
