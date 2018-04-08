(function () {
    'use strict';

    angular
        .module('app')
        .controller('RegisterController', RegisterController);

    RegisterController.$inject = ['$scope','$state','UserService', '$rootScope', 'FlashService'];
    function RegisterController($scope,$state, UserService, $rootScope, FlashService) {

        
        $scope.register = function() {
            if($scope.user.password != $scope.reenterpassword)
            {
                FlashService.Error("Passwords do not match");
                return;
            }
            $scope.dataLoading = true;
            UserService.register($scope.user)
                .then(function (response) {
                    if (response.success) {
                        FlashService.Success('Registration successful', true);
                        $state.go('login')
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