JFXMaps
===========================
This project aims to deliver a stand alone Client for car navigation systems built on Java FX 2.0 and the Google Maps APIs.

#Features

##Navigation from Origin to Destination

##Visualization of the current Position

##Integration of Google's Maps API and Services

##Update Mechanism (only for Approach 2)
* separate service for downloading 25.000 image maps per day, to enable an up-to-date set of image maps
* update the application every week/month (depending on the overall amount of image maps), to load uptodate map data

##Further
* runnable multi-platform JAR

#HowTos

##Generate a Native Client
Run ..
<pre>
$ mvn clean jfx:native
</pre>


###Exe (will not work 4 now)
<pre>
target\jfx\native\bundles\
</pre>

##Executable JAR
<pre>
../target/jfx/native $ "c:\Program Files\Java\jdk1.8.0\bin"\java -jar org.devel.javafx.navigation.prototype-0.1-SNAPSHOT-jfx.jar
</pre>


#Projectenvironement
* Dependency and Build Management: Maven 3 project
* Versioning: Git
* IDE: Eclipse
* optional: Continous Integration: Jenkins with Plugins as Checkstyle, JaCoCo, ..

#Evaluation

##HTML5 vs Java FX for Car Navigation

<table>
 <tr>
  <th>Criteria</th>
  <th>HTML 5 and Javascript</th>
  <th>Java FX 2.0</th>
  <th>Sources</th>
  <td><a href=""></a></td>
 </tr>
 <tr>
  <td>Reuse Google Maps APIs</td>
  <td>+</td>
  <td>+</td>
  <td><a href=""></a></td>
 </tr>
 <tr>
  <td>Mobile Clients</td>
  <td>low effort (browser application)</td>
  <td>no support yet</td>
  <td><a href="http://jaxenter.de/news/6-Erfolgskriterien-fuer-JavaFX-auf-Android-iOS-168252">[1]</a></td>
 </tr>
 <tr>
  <td>Offline Application</td>
  <td>no (Maps Web Service Requests for calculating routes or getting the current position)</td>
  <td>no (Maps Web Service Requests for calculating routes or getting the current position)</td>
  <td><a href=""></a></td>
 </tr>
</table>

###Mobile Clients
With HTML5 the effort for implementing of a mobile client is rather low as it is a browser application. Thus an appropriate UI-Framework as TwitterBootstrap should do the trick.

The iOS and Android support for Java FX is done by the OpenJFX community, while there were only "good prototypes" committed yet (as described <a href="http://jaxenter.de/news/6-Erfolgskriterien-fuer-JavaFX-auf-Android-iOS-168252">here</a>). Current challenges are related to testing, graphics and performance issues, while the code for integration of Java FX with each platforms OS is fixed already.

"So those would be the main things from my perspective: performance testing,  functional / unit testing, native platform integration, and graphics."

##Google's Maps API and Services

###Google Web Services
Google comes with a hand full of web services delivering some fancy functional components to be used for navigation issues. 

####<a href="https://developers.google.com/maps/documentation/directions/?hl=fr-FR">The Google Directions API</a>
"The Google Directions API is a service that calculates directions between locations using an HTTP request. You can search for directions for several modes of transportation, include transit, driving, walking or cycling. Directions may specify origins, destinations and waypoints either as text strings (e.g. "Chicago, IL" or "Darwin, NT, Australia") or as latitude/longitude coordinates. The Directions API can return multi-part directions using a series of waypoints." It describes a navigation route in a very detailled way.

#####Use Cases 
* Calculate and draw alternative routes for navigation for a given origin and destination (address XOR latitudes and longitudes)

####<a href="https://developers.google.com/maps/documentation/distancematrix/?hl=fr-FR">The Google Distance Matrix API</a>
The Google Distance Matrix API is a service that provides travel distance and time for a matrix of origins and destinations. The information returned is based on the recommended route between start and end points, as calculated by the Google Maps API, and consists of rows containing duration and distance values for each pair.

#####Use Cases 
* Provide the travel distance and time for one (later a whole matrix of) origin(s) and destination(s) (address XOR latitudes and longitudes), to select the best route.

