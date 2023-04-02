package edu.sabanciuniv.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.Log;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;


public class NewsRepo
{

/*

     private List<News> data1 = new ArrayList<News>();
     private List<News> data2 = new ArrayList<News>();
     private  List<News> data3 = new ArrayList<News>();

    public NewsRepo()
    {

        String date1 = "2022-12-13T21:00:00.000+00:00";
        String Text1 = "Elon Musk’s Twitter has dissolved its Trust and Safety Council," +
                " the advisory group of around 100 independent civil, human rights and other organizations " +
                "that the company formed in 2016 to address hate speech, child exploitation, suicide, self-harm " +
                "and other problems on the platform. The council had been scheduled to meet with Twitter" +
                " representatives Monday night. But Twitter informed the group via email that it" +
                "was disbanding it shortly before the meeting was to take place, according to multiple members."+"Elon Musk’s Twitter has dissolved its Trust and Safety Council," +
                " the advisory group of around 100 independent civil, human rights and other organizations " +
                "that the company formed in 2016 to address hate speech, child exploitation, suicide, self-harm " +
                "and other problems on the platform. The council had been scheduled to meet with Twitter" +
                " representatives Monday night. But Twitter informed the group via email that it" +
                "was disbanding it shortly before the meeting was to take place, according to multiple members."+"Elon Musk’s Twitter has dissolved its Trust and Safety Council," +
                " the advisory group of around 100 independent civil, human rights and other organizations " +
                "that the company formed in 2016 to address hate speech, child exploitation, suicide, self-harm " +
                "and other problems on the platform. The council had been scheduled to meet with Twitter" +
                " representatives Monday night. But Twitter informed the group via email that it" +
                "was disbanding it shortly before the meeting was to take place, according to multiple members.";
        News news1 = new News(16, "Musk’s Twitter disbands its Trust and Safety advisory group", Text1, "http://10.3.0.14:8080/newsapp/images/news16.png", "Economics",date1);
        data1.add(news1);


        String date2 = "2022-12-13T21:00:00.000+00:00";
        String Text2 = "Brazil's soccer coach unveiled the 23 players that will seek a sixth World Cup title in the tournament that kicks off June 12.";
        News news2 = new News(10, "Brazil Unveils Squad for World Cup", Text2, "http://10.3.0.14:8080/newsapp/images/news10.jpg", "Sports",date2);
        data2.add(news2);



        String date3 = "2022-12-14T21:00:00.000+00:00";
        String Text3 = "CARACAS, Venezuela (AP) — Venezuelan President Nicolás Maduro on Monday announced his intention to fully open the border crossings with Colombia starting Jan. 1, a measure repeatedly postponed following the restoration of diplomatic and commercial ties between the South American neighbors.";
        News news3 = new News(17, "Venezuela’s Maduro to fully open border with Colombia", Text3, "http://10.3.0.14:8080/newsapp/images/news17.png", "Politics",date3);
        data3.add(news3);




    }

 */








    public void getAllNews(ExecutorService srv, Handler uiHandler){

        srv.execute(()->{
            try {
                URL url = new URL("http://10.3.0.14:8080/newsapp/getall");
                HttpURLConnection conn =(HttpURLConnection)url.openConnection();


                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line = "";

                while((line=reader.readLine())!=null){

                    buffer.append(line);

                }


                JSONObject json = new JSONObject(buffer.toString());
                JSONArray arr = json.getJSONArray("items");
                List<News> data = new ArrayList<>();

                for (int i = 0; i < arr.length(); i++)
                {

                    JSONObject current = arr.getJSONObject(i);



                    News news = new News(current.getInt("id"),
                            current.getString("title"),
                            current.getString("text"),
                            current.getString("image"),
                            current.getString("categoryName"),
                            current.getString("date"));


                    data.add(news);


                }



                Message msg = new Message();
                msg.obj = data;
                uiHandler.sendMessage(msg);



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        });



    }






    public void getNewsById(ExecutorService srv, Handler uiHandler,int id){


        srv.execute(()->{
/*
        List<News>temp= new ArrayList<News>();
            temp.addAll(data1);
            temp.addAll(data2);
            temp.addAll(data3);

            Message msg = new Message();
            for(int j=0;j<temp.size();j++)
            {
                News t = temp.get(j);
                if(t.getId()==id)
                {
                    msg.obj = t;
                    uiHandler.sendMessage(msg);
                    break;
                }
            }

 */

            try {
                URL url = new URL("http://10.3.0.14:8080/newsapp/getnewsbyid/" + String.valueOf(id));
                HttpURLConnection conn =(HttpURLConnection)url.openConnection();


                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line = "";

                while((line=reader.readLine())!=null){

                    buffer.append(line);

                }
                JSONObject json = new JSONObject(buffer.toString());
                JSONArray json2 = json.getJSONArray("items");
                JSONObject current = json2.getJSONObject(0);

                News news = new News(current.getInt("id"),
                        current.getString("title"),
                        current.getString("text"),
                        current.getString("image"),
                        current.getString("categoryName"),
                        current.getString("date"));


                Message msg = new Message();
                msg.obj = news;
                uiHandler.sendMessage(msg);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        });

    }



    public void getNewsByCategoryId(ExecutorService srv, Handler uiHandler,int caid)
    {


        srv.execute(() -> {
/*
            Message msg = new Message();

            if(caid==1){
                msg.obj = data1;
                uiHandler.sendMessage(msg);
            }
            else if(caid==2){
                msg.obj = data2;
                uiHandler.sendMessage(msg);
            }
            else if(caid==3){
                msg.obj = data3;
                uiHandler.sendMessage(msg);
            }

 */

            try {
                URL url = new URL("http://10.3.0.14:8080/newsapp/getbycategoryid/" + String.valueOf(caid));
                HttpURLConnection conn =(HttpURLConnection)url.openConnection();
                conn.setRequestMethod("GET");
                conn.setDoInput(true);



                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line = "";

                while((line=reader.readLine())!=null){

                    buffer.append(line);

                }



                JSONObject json = new JSONObject(buffer.toString());
                JSONArray arr = json.getJSONArray("items");
                List<News> data = new ArrayList<News>();

                for (int i = 0; i < arr.length(); i++)
                {

                    JSONObject current = arr.getJSONObject(i);

                    News news = new News(current.getInt("id"),
                            current.getString("title"),
                            current.getString("text"),
                            current.getString("image"),
                            current.getString("categoryName"),
                            current.getString("date"));
                    data.add(news);


                }


                Message msg = new Message();
                msg.obj = data;
                uiHandler.sendMessage(msg);



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        });


    }




    public void downloadImage(ExecutorService srv, Handler uiHandler,String path)

    {
        srv.execute(()->
        {
            try
            {
                URL url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();

                Bitmap bitmap =  BitmapFactory.decodeStream(conn.getInputStream());

                Message msg = new Message();
                msg.obj = bitmap;
                uiHandler.sendMessage(msg);


            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        });
    }

}











