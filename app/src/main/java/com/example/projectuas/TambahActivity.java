package com.example.projectuas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TambahActivity extends AppCompatActivity {
    EditText Barang, Jenis, Berat, Merk, Harga;
    Button Simpan;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        Barang = findViewById(R.id.edBarang);
        Jenis = findViewById(R.id.edJenis);
        Berat = findViewById(R.id.edBerat);
        Merk = findViewById(R.id.edMerk);
        Harga = findViewById(R.id.edHarga);
        Simpan = findViewById(R.id.btn_simpan);
        Simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getBarang = Barang.getText().toString();
                String getJenis = Jenis.getText().toString();
                String getBerat = Berat.getText().toString();
                String getMerk = Merk.getText().toString();
                String getHarga = Harga.getText().toString();

                if (getBarang.isEmpty()) {
                    Barang.setError("Barang masih kosong!");
                } else if (getJenis.isEmpty()) {
                    Jenis.setError("Jenis masih kosong!");
                } else if (getBerat.isEmpty()) {
                    Berat.setError("Berat masih kosong!");
                } else if (getMerk.isEmpty()) {
                    Merk.setError("Merk masih kosong!");
                } else if (getHarga.isEmpty()) {
                    Harga.setError("Harga masih kosong!");
                } else {
                    database.child("Produk").push().setValue(new ModelBarang(getBarang, getJenis, getBerat, getMerk,getHarga)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(TambahActivity.this, "Data Berhasi Disimpan", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(TambahActivity.this, "Gagal Menyimpan Data", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}