(function () {
    'use strict';

    angular
        .module('app')
        .factory('UserService', UserService);

    UserService.$inject = ['$http'];
    function UserService($http) {
        var service = {};


        service.login = login;
        service.register = register;
        service.hasSecret = service.hasSecret;
        service.setSecret = setSecret;
        service.authorizeSecretToMerchant = authorizeSecretToMerchant;
        service.checkIfMerchant1IsLegit = checkIfMerchant1IsLegit;


        return service;



        function login(user) {
            return $http.get('/login/?email='+user.email+'&password='+user.password+'&userType='+user.type).then(handleSuccess, handleError('Login failed'));
        }

        function register(user) {
            if(user.type == "user")
                return $http.get('/customer/registerCustomerToCentralAuthority/?identity='+user.email+'&name='+user.name+'&address='+user.address+'&email='+user.email+'&password='+user.password).then(handleSuccess, handleError('Error register user by username'));

            else if(user.type == "merchant")
            {
                return $http.get('/customer/registerCustomerToCentralAuthority/?identity='+user.email+'&name='+user.name+'&address='+user.address+'&email='+user.email+'&password='+user.password+'&description='+user.description).then(handleSuccess, handleError('Error register user by username'));
            }
        }

        function setSecret(secret){
            return $http.get('/centralAuthority/addCustomerSecret/?customerIdentity=', secret.customerIdentity+'&secret='+secret.secret+'&secretType='+secret.secretType).then(handleSuccess, handleError('Error authorizeSecretToMerchant user'));
        }

        function authorizeSecretToMerchant(authorizeMerchant) {
            return $http.get('/centralAuthority/authorizeSecretToMerchant/?customerIdentity=', authorizeMerchant.customerIdentity+'&secretType='+authorizeMerchant.secretType+'&merchantIdentity='+authorizeMerchant.merchantIdentity+'&minutes='+authorizeMerchant.minutes).then(handleSuccess, handleError('Error authorizeSecretToMerchant user'));
        }

        function checkIfMerchant1IsLegit(legitInfo) {
            return $http.get('/merchant/checkIfMerchantInfoIsLegit/?merchant1Identity=' + legitInfo.merchant1Identity+'&merchant2Identity='+legitInfo.merchant2Identity+'&maskedSecret='+legitInfo.maskedSecret+'&secretType='+legitInfo.secretType+'&customerIdentity='+legitInfo.customerIdentity).then(handleSuccess, handleError('Error checking merchant is legit or not user'));
        }

        function Delete(id) {
            return $http.delete('/api/users/' + id).then(handleSuccess, handleError('Error deleting user'));
        }

        // private functions

        function handleSuccess(res) {
            return res.data;
        }

        function handleError(error) {
            return function () {
                return { success: false, message: error };
            };
        }
    }

})();
