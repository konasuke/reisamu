package android.wings.websarva.samuraispirits2019_capture;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final String[] names = {
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

        //インテントオブジェクトを取得。
        Intent intent = getIntent();
        //ListViewオブジェクトを取得。
        ListView listView = findViewById(R.id.listView);

        BaseAdapter adapter = new TestAdapter(this.getApplicationContext(),
                R.layout.list_items, photos);

        // ListViewにadapterをセット
        listView.setAdapter(adapter);
    }
    /**
     * リストがタップされたときの処理が記述されたメンバクラス。
     */
    private class ListItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //タップされた定食名を取得。
            String item = (String) parent.getItemAtPosition(position);
            //トーストで表示する文字列を生成。
            String show = "あなたが選んだ定食: " + item;
            //トーストの表示。
            Toast.makeText(MainActivity.this, show, Toast.LENGTH_LONG).show();
        }
    }
}
