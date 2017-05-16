# agoda-loading-view
agoda-loading-view is imitative of the loading effection of [Agoda App][agoda]. <br />

## Demo
![Demo](https://media.giphy.com/media/l4FGGJ49q81c9F1Nm/giphy.gif "Demo")
## Usage
### Gradle:
```groovy
compile 'ellison.app.lib:agodaloadingview:1.0.0'
```
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
## License
    Copyright 2017 evil0327@gmail.com
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.






*******************
[agoda]:https://play.google.com/store/apps/details?id=com.agoda.mobile.consumer&hl=zh-TW
