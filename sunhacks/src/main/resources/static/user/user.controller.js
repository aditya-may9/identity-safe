(function () {
    'use strict';

    angular
        .module('app')
        .controller('UserController', UserController);

        UserController.$inject = ['$scope','$state','UserService', '$rootScope', 'FlashService'];
    function UserController($scope,$state, UserService, $rootScope, FlashService) {
    }

})();