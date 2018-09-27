package com.example.a20151inf0182.organizeme.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.a20151inf0182.organizeme.DAO.ConfiguracaoFirebase;
import com.example.a20151inf0182.organizeme.Entidades.Usuarios;
import com.example.a20151inf0182.organizeme.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private EditText edtEmail;
    private EditText edtSenha;
    private Button btnLogar;

    private FirebaseAuth Auth;
    private FirebaseUser usuarioConectado;
    private DatabaseReference Database;
    private Button btnTeste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Database = ConfiguracaoFirebase.getDatabaseReference();
        Auth = ConfiguracaoFirebase.getFirebaseAuth();
        final Usuarios usuario = new Usuarios();
        usuarioConectado = Auth.getCurrentUser();
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        btnLogar = (Button) findViewById(R.id.btnLogar);
        btnTeste = (Button) findViewById(R.id.btnTeste);


        btnTeste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent perfil=new Intent(MainActivity.this, PerfilActivity.class);
//                startActivity(perfil);
//                finish();
                // startActivity(new Intent(MainActivity.this, PerfilActivity.class));
//                Toast.makeText(MainActivity.this, ""+usuario.getId(), Toast.LENGTH_SHORT).show();


            }
        });

        final TextView tvTeste = (TextView) findViewById(R.id.tvTeste);
        if (usuarioConectado != null){
            edtEmail.setText(usuarioConectado.getEmail().toString());
            Toast.makeText(MainActivity.this, "Por favor insira sua senha novamente", Toast.LENGTH_SHORT).show();
//            startActivity(new Intent(MainActivity.this, PerfilActivity.class));


        }

//        btnLogar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(!edtEmail.getText().toString().equals("") && !edtSenha.getText().toString().equals("")){
//                    validarLogin();
//                }else{
//                    Toast.makeText(MainActivity.this, "Há algo errado", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });


        TextView txtCadastro = findViewById(R.id.txtCadastro);

        txtCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaCadastro = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(telaCadastro);
            }
        });

    }


    public void validarLogin(){
        final Usuarios usuario = new Usuarios();
        Auth.signInWithEmailAndPassword(edtEmail.getText().toString(), edtSenha.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Database.child("Usuarios").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                                    if(snapshot.child("email").getValue().toString().equals(edtEmail.getText().toString())) {
                                        usuario.setId((String) snapshot.getKey().toString());
                                        usuario.setEmail((String) snapshot.child("email").getValue().toString());
                                        usuario.setNome((String) snapshot.child("nome").getValue().toString());
                                        Toast.makeText(MainActivity.this, ""+usuario.getId(), Toast.LENGTH_SHORT).show();
                                        Intent mainToPerfil = new Intent(MainActivity.this, PerfilActivity.class);
                                        mainToPerfil.putExtra("Usuario",usuario);
                                        startActivity(mainToPerfil);
                                        Toast.makeText(MainActivity.this, "Login efetuado com sucesso", Toast.LENGTH_SHORT).show();
                                        finish();
                                        break;
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    }else{
                        Toast.makeText(MainActivity.this, "Usuário inexistente", Toast.LENGTH_SHORT).show();
                    }
            }
        });

    }

}
