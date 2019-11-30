package android.wings.websarva.samuraispirits2019_capture;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;

public class CharacterIntroduction extends Activity implements AdapterView.OnClickListener {

    private String charNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_introduction);

        // Intentから値を取得
        Intent getIntent = getIntent();
        String charNo = getIntent.getStringExtra("chaNo");
        this.charNo = charNo;

        ImageView imgView = findViewById(R.id.characterImg);

        String fileName = "ci_chara" + charNo;
        int picInt = getResources().getIdentifier(fileName, "drawable", getPackageName());

        // キャラクター画像変更
        imgView.setImageResource(picInt);

        Button commandListButton = findViewById(R.id.commandListButton);

        Button memoButton = findViewById(R.id.memoButton);
        memoButton.setText("メモ");
        memoButton.setEnabled(false);

        Button backButton = findViewById(R.id.backButton);
        backButton.setText("戻る");

        commandListButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch(v.getId()){
            case R.id.commandListButton:
                intent = new Intent(this.getApplicationContext(), CommandList.class);
                intent.putExtra("chaNo", this.charNo);
                startActivity(intent);
                break;
            case R.id.memoButton:
                break;
            case R.id.backButton:
                intent = new Intent(this.getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;
        }
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
