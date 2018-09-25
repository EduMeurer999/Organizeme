package com.example.a20151inf0182.organizeme.Activity;

import android.content.Intent;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a20151inf0182.organizeme.DAO.ConfiguracaoFirebase;
import com.example.a20151inf0182.organizeme.Entidades.Usuarios;
import com.example.a20151inf0182.organizeme.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import org.kamal.crypto.SimpleProtector;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class PerfilActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private FirebaseUser usuarioConectado;
    private MainActivity login;
    private static final String TAG = "PerfilActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        usuarioConectado = mAuth.getCurrentUser();
        mAuth.signOut();
        ValueEventListener mUsuariosListener;
        Intent i = getIntent();
        String email = (String) i.getSerializableExtra("email");
        Log.v(TAG, "teste"+email);

        final TextView tvNome = (TextView) findViewById(R.id.txtNome);
        final TextView tvIdade = (TextView) findViewById(R.id.txtIdade);
        final TextView tvCurso = (TextView) findViewById(R.id.txtCurso);
        final TextView tvEmail = (TextView) findViewById(R.id.txtEmail);
        final TextView tvSerie = (TextView) findViewById(R.id.txtSerie);
        Button btnLogout = (Button) findViewById(R.id.btnLogout);
        DatabaseReference usuarioDatabase = null;
//        try {
//            usuarioDatabase = mDatabase.child("Usuarios").child(SimpleProtector.encrypt(email));
//        } catch (Exception e) {
//
//            e.printStackTrace();
//        }
//        ValueEventListener usuarioListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Usuarios usuario = dataSnapshot.getValue(Usuarios.class);
//                tvNome.setText("Nome: " + usuario.getNome());
//                tvEmail.setText("E-mail: " + usuario.getEmail());
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        };
//        usuarioDatabase.addValueEventListener(usuarioListener);
//        mUsuariosListener = usuarioListener;

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(PerfilActivity.this, MainActivity.class));
            }
        });


    }
    /*public void btnEditarAction(){
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewSwitcher switcher = (ViewSwitcher) findViewById(R.id.swEditar);
                switcher.showNext(); //or switcher.showPrevious();
                TextView myTV = (TextView) switcher.findViewById(R.id.);
                myTV.setText("value");
            }
        });
    }*/

}
