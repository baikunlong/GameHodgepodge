package cn.baikunlong.gamehodgepodge.dao;

import android.widget.Toast;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import cn.baikunlong.gamehodgepodge.bean.Game;
import cn.baikunlong.gamehodgepodge.bean.MyUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Android Studio.
 * User: baikunlong
 * Date: 2019/6/7
 * Time: 13:49
 */
public class DataServer {
    private static OkHttpClient client = new OkHttpClient();

    public static ArrayList<Game> getGames(MyUrl url) {
        if("".equals(url.getUrl())){
            return null;
        }
        ArrayList<Game>games=new ArrayList<>();
        try {
            Request request = new Request.Builder().url(url.getUrl()).get().build();
            Response response = client.newCall(request).execute();
            String allHtml = response.body().string();
            response.close();
            JSONObject jsonObject = new JSONObject(allHtml);
            JSONObject data = jsonObject.getJSONObject("data");
            String html = data.getString("html");
            if (html.length() == 0) {
                return null;
            }
            //把url变为下一页
            url.setUrl(data.getString("next"));
            System.out.println("url = " + url.getUrl());

            Document document = Jsoup.parse(html);
            Elements elements = document.getElementsByClass("taptap-top-card");
            for (int i = 0; i < elements.size(); i++) {
                //寻找图片
                Elements imgs = elements.get(i).getElementsByTag("img");
                String src = imgs.get(0).attr("src");
                String title = imgs.get(0).attr("title");
                //寻找分数
                Elements gradeElements = elements.get(i).getElementsByClass("card-middle-score");
                String grade = gradeElements.get(0).text();
                //寻找标签
                Elements tags = elements.get(i).getElementsByClass("btn btn-xs btn-default ");
                StringBuilder tag = new StringBuilder();
                for (int j = 0; j < tags.size(); j++) {
                    if (j == tags.size() - 1) {
                        tag.append(tags.get(j).text());
                    } else {
                        tag.append(tags.get(j).text()).append("、");
                    }
                }
                //寻找分类
                String category = elements.get(i).getElementsByClass("card-middle-category").get(0).child(0).text();
                games.add(new Game(title,src,grade,tag.toString(),category));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return games;
    }

}
