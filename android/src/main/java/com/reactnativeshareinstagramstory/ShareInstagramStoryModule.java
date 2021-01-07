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

  private static final int IMAGE_PICKER_REQUEST = 1;
  private static final String E_ACTIVITY_DOES_NOT_EXIST = "E_ACTIVITY_DOES_NOT_EXIST";
  private static final String E_PICKER_CANCELLED = "E_PICKER_CANCELLED";
  private static final String E_FAILED_TO_SHOW_PICKER = "E_FAILED_TO_SHOW_PICKER";
  private static final String E_NO_IMAGE_DATA_FOUND = "E_NO_IMAGE_DATA_FOUND";
 private Promise mPickerPromise;
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
        Activity currentActivity = getCurrentActivity();

    if (currentActivity == null) {
      promise.reject(E_ACTIVITY_DOES_NOT_EXIST, "Activity doesn't exist");
      return;
    }

    // Store the promise to resolve/reject when picker returns data
    mPickerPromise = promise;


      final Intent galleryIntent = new Intent("com.instagram.share.ADD_TO_STORY");
Uri backgroundAssetUri = Uri.parse(uri);
galleryIntent.putExtra("source_application", "com.appchoose.choose.android");

      galleryIntent.setDataAndType(backgroundAssetUri,"video/*");
galleryIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
galleryIntent.putExtra("content_url", attributionLinkUrl);
      final Intent chooserIntent = Intent.createChooser(galleryIntent, "Pick an image");


 try {
      currentActivity.startActivityForResult(chooserIntent, 0);
    } catch (Exception e) {
      mPickerPromise.resolve(e.getMessage());
    }
  }
}
