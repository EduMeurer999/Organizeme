package com.example.a20151inf0182.organizeme.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.a20151inf0182.organizeme.DAO.ConfiguracaoFirebase;
import com.example.a20151inf0182.organizeme.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PerfilActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private FirebaseUser usuarioConectado;
    private MainActivity login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        mDatabase = ConfiguracaoFirebase.getFirebase();
        mAuth = ConfiguracaoFirebase.getFirebaseAutenticacao();
        usuarioConectado = mAuth.getCurrentUser();



        TextView tvNome = (TextView) findViewById(R.id.txtNome);
        TextView tvIdade = (TextView) findViewById(R.id.txtIdade);
        TextView tvCurso = (TextView) findViewById(R.id.txtCurso);
        TextView tvEmail = (TextView) findViewById(R.id.txtEmail);
        TextView tvSerie = (TextView) findViewById(R.id.txtSerie);
        Button btnLogout = (Button) findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
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
