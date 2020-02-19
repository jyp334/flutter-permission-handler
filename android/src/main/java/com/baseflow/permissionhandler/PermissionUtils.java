package com.baseflow.permissionhandler;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;


public class PermissionUtils {

  @RequiresApi(api = Build.VERSION_CODES.M)
  static boolean neverAskAgainSelected(final Activity activity, final String permission) {
    final boolean hasRequestedPermissionBefore = getRequestedPermissionBefore(activity, permission);
    final boolean shouldShowRequestPermissionRationale = ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
    return hasRequestedPermissionBefore && !shouldShowRequestPermissionRationale;
  }

  static void setRequestedPermission(final Context context, final String permission) {
    SharedPreferences genPrefs = context.getSharedPreferences("GENERIC_PREFERENCES", Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = genPrefs.edit();
    editor.putBoolean(permission, true);
    editor.apply();
  }

  private static boolean getRequestedPermissionBefore(final Context context, final String permission) {
    SharedPreferences genPrefs = context.getSharedPreferences("GENERIC_PREFERENCES", Context.MODE_PRIVATE);
    return genPrefs.getBoolean(permission, false);
  }
}
