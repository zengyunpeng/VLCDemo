package com.example.myapplication.majorkernelpanic.spydroid.api;


import com.example.myapplication.majorkernelpanic.streaming.rtsp.RtspServer;

public class CustomRtspServer extends RtspServer {
	public CustomRtspServer() {
		super();
		// RTSP server disabled by default
		mEnabled = false;
	}
}

