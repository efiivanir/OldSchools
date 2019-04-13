
app.factory("messages", function($q, $http,$scope) {

   
    function getAllMessages() {
        
            
        
                // messages.push(mew Message(
                //     response.data[i].date,
                //     response.data[i].fname,
                //     response.data[i].lname,
                //     response.data[i].title,
                //     response.data[i].text,
                //     response.data[i].priority,
                //     response.data[i].isRead
                    
                // ));
            }
        }, function(error) {
            async.reject(error);
        });

        return async.promise;
    }

    
    return {
        getAllMessages: getAllMessages
        
    }
})
