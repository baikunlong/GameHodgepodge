package cn.baikunlong.gamehodgepodge;

import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import cn.baikunlong.gamehodgepodge.activity.BaseActivity;
import cn.baikunlong.gamehodgepodge.fragment.GameFragment;
import cn.baikunlong.gamehodgepodge.fragment.NewsFragment;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final int PAGE_NEWS = 0;
    private static final int PAGE_GAME = 1;
    SparseArray<Fragment> fragments = new SparseArray<>();
    private Toolbar toolbar;
    private AppBarLayout AppBarLayout01;
    private FrameLayout container;
    private BottomNavigationView bottom_navigation;
    private NavigationView nav_view;
    private DrawerLayout drawer_layout;
    private GameFragment gameFragment;
    private NewsFragment newsFragment;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        AppBarLayout01 = (AppBarLayout) findViewById(R.id.AppBarLayout01);
        container = (FrameLayout) findViewById(R.id.container);
        bottom_navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        nav_view = (NavigationView) findViewById(R.id.nav_view);
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setBackgroundDrawable(null);
        getSupportActionBar().setCustomView(View.inflate(this, R.layout.toolbar_news, null), new Toolbar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        showFragment(PAGE_NEWS);
    }

    public void showFragment(int index) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideAllFragment(ft);
        switch (index) {
            case PAGE_NEWS:
                if (newsFragment == null) {
                    newsFragment = new NewsFragment();
                    ft.add(R.id.container, newsFragment);
                    fragments.append(PAGE_NEWS, newsFragment);
                } else {
                    ft.show(newsFragment);
                }
                break;
            case PAGE_GAME:
                if (gameFragment == null) {
                    gameFragment = new GameFragment();
                    ft.add(R.id.container, gameFragment);
                    fragments.append(PAGE_GAME, gameFragment);
                } else {
                    ft.show(gameFragment);
                }
                break;

            default:

                break;
        }
        ft.commit();
    }

    private void hideAllFragment(FragmentTransaction ft) {
        for (int i = 0; i < fragments.size(); i++) {
            ft.hide(fragments.get(i));
        }
    }

    @Override
    protected void setListener() {
        //设置菜单选择事件
        nav_view.setNavigationItemSelectedListener(this);
        //设置底部item选择事件
        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_news:
                        showFragment(PAGE_NEWS);
                        break;
                    case R.id.action_game:
                        showFragment(PAGE_GAME);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        bottom_navigation.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {

            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        switch (itemId) {
            case R.id.nav_view:

                break;
            default:
                break;
        }
        return true;
    }
}
