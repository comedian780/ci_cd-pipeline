
angular.
  module('parcelConfig').
  component('optionList', {
	  transclude: true,
	  template:
    	'<div class="w3-panel w3-card-2">'+
    		'<div class="w3-container w3-teal">'+
    			'<h2>Lieferoptionen</h2>'+
    		'</div>' +
	    	'<div class="w3-cell-row" style="width:75%">'+
	    		'<form class="w3-container" >'+
	    			'<div class="w3-row">'+
	    			'<div class="w3-col s6">'+
	    				'<h3>Absender-Optionen</h3>'+
		    			'<div class="w3-row" ng-repeat="so in $ctrl.sentOptions">'+
		    				'<div class="w3-col s9">'+
		    					'<p><input class="w3-radio" ng-click="$ctrl.checkSentOption($ctrl.selectedSentOption)" type="radio" name="sentoption" data-ng-value="{{so}}" ng-model="$ctrl.selectedSentOption">'+
		    					'<label>Sendeoption</label></p>'+
		    				'</div>'+
		    				'<div class="w3-col s3"><p>{{so.price}} EUR</p></div>'+
		    			'</div>'+
	    			'</div>'+
	    			'<div class="w3-col s6">'+
	    				'<h3>Lieferoptionen</h3>'+
		    			'<div class="w3-row" ng-repeat="do in $ctrl.deliveryOptions">'+
	    				'<div class="w3-col s9">'+
	    					'<p><input class="w3-radio" ng-click="$ctrl.checkDeliveryOption($ctrl.selectedDeliveryOption)" type="radio" name="deliveryoption" data-ng-value="{{do}}" ng-model="$ctrl.selectedDeliveryOption">'+
	    					'<label>Lieferoption</label></p>'+
	    				'</div>'+
	    				'<div class="w3-col s3"><p>{{do.price}} EUR</p></div>'+
	    				'</div>'+
	    			'</div>'+
	    			'</div>'+
	    		'</form>'+
	    	'</div>'+
		'</div>',
    	
    controller: function ParcelOptionController($rootScope, $http) {
    	this.selectedSentOption={
    			name: '',
    			price: 0.00,
    			checked: false
    	};
    	
    	this.selectedDeliveryOption={
    			name: '',
    			price: 0.00,
    			checked: false
    	};
    	
        //$rootScope.parceloptions = [this.selectedSentOption, this.selectedDeliveryOption];
        $rootScope.parceloptions = {options:[this.selectedSentOption, this.selectedDeliveryOption], price: 0.0};
    	this.sentOptions = [
        {
          name: 'Sendung@Home',
          price: 0.00,
          checked: false
        }, {
          name: 'Sendung@Shop',
          price: 0.00,
          checked: false
        }
      ];
     
    	this.deliveryOptions = [
            {
              name: 'Abgabe in Paketshop',
              price: 0.00,
              checked: false
            }, {
              name: 'Abholung an der Haustüre',
              price: 0.00,
              checked: false
            }
          ];
   
      this.checkSentOption=function($opt){
    	  $opt.checked = true;
    	  $rootScope.parceloptions.options[0] ={name: $opt.name, checked: $opt.checked};
      }
      
      this.checkDeliveryOption=function($opt){
    	  $opt.checked = true;
    	  $rootScope.parceloptions.options[1] = {name: $opt.name, checked: $opt.checked};
      }
      
   
 
    }
  });
  
 
  