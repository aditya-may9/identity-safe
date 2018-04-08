(function () {
    'use strict';

    angular
        .module('app')
        .controller('UserController', UserController);

    UserController.$inject = ['$stateParams','$scope','$state','UserService', '$rootScope', 'FlashService'];
    function UserController($stateParams,$scope,$state, UserService, $rootScope, FlashService) {
        debugger;
        console.log($stateParams);
    }

})();