package edu.sabanciuniv.myapplication;

import android.os.Handler;
import android.os.Message;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class CommentsRepo {



    public void getCommentsByNewsId(ExecutorService srv, Handler uiHandler, int naid)
    {


        srv.execute(() -> {

            try {
                URL url = new URL("http://10.3.0.14:8080/newsapp/getcommentsbynewsid/" + String.valueOf(naid));
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
                List<Comments> data = new ArrayList<Comments>();

                for (int i = 0; i < arr.length(); i++)
                {

                    JSONObject current = arr.getJSONObject(i);



                    Comments comment = new Comments(current.getInt("id"),
                            current.getInt("news_id"),
                            current.getString("text"), current.getString("name"));


                    data.add(comment);



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



    public void PostComment(ExecutorService srv, Handler uiHandler,String name, String text,int news_id){

        srv.execute(()->{

            try {
                URL url = new URL("http://10.3.0.14:8080/newsapp/savecomment");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setDoInput(true);
                conn.setDoOutput(true);

                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type","application/JSON");


                JSONObject outputData  = new JSONObject();

                outputData.put("name",name);
                outputData.put("text",text);
                outputData.put("news_id",String.valueOf(news_id));

                BufferedOutputStream writer =
                        new BufferedOutputStream(conn.getOutputStream());


                writer.write(outputData.toString().getBytes(StandardCharsets.UTF_8));
                writer.flush();

                BufferedReader reader
                        = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();

                String line ="";

                while((line=reader.readLine())!=null){

                    buffer.append(line);

                }

                JSONObject retVal = new JSONObject(buffer.toString());


                String returnMessage= retVal.getString("serviceMessageText");

                conn.disconnect();



                Message msg = new Message();
                msg.obj = returnMessage;

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


}
