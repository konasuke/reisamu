package android.wings.websarva.samuraispirits2019_capture;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class CharacterIntroduction extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_introduction);

        Intent intent = getIntent();
        // MainActivityからintentで受け取ったものを取り出す
        int selectedPhoto = intent.getIntExtra("Photo", 0);

        ImageView imageView = findViewById(R.id.selected_photo);
        imageView.setImageResource(selectedPhoto);
    }
}
