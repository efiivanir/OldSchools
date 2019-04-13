moviesApp.factory("moviesSrv", function($http, $log,minuts2hours, $q) {

    // Constructor
  function Movie(name, release, poster, duration, director,actors) {
        this.name = name;
        this.release = release;
        this.poster = poster;
        this.duration = duration;
        this.duration_beauty = minuts2hours.minuts2hours(duration);
        this.director = director;
        this.actors = actors;
        this.actors_s = actors.join(' ,');
    }  
  

  

  function getAll() {
    var async = $q.defer();
    var movies = [];
    
    $http.get("movies.json").then(function(response) {
        // Success
        for (var i = 0; i < response.data.length; i++) {
            movies.push(new Movie(
                response.data[i].name,
                response.data[i].release,
                response.data[i].poster,
                response.data[i].duration,
                response.data[i].director,
                response.data[i].actors
            ));
        }
        // alert(JSON.stringify(response.data));
        async.resolve(movies);
    }, function(error) {
      // Error
      async.reject(error);
    });

    return async.promise;
  }

  return {
    getAll: getAll
    
  };


});
