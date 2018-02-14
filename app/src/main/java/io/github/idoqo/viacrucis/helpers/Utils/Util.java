package io.github.idoqo.viacrucis.helpers.Utils;


import android.content.Context;
import android.util.Log;

import junit.framework.Assert;

import java.io.IOException;
import java.io.InputStream;

public class Util {
    public static String loadJsonFromAsset(Context context, String fname) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(fname);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            String tag = context.getClass().getName();
            Log.e(tag, ex.getMessage());
        }
        return json;
    }

    public static int getDrawable(Context context, String name) {
        Assert.assertNotNull(context);
        Assert.assertNotNull(name);

        return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    }
}
