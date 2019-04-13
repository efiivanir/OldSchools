app.controller("loginCtrl", function($scope, $location, user) {

    $scope.email = "efi.ivanir@gmail.com";
    $scope.pwd = "123456";

    $scope.invalidLogin = false;

    $scope.login = function() {
        $scope.invalidLogin = false;

        user.login($scope.email, $scope.pwd).then(function() {
            // success login
            $location.path("/messages")
        }, function(error) {
            // failed login
            $scope.invalidLogin = true;
        })
    }
});
