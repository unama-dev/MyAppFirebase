package br.unama.adonias.myappfirebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.google.firebase.database.*;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference banco = FirebaseDatabase.getInstance().getReference();
    private TextView txtDescricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtDescricao = findViewById(R.id.txtDescricao);
        DatabaseReference produtos = banco.child("produtos");
        Usuario u = new Usuario();
        u.setDescricao("Arroz");
        produtos.push().setValue(u);

        produtos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Usuario u;
                for(DataSnapshot data: dataSnapshot.getChildren()){
                    u = data.getValue(Usuario.class);
                    txtDescricao.setText(u.getDescricao());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
