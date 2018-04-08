(function () {
    'use strict';

    angular
        .module('app')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$state','$scope','UserService', 'FlashService','$rootScope'];
    function LoginController($state,$scope, UserService, FlashService, $rootScope) {        
        
        $scope.login = function(){
            $rootScope.globals.currentUser = $scope.user.email;
            $rootScope.globals.currentUserType = $scope.user.type;
            $state.go($scope.user.type);
        }

        /*$scope.login = function() {
            $scope.dataLoading = true;
            UserService.login($scope.user, function (response) {
                if (response.success) {
                    var authdata = AuthenticationService.GetAuthdata($scope.username, $scope.password);
                    var params = {username:$scope.username, authdata:authdata};
                    $state.go($scope.user.type,params);
                } else {
                    FlashService.Error(response.message);
                    $scope.dataLoading = false;
                }
            });
        };*/

        $scope.cancel = function(){
            $state.go('/');
        };
    }

})();
