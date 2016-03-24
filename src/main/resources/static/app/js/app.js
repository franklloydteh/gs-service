angular.module('Application', ['ngRoute', 'GreetingsModule'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.
            otherwise({
                redirectTo: '/greetings'
            });
    }]);