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
	Parcel parcel = new Parcel();
	parcel.height = 1;
	parcel.length = 2;
	parcel.width = 3;
	parcel.size = "S";
	Gson gs = new Gson();
    //return parcel.toString();
	return Response.ok(gs.toJson(parcel, Parcel.class)).header("Access-Control-Allow-Origin", "*")
		      .header("Access-Control-Allow-Credentials", "true")
		      .header("Access-Control-Allow-Headers",
		         "origin, content-type, accept, authorization")
		       .header("Access-Control-Allow-Methods",
		         "GET, POST, PUT, DELETE, OPTIONS, HEAD").build();
  }

  public double getGurtSize(Parcel parcel) {
	  int fakt_len = 2;
	  int fakt_hei = 2;
	  int fakt_wid = 2;

	  parcel.length = Math.abs(parcel.length);
	  parcel.height = Math.abs(parcel.height);
	  parcel.width = Math.abs(parcel.width);

	  if(parcel.length > parcel.height && parcel.length > parcel.width) {
		  fakt_len = 1;
	  }else if(parcel.height > parcel.length && parcel.height > parcel.width){
		  fakt_hei = 1;
	  }else {
		  fakt_wid = 1;
	  }

	  return fakt_len * parcel.length + fakt_hei * parcel.height + fakt_wid * parcel.width;
  }

  @POST
  public Response size(String json) {
	  Gson gs = new Gson();
	  Parcel parcel = gs.fromJson(json, Parcel.class);


	  double gurt = this.getGurtSize(parcel);
	  MysqlCon con = new MysqlCon();
	  parcel.size = con.getSize(gurt);

	  //return gs.toJson(parcel, Package.class);
	  return Response.ok(gs.toJson(parcel, Parcel.class)).header("Access-Control-Allow-Origin", "*")
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
