package api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

@Path( "size" )
@Consumes( MediaType.APPLICATION_JSON  )
@Produces( MediaType.APPLICATION_JSON  )
public class MessageResource
{
  @GET 
  public Response message()
  {
	Package parcel = new Package();
	parcel.height = 1;
	parcel.length = 2;
	parcel.width = 3;
	parcel.size = "S";
	Gson gs = new Gson();
    //return parcel.toString();
	return Response.ok(gs.toJson(parcel, Package.class)).header("Access-Control-Allow-Origin", "*")
		      .header("Access-Control-Allow-Credentials", "true")
		      .header("Access-Control-Allow-Headers",
		         "origin, content-type, accept, authorization")
		       .header("Access-Control-Allow-Methods",
		         "GET, POST, PUT, DELETE, OPTIONS, HEAD").build();
  }
  
  @POST
  public Response size(String json) {
	  Gson gs = new Gson();
	  Package parcel = gs.fromJson(json, Package.class);
	  int fakt_len = 2;
	  int fakt_hei = 2;
	  int fakt_wid = 2;
	  
	  if(parcel.length > parcel.height && parcel.length > parcel.width) {
		  fakt_len = 1; 
	  }else if(parcel.height > parcel.length && parcel.height > parcel.width){
		  fakt_hei = 1;
	  }else {
		  fakt_wid = 1;
	  }
	  
	  double gurt = fakt_len * parcel.length + fakt_hei * parcel.height + fakt_wid * parcel.width;
	  MysqlCon con = new MysqlCon();
	  parcel.size = con.getSize(gurt);
	  
	  //return gs.toJson(parcel, Package.class);
	  return Response.ok(gs.toJson(parcel, Package.class)).header("Access-Control-Allow-Origin", "*")
      .header("Access-Control-Allow-Credentials", "true")
      .header("Access-Control-Allow-Headers",
         "origin, content-type, accept, authorization")
       .header("Access-Control-Allow-Methods",
         "GET, POST, PUT, DELETE, OPTIONS, HEAD").build();
  }
  
  @OPTIONS
  public Response getOptions() {
    return Response.ok()
      .header("Access-Control-Allow-Origin", "*")
      .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
      .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
  }
}