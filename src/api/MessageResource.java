package api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
  public String message()
  {
	Package parcel = new Package();
	parcel.height = 1;
	parcel.length = 2;
	parcel.width = 3;
	parcel.size = "S";
    return parcel.toString();
  }
  
  @POST
  public Response size(String json) {
	  Gson gs = new Gson();
	  Package parcel = gs.fromJson(json, Package.class);
	  double gurt = parcel.length + 2 * parcel.height + 2 * parcel.width;
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
}