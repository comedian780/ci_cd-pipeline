package test;

import org.junit.jupiter.api.Test;

import static java.time.Duration.ofMillis;
import static java.time.Duration.ofMinutes;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import api.*;

 
class TestJunit {
 
    
    @Test
    void packageTest() {
    	String expectedResult = "{ length: 25.0, width: 24.0, height: 7.3, size: S }";
    	double len = 25;
    	double wid = 24;
    	double hei = 7.3;
    	String size = "S";
    	
        Parcel parcel = new api.Parcel();
        parcel.size = size;
        parcel.length = len;
        parcel.height = hei;
        parcel.width = wid;
        
        assertEquals( expectedResult, parcel.toString());
    }
    
    @Test
    void dbValidTest() {
    	MysqlCon mysql = new MysqlCon();
    	
    	String expectedResult = "L";
    	String res = mysql.getSize(71.0f);
    	
    	assertEquals( expectedResult, res);    	
    }
    
    @Test
    void postSizeTest() {
    	String expectedResult = "{\"length\":25.0,\"width\":24.0,\"height\":7.3,\"size\":\"XL\"}";
    	MessageResource meRes = new MessageResource();
    	
    	String res = meRes.size("{ length: 25.0, width: 24.0, height: 7.3, size: ' ' }").getEntity().toString();
    	
    	assertEquals(expectedResult, res);
    }
    
    
    @Test
    void gurtSizeValidTest() {
    	double expectedResult = 87.0f;
    	
    	double len = 25;
    	double wid = 24;
    	double hei = 7;
    	
    	Parcel parcel = new api.Parcel();
        parcel.length = len;
        parcel.height = hei;
        parcel.width = wid;
        
        MessageResource meRes = new MessageResource();
         
        double res = meRes.getGurtSize(parcel);
    	
    	assertEquals(expectedResult, res);
    }
    
    
    @Test
    void gurtSizeInvalidTest() {
    	double expectedResult = 87.0f;
    	
    	double len = -25;
    	double wid = 24;
    	double hei = 7;
    	
    	Parcel parcel = new api.Parcel();
        parcel.length = len;
        parcel.height = hei;
        parcel.width = wid;
        
        MessageResource meRes = new MessageResource();
         
        double res = meRes.getGurtSize(parcel);
    	
    	assertEquals(expectedResult, res);
    }
    
    
    @Test
    void dbInvalidTest() {
    	/*
    	 assertThrows(SQLException.class,
                ()->{
                	MysqlCon mysql = new MysqlCon();
                	mysql.getSize(-33.0);
                });
                */
    	
    	MysqlCon mysql = new MysqlCon();
    	
    	String expectedResult = "";
    	String res = mysql.getSize(-33.7f);
    	
    	assertEquals( expectedResult, res); 
    }
    
}