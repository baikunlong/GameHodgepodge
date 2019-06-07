package cn.baikunlong.gamehodgepodge.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by Android Studio.
 * User: baikunlong
 * Date: 2019/6/6
 * Time: 10:13
 */
public abstract class BaseFragment extends Fragment {

    public Handler handler=new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(getLayout(), null);
        initView(inflate);
        setListener();
        initData();
        return inflate;
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
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }
    protected abstract int getLayout();
    protected abstract void initView(View view);
    protected abstract void setListener();
    protected abstract void initData();
}
