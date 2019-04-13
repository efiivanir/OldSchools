moviesApp.controller ('actorsCtrl', function ($scope,$http) {
      // Constructor
  function Actor(fname, lname, birthDate, imgURL, imdbURL) {
    this.fname = fname;
    this.lname = lname;
    this.birthDate = birthDate;
    this.imgURL = imgURL;
    this.imdbURL = imdbURL;
  }
    $http.get("actors.json").then(function(response) {
        // Success
        $scope.actors = [];
        for (var i = 0; i < response.data.length; i++) {
            $scope.actors.push(new Actor(
                response.data[i].fname,
                response.data[i].lname,
                response.data[i].birthDate,
                response.data[i].imgURL,
                response.data[i].imdbURL));
        }
        // alert(JSON.stringify(response.data));
          
  }, function(error) {
    // Error
    console.error(error);
  }) 
    $scope.toggle = function (item) {
        for(var i in $scope.actors){
            console.log($scope.actors[i]);
            $scope.actors[i].selected = false;
        }
        // console.log(item);
        item.selected = true;
    };

    
});
