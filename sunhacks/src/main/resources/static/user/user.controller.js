(function () {
    'use strict';

    angular
        .module('app')
        .controller('UserController', UserController);

    UserController.$inject = ['$scope','$state','UserService', '$rootScope', 'FlashService'];
    function UserController($scope,$state, UserService, $rootScope, FlashService) {

        $scope.getAllMaskedDataForCustomer = function(){
            if( $scope.secretExists == 1){
                UserService.getAllMaskedDataForCustomer($rootScope.globals.currentUser).then(function (response) {
                    //  console.log("From user"+response);
                    if (response) {
                        console.log(response);
                        $scope.merchants = response;
                    } else {
                    }
                });
            };
        };


        $scope.secretExists = 0;

     //   $scope.secret = {};
     //   $scope.secret.customerIdentity = $rootScope.globals.currentUser;
    //    $scope.secret.secretType = 'ssn';

        $scope.authorizeMerchant = {}

        $scope.hasSecret = function(){
        UserService.hasSecret($rootScope.globals.currentUser,"ssn").then(function (response) {
            if (response) {
                $scope.secretExists = 1;
                $scope.getAllMaskedDataForCustomer();
            } else {
                $scope.secretExists = 0
            }
        });
        };
        
        $scope.hasSecret();

        $scope.setSecret = function(){
            $scope.dataLoading = true;
            UserService.setSecret($scope.secret,$rootScope.globals.currentUser,"ssn").then(function (response) {
                if (response) {
                    FlashService.Success('Secret added successfully', false);
                    $scope.secretExists = 1
                } else {
                    FlashService.Error('Adding secret failed', false);
                    $scope.secretExists = 0
                }
            });
            $scope.dataLoading =false;
        };

        $scope.authorizeSecretToMerchant = function(){
            $scope.dataLoading = true;
            $scope.authorizeMerchant.customerIdentity = $rootScope.globals.currentUser;
            $scope.authorizeMerchant.secretType = "ssn";
            UserService.authorizeSecretToMerchant($scope.authorizeMerchant).then(function (response) {
             //   console.log(response);
                if (response) {
                    console.log(response);
                    $scope.maskedSec = response.maskedSecret;
                    FlashService.Success('Merchant authorized successfully', false);
                    $scope.getAllMaskedDataForCustomer();
                } else {
                    FlashService.Error('Merchant authorization failed', false);
                }
                $scope.dataLoading = false;
            });
        };




    }

})();