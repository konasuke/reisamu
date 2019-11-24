package android.wings.websarva.samuraispirits2019_capture;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
private final String CHARA_NAME = "CHARA_NAME";

//    private static final String[] scenes  = {
//            "HAOHMARU",
//            "NAKORURU",
//            "HATTORIHANZO",
//            "GALFORD",
//            "TACHIBANAUKYO",
//            "SENRYOKYOSHIRO",
//            "YAGYUJUBEI",
//            "TAMTAM",
//            "CHARLOTTE",
//            "KIBAGAMI_GENJURO"
//    };

    // drawableに画像を入れる、R.id.xxx はint型
    private static final int[] images = {
            R.drawable.chara01,
            R.drawable.chara02,
            R.drawable.chara03,
            R.drawable.chara04,
            R.drawable.chara05,
            R.drawable.chara06,
            R.drawable.chara07,
            R.drawable.chara08,
            R.drawable.chara09,
            R.drawable.chara10
    };

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
                R.layout.inflaterlist, getScenes(), images);

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

        // clickされたpositionのtextとphotoのID
        String selectedText = scenes[position];
        // int selectedPhoto = images[position];
        // インテントにセット
        intent.putExtra("Text", selectedText);
        //intent.putExtra("Photo", selectedPhoto);

        // CharacterIntroductionへ遷移
        startActivity(intent);
    }

    /**
     *　キャラクターリスト作成
     */
    private void createCharaList() {
        // JSONを取得
        String jsonStr = getAssetJsonData();

        JSONObject json = null;
        JSONArray jsonArr = null;
        try{

            json = new JSONObject(jsonStr);
            jsonArr = json.getJSONArray("charaList");

        }catch (org.json.JSONException jex){
            jex.printStackTrace();
        }
        String[] scenes = new String[jsonArr.length()];
        for(int i = 0 ; i < jsonArr.length() ; i++) {
            try {
                JSONObject data = jsonArr.getJSONObject(i);

                // CHARA_NAME
                scenes[i] = data.getString(CHARA_NAME);

            } catch ( org.json.JSONException ex) {
                ex.printStackTrace();
            }
        }
        setScenes(scenes);
    }
    /**
     * AssetsのJSONデータ取得処理
     * @return
     */
    private String getAssetJsonData() {
        String json;
        try {
            InputStream is = this.getAssets().open("CharaRoster.json");
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
}
