package View;/*
  Author: Hisham Maged
  Date : 7/11/2019
  Project Name : A Map with visualized EarthQuakes data
*/

import Controller.MarkerHandler;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.utils.MapUtils;
import java.net.URL;
import java.util.List;
import processing.core.PApplet;

public class EarthQuakeLiveDataMap extends PApplet {

  // Map reference
  private UnfoldingMap map;
  // used markers reference
  private List<Marker> markers;
  /*
  * URLS for live earth quake data
  * past 30 days
  * past 7 days
  * past day
  * past hour
  * */
  private static URL LIVE_EARTHQUAKE_DATA_PAST_30_DAYS;
  private static URL LIVE_EARTHQUAKE_DATA_PAST_7_DAYS;
  private static URL LIVE_EARTHQUAKE_DATA_PAST_DAY;
  private static URL LIVE_EARTHQUAKE_DATA_PAST_HOUR;

  // static initializer block to give links to URL fields
  static{
    try {
      LIVE_EARTHQUAKE_DATA_PAST_30_DAYS = new URL("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_month.atom");
      LIVE_EARTHQUAKE_DATA_PAST_7_DAYS = new URL("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom");
      LIVE_EARTHQUAKE_DATA_PAST_DAY = new URL("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_day.atom");
      LIVE_EARTHQUAKE_DATA_PAST_HOUR = new URL("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_hour.atom");
    }catch(Exception ex)
    {
      ex.printStackTrace();
    }

  }
  // main setup method, the looks of the window
  public void setup()
  {
    size(1280,720,OPENGL);
    initMap();
  }
  // the draw loop, invoked due to any change or loop (determined in backend of Processing library)
  public void draw()
  {
    background(30);
    this.map.draw();
    addLegend();
  }

  /*
  * a method that initializes the map to be of Microsoft Road Provider Map
  * and adds interactivity to the map
  * and zooms to level 2
  * and uses the default XML File and gets the markers of it using the MarkerHandler Class
  * and adds those markers to the map
  * */
  private void initMap()
  {
    this.map = new UnfoldingMap(this,200,50,1020,620,new Microsoft.RoadProvider());
    MapUtils.createDefaultEventDispatcher(this,this.map);
    /*
    * change first parameter in makeEarthQuakeMarkers
    * no 1st parameter >> FileChooser to choose ATOM file
    * String >> filePath
    * File >> Atom file object
    * URL >> live data url
    * */
    this.markers = MarkerHandler.makeEarthQuakeMarkers("./data/2.5_week.atom",4.0,5.0,10.0,color(0,0,255),color(255,255,0),color(255,0,0));
    this.map.addMarkers(this.markers);
    this.map.zoomLevel(2);
  }

  /*
  * adds Legend on right hand side with Magnitude and
  * their respected circle color and size
  * */
  private void addLegend()
  {
    fill(color(230));
    rect(30,50,140,250);
    textSize(13);
    fill(color(0));
    text("Legend",65,75);
    text("5.0 + Magnitude",60,120);
    text("4.0 + Magnitude",60,170);
    text("Below 4.0",60,220);
    fill(color(255,0,0));
    ellipse(45,115,18,18);
    fill(color(255,255,0));
    ellipse(45,165,9,9);
    fill(color(255,0,0));
    ellipse(45,215,4,4);
  }





}
