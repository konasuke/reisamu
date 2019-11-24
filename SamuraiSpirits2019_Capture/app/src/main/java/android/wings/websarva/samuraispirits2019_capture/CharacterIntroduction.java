package android.wings.websarva.samuraispirits2019_capture;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CharacterIntroduction extends Activity implements AdapterView.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_introduction);

//        Intent intent = getIntent();
//        String selectedText = intent.getStringExtra("Text");
//        int selectedPhoto = intent.getIntExtra("Photo", 0);
//
//        TextView textView = findViewById(R.id.selected_text);
//        textView.setText(selectedText);
//        ImageView  imageView = findViewById(R.id.selected_photo);
//        imageView.setImageResource(selectedPhoto);

        ImageView imgView = findViewById(R.id.characterImg);
        // キャラクター画像変更
        imgView.setImageResource(R.drawable.ci_chara01);

        Button commandListButton = findViewById(R.id.commandListButton);
        commandListButton.setText("コマンドリスト");

        Button memoButton = findViewById(R.id.memoButton);
        memoButton.setText("メモ");
        memoButton.setEnabled(false);

        Button backButton = findViewById(R.id.backButton);
        backButton.setText("戻る");

        commandListButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
    }

    public void onClick(View v) {
        Intent intent = null;
        switch(v.getId()){
            case R.id.commandListButton:
                intent = new Intent(this.getApplicationContext(), CommandList.class);
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
}
