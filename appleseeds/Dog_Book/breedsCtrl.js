var app = angular.module("dogsApp", ["ngRoute"]);
app.controller("breedsCtrl", function($scope, $http,$log, $location,$q,breedName2img) {
    var breeds_names = [];
    $scope.breeds = [];
    var i;
    var j;
    var img_path;
    var breedsUrl = "https://dog.ceo/api/breeds/list/all";

    function Breed(name,img){
        this.name = name;
        this.img = img;
    }
    
    $http.get(breedsUrl).then(function(response) {
        for(i in response.data.message){
            breeds_names.push(i);
                
        }
        for(i = 0 ; i < breeds_names.length; i++){
            console.log(breeds_names[i]);
            // breedName2img.breedName2img.randomUrl(breeds_names[i],function(response){
            //     img_path = response;
            // });
            img_path = function
            console.log(img_path);
            //$scope.breeds.push(breeds_names[i],img_path);
            
        }
    }, function(error) {
        console.log(error)
        
    })
});
 

