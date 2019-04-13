moviesApp.controller ('moviesCtrl', function ($scope,$http,minuts2hours,moviesSrv) {
    $scope.movies = [];
    moviesSrv.getAll().then(function(movies) {
    $scope.movies = movies;
  }, function(error) {
    $log.error(error);
  });
    
});

moviesApp.directive('movieCard', function() {
  return {
      template:
                   '<div class="card flex-row flex-wrap">' +
                        '<div class="card-header border-0">' +
                            '<img ng-src = "{{movie.poster}}" alt="">' +
                        '</div>' +
                        '<div class="card-block px-2">' +
                            '<h4 class="card-title">{{movie.name}}</h4>' +
                            '<p class="card-text"><b>Release:</b> {{movie.release}}</p>' +
                            '<p class="card-text"><b>Director:</b> {{movie.director}}</p>' +
                            '<p class="card-text"><b>Actors:</b> {{movie.actors_s}}</p>' +
                            '<p class="card-text"><b>Duration:</b> {{movie.duration_beauty}}</p>' +
                         '</div>' +
                    '</div>'
      
  };
});
