app.factory("breedSrv",function($log,$http,$q) {
    var breeds = [];

  function getAll() {
    var async = $q.defer();
    
    breeds = [];
    
    $http.get("app/carGallery/cars.json").then(function(response) {
      // Success
      for (var i = 0; i < response.data.length; ++i) {
        cars.push(new Car(response.data[i].brand,
          response.data[i].model, response.data[i].km,
          response.data[i].year, response.data[i].lastService));
      }
      
      async.resolve(cars);
    }, function(error) {
      // Error
      async.reject(error);
    });

    return async.promise;
  }


  function getByIndex(index) {
    return cars[index];
  }

  return {
    getAll: getAll,
    getByIndex: getByIndex
  };


});
