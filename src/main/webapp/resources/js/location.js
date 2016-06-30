/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
ymaps.ready(init);
        var myMap, 
            myPlacemark;

        function init(){ 
            myMap = new ymaps.Map ("map", {
                center: [ymaps.geolocation.latitude, ymaps.geolocation.longitude],
                zoom: 7
            }); 
            
            myPlacemark = new ymaps.Placemark([ymaps.geolocation.latitude, ymaps.geolocation.longitude], {
                hintContent: ymaps.geolocation.country + ' : ' +ymaps.geolocation.city,
                balloonContent: ymaps.geolocation.country + ' : ' +ymaps.geolocation.city
            });
            
            myMap.geoObjects.add(myPlacemark);
        }
