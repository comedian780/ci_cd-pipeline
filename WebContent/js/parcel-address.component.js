
angular.
  module('parcelConfig').
  component('parcelAddress', {
	  transclude: true,
	  template:
    	'<div class="w3-panel w3-card-2">'+
    		'<div class="w3-container w3-teal">'+
    			'<h2>Lieferadresse</h2>'+
    		'</div>' +
    		'<form class="w3-container">'+
    	  '<div class="w3-cell">'+
    	  '<h3>Empfänger</h3>'+
    	  
    	  '<div class="w3-cell">'+
   		  '<label>Vor- und Nachname</label>'+
   		  '<input class="w3-input" id="cfg-recp-name" type="text" ng-model="$ctrl.parcel.recipient.name">'+
   		  '</div>'+
   		  
   		 '<div class="w3-cell">'+
   		  '<label>Firma</label>'+
   		  '<input class="w3-input" id="cfg-recp-firm" type="text" ng-model="$ctrl.parcel.recipient.firm">'+
   		  '</div>'+
   		 
   		  '<div class="w3-row">'+
   		  	'<div class="w3-cell">'+
   		  	'<label>Straße</label>'+
   		  	'<input class="w3-input" id="cfg-recp-street" type="text" ng-model="$ctrl.parcel.recipient.street">'+
   		  	'</div>'+
   		  	'<div class="w3-cell">'+
   		  	'<label>Nr.</label>'+
		  	'<input class="w3-input" id="cfg-recp-nr" type="text" ng-model="$ctrl.parcel.recipient.nr">'+
		  	'</div>'+
   		  '</div>'+
	   	  '<div class="w3-row">'+
			 '<div class="w3-cell">'+
			  	'<label>PLZ</label>'+
			  	'<input class="w3-input" id="cfg-recp-plz" type="text" ng-model="$ctrl.parcel.recipient.plz">'+
			 '</div>'+
			  	'<div class="w3-cell">'+
			  	'<label>Ort</label>'+
			  	'<input class="w3-input" id="cfg-recp-city" type="text" ng-model="$ctrl.parcel.recipient.city">'+
			 '</div>'+
		  '</div>'+
		  '<div class="w3-row">'+
			 '<div class="w3-cell">'+
			  '<label>Land</label>'+
			  '<input class="w3-input" id="cfg-recp-country" type="text" ng-model="$ctrl.parcel.recipient.country">'+
			  '</div>'+
		  '</div>'+
		  '</div>'+
		  
		  '<div class="w3-cell">'+
		 ' &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;'+
		  '</div>'+
		  
		  
		  '<div class="w3-cell">'+
    	  '<h3>Absender</h3>'+
    	  
    	  '<div class="w3-cell">'+
   		  '<label>Vor- und Nachname</label>'+
   		  '<input class="w3-input" id="cfg-sent-name" type="text" ng-model="$ctrl.parcel.sender.name">'+
   		  '</div>'+
   		  
   		 '<div class="w3-cell">'+
   		  '<label>Firma</label>'+
   		  '<input class="w3-input" id="cfg-sent-firm" type="text" ng-model="$ctrl.parcel.sender.firm">'+
   		  '</div>'+
   		 
   		  '<div class="w3-row">'+
   		  	'<div class="w3-cell">'+
   		  	'<label>Straße</label>'+
   		  	'<input class="w3-input" id="cfg-sent-steet" type="text" ng-model="$ctrl.parcel.sender.street">'+
   		  	'</div>'+
   		  	'<div class="w3-cell">'+
   		  	'<label>Nr.</label>'+
		  	'<input class="w3-input" id="cfg-sent-nr" type="text" ng-model="$ctrl.parcel.sender.nr">'+
		  	'</div>'+
   		  '</div>'+
	   	  '<div class="w3-row">'+
			 '<div class="w3-cell">'+
			  	'<label>PLZ</label>'+
			  	'<input class="w3-input" id="cfg-sent-plz" type="text" ng-model="$ctrl.parcel.sender.plz">'+
			 '</div>'+
			  	'<div class="w3-cell">'+
			  	'<label>Ort</label>'+
			  	'<input class="w3-input" id="cfg-sent-city" type="text" ng-model="$ctrl.parcel.sender.city">'+
			 '</div>'+
		  '</div>'+
		  '</div>'+
   		  '</form>'+
   		  '</div>',
    	
    controller: function ParcelAddressController($rootScope) {
    this.parcel = {
    	recipient: {name: '', street: '', nr: '', plz: '', city: '', country: ''},
    	sender: {name: '', street: '', nr: '', plz: '', city: ''},
    	size: '',
    	price: 0.0
    	
    };
      //$rootScope.sender = this.parcel.sent;
      //$rootScope.recipient = this.parcel.recipient;
      $rootScope.parceladdresses = this.parcel;
    }
  });
  
 
  