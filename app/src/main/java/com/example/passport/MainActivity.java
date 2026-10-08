package com.example.passport;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Paszport";

    private ImageView imageZdjecie;
    private ImageView imageOdcisk;
    private EditText editNumer;
    private EditText editImie;
    private EditText editNazwisko;
    private RadioGroup radioGroupKolor;
    private Button buttonOk;
    private TextView textWynik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicjalizujKontrolki();
        ustawObrazy("000");

        // opuszczenie pola numer, amiana zdjec
        editNumer.setOnFocusChangeListener((widok, maFokus) -> {
            if (!maFokus) {
                ustawObrazy(editNumer.getText().toString().trim());
            }
        });

        buttonOk.setOnClickListener(widok -> zatwierdzDane());
    }

    private void inicjalizujKontrolki() {
        imageZdjecie = findViewById(R.id.imageZdjecie);
        imageOdcisk = findViewById(R.id.imageOdcisk);
        editNumer = findViewById(R.id.editNumer);
        editImie = findViewById(R.id.editImie);
        editNazwisko = findViewById(R.id.editNazwisko);
        radioGroupKolor = findViewById(R.id.radioGroupKolor);
        buttonOk = findViewById(R.id.buttonOk);
        textWynik = findViewById(R.id.textWynik);
    }

    private void ustawObrazy(String numer) {
        ustawObraz(imageZdjecie, numer + "-zdjecie.jpg");
        ustawObraz(imageOdcisk, numer + "-odcisk.jpg");
    }

    private void ustawObraz(ImageView imageView, String nazwaPliku) {
        try (InputStream strumien = getAssets().open(nazwaPliku)) {
            Bitmap bitmapa = BitmapFactory.decodeStream(strumien);
            imageView.setImageBitmap(bitmapa);
        } catch (IOException e) {
            imageView.setImageDrawable(null);
        }
    }


    private void zatwierdzDane() {
        String imie = editImie.getText().toString().trim();
        String nazwisko = editNazwisko.getText().toString().trim();

        String komunikat;
        if (imie.isEmpty() || nazwisko.isEmpty()) {
            komunikat = "Wprowadź dane";
        } else {
            String kolorOczu = pobierzKolorOczu();
            komunikat = imie + " " + nazwisko + " kolor oczu " + kolorOczu;
        }

        wyswietlWynik(komunikat);
    }

    private String pobierzKolorOczu() {
        int zaznaczoneId = radioGroupKolor.getCheckedRadioButtonId();
        RadioButton zaznaczony = findViewById(zaznaczoneId);
        return zaznaczony.getText().toString();
    }

    private void wyswietlWynik(String komunikat) {
        Toast.makeText(this, komunikat, Toast.LENGTH_LONG).show();
        Log.d(TAG, komunikat);
        textWynik.setText(komunikat);
    }
}