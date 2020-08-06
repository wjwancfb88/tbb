package com.tubaoapi.modules.utils;

public class LatLngUtils {   
       
    private static final double pi = Math.PI;   
    private static final double earth_radius = 6378137;   
    private static final double rad = Math.PI / 180.0;   
  
    //@see http://snipperize.todayclose.com/snippet/php/sql-query-to-find-all-retailers-within-a-given-radius-of-a-latitude-and-longitude--65095/    
    //the circumference of the earth is 24,901 miles.   
    //24,901/360 = 69.17 miles / degree     
    /**  
     * @param raidus 单位米  
     * return minlat,minlng,maxlat,maxlng  
     */  
    public static double[] getaround(double lat,double lng,int raidus){   
           
        double latitude = lat;   
        double longitude = lng;   
           
        double degree = (24901*1609)/360.0;   
        double raidusmile = raidus;   
           
        double dpmlat = 1/degree;   
        double radiuslat = dpmlat*raidusmile;   
        double minlat = latitude - radiuslat;   
        double maxlat = latitude + radiuslat;   
           
        double mpdlng = degree*Math.cos(latitude * (pi/180));   
        double dpmlng = 1 / mpdlng;   
        double radiuslng = dpmlng*raidusmile;   
        double minlng = longitude - radiuslng;   
        double maxlng = longitude + radiuslng;   
        //system.out.println(&quot;[&quot;+minlat+&quot;,&quot;+minlng+&quot;,&quot;+maxlat+&quot;,&quot;+maxlng+&quot;]&quot;);   
        return new double[]{minlat,maxlat,minlng,maxlng};   
    }   
       
    /**  
     * 根据两点间经纬度坐标（double值），计算两点间距离，单位为米  
     * @param lng1  
     * @param lat1  
     * @param lng2  
     * @param lat2  
     * @return  
     */  
    public static double getdistance(double lng1, double lat1, double lng2, double lat2)   
   {   
      double radlat1 = lat1*rad;   
       double radlat2 = lat2*rad;   
       double a = radlat1 - radlat2;   
       double b = (lng1 - lng2)*rad;   
       double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +   
        Math.cos(radlat1)*Math.cos(radlat2)*Math.pow(Math.sin(b/2),2)));   
       s = s * earth_radius;   
       s = Math.round(s * 10000) / 10000; 
       return s;   
    }   
    
    
    public static void main(String[] args){   
//        double lat1 = 34.264648;   
//        double lon1 = 108.952736;   
//           
//       int radius = 1000;   
//        //[34.25566276027792,108.94186385411045,34.27363323972208,108.96360814588955]   
//        getaround(lat1,lon1,radius);   
//           
//        //911717.0   34.264648,108.952736,39.904549,116.407288   
//        double dis = getdistance(108.952736,34.264648,116.407288,39.904549); 
//        System.out.println(dis); 
    	Double centerLat=null;
		Double centerLon=null;
//		getaround(centerLat, centerLon, 1000);
    	getdistance(centerLat, centerLon, 0.21, 0.33);
    }   
}  