package android.wings.websarva.samuraispirits2019_capture;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

    private String[] scenes;
    /**
     * フィールドの値を取得します。
     */
    public String[] getScenes()
    {
        return scenes;
    }

    /**
     * フィールドに値を入れます。
     */
    public void setScenes( String[] param )
    {
        scenes = param;
    }

    private int[] images;
    /**
     * フィールドの値を取得します。
     */
    public int[] getImages()
    {
        return images;
    }

    /**
     * フィールドに値を入れます。
     */
    public void setImages( int[] param )
    {
        images = param;
    }

    private String[] chaNo;
    /**
     * フィールドの値を取得します。
     */
    public String[] getChaNo()
    {
        return chaNo;
    }

    /**
     * フィールドに値を入れます。
     */
    public void setChaNo( String[] param)
    {
        chaNo = param;
    }

private final String CHARA_NO = "CHARA_NO";
private final String CHARA_NAME = "CHARA_NAME";
private final String CHARA_IMAGE = "CHARA_IMAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createCharaList();

        // ListViewのインスタンスを生成
        ListView listView = findViewById(R.id.listview);

        // BaseAdapter を継承したadapterのインスタンスを生成
        // レイアウトファイル list.xml を activity_main.xml に
        // inflate するためにadapterに引数として渡す
        BaseAdapter adapter = new ListViewAdapter(this.getApplicationContext(),
                R.layout.inflaterlist, getScenes(), getImages());

        // ListViewにadapterをセット
        listView.setAdapter(adapter);

        // クリックリスナーをセット
        listView.setOnItemClickListener(this);
    }
    /**
     * リストがタップされたときの処理が記述されたメンバクラス。
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View v,
                            int position, long id) {

        Intent intent = new Intent(
                this.getApplicationContext(), CharacterIntroduction.class);

        String[] scenes = getScenes();
        String[] chaNo = getChaNo();
        int[] images = getImages();

        // clickされたpositionのtextとphotoのID
        String selectedText_scenes = scenes[position];
        String selectedText_chaNo = chaNo[position];
        int selectedImages = images[position];

        // インテントにセット
        intent.putExtra("Scenes", selectedText_scenes);
        intent.putExtra("chaNo", selectedText_chaNo);
        intent.putExtra("Images", selectedImages);

        // CharacterIntroductionへ遷移
        startActivity(intent);
    }

    /**
     *　キャラクターリスト作成
     */
    private void createCharaList() {
        // JSONを取得
        String jsonStr = Common.getAssetJsonData(this,"json/CharaRoster.json");

        JSONObject json = null;
        JSONArray jsonArr = null;
        try{

            json = new JSONObject(jsonStr);
            jsonArr = json.getJSONArray("charaList");

        }catch (org.json.JSONException jex){
            jex.printStackTrace();
        }
        String[] chaNo = new String[jsonArr.length()];
        String[] scenes = new String[jsonArr.length()];
        int[] images = new int[jsonArr.length()];

        for(int i = 0 ; i < jsonArr.length() ; i++) {
            try {
                JSONObject data = jsonArr.getJSONObject(i);

                // CHARA_NO
                chaNo[i] = data.getString(CHARA_NO);
                // CHARA_NAME
                scenes[i] = data.getString(CHARA_NAME);
                // CHARA_IMAGE
                images[i] = getResources().getIdentifier(data.getString(CHARA_IMAGE), "drawable", getPackageName());

            } catch ( org.json.JSONException ex) {
                ex.printStackTrace();
            }
        }

        setChaNo(chaNo);
        setScenes(scenes);
        setImages(images);
    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction()==KeyEvent.ACTION_DOWN) {
            switch (event.getKeyCode()) {
                // Backキー押下時の処理
                case KeyEvent.KEYCODE_BACK:
                    // ダイアログ表示等の処理を行いたい場合はここに記述

                    // 親クラスのdispatchKeyEvent()を呼び出さずにtrueを返すことで、
                    // Backキーを無効にする
                    return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }
}
