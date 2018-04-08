(function () {
    'use strict';

    angular
        .module('app')
        .controller('MerchantController', MerchantController);

    MerchantController.$inject = ['$scope','$state','UserService', '$rootScope', 'FlashService'];
    function MerchantController($scope,$state, UserService, $rootScope, FlashService) {
    }

})();