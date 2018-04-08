(function () {
    'use strict';

    angular
        .module('app')
        .controller('MerchantController', MerchantController);

    MerchantController.$inject = ['$scope','$state','UserService', '$rootScope', 'FlashService'];
    function MerchantController($scope,$state, UserService, $rootScope, FlashService) {
        console.log($rootScope.currentUser);
        $scope.callme = function() {UserService.getClientDataByMerchant($rootScope.currentUser)
            .then(function (response) {
                console.log("From merchant"+response);
                if (response) {
                    $scope.clientData = response;
                } else {
                    FlashService.Error(response.message);
                    $scope.dataLoading = false;
                }
            });
        };
        $scope.callme();
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


            UserService.checkIfMerchant1IsLegit(legitInfo)
                .then(function (response) {
                    if (response) {
                        FlashService.Success('Credit Score: '+Math.random()*100, true);
                    } else {
                        FlashService.Error("Unauthorised to access credit scores");
                        $scope.dataLoading = false;
                    }
                });
        };
        $scope.cancel = function(){
            $state.go('/');
        };
    }
})();
