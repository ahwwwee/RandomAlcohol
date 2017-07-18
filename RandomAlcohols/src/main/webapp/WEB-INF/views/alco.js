var app = angular.module('AlcoApp', []);

app.service("apiAccess", function($http) {
	this.getRandomAlcohol = function(type) {
		return $http.post("/alcohol/random", {
			"type" : type
		});
	}

	this.getRandomAlcoholAll = function() {
		return $http.get("/alcohol/random");
	}
})

app.controller('randomAlcohol', function($scope, apiAccess) {
	$scope.typesArray = [ "All", "Wine", "Beer", "Something Harder" ]
	$scope.AlcoBool = false;
	$scope.alcohol = {
		name : "Beer",
		details : "drinkable",
		percent : 10,
		location : "somewhere",
		type : "Beer"
	}

	$scope.random = function(type) {
		if (type == "All") {
			var data = apiAccess.getRandomAlcoholAll();
			data.success(function(result) {
				$scope.AlcoBool = true;
				$scope.alcohol = result;
			}).error(function(err) {
				console.log(err);
				alert("There was a wee issue, please try again.");
			})
		} else {
			var data = apiAccess.getRandomAlcohol(type);
			data.success(function(result) {
				$scope.AlcoBool = true;
				console.log(result)
				$scope.alcohol = result;
			}).error(function(err) {
				console.log(err);
				alert("There was a wee issue, please try again.");
			})
		}
	}
})