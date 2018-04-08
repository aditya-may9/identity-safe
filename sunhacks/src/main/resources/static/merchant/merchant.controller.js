(function () {
    'use strict';

    angular
        .module('app')
        .controller('MerchantController', MerchantController);

    MerchantController.$inject = ['$scope','$state','UserService', '$rootScope', 'FlashService'];
    function MerchantController($scope,$state, UserService, $rootScope, FlashService) {
        $scope.checkIfMerchant1IsLegit = function() {

            $scope.dataLoading = true;
            $scope.legitInfo = {
                merchant1Identity:$rootScope.currentUser,
                merchant2Identity:$scope.info.merchant2Identity,
                maskedSecret:$scope.info.maskedSecret,
                secretType:$rootScope.currentUserType,
                customerIdentity:$scope.info.customerIdentity
            };

            console.log($scope.legitInfo);

            UserService.getClientDataByMerchant($rootScope.currentUser)
                .then(function (response) {
                    if (response.success) {
                       $scope.clientData = response.data;
                    } else {
                        FlashService.Error(response.message);
                        $scope.dataLoading = false;
                    }
                });
            UserService.checkIfMerchant1IsLegit(legitInfo)
                .then(function (response) {
                    if (response.success) {
                        FlashService.Success('Credit Score: '+Math.random()*100, true);
                    } else {
                        FlashService.Error(response.message);
                        $scope.dataLoading = false;
                    }
                });
        };

        $scope.cancel = function(){
            $state.go('/');
        };
    }

})();
