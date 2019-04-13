app.controller("messagesCtrl", function($scope,$location,$http,$log,$modal) {
    function Message(date,fname,lname,title,text,priority,isRead) {
        this.date = date;
        this.fname = fname;
        this.lname = lname;
        this.title = title;
        this.text = text;
        this.priority = priority;
        this.isRead = isRead;
        
    }
    
    $scope.messages = [];
    var messagesURL = "messages.json";
    var i;
    $http({method:'GET',
           url:messagesURL,
           responseType:'json'}).then(function(response) {
               for(i = 0; i < response.data.length;i++){
                   $scope.messages.push(
                       new Message(response.data[i].date,response.data[i].fname,
                                   response.data[i].lname,response.data[i].title,
                                   response.data[i].text,response.data[i].priority,response.data[i].read
                                  )
                   );
                   
               }
           });

    
    $scope.remove_message = function(message) {
        console.log($scope.messages.indexOf(message));
        $scope.messages.splice($scope.messages.indexOf(message), 1);
    };

    $scope.edit_message = function(message) {
        console.log($scope.messages.indexOf(message));
        $scope.modal = {
            "title": message.title,
            "content": message.text
        };
        
    };

    $scope.add_message = function() {
        
        $scope.modal = {
            "date":"Insert date",
            "fname":"First Name",
            "lname":"Last Name",
            "title":"Title",
            "text":"Text",
            
        };
        
    };

    
});



