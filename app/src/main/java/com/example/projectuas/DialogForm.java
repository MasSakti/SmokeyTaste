package com.example.projectuas;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DialogForm extends DialogFragment {
    String iBarang, iJenis, iBerat, iMerk,iHarga,iKey,pilih;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public DialogForm(String barang, String jenis, String berat, String merk, String harga, String key, String pilih) {
        this.iBarang = barang;
        this.iJenis = jenis;
        this.iBerat = berat;
        this.iMerk = merk;
        this.iHarga = harga;
        this.iKey = key;
        this.pilih = pilih;
    }
    TextView tbarang, tjenis, tberat, tmerk, tharga;
    Button simpan;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_tambah, container, false);
        tbarang = view.findViewById(R.id.edBarang);
        tjenis = view.findViewById(R.id.edJenis);
        tberat = view.findViewById(R.id.edBerat);
        tmerk = view.findViewById(R.id.edMerk);
        tharga = view.findViewById(R.id.edHarga);
        simpan = view.findViewById(R.id.btn_simpan);


        tbarang.setText(iBarang);
        tjenis.setText(iJenis);
        tberat.setText(iBerat);
        tmerk.setText(iMerk);
        tharga.setText(iHarga);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String barang = tbarang.getText().toString();
                String jenis = tjenis.getText().toString();
                String berat = tberat.getText().toString();
                String merk = tmerk.getText().toString();
                String harga = tharga.getText().toString();
                if (pilih.equals("Ubah")){
                    database.child("Produk").child(iKey).setValue(new com.example.projectuas.ModelBarang(barang, jenis, berat, merk, harga)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(view.getContext(), "Berhasil mengubah barang", Toast.LENGTH_SHORT).show();
                            dismiss();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(view.getContext(), "Gagal mengubah barang", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }
}
