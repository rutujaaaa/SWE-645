package restWebService;

import javax.ws.rs.core.MediaType;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/identifycitystate")
public class identifycitystate 
{
	@GET
	@Path("{zipcode}")
	@Produces({MediaType.TEXT_PLAIN})
	public String getCityState(@PathParam("zipcode") Double zipcode)
	{
		//return "Hello Jersey";
		String result = null; 

		if(zipcode == 22312)
		{
			result = "Alexandria" + ":" + "VA"; 
		}
		else if(zipcode == 20148)
		{
			result = "Ashburn" + ":" + "VA"; 
		}else if(zipcode == 22030)
		{
			result =  "Fairfax" + ":" + "VA"; 
		}else if(zipcode == 22301)
		{
			result = "Tysons Corner" + ":" + "MD"; 
		}

		return result;
	}
}
