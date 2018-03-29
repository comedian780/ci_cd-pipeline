
angular.
  module('parcelConfig').
  component('parcelPrice', {
	  transclude: true,
	  template:
		 
		  '<form class="w3-container">'+
	
		  '<div class="w3-panel w3-teal" ng-click="$ctrl.calcPrice($ctrl.parcelprice)" style="cursor: pointer;">'+
		    '<h3 class="w3-opacity" >Gesamtpreis berechnen: {{$ctrl.parcelprice.price}} EUR</h3>'+
		  '</div>'+
		  '</form>'+
		  '<br />'+
		  '</div>' ,
    	
    	
    controller: function ParcelPriceController($rootScope, $http) {
    	
    	
    	this.parcelprice = {
    		parcelsize : $rootScope.parcelsize,
    		parceladdresses: $rootScope.parceladdresses,
    		parceloptions: $rootScope.parceloptions,
    		price: this.price
    	};
      
      
 
    }
  });
  
 
  