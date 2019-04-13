app.factory("breedSrv", function($http, $log, $q) {

    // Constructor
    function Breed(name,img) {
        this.name = name;
        this.img = img;
        
    }  

    function getAllBreedsNames() {
        var async = $q.defer();
        var i;
        var breedsUrl = "https://dog.ceo/api/breeds/list/all";
        var breeds_names = [];
        $http.get(breedsUrl).then(function(response) {
            for(i in response.data.message){
                breeds_names.push(i);
            }
            async.resolve(breeds_names);
        }, function(error) {
            async.reject(error);
            
        })
        return async.promise;
    }
  
  return {
    getAllBreedsNames:breeds_names
    
  };


});
