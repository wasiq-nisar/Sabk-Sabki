package com.example.madrasaregister;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btn_add_std, btn_show_reocrds, btn_add_record;
    DbHandler dbHandler;
    ImageView git_link;
    EditText rec_date, rec_sabak, rec_sabki, rec_manzil;
    Spinner stdSpinner;
    ArrayAdapter<Student> spinnerAdapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_add_std=findViewById(R.id.btn_add_std);
        btn_show_reocrds= findViewById(R.id.btn_see_records);
        btn_add_record= findViewById(R.id.btn_add_record);
        rec_date = findViewById(R.id.txt_date);
        rec_sabak = findViewById(R.id.txt_sabak);
        rec_sabki = findViewById(R.id.txt_sabki);
        rec_manzil = findViewById(R.id.txt_manzil);
        git_link = findViewById(R.id.git_link);
        

    }


}