package net.erickelly.huskyhunters.services;

import org.json.JSONArray;

import android.net.Uri;

public interface IHttpClueHandler {
	
	/**
	 * Get clues from the server
	 * @param groupHash The hash indicating which group's clues to download
	 * @return JSON representation of the data in the form of a string
	 * @author eric
	 */
	public JSONArray requestClues(String url);
}
