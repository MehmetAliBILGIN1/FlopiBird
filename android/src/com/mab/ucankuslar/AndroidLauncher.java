package com.mab.ucankuslar;

import android.net.wifi.aware.PublishConfig;
import android.os.Bundle;
import android.webkit.WebView;
import android.R;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class AndroidLauncher extends AndroidApplication {

//	public AdView mAdView;
//	private InterstitialAd mInterstitialAd;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	MobileAds.initialize(this, "ca-app-pub-4055756653297968~7811372494");

//		mInterstitialAd = new InterstitialAd(this);
//		mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
//
////		AdRequest adRequest = new AdRequest.Builder().build();
////		mAdView = findViewById(R.id.AdView);
////		mAdView.loadAd(adRequest);
//
//		mInterstitialAd.setAdListener(new AdListener() {
//			@Override
//			public void onAdClosed() {
//				// Load the next interstitial.
//				mInterstitialAd.loadAd(new AdRequest.Builder().build());
//			}
//
//		});

//		WebView view = this.findViewById(R.id.webView);
//		view.loadAd(adRequest);

//		AdView adView = new AdView(this);
//		adView.setAdSize(AdSize.SMART_BANNER);

//		mAdView.setAdListener(new AdListener() {
//			@Override
//			public void onAdLoaded() {
//				// Code to be executed when an ad finishes loading.
//			}
//
//			@Override
//			public void onAdFailedToLoad(int errorCode) {
//				// Code to be executed when an ad request fails.
//			}
//
//			@Override
//			public void onAdOpened() {
//				// Code to be executed when an ad opens an overlay that
//				// covers the screen.
//			}
//
//			@Override
//			public void onAdLeftApplication() {
//				// Code to be executed when the user has left the app.
//			}
//
//			@Override
//			public void onAdClosed() {
//				// Code to be executed when when the user is about to return
//				// to the app after tapping on an ad.
//			}
//		});
	}
}





