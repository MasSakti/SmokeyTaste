package com.example.projectuas;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AdapterBarang extends RecyclerView.Adapter<AdapterBarang.MyViewHolder> {
    private List<com.example.projectuas.ModelBarang> mList;
    private Activity activity;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public AdapterBarang(List<com.example.projectuas.ModelBarang>mList, Activity activity) {
        this.mList = mList;
        this.activity = activity;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewItem = inflater.inflate(R.layout.layout_item, parent, false);
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final com.example.projectuas.ModelBarang mData = mList.get(position);
        holder.barang.setText("Nama : " + mData.getBarang());
        holder.jenis.setText("Jenis : " + mData.getJenis());
        holder.berat.setText("Berat : " + mData.getBerat());
        holder.merk.setText("Merk : " + mData.getMerk());
        holder.harga.setText("Harga : " + mData.getHarga());

        holder.hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        database.child("Produk").child(mData.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(activity, "Barang berhasil dihapus", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(activity, "Gagal menghapus barang", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setMessage("Apakah anda yakin mau menghapus barang \"" + mData.getBarang() + "\"?");
                builder.show();
            }
        });

        holder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                FragmentManager manager = ((AppCompatActivity)activity).getSupportFragmentManager();
                DialogForm dialog = new DialogForm(
                        mData.getBarang(),
                        mData.getJenis(),
                        mData.getBerat(),
                        mData.getMerk(),
                        mData.getHarga(),
                        mData.getKey(),
                        "Ubah"
                );
                dialog.show(manager, "form");
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView barang, jenis, berat, merk, harga;
        CardView card;
        ImageView hapus;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            barang = itemView.findViewById(R.id.tv_barang);
            jenis = itemView.findViewById(R.id.tv_jenis);
            berat = itemView.findViewById(R.id.tv_berat);
            merk = itemView.findViewById(R.id.tv_merk);
            harga = itemView.findViewById(R.id.tv_harga);
            hapus = itemView.findViewById(R.id.btn_hapus);
            card = itemView.findViewById(R.id.card_hasil);
        }
    }
}
