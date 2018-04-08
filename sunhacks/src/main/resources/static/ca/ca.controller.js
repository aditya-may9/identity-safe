(function () {
    'use strict';

    angular
        .module('app')
        .controller('CAController', CAController);

    CAController.$inject = ['$scope','$state','UserService', '$rootScope', 'FlashService'];
    function CAController($scope,$state, UserService, $rootScope, FlashService) {
    }

})();