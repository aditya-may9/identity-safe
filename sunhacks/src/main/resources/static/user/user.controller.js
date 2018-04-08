(function () {
    'use strict';

    angular
        .module('app')
        .controller('UserController', UserController);

    UserController.$inject = ['$scope','$state','UserService', '$rootScope', 'FlashService'];
    function UserController($scope,$state, UserService, $rootScope, FlashService) {
        
        
        $scope.secret.customerIdentity = $rootScope.currentUser;
        $scope.secret.secretType = 'SSN';

        $scope.hasSecret = function(){
        UserService.hasSecret(secret).then(function (response) {
            if (response.success) {
                $scope.secretExists = true
            } else {
                $scope.secretExists = false
            }
        });
        };
        
        $scope.hasSecret();

        $scope.setSecret = function(){
            $scope.dataLoading = true;
            UserService.setSecret(secret).then(function (response) {
                if (response.success) {
                    FlashService.Success('Secret added successfully', true);
                    $scope.secretExists = true
                } else {
                    FlashService.Error('Adding secret failed', true);
                    $scope.secretExists = false
                }
            });
            $scope.dataLoading =false;
        };

        $scope.authorizeSecretToMerchant = function(){
            $scope.dataLoading = true;
            UserService.authorizeSecretToMerchant($scope.authorizeMerchant).then(function (response) {
                if (response.success) {
                    console.log(respose);
                    FlashService.Success('Merchant authorized successfully', true);
                } else {
                    FlashService.Error('Merchant authorization failed', true);
                }
                $scope.dataLoading = false;
            });
        }

        $scope.getAllMaskedDataForMerchant = function(){
            $scope.merchants = [{"merchant":"sddfs","secretType":"hasajkk","maskedSecret":"huii"},
            {"merchant":"sddfs1","secretType":"hasajkk1","maskedSecret":"huii1"},
            {"merchant":"sddfs2","secretType":"hasajkk2","maskedSecret":"huii2"}];
            if($scope.hasSecret){
                UserService.getAllMaskedDataForMerchant($scope.secret.customerIdentity).then(function (response) {
                    if (response.success) {
                        console.log(response);
                        $scope.merchants = [];
                    } else {
                    }
                });
            }
        }

        $scope.getAllMaskedDataForMerchant();


    }

})();