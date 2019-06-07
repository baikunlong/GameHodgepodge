package cn.baikunlong.gamehodgepodge.fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

import cn.baikunlong.gamehodgepodge.R;
import cn.baikunlong.gamehodgepodge.adapter.GameQuickAdapter;
import cn.baikunlong.gamehodgepodge.bean.Game;
import cn.baikunlong.gamehodgepodge.bean.MyUrl;
import cn.baikunlong.gamehodgepodge.dao.DataServer;

/**
 * Created by Android Studio.
 * User: baikunlong
 * Date: 2019/6/6
 * Time: 10:11
 *
 * @author baikunlong
 */
public class GameFragment extends BaseFragment {

    /**
     * 使用对象的引用调用，方便DataServer直接更改url
     */
    public static MyUrl downloadUrl = new MyUrl("https://www.taptap.com/ajax/top/download?page=1");
    public static MyUrl newUrl = new MyUrl("https://www.taptap.com/ajax/top/new?page=1");
    public static MyUrl playedUrl = new MyUrl("https://www.taptap.com/ajax/top/played?page=1");
    private TabLayout tl_game;
    private ViewPager vp_game;
    private ArrayList<View> views;
    private String[] titles = new String[]{"热门榜", "新品榜", "下载榜"};
    private String[] types = new String[]{"download", "new", "played"};

    @Override
    protected int getLayout() {
        return R.layout.fragment_game;
    }

    @Override
    protected void initView(final View view) {
        tl_game = (TabLayout) view.findViewById(R.id.tl_game);
        vp_game = (ViewPager) view.findViewById(R.id.vp_game);

        tl_game.setupWithViewPager(vp_game);
        tl_game.setTabMode(TabLayout.MODE_SCROLLABLE);

        views = new ArrayList<>();
        views.add(getView(downloadUrl));
        views.add(getView(newUrl));
        views.add(getView(playedUrl));
        vp_game.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return views.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                return view == o;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                container.addView(views.get(position));
                return views.get(position);
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView(views.get(position));
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });

    }

    private View getView(final MyUrl url) {
        View inflate = View.inflate(getActivity(), R.layout.view_game, null);
        final SwipeRefreshLayout srl_game = inflate.findViewById(R.id.srl_game);
        RecyclerView rv_game = inflate.findViewById(R.id.rv_game);
        rv_game.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        ArrayList<Game> data = new ArrayList<>();
        final GameQuickAdapter adapter = new GameQuickAdapter(R.layout.item_game, data, getActivity());
        rv_game.setAdapter(adapter);

        srl_game.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toast("刷新成功");
                        srl_game.setRefreshing(false);
                    }
                }, 2000);
            }
        });

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                addData(adapter, url);
            }
        }, rv_game);
        //自认为最帅的动画
        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        adapter.isFirstOnly(false);

        addData(adapter, url);
        return inflate;
    }

    private void addData(final GameQuickAdapter adapter, final MyUrl url) {
        thread(new Runnable() {
            @Override
            public void run() {
                final ArrayList<Game> games = DataServer.getGames(url);
                post(new Runnable() {
                    @Override
                    public void run() {
                        if (games == null) {
                            toast("已经是最后一页了");
                            adapter.loadMoreEnd();
                            return;
                        }
                        adapter.addData(games);
                        adapter.loadMoreComplete();
                    }
                });
            }
        });
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {

    }
}
