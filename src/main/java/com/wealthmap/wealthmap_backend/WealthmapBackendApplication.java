package com.wealthmap.wealthmap_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WealthmapBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(WealthmapBackendApplication.class, args);
	}

}
 //Notes :
//✅ Filter by Bounding Box
//“Show me properties inside the current map view.”
//→ Needed for your Leaflet map when zooming/panning
//
//✅ Radius Search (Near Me)
//“Show all properties within 10km of this point.”
//→ Great for “Explore nearby assets” kind of features
//
//✅ Clustering & Aggregation
//“Group properties together for heatmaps or markers.”
//→ Perfect for efficient map rendering
//
//✅ Polygon Search
//“User draws a shape on map → show what’s inside.”
//→ This is Zillow-level pro stuff 👑
//
//✅ Distance Sorting
//“Sort properties by how close they are to me or to a company HQ.”