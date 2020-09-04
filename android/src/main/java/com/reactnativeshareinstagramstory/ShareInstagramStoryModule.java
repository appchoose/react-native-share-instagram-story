package com.reactnativeshareinstagramstory;


import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;
import android.app.Activity;
import android.content.Intent;
import android.content.ActivityNotFoundException;
import android.net.Uri;
import androidx.annotation.Nullable;

import java.util.Map;
import java.util.HashMap;

public class ShareInstagramStoryModule  extends ReactContextBaseJavaModule {


  public static final String NOT_INSTALLED = "Not installed";
  public static final String INTERNAL_ERROR = "Data conversion failed";
  public static final String NO_BASE64_IMAGE = "No base64 image";
  public static final String INVALID_PARAMETER = "Invalid parameter";
  private  static final String MEDIA_TYPE_JPEG = "image/*";


 public ShareInstagramStoryModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

     @Override
  public String getName() {
        return "ShareInstagramStory";
    }

    @Override
  public Map<String, Object> getConstants() {
    final Map<String, Object> constants = new HashMap<>();
    constants.put(NOT_INSTALLED, NOT_INSTALLED);
    constants.put(INTERNAL_ERROR, INTERNAL_ERROR);
    constants.put(NO_BASE64_IMAGE, NO_BASE64_IMAGE);
    constants.put(INVALID_PARAMETER, INVALID_PARAMETER);
    return constants;
  }

  @ReactMethod
  public void shareBackgroundVideo(String attributionLinkUrl, String appId, String uri , Promise promise) {
    try {
      Uri backgroundAssetUri = Uri.parse(uri);

      // Instantiate implicit intent with ADD_TO_STORY action,
      // background asset, and attribution link
     Intent intent = new Intent("com.instagram.share.ADD_TO_STORY");
      intent.putExtra("source_application", "com.appchoose.choose.android");
      intent.setDataAndType(backgroundAssetUri, "video/*");
      intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
      intent.putExtra("content_url", attributionLinkUrl);

      // Instantiate activity and verify it will resolve implicit intent
      Activity activity = getCurrentActivity();
if (activity.getPackageManager().resolveActivity(intent, 0) != null) {
  activity.startActivityForResult(intent, 0);
 promise.resolve("ok");
}else{
        promise.resolve("cannot open");
}

    } catch(ActivityNotFoundException ex) {
        System.out.println("ERROR");
        System.out.println(ex.getMessage());
           promise.resolve("cannot share");
    }
  }


}
