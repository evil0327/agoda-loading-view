package ellison.app.agodaloadingview;


import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;

public class AgodaLoadingDialog extends Dialog{
    private AgodaLoadingView agodaLoadingView;
    public AgodaLoadingDialog(Context context){
        super(context, android.R.style.Theme_Black_NoTitleBar);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.loading_dialog);

        agodaLoadingView = (AgodaLoadingView) findViewById(R.id.loading_view);
    }

    public AgodaLoadingDialog setCircleColors(String[] colors){
        agodaLoadingView.setCircleColors(colors);
        return this;

    }

    public AgodaLoadingDialog setCircleColors(int[] colors){
        agodaLoadingView.setCircleColors(colors);
        return this;

    }


}
