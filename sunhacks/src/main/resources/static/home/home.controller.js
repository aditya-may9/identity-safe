(function () {
    'use strict';

    angular
        .module('app')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$scope','UserService', '$rootScope'];
    function HomeController($scope,UserService, $rootScope) {
    }

})();