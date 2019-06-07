package cn.baikunlong.gamehodgepodge.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;


/**
 * @author Amoly
 * @date 2019/4/11.
 * description：
 */

public abstract class BaseActivity extends AppCompatActivity{

    public Handler handler=new Handler();

    /**
     * 初始化 Toolbar
     */
    public void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initView();
        setListener();
        initData();
    }


    public void post(Runnable runnable){
        handler.post(runnable);
    }
    public void postDelayed(Runnable runnable,long delayMillis){
        handler.postDelayed(runnable,delayMillis);
    }
    public void thread(Runnable runnable){
        new Thread(runnable).start();
    }
    public void toast(String text){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
    protected abstract int getLayout();
    protected abstract void initView();
    protected abstract void setListener();
    protected abstract void initData();
}
