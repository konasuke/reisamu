package android.wings.websarva.samuraispirits2019_capture;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CommandList  extends Activity implements View.OnClickListener {

    private String charNo;
    private final String JSON_SKILL_NAME = "skillName";
    private final String JSON_COMMAND = "command";
    private final String JSON_DAMAGE = "damage";
    private final String JSON_OCC_F = "occ_F";
    private final String JSON_DEF_F = "def_F";
    private final String JSON_HIT_F = "hit_F";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.command_list);

        Intent intent = getIntent();
        String charNo = intent.getStringExtra("chaNo");
        this.charNo = charNo;

        // テーブルレイアウトを生成
//        TableLayout tableLayout = new TableLayout(this);
        TableLayout tableHead = findViewById(R.id.tableHead);
        TableRow rowHead = findViewById(R.id.rowHead);
        rowHead.setMinimumHeight(Common.calcPxToDp(this,30));
        TextView headSkillName = findViewById(R.id.headSkillName);
        TextView headCommand = findViewById(R.id.headCommand);
        TextView headDamage = findViewById(R.id.headDamage);
        TextView headOccF = findViewById(R.id.headOccF);
        TextView headDefF = findViewById(R.id.headDefF);
        TextView headHitF = findViewById(R.id.headHitF);
        textViewSetting(headSkillName);
        headSkillName.setWidth(Common.calcPxToDp(this,120));
        textViewSetting(headCommand);
        headCommand.setWidth(Common.calcPxToDp(this,120));
        textViewSetting(headDamage);
        textViewSetting(headOccF);
        textViewSetting(headDefF);
        textViewSetting(headHitF);

        TableLayout tableBody = findViewById(R.id.tableBody);
        // 行を生成
        createRows(tableBody, charNo);


        Button backButton = findViewById(R.id.backButton);
        backButton.setText("戻る");

        backButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        Intent intent = null;
        switch(v.getId()){
            case R.id.backButton:
                intent = new Intent(this.getApplicationContext(), CharacterIntroduction.class);
                intent.putExtra("chaNo", charNo);
                startActivity(intent);
                break;
        }
    }

    /**
     * 行を生成
     * @param tableLayout
     */
    private void createRows(TableLayout tableLayout, String charNo) {

        // JSONを取得
//        String jsonStr = getAssetJsonData();
        Common util = new Common();
        String jsonStr = util.getAssetJsonData(this, "json/commandList.json");

        JSONObject json = null;
        JSONArray jsonArr = null;
        try{

            json = new JSONObject(jsonStr);
            jsonArr = json.getJSONArray("char" + charNo);

        }catch (JSONException jex){
            jex.printStackTrace();
        }

        if(jsonArr != null) {

            for (int i = 0; i < jsonArr.length(); i++) {
                TableRow row = new TableRow(this);
//            TableRow row = findViewById(R.id.row);
//            row.setBackgroundColor(Color.WHITE);

                try {
                    JSONObject data = jsonArr.getJSONObject(i);

                    row.setBackgroundColor(Color.parseColor("#77FFFFFF"));
//                row.setBackgroundResource(R.drawable.border);

                    // skillName
                    TextView txtSkill = new TextView(this);
                    textViewSetting(txtSkill);
                    txtSkill.setText(data.getString(JSON_SKILL_NAME));
                    txtSkill.setWidth(Common.calcPxToDp(this, 120));
                    row.addView(txtSkill);

                    // command
                    TextView txtCommand = new TextView(this);
                    textViewSetting(txtCommand);
                    txtCommand.setText(data.getString(JSON_COMMAND));
                    txtCommand.setWidth(Common.calcPxToDp(this, 120));
                    row.addView(txtCommand);

                    // damage
                    TextView txtDmg = new TextView(this);
                    textViewSetting(txtDmg);
                    txtDmg.setText(data.getString(JSON_DAMAGE));
                    row.addView(txtDmg);

                    // occ_F
                    TextView txtOccF = new TextView(this);
                    textViewSetting(txtOccF);
                    txtOccF.setText(data.getString(JSON_OCC_F));
                    row.addView(txtOccF);

                    // def_F
                    TextView txtDefF = new TextView(this);
                    textViewSetting(txtDefF);
                    txtDefF.setText(data.getString(JSON_DEF_F));
                    row.addView(txtDefF);


                    // hit_F
                    TextView txtHitF = new TextView(this);
                    textViewSetting(txtHitF);
                    txtHitF.setText(data.getString(JSON_HIT_F));
                    row.addView(txtHitF);

                    tableLayout.addView(row);

                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }


    /**
     * テキストビュー設定用
     * @param txtView
     * @return
     */
    private TextView textViewSetting(TextView txtView){
        
        // 背景色
//        txtView.setBackgroundColor(Color.parseColor("#77000000"));

//        txtView.setHeight(calcPxToDp(30));

        // 文字色
        txtView.setTextColor(Color.BLACK);
        // 文字サイズ
        txtView.setTextSize(15);

        int paddingDp = Common.calcPxToDp(this,10);

        txtView.setPadding(paddingDp,paddingDp,paddingDp,paddingDp);

        txtView.setWidth(Common.calcPxToDp(this, 42));
        txtView.setHeight(Common.calcPxToDp(this, 50));

        txtView.setBackgroundResource(R.drawable.border);

        return txtView;
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
