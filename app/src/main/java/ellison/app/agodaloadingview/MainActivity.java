package ellison.app.agodaloadingview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] colors = new String[]{"#FFC107", "#03A9F4", "#B71C1C", "#7B1FA2", "#388E3C"};

        new AgodaLoadingDialog(this)
                .setCircleColors(colors)
                .show();
    }
}
