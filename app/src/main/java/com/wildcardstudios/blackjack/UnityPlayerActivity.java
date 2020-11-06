package com.wildcardstudios.blackjack;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.adincube.sdk.AdinCube;
import com.adincube.sdk.AdinCubeBannerEventListener;
import com.adincube.sdk.AdinCubeInterstitialEventListener;
import com.adincube.sdk.AdinCubeRewardedEventListener;
import com.adincube.sdk.BannerView;
import com.mopub.common.MoPub;
import com.mopub.common.SdkConfiguration;
import com.mopub.common.SdkInitializationListener;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubView;

public class UnityPlayerActivity extends AppCompatActivity {

    private MoPubView moPubView;
    private MoPubView.BannerAdListener bannerAdListener = new MoPubView.BannerAdListener() {
        @Override
        public void onBannerLoaded(@NonNull MoPubView banner) {
            Toast.makeText(UnityPlayerActivity.this, "Loaded banner successfully!",
                    Toast.LENGTH_LONG).show();
        }

        @Override
        public void onBannerFailed(MoPubView banner, MoPubErrorCode errorCode) {
            Log.d("AAA", "onBannerFailed: " + errorCode);
            Toast.makeText(UnityPlayerActivity.this, "Banner error: !" + errorCode.toString(),
                    Toast.LENGTH_LONG).show();
        }

        @Override
        public void onBannerClicked(MoPubView banner) {
            Toast.makeText(UnityPlayerActivity.this, "Banner Clicked",
                    Toast.LENGTH_LONG).show();
        }

        @Override
        public void onBannerExpanded(MoPubView banner) {
            Toast.makeText(UnityPlayerActivity.this, "Banner Expanded",
                    Toast.LENGTH_LONG).show();
        }

        @Override
        public void onBannerCollapsed(MoPubView banner) {
            Toast.makeText(UnityPlayerActivity.this, "Banner Collapsed ",
                    Toast.LENGTH_LONG).show();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.wildcardstudios.blackjack.R.layout.activity_calculator);

        MoPub.onCreate(this);
        moPubView = findViewById(R.id.bannerView);
        initMoPubSDK(getString(R.string.id_banner));

        AdinCube.setAppKey("OGY-824674D1B5EE");
        //AdinCube.UserConsent.ask(this);
        AdinCube.Interstitial.setEventListener(new AdinCubeInterstitialEventListener() {

            @Override
            public void onAdCached() {
                Toast.makeText(UnityPlayerActivity.this, "Interstitial cached!",
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAdShown() {
                Toast.makeText(UnityPlayerActivity.this, "Interstitial showed!",
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(String s) {
                Toast.makeText(UnityPlayerActivity.this, "Interstitial error: " + s,
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAdClicked() {
                Toast.makeText(UnityPlayerActivity.this, "Interstitial clicked!",
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAdHidden() {
                Toast.makeText(UnityPlayerActivity.this, "Interstitial hidden (= press closed)!",
                        Toast.LENGTH_LONG).show();
            }
        });

        AdinCube.Rewarded.setEventListener(new AdinCubeRewardedEventListener() {
            @Override
            public void onAdCompleted() {
                Toast.makeText(UnityPlayerActivity.this, "Finish viewing video!",
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAdFetched() {
                Toast.makeText(UnityPlayerActivity.this, "Video load completed!",
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFetchError(String s) {
                Toast.makeText(UnityPlayerActivity.this, "Video load error!",
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAdShown() {
                Toast.makeText(UnityPlayerActivity.this, "Video shown!",
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(String s) {
                Toast.makeText(UnityPlayerActivity.this, "Video showing error!",
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAdClicked() {
                Toast.makeText(UnityPlayerActivity.this, "Video clicked!",
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAdHidden() {
                Toast.makeText(UnityPlayerActivity.this, "Video hidden(= press closed)!",
                        Toast.LENGTH_LONG).show();
            }
        });

        BannerView bannerView = (BannerView) findViewById(R.id.bannerView1);
//        BannerView bannerView = AdinCube.Banner.createView(UnityPlayerActivity.this, AdinCube.Banner.Size.BANNER_AUTO);
        AdinCube.Banner.setEventListener(bannerView, new AdinCubeBannerEventListener() {

            @Override
            public void onAdLoaded(BannerView bannerView) {
                Toast.makeText(UnityPlayerActivity.this, "Loaded banner successfully!",
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLoadError(BannerView bannerView, String s) {
                Toast.makeText(UnityPlayerActivity.this, "Load banner error: !" + s,
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAdShown(BannerView bannerView) {
                Toast.makeText(UnityPlayerActivity.this, "Showing banner!",
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(BannerView bannerView, String s) {
                Toast.makeText(UnityPlayerActivity.this, "Banner error: !" + s,
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAdClicked(BannerView bannerView) {
                Toast.makeText(UnityPlayerActivity.this, "Banner clicked!",
                        Toast.LENGTH_LONG).show();
            }
        });

        AdinCube.Interstitial.init(this);
        AdinCube.Rewarded.fetch(this);
        AdinCube.Banner.load(bannerView);

        Button btnInter = (Button) findViewById(com.wildcardstudios.blackjack.R.id.btnInter);
        Button btnVid = (Button) findViewById(com.wildcardstudios.blackjack.R.id.btnVid);

        btnInter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdinCube.Interstitial.show(UnityPlayerActivity.this);
            }
        });

        btnVid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdinCube.Rewarded.show(UnityPlayerActivity.this);
            }
        });
    }

    private void initMoPubSDK(String adUnit) {
        // configurations required to initialize
        SdkConfiguration sdkConfiguration = new SdkConfiguration.Builder(adUnit)
                .withLegitimateInterestAllowed(false)
                .build();
        MoPub.initializeSdk(this, sdkConfiguration, initSdkListener());
    }

    private SdkInitializationListener initSdkListener() {
        return new SdkInitializationListener() {
            @Override
            public void onInitializationFinished() {
                Log.d("AAA", "onInitializationFinished: ");
                moPubView.setAdUnitId(getString(R.string.id_banner));
                moPubView.setBannerAdListener(bannerAdListener);
                moPubView.loadAd();
            }
        };
    }
}
