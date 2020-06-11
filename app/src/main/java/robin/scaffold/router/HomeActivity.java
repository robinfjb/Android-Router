package robin.scaffold.router;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import robin.scaffold.lib.base.IResultCallback;
import robin.scaffold.lib.core.RouterExcuter;
import robin.scaffold.lib.exception.RouterException;

public class HomeActivity extends AppCompatActivity {
    FragmentManager mFragmentManager;
    final String s1 = "robin://robin.test/open/native/home/tab1?param=test1";
    final String s2 = "robin://robin.test/open/native/home/tab2?param=test2";
    final String s3 = "robin://robin.test/open/native/home/tab3?param=test3";
    private List<Fragment> mFragmentMap = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_router);
        mFragmentManager = getSupportFragmentManager();
        initFragment();
    }

    private void initFragment() {
        TestData data = new TestData();
        try {
            new RouterExcuter()
                    .withSerializableObj(data)
                    .execute(this, s1, RobinRouterConfig.GROUP_HOME, null, new IResultCallback() {
                        @Override
                        public void onResult(Object fragment) {
                            if(fragment != null) {
                                if(!(fragment instanceof Fragment)) {
                                    return;
                                }
                                FragmentTransaction transaction = mFragmentManager.beginTransaction();
                                for (Fragment f : mFragmentMap) {
                                    if (f != null && f.isVisible()) {
                                        transaction.hide(f);
                                    }
                                }
                                if (((Fragment)fragment).isAdded()) {
                                    transaction.show(((Fragment)fragment));
                                } else {
                                    mFragmentMap.add(((Fragment)fragment));
                                    transaction.add(R.id.content_layout_home, ((Fragment)fragment),"page1");
                                }
                                transaction.commitAllowingStateLoss();
                            }
                        }
                    });
        } catch (RouterException e) {
            e.printStackTrace();
        }
    }

    public void onClick1(View v) {
        Fragment f = mFragmentManager.findFragmentByTag("page1");
        if(f != null) {
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            for (Fragment fd : mFragmentMap) {
                transaction.hide(fd);
            }
            if (f.isAdded()) {
                transaction.show(f);
            } else {
                mFragmentMap.add(f);
                transaction.add(R.id.content_layout_home, f,"page1");
            }
            transaction.commitAllowingStateLoss();
            return;
        }
        try {
            new RouterExcuter().execute(this, s1, RobinRouterConfig.GROUP_HOME, null, new IResultCallback() {
                @Override
                public void onResult(Object fragment) {
                    if(fragment != null) {
                        if(!(fragment instanceof Fragment)) {
                            return;
                        }
                        FragmentTransaction transaction = mFragmentManager.beginTransaction();
                        for (Fragment f : mFragmentMap) {
                            if (f != null && f.isVisible()) {
                                transaction.hide(f);
                            }
                        }
                        if (((Fragment)fragment).isAdded()) {
                            transaction.show(((Fragment)fragment));
                        } else {
                            mFragmentMap.add(((Fragment)fragment));
                            transaction.add(R.id.content_layout_home, ((Fragment)fragment),"page1");
                        }
                        transaction.commitAllowingStateLoss();
                    }
                }
            });
        } catch (RouterException e) {
            e.printStackTrace();
        }
    }

    public void onClick2(View v) {
        Fragment f = mFragmentManager.findFragmentByTag("page2");
        if(f != null) {
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            for (Fragment fd : mFragmentMap) {
                transaction.hide(fd);
            }
            if (f.isAdded()) {
                transaction.show(f);
            } else {
                mFragmentMap.add(f);
                transaction.add(R.id.content_layout_home, f,"page2");
            }
            transaction.commitAllowingStateLoss();
            return;
        }
        try {
            new RouterExcuter().execute(this, s2, RobinRouterConfig.GROUP_HOME, null, new IResultCallback() {
                @Override
                public void onResult(Object fragment) {
                    if(fragment != null) {
                        if(!(fragment instanceof Fragment)) {
                            return;
                        }
                        FragmentTransaction transaction = mFragmentManager.beginTransaction();
                        for (Fragment f : mFragmentMap) {
                            if (f != null && f.isVisible()) {
                                transaction.hide(f);
                            }
                        }
                        if (((Fragment)fragment).isAdded()) {
                            transaction.show(((Fragment)fragment));
                        } else {
                            mFragmentMap.add(((Fragment)fragment));
                            transaction.add(R.id.content_layout_home, ((Fragment)fragment),"page2");
                        }
                        transaction.commitAllowingStateLoss();
                    }
                }
            });
        } catch (RouterException e) {
            e.printStackTrace();
        }
    }

    public void onClick3(View v) {
        Fragment f = mFragmentManager.findFragmentByTag("page3");
        if(f != null) {
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            for (Fragment fd : mFragmentMap) {
                transaction.hide(fd);
            }
            if (f.isAdded()) {
                transaction.show(f);
            } else {
                mFragmentMap.add(f);
                transaction.add(R.id.content_layout_home, f,"page3");
            }
            transaction.commitAllowingStateLoss();
            return;
        }
        try {
            new RouterExcuter().execute(this, s3, RobinRouterConfig.GROUP_HOME, null, new IResultCallback() {
                @Override
                public void onResult(Object fragment) {
                    if(fragment != null) {
                        if(!(fragment instanceof Fragment)) {
                            return;
                        }
                        FragmentTransaction transaction = mFragmentManager.beginTransaction();
                        for (Fragment f : mFragmentMap) {
                            if (f != null && f.isVisible()) {
                                transaction.hide(f);
                            }
                        }
                        if (((Fragment)fragment).isAdded()) {
                            transaction.show(((Fragment)fragment));
                        } else {
                            mFragmentMap.add(((Fragment)fragment));
                            transaction.add(R.id.content_layout_home, ((Fragment)fragment),"page3");
                        }
                        transaction.commitAllowingStateLoss();
                    }
                }
            });
        } catch (RouterException e) {
            e.printStackTrace();
        }
    }
}
