import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.core.MultivaluedMap;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;


public class RestClient {
	Gson gson = new Gson();
	Client client = Client.create();

	<T> T getServerData(String url, Map<String, String> params, Class<T> clazz) {
		 
		WebResource webResource = client.resource(url);
 
		MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
		for (Entry<String, String> entry: params.entrySet()) {
			formData.add(entry.getKey(), entry.getValue());
		}

		ClientResponse response = webResource.accept("application/x-www-form-urlencoded")
                   .post(ClientResponse.class, formData);
 
		String output = response.getEntity(String.class);
		if (response.getStatus() != 200) {
		   throw new RuntimeException("Failed : HTTP error code : "
			+ response.getStatus() + " - " + output);
		}
 
//		System.out.println("response: " + output);
		
		return gson.fromJson(output, clazz);
	}
}
