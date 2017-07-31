package tw.com.ei.myviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    MyView myView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myView=(MyView)findViewById(R.id.myView);
    }
    public void doClear(View v){
        myView.doClear();
    }
    public void unDo(View v){
        myView.unDo();
    }
    public void reDo(View v){
        myView.reDo();
    }
}
