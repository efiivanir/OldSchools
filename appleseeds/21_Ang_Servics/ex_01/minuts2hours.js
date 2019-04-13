moviesApp.factory("minuts2hours", function($log) {
  
    function minuts2hours(minuts) {
        var hours = Math.floor(minuts / 60);
        var min = minuts - hours * 60 ;
        var msg = hours + "h " + min + "min";
        // $log.log(msg);
        return msg;
    }

    return {
      minuts2hours: minuts2hours
   
  };
})


