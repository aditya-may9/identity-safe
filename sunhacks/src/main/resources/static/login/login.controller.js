(function () {
    'use strict';

    angular
        .module('app')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$state','$scope','UserService', 'FlashService'];
    function LoginController($state,$scope, UserService, FlashService) {        
        
        $scope.login = function() {};
        $scope.login = function() {
            $scope.dataLoading = true;
            UserService.login($scope.user, function (response) {
                if (response.success) {
                    var authdata = AuthenticationService.GetAuthdata($scope.username, $scope.password);
                    var params = {username:$scope.username, authdata:authdata};
                    $state.go('login.manage',params);
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
