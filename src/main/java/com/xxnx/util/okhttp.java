package com.xxnx.util;
import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;

public class okhttp {
    static OkHttpClient client = new OkHttpClient();

     public static JSONObject run(Integer schoolID, String Mdata, String Zhanghao) throws IOException {
        RequestBody requestBody = new FormBody.Builder()
                .add("app_version","4.3.9")
                .add("registration_id","")
                .add("uuid","0E:69:AB:C8:4F:53")
                .add("request_source","3")
                .add("platform","2")
                .add("system","10")
                .add("school_id", String.valueOf(schoolID))
                .add("m_data",Mdata)
                .add("model","OPPO Find N3")
                .add("alg","alg101")
                .add("app_id","cn.vanber.xixunyun.xxnx")
                .add("account", String.valueOf(Zhanghao))
                .add("key","")
                .build();

        Request request = new Request.Builder()
                .url("http://sxapi.nxeduyun.com/login/api?from=app&version=4.3.9&platform=android&entrance_year=0&graduate_year=0")
                .post(requestBody)
                .build();



        Response resp  = client.newCall(request).execute();
        String n = resp.body().string();
         System.out.println(n);
         JSONObject jsonObject = new JSONObject(n);

         return  jsonObject;

    }

    public void parseJSON(String jsonDATA){
         try {

         } catch (Exception e) {
             throw new RuntimeException(e);
         }
    }


    public static int  getcode(JSONObject jsonObject){

        //获得code到前台
        Integer code = jsonObject.getInt("code");
        if(code == 20000) {


        }
     //  try (Response response = client.newCall(request).execute()) {
      return code;
       // }
    }

    public static int getuser_id(JSONObject jsonObject){
        JSONObject dataObject = jsonObject.getJSONObject("data");
        // 获取user_id值
        int user_id = dataObject.getInt("user_id");
         return  user_id;
    }
    public static String getToken(JSONObject jsonObject){
        JSONObject dataObject = jsonObject.getJSONObject("data");


        // 获取token值
        String token = dataObject.getString("token");
        return  token;
    }
    public static String getuser_name( JSONObject jsonObject){
        JSONObject dataObject = jsonObject.getJSONObject("data");


        // 获取user_name值
        String user_name = dataObject.getString("user_name");
        return  user_name;
    }


    //zhoubao
    public  static void bao(String token ,String type,String content) throws IOException, ParseException {
        getTime o = new getTime();
        RequestBody requestBody = new FormBody.Builder()
                .add("end_date",o.ZhougatStarttime().get(1))
                .add("attachment","")
                .add("business_type",type)//week , mo
                .add("content",content)
                .add("start_date",o.ZhougatStarttime().get(0))

                .build();

        Request request = new Request.Builder()
                .url("http://sxapi.nxeduyun.com/Reports/StudentOperator?token="+token+"&from=app&version=4.3.9&platform=android&entrance_year=0&graduate_year=0")
                .post(requestBody)
                .build();



        Response resp  = client.newCall(request).execute();
        String n = resp.body().string();
        System.out.println(n);
    }

    public static void qiandao(String token , String Longitude, String Latitude, String address, String address_name) throws IOException, ParseException {
        RequestBody requestBody = new FormBody.Builder()
                .add("address",address)
                .add("address_name",address_name)
                .add("latitude",Latitude)
                .add("longitude",Longitude)
                .add("remark","0")
                .add("change_sign_resource","0")
                .add("comment","")

                .build();

        Request request = new Request.Builder()
                .url("http://sxapi.nxeduyun.com/signin?token="+token+"&from=app&version=4.3.9&platform=android&entrance_year=0&graduate_year=0")
                .post(requestBody)
                .build();



        Response resp  = client.newCall(request).execute();
        String n = resp.body().string();
        System.out.println(n);


        }

}

