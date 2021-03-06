package cn.baikunlong.gamehodgepodge;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("cn.baikunlong.gamehodgepodge", appContext.getPackageName());
    }

    @Test
    public void test01() {
        String url = "https://www.taptap.com/ajax/top/download?page=1";
        try {
            OkHttpClient client = new OkHttpClient();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(url).get().build();
            Response response = client.newCall(request).execute();
            String allHtml = response.body().string();
            response.close();
            JSONObject jsonObject = new JSONObject(allHtml);
            JSONObject data = jsonObject.getJSONObject("data");
            String html = data.getString("html");
            if(html.length()==0){
                System.out.println("没有下一页了");
                return;
            }

            Document document = Jsoup.parse(html);
            Elements elements = document.getElementsByClass("taptap-top-card");
            for (int i = 0; i < elements.size(); i++) {
                //寻找图片
                Elements imgs = elements.get(i).getElementsByTag("img");
                String src = imgs.get(0).attr("src");
                String title = imgs.get(0).attr("title");
                //寻找分数
                Elements gradeElements = elements.get(i).getElementsByClass("middle-footer-rating");
                String grade = gradeElements.get(0).child(0).text();
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
