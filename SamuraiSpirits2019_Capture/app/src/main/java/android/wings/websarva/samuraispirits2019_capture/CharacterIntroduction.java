package android.wings.websarva.samuraispirits2019_capture;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CharacterIntroduction extends Activity {
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

        Button commandListButton = findViewById(R.id.commandListButton);
        commandListButton.setText("コマンドリスト");

        Button memoButton = findViewById(R.id.memoButton);
        memoButton.setText("メモ");
        memoButton.setEnabled(false);

        Button backButton = findViewById(R.id.backButton);
        backButton.setText("戻る");
    }
}
