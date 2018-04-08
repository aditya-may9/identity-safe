(function () {
    'use strict';

    angular
        .module('app')
        .controller('UserController', UserController);

    UserController.$inject = ['$scope','$state','UserService', '$rootScope', 'FlashService'];
    function UserController($scope,$state, UserService, $rootScope, FlashService) {
        
        $scope.secretExists = 0

        $scope.secret = {};
        $scope.secret.customerIdentity = $rootScope.globals.currentUser;
        $scope.secret.secretType = 'SSN';

        $scope.authorizeMerchant = {}

        $scope.hasSecret = function(){
        UserService.hasSecret(secret).then(function (response) {
            if (response) {
                $scope.secretExists = 1
            } else {
                $scope.secretExists = 0
            }
        });
        };
        
        $scope.hasSecret();

        $scope.setSecret = function(){
            $scope.dataLoading = true;
            UserService.setSecret(secret).then(function (response) {
                if (response) {
                    FlashService.Success('Secret added successfully', true);
                    $scope.secretExists = 1
                } else {
                    FlashService.Error('Adding secret failed', true);
                    $scope.secretExists = 0
                }
            });
            $scope.dataLoading =false;
        };

        $scope.authorizeSecretToMerchant = function(){
            $scope.dataLoading = true;
            $scope.authorizeMerchant.customerIdentity = $rootScope.globals.currentUser;
            $scope.authorizeMerchant.secretType = "SSN";
            UserService.authorizeSecretToMerchant($scope.authorizeMerchant).then(function (response) {
                if (response.success) {
                    console.log(respose);
                    FlashService.Success('Merchant authorized successfully', true);
                } else {
                    FlashService.Error('Merchant authorization failed', true);
                }
                $scope.dataLoading = false;
            });
        };

        $scope.getAllMaskedDataForMerchant = function(){
            if($scope.hasSecret == 1){
                UserService.getAllMaskedDataForMerchant($scope.secret.customerIdentity).then(function (response) {
                    console.log("From user"+response);
                    if (response) {
                        console.log(response);
                        $scope.merchants = [];
                    } else {
                    }
                });
            };
        };

        $scope.getAllMaskedDataForMerchant();


    }

})();