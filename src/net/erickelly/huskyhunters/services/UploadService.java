package net.erickelly.huskyhunters.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;

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
	    HttpContext localContext = new BasicHttpContext();
	    HttpPost httpPost = new HttpPost(url);

	    try {
	        MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
	        
	        for (Uri imageUri : uriList) {
				//Bitmap bm = MediaStore.Images.Media.getBitmap(
	        		//this.getContentResolver(), imageUri);
				entity.addPart(imageUri.getLastPathSegment(), 
						new FileBody(new File(imageUri.toString())));				
	        }
	        httpPost.setEntity(entity);
	        HttpResponse response = httpClient.execute(httpPost, localContext);
	    } catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
	    }
	}
}
