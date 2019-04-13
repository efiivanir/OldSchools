var app = angular.module("homeOwnerApp", ["ngRoute",'ui.bootstrap','mgcrea.ngStrap']);

app.config(function($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl: "app/home/home.html"
    }).when("/login", {
        templateUrl: "app/login/login.html",
        controller: "loginCtrl"
    }).when("/voting", {
        templateUrl: "app/voting/voting.html",
        controller: "votingCtrl"
    }).when("/messages" , {
        templateUrl: "app/messages/messages.html",
        controller: "messagesCtrl"
    }).when("/issues" , {
        templateUrl: "app/issues/issues.html",
        controller: "issuesCtrl"
    })
})
