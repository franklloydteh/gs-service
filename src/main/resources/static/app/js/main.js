(function () {

    require.config({
        baseUrl: '/app/js',
        paths: {
            jquery: 'lib/jquery-2.1.3',
            angular: 'lib/angular',
            angularResource: 'lib/angular-resource',
            angularRoute: 'lib/angular-route',
            angularUIBootstrap: 'lib/ui-bootstrap-tpls-1.2.5'
        },
        shim: {
            angular: {deps: ['jquery'], exports: 'angular'},
            angularResource: {deps: ['angular']},
            angularRoute: {deps: ['angular']},
            angularUIBootstrap: ['angular']
        }
    });

    require(['app'], function () {
        console.log('Done Loading');
    });

})();