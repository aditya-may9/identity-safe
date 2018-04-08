(function () {
    'use strict';

    angular
        .module('app')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$state','$scope','UserService', 'FlashService','$rootScope'];
    function LoginController($state,$scope, UserService, FlashService, $rootScope) {        
        
     /*   $scope.login = function(){

        }
*/
        $scope.login = function() {
            $scope.dataLoading = true;
            UserService.login($scope.user, function (response) {
                if (response.success) {
                    $rootScope.globals.currentUser = $scope.user.email;
                    $rootScope.globals.currentUserType = $scope.user.type;
                    $state.go($scope.user.type);
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
