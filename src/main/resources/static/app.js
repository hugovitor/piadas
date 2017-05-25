var app = angular.module("piadaapp",['ngRoute']);


app.controller("ListarController", function($scope, $http){
    $http.get("http://piadas.herokuapp.com/piada")
                .then(function(result){
                   $scope.buscando=false;
                   console.log(result);
                   $scope.piadas = result.data;
                },function(erro){
                   $scope.buscando=false;
                   console.log(erro);
                })
})

app.config(function($routeProvider){
     $routeProvider
             .when('/cadastro', {
                 templateUrl: 'cadastro.html'
             })
             .when('/listar',{
                 templateUrl: 'listar.html',
                 controller: 'ListarController'
             }) .otherwise({
            templateUrl: 'home.html'
        });
             /*.otherwise({
                 templateUrl: 'html/home.html'
             });  */  
});



app.controller("piadactrl", function($scope, $http){
    
    
    $scope.listar = function(){
        $scope.buscando = true;
        $http.get("/piada")
                .then(function(result){
                   $scope.buscando=false;
                   console.log(result);
                   $scope.piadas = result.data;
                },function(erro){
                   $scope.buscando=false;
                   console.log(erro);
                })
        
    }
    
    $scope.salvar = function(piada){
        $scope.enviando=true;
        console.log(piada);
        $http.put("/piada",piada)
                .then(function(result){
                   $scope.enviando=false;
                   $scope.mensagem="Piada cadastrada com sucesso";
                   console.log(result);
                },  function(erro){
                   $scope.enviando=false;
                   $scope.mensagem="Erro no cadastro."
                   console.log(erro);
                });
        console.log("foi!");
        
    }
    
});