####<a href="https://developers.google.com/maps/documentation/elevation/?hl=fr-FR">The Google Elevation API</a>
The Google Elevation API provides you a simple interface to query locations on the earth for elevation data. Additionally, you may request sampled elevation data along paths.

#####Use Cases
* calculate elevation changes along routes, to present this information to the user

####<a href="https://developers.google.com/maps/documentation/geocoding/?hl=fr-FR">The Google Geocoding API</a>
Geocoding is the process of converting addresses (like "1600 Amphitheatre Parkway, Mountain View, CA") into geographic coordinates (like latitude 37.423021 and longitude -122.083739).

#####Use Cases
* re-/convert addresses to longitudes and latitudes, to place markers (or position the map)

####<a href="https://developers.google.com/maps/documentation/timezone/?hl=fr-FR">The Google Time Zone API</a>
The Google Time Zone API provides a simple interface to request the time zone for a location on the earth, as well as that location's time offset from UTC.

#####Use Cases
* Relate a navigation route markers towards absolute points in time, to present information about the point of time for route marks (i.e. start, intermediary and end marks of the route) regarding the time zone (i.e. when moving from one to another time zone during the travel).

####<a href="https://developers.google.com/maps/documentation/staticmaps/index?hl=fr-FR">Static Maps API V2 (for Business)</a>
"The Google Static Maps API lets you embed a Google Maps image on your web page without requiring JavaScript or any dynamic page loading. The Google Static Map service creates your map based on URL parameters sent through a standard HTTP request and returns the map as an image you can display on your web page."

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

#####Use Cases
* download the image maps, to 
  * display the street plan and the users current position, to let him know about the progress of the travel and to navigate him through the streets
  
####<a href="https://developers.google.com/maps/documentation/javascript/tutorial?hl=fr-FR">Google Maps JavaScript API v3</a>
Javascript API for easy integration of Google Maps with HTML5 concepts.

##Integration of Google's Maps API and Services

###Approach 1: Java FX WebView + Google Maps JavaScript API v3 (DONE)
- browser consumes much of resources
?  set and present GPS coordinates without internet connection (but GPS receiver)
+ easy implementation
-/+ offline functionality for image maps and routes with webdb exists as long as the browser is opened

###Approach 2: Java FX SVG Features + Static Maps API V2 (for Business) + Google Web Services (TODO)
In this approch the Google Static Maps API is used to download the images provided by google and buffer it in some way (i.e. using some persitence API as JPA). The other services may be used to request and draw routes from within Java FX.
+ no browser but a small and lightweight JAR
+ set and present GPS coordinates without internet connection (but GPS receiver)
- high implementation effort
+ offline functionality for image maps and routes while persisting them via usual database

##Building Customn Controls with JavaFX 8
Ways:
* Custom Controls via FXML + dynamic root + Override Control + css
* Library Style: Complete Own Control, Skin + Behaviour
  * examples
    * java7
      * <a href="http://www.guigarage.com/2012/11/custom-ui-controls-with-javafx-part-1/">Gui Garage: Control, Skin + Behaviour for Java 7</a>
* only adapt the Skin + CSS of the control without defining custom controls 
  * main benefit: reuse existing controls and just define the skin
  * examples: aquafx
  * links: 
    * <a href="http://download.java.net/jdk8/jfxdocs/javafx/scene/control/SkinBase.html">SkinBase</a>
      * Base class for defining skins (coloring, gradientsa, css inclusion)
    * <a href="https://wiki.openjdk.java.net/display/OpenJFX/UI+Controls+Architecture">UI Controls Architecture</a>
    * <a href="https://wiki.openjdk.java.net/display/OpenJFX/CSS+API+to+support+custom+UI+Controls">CSS API to support custom UI Controls</a>
    * CSS API to support custom UI Controls
      * Use Case: style properties via CSS

###UI Control Libraries

most important:

* aquafx (JDK8)
  * Mac OS Controls
  * built on java8 early access
  * supports Emoticons in a special FlowView
* JFXtras (JDK7&8) 
  * LEDs
* JideFX (JDK8)
* ConstrolsFX (JDK8)
* FXForm2  (JDK7)

further:

* GridFX
* Mondena
* ZenGuitar

see also <a href="http://www.oracle.com/technetwork/java/javafx/community/3rd-party-1844355.html">here</a>!
