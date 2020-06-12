package robin.scaffold.router;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import robin.scaffold.lib.base.IResultCallback;
import robin.scaffold.lib.core.RouterAction;
import robin.scaffold.lib.core.RouterExcuter;
import robin.scaffold.lib.core.handler.IRouterHandler;
import robin.scaffold.lib.exception.RouterException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_router);
    }

    public void onClickBt1(View v) {
        String testUrl = "robin://robin.test/open/native/second?param=test001";
        try {
            new RouterExcuter().execute(MainActivity.this, testUrl, RobinRouterConfig.GROUP_HOME, null, null);
        } catch (RouterException e) {
            e.printStackTrace();
        }
    }

    public void onClickBt2(View v) {
        String testUrl = "robin://robin.test/open/native/home?param=test002";
        try {
            new RouterExcuter().withRequestCode(1000).execute(MainActivity.this, testUrl, RobinRouterConfig.GROUP_HOME, null, null);
        } catch (RouterException e) {
            e.printStackTrace();
        }
    }

    public void onClickBt3(View v) {
        String testUrl = "robin://robin.test/open/web?url=https://m.baidu.com";
        try {
            new RouterExcuter().withRequestCode(1000).execute(MainActivity.this, testUrl, RobinRouterConfig.GROUP_HOME, new IRouterHandler() {
                @Override
                public boolean handle(Context context, RouterAction action, IResultCallback callback) throws RouterException {
                    Log.d("MainActivity", "action内容：" + action.toString());
                    if("/open/web".equals(action.getPath())) {
                        String url = action.getParameters().getParameter("url");
                        if(!TextUtils.isEmpty(url)) {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse(url));
                            context.startActivity(intent);
                        }
                    }
                    return true;
                }
            }, null);
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
