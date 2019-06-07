package cn.baikunlong.gamehodgepodge.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.baikunlong.gamehodgepodge.R;
import cn.baikunlong.gamehodgepodge.bean.Game;

public class GameQuickAdapter extends BaseQuickAdapter<Game,BaseViewHolder> {

    Context context;

    public GameQuickAdapter (int layoutResId, @Nullable List<Game> data,Context context) {
        super(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Game item) {
        helper.setText(R.id.tv_serialNumber,""+(helper.getAdapterPosition()+1))
        .setText(R.id.tv_gameName,item.getGameName())
        .setText(R.id.tv_grade,"评分："+item.getGrade())
        .setText(R.id.tv_type,item.getTag())
        .setText(R.id.tv_category,item.getCategory());

        ImageView iv_icon = helper.getView(R.id.iv_icon);
        Glide.with(context).load(item.getImgUrl()).into(iv_icon);

    }
}