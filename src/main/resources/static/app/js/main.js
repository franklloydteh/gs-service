(function () {

    require.config({
        baseUrl: '/app/js',
        paths: {
            jquery: 'lib/jquery-2.1.3',
            angular: 'lib/angular',
            angularResource: 'lib/angular-resource',
            angularRoute: 'lib/angular-route'
        },
        shim: {
            angular: {deps: ['jquery'], exports: 'angular'},
            angularResource: {deps: ['angular']},
            angularRoute: {deps: ['angular']}
        }
    });

    require(['app'], function () {
        console.log('Done Loading');
    });

})();