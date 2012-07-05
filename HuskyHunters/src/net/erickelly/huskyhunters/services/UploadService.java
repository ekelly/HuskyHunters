package net.erickelly.huskyhunters.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;

public class UploadService extends IntentService {	
	public static final String PARAM_IN_MSG = "imsg";
    public static final String PARAM_OUT_MSG = "omsg";
	private static final String SERVER = "http://huskyhunter.com";

	public UploadService() {
		super("PictureUploadService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		List<String> imageUris = intent.getStringArrayListExtra(PARAM_IN_MSG);
		List<Uri> uriList = new ArrayList<Uri>();
		for (String image : imageUris) {
			uriList.add(Uri.parse(image));
		}
		post(SERVER, uriList);
	}
	
	public void post(String url, List<Uri> uriList) {
	    HttpClient httpClient = new DefaultHttpClient();

	    try {        
	        for (Uri imageUri : uriList) {
	        	HttpPost httpPost = new HttpPost(url);
		        MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
	        	entity.addPart("type", new StringBody("photo"));
				entity.addPart(imageUri.getLastPathSegment(), 
						new FileBody(new File(imageUri.toString())));
				httpPost.setEntity(entity);
				httpClient.execute(httpPost, new ResponseHandler<Object>() {
			        public Object handleResponse(HttpResponse response) throws 
			        		ClientProtocolException, IOException {
			        	HttpEntity respEntity = response.getEntity();
			        	String responseString = EntityUtils.toString(respEntity);
			        	// do something with the response string
			        	
			        	// Delete the row corresponding to the uri in the database
			        	
			        	// Update the clue table
			        	return null;
			        }
			    });
	        }
	    } catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
	    }
	}
}
