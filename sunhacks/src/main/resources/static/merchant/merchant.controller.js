(function () {
    'use strict';

    angular
        .module('app')
        .controller('MerchantController', MerchantController);

    MerchantController.$inject = ['$scope','$state','UserService', '$rootScope', 'FlashService'];
    function MerchantController($scope,$state, UserService, $rootScope, FlashService) {
        console.log($rootScope.globals.currentUser+"from merchant");
        var presU = $rootScope.globals.currentUser;
        $scope.callme = function() {UserService.getClientDataByMerchant(presU)
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
                merchant1Identity:$rootScope.globals.currentUser,
                merchant2Identity:$scope.info.email,
                maskedSecret:$scope.info.maskedSecret,
                secretType:"ssn",
                customerIdentity:$scope.info.customerIdentity
            };

            console.log($scope.legitInfo);


            UserService.checkIfMerchant1IsLegit($scope.legitInfo)
                .then(function (response) {
                    if (response) {
                        FlashService.Success('Credit Score: '+Math.random()*100, false);
                    } else {
                        FlashService.Error("Unauthorised to access credit scores");

                    }
                    $scope.dataLoading = false;
                });
        };
        $scope.cancel = function(){
            $state.go('/');
        };
    }
})();
