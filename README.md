# agoda-loading-view

## Introduction
agoda-loading-view is imitative of the loading effection of [Agoda App][agoda]. 
## Demo
![Demo](https://media.giphy.com/media/l4FGGJ49q81c9F1Nm/giphy.gif "Demo")
## Usage
### Code
```Java
//Usage of loading dialog
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
```
### Layout
```XML
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    >

    <ellison.app.agodaloadingview.AgodaLoadingView
        android:id="@+id/loading_view"
        android:layout_width="100dp"
        android:layout_height="100dp"
        android:layout_centerInParent="true"
        android:background="@drawable/circle_background"
        />

</RelativeLayout>
```








*******************
[agoda]:https://play.google.com/store/apps/details?id=com.agoda.mobile.consumer&hl=zh-TW
