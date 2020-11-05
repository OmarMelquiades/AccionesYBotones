package com.example.accionesybotones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Llamar(View view) {
        Intent llamar = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:2311745579"));
        startActivity(llamar);
    }

    public void IniciarWhatsApp(View view) {
        String phone="2261035501";
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.setPackage("com.whatsapp");
        intent.putExtra(Intent.EXTRA_TEXT, "Hola como estamos");
        startActivity(intent);
    }

    public void IniciarImgWhatsApp(View view) {
        Uri uri = Uri.parse("https://www.facebook.com/melquiadesrojas");
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        try{
            startActivity(intent);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            finish();
        }
    }

    public void SendMessenger(View view){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,"PRUEBA DESDE ANDROID STUDIO.");
        sendIntent.setType("text/plain");
        sendIntent.setPackage("com.facebook.orca");
        startActivity(sendIntent);
    }

    public  void ShareFacebook(View view){
        String urlToShare = "https://stackoverflow.com/questions/7545254";
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, urlToShare);

        boolean facebookAppFound = false;
        List<ResolveInfo> matches = getPackageManager().queryIntentActivities(intent, 0);
        for (ResolveInfo info : matches) {
            if (info.activityInfo.packageName.toLowerCase().startsWith("com.facebook.katana")) {
                intent.setPackage(info.activityInfo.packageName);
                facebookAppFound = true;
                break;
            }
        }

        if (!facebookAppFound) {
            String sharerUrl = "https://www.facebook.com/sharer/sharer.php?u=" + urlToShare;
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
        }
        startActivity(intent);
    }

    public void InstagramPerfil(View view){

        Uri uri = Uri.parse("https://www.instagram.com/omarmr04/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.instagram.android");

        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {

            //No encontró la aplicación, abre la versión web.
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.instagram.com/omarmr04/")));

        }
    }

    public void TwitterPerfil(View view){

        Uri uri = Uri.parse("https://twitter.com/donaldtrump");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.twitter.android");

        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            //No encontró la aplicación, abre la versión web.
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://twitter.com/donaldtrump")));
        }
    }

}