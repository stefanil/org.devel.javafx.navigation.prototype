FXMaps
===========================
This project aims to deliver a stand alone Client for car navigation systems built on Java FX 2.0 and the Google Maps APIs.

#HTML5 vs Java FX for Car Navigation

<table>
 <tr>
  <th>Criteria</th>
  <th>HTML 5 and Javascript</th>
  <th>Java FX 2.0</th>
 </tr>
 <tr>
  <td>Reuse Google Maps APIs</td>
  <td>+</td>
  <td>+</td>
 </tr>
 <tr>
  <td></td>
  <td></td>
  <td></td>
 </tr>
</table>

#Features of the Client

##Integeration of Google's Maps API and Services

###Approach 1: <a href="https://developers.google.com/maps/documentation/staticmaps/index?hl=fr-FR">Static Maps API V2 (for Business)</a>
"The Google Static Maps API lets you embed a Google Maps image on your web page without requiring JavaScript or any dynamic page loading. The Google Static Map service creates your map based on URL parameters sent through a standard HTTP request and returns the map as an image you can display on your web page."

In this approch the API is used to download the images provided by google and buffer it in some way (i.e. using some persitence API as JPA).



* Useful API Parameters:
  * Location (center)
    * location for setting the center of the image map
    * location can be set via addresses xor latitudes and longitudes
  * Zoom Level
    * between 0 (the lowest zoom level, in which the entire world can be seen on one map) to 21+ (down to individual buildings) 
  * <a href="https://developers.google.com/maps/documentation/staticmaps/index?hl=fr-FR#Imagesizes">Image Sizes</a>
    * for highest free quality scaled to 2: 640x640 px
  * Image Formats
    * png, jpg and gif
  * Map Types
* <a href="https://developers.google.com/maps/faq?hl=en&csw=1#usage_pricing">Pricing:</a> no costs until there are 25.000 map requests to this api > this is why the update mechanism is necessary



##Update Mechanism
* separate service for downloading 25.000 image maps per day, to enable an up-to-date set of image maps
* update the application every week/month (depending on the overall amount of image maps), to load uptodate map data

#Projectenvironement
* Dependancy management: Maven 3 project
* Continous Integration: Jenkins with Plugins as Checkstyle, JaCoCo, ..
