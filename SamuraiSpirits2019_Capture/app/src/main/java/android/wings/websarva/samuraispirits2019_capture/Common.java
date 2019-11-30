package android.wings.websarva.samuraispirits2019_capture;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;
import java.lang.reflect.Field;


public class Common {


    /**
     * AssetsのJSONデータ取得処理
     * @return
     */
    public static String getAssetJsonData(Context context, String fileName) {
        String json = null;
        try {

            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


    /**
     * PXをDPに変換する処理
     * @param px
     * @return
     */
    public static int calcPxToDp(Context context, int px){
        float scale = context.getResources().getDisplayMetrics().density; //画面のdensityを指定。
        int dp = (int) (px * scale + 0.5f);

        return dp;
    }

}
