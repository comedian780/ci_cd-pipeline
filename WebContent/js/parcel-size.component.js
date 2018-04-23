angular.
  module('parcelConfig').
  component('parcelSize', {
	  transclude: true,
	  template:'<div class="w3-panel w3-card-2">'+
      '<div class="w3-container w3-teal">'+
        '<h2>Paketgröße</h2>'+
      '</div>'+
    	'<form class="w3-container">'+
    '<div class="w3-cell">'+
    '<label>Länge</label>'+
    '<input class="w3-input" id="cfg-size-length" type="text" ng-model="$ctrl.parcel.size.length">'+
    '</div>'+
    '</br>'+
    '<div class="w3-cell">'+
    '<label>Breite</label>'+
    '<input class="w3-input" id="cfg-size-height" type="text" ng-model="$ctrl.parcel.size.width">'+
    '</div>'+
    '</br>'+
    '<div class="w3-cell">'+
    '<label>Tiefe</label>'+
    '<input class="w3-input" id="cfg-size-depth" type="text" ng-model="$ctrl.parcel.size.height">'+
    '</div>'+
    '</br>'+
    '<div class="w3-button w3-teal" ng-click="$ctrl.calcSize($ctrl.parcel)" style="cursor: pointer;">'+
      'Paketgröße berechnen</div>'+
    '<div class="w3-cell" >'+
      '<label id="cfg-category">Paketgröße: {{$ctrl.parcel.size.category}}</label>'+
    '</div>'+
    '</form>'+
    '</div>',


  controller: function ParcelSiceController($rootScope, $http) {

    this.parcel = {
      size: {length: '0', height: '0', width: '0', category: 'S'}

    };

    $rootScope.parcelsize = this.parcel;

    this.calcSize=function($parcel){
      /*pak_cat = "";
      gurtmass = +$parcel.size.length + 2 * $parcel.size.height + 2 * $parcel.size.depth;

      if(gurtmass >= 80 && gurtmass <= 300){
        pak_cat = "XL";
      }else if (gurtmass >= 65 && gurtmass <= 80) {
        pak_cat = "L";
      }else if (gurtmass >= 50 && gurtmass <= 65) {
        pak_cat = "M";
      }else if (gurtmass >= 35 && gurtmass <= 50) {
        pak_cat = "S";
      }else if (gurtmass >= 0 && gurtmass <= 35){
        pak_cat = "XS";
      }else{
        pak_cat = "";
      }*/

      var parameter = JSON.stringify($parcel.size);
      var url = "http://localhost/api/parcel/size";
      $http.post(url, parameter).then(function(data, status, headers, config) {
          // this callback will be called asynchronously
          // when the response is available
          ret_data = angular.fromJson(data);
          $parcel.size.category=ret_data['data']['size'];
        });
    }

  }
});
