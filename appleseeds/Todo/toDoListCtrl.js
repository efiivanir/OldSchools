app.controller("toDoListCtrl",function($scope, $http) {
    var jsonFile = "tasks.json";
    function Task(done,task){
        this.done = done;
        this.task = task;
    }
    
    $http.get(jsonFile).then(function(response) {
        $scope.taskList = [];
        for(var i = 0; i < response.data.length;i++){
            $scope.taskList.push(new Task(response.data[i].done,response.data[i].task));
            console.log(i);
        }
        
    }, function(error) {
        console.log(error)

    })

    $scope.addTask = function(task) {
        if(task.trim().length !== 0) {
            $scope.taskList.push(new Task('false',task)); 
            
        }
    };

    $scope.delTask = function() {
        $scope.taskList.splice(this.$index, 1);
    };

     $scope.hover = function(task) {
       
         return task.showDelete = ! task.showDelete;
    };
    
    
    $scope.getUncompletedTaskNum = function() {
        var allTasksNum = $scope.taskList.length;
        var UndoneTasksNum = 0;
        for(var i = 0; i < $scope.taskList.length;i++){
            console.log($scope.taskList[i].done);
            console.log($scope.taskList[i].task);
            if($scope.taskList[i].done === 'false'){
                UndoneTasksNum += 1; 
            }
        }
        return UndoneTasksNum ;
    };

});

