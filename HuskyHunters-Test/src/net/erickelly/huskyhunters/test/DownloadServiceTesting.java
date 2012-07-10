package net.erickelly.huskyhunters.test;

import net.erickelly.huskyhunters.services.DownloadService;
import android.content.Intent;
import android.test.ServiceTestCase;

public class DownloadServiceTesting extends ServiceTestCase<DownloadService> {
	
	DownloadServiceTesting() {
		super(DownloadService.class);
	}
	
	@Override
    protected void setUp() throws Exception {
        super.setUp();
    }
	
	/**
     * The name 'test preconditions' is a convention to signal that if this
     * test doesn't pass, the test case was not set up properly and it might
     * explain any and all failures in other tests.  This is not guaranteed
     * to run before other tests, as junit uses reflection to find the tests.
     */
    public void testPreconditions() {
    }
    
    /**
     * Test basic startup/shutdown of Service
     */
    public void testStartable() {
        Intent startIntent = new Intent();
        startIntent.setClass(getContext(), DownloadService.class);
        startService(startIntent); 
    }
}
