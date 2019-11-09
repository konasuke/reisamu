package android.wings.websarva.samuraispirits2019_capture;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

    private static final String[] scenes  = {
            "chara01",
            "chara02",
            "chara03",
            "chara04",
            "chara05",
            "chara06",
            "chara07",
            "chara08",
            "chara09",
            "chara10"
    };

    // drawableに画像を入れる、R.id.xxx はint型
    private static final int[] photos = {
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

        // ListViewのインスタンスを生成
        ListView listView = findViewById(R.id.listview);

        // BaseAdapter を継承したadapterのインスタンスを生成
        // レイアウトファイル list.xml を activity_main.xml に
        // inflate するためにadapterに引数として渡す
        BaseAdapter adapter = new ListViewAdapter(this.getApplicationContext(),
                R.layout.inflaterlist, scenes, photos);

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
        int selectedPhoto = photos[position];
        // インテントにセット
        intent.putExtra("Text", selectedText);
        intent.putExtra("Photo", selectedPhoto);

        // SubActivityへ遷移
        startActivity(intent);
    }
}
