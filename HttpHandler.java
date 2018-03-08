import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HttpHandler {
    private static final String TAG=HttpHandler.class.getSimpleName();
    public HttpHandler(){

    }

    public String makeServiceCall(String requastUrl){
        String response=null;
        try {
            URL url=new URL(requastUrl);
            //url web sayfası bağlantımızdır. requastUrl ise,parametreden gelen değer.
            HttpURLConnection conn=(HttpURLConnection) url.openConnection();
            //web sayfasına içeriği okumak amaçlı bağlanır.
            conn.setRequestMethod("GET");
            //conn nesnesine GET istek tipi yollar.
            InputStream in=new BufferedInputStream(conn.getInputStream());
            //inputStream conn dan gelen dosya içeriğini okur.
            response=convertStreamToString(in);
            //String in içeriğini convertStreamToString nesnesinden dönen değeri yollar.
        } catch (MalformedURLException e) {
            Log.e(TAG,"MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG,"ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG,"IOException: " + e.getMessage());
        } catch (Exception e){
            Log.e(TAG,"Exception: " + e.getMessage());
        }
        return response;
    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader=new BufferedReader(new InputStreamReader(is));
        StringBuilder sb=new StringBuilder();

        String satir="";
        try {
            while((satir=reader.readLine())!=null){
                sb.append(satir).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
    
