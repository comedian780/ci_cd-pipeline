package api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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
  public String size(String json) {
	  Gson gs = new Gson();
	  Package parcel = gs.fromJson(json, Package.class);
	  double gurt = parcel.length + 2 * parcel.height + 2 * parcel.width;
	  MysqlCon con = new MysqlCon();
	  parcel.size = con.getSize(gurt);
	  
	  return gs.toJson(parcel, Package.class);
  }
}