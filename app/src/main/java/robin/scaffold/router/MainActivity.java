package robin.scaffold.router;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import robin.scaffold.lib.core.RouterExcuter;
import robin.scaffold.lib.exception.RouterException;
import robin.scaffold.lib.robin.RobinRouterConfig;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_router);
    }

    public void onClickBt1(View v) {
        String testUrl = "crf://crfchina.com/open/native/second?param=test001";
        try {
            new RouterExcuter().execute(MainActivity.this, testUrl, RobinRouterConfig.GROUP_HOME, null, null);
        } catch (RouterException e) {
            e.printStackTrace();
        }
    }

    public void onClickBt2(View v) {
        String testUrl = "crf://crfchina.com/open/native/home?param=test002";
        try {
            new RouterExcuter().withRequestCode(1000).execute(MainActivity.this, testUrl, RobinRouterConfig.GROUP_HOME, null, null);
        } catch (RouterException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        Toast.makeText(this, "requestCode=" + requestCode + " resultCode=" + resultCode, Toast.LENGTH_LONG).show();
    }
}
