package android.wings.websarva.samuraispirits2019_capture;


import android.app.Activity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

public class CommandList  extends Activity {

    private final String JSON_SKILL_NAME = "skillName";
    private final String JSON_DAMAGE = "damage";
    private final String JSON_FRAME = "frame";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.command_list);

        // テーブルレイアウトを生成
        TableLayout tableLayout = new TableLayout(this);
        tableLayout.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.MATCH_PARENT
        ));
        setContentView(tableLayout);

        // 行を生成
        createRows(tableLayout);

    }

    /**
     * 行を生成
     * @param tableLayout
     */
    private void createRows(TableLayout tableLayout) {
        // JSONを取得
        String jsonStr = getAssetJsonData();

        JSONObject json = null;
        JSONArray jsonArr = null;
        try{

            json = new JSONObject(jsonStr);
            jsonArr = json.getJSONArray("chara_01");

        }catch (JSONException jex){
            jex.printStackTrace();
        }

        for(int i = 0 ; i < jsonArr.length() ; i++) {
            TableRow row = new TableRow(this);
            try {
                JSONObject data = jsonArr.getJSONObject(i);

                // skillName
                TextView txtSkill = new TextView(this);
                txtSkill.setText(data.getString(JSON_SKILL_NAME));
                row.addView(txtSkill);

                // damage
                TextView txtDmg = new TextView(this);
                txtDmg.setText(data.getString(JSON_DAMAGE));
                row.addView(txtDmg);

                // frame
                TextView txtFrame = new TextView(this);
                txtFrame.setText(data.getString(JSON_FRAME));
                row.addView(txtFrame);


                tableLayout.addView(row);

            } catch (JSONException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * AssetsのJSONデータ取得処理
     * @return
     */
    private String getAssetJsonData() {
        String json;
        try {
            InputStream is = this.getAssets().open("commandList.json");
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
