package com.example.a20151inf0182.organizeme.Activity;

import android.content.Context;
import android.content.Intent;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a20151inf0182.organizeme.DAO.ConfiguracaoFirebase;
import com.example.a20151inf0182.organizeme.Entidades.Usuarios;
import com.example.a20151inf0182.organizeme.R;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private EditText edtEmail;
    private EditText edtSenha;
    private Button btnLogar;
    private FirebaseAuth autenticacao;
    private Usuarios usuario;
    private FirebaseUser usuarioConectado;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //fazer a tela de login video aula 3
        autenticacao = FirebaseAuth.getInstance();
        usuarioConectado = autenticacao.getCurrentUser();
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        btnLogar = (Button) findViewById(R.id.btnLogar);
        Button btnTeste = (Button) findViewById(R.id.btnTeste);
        final TextView tvTeste = (TextView) findViewById(R.id.tvTeste);
        btnTeste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = "tes.te123ee@email.com";
                int posicao = email.indexOf("@");
                for(int i =0; i< email.length(); i++){


                }
//                if(email.indexOf(".") != -1){
//                    if(email.indexOf(".") > posicao){
//                        tvTeste.setText(""+email.indexOf("."));
//                    }
//                }


//                tvTeste.setText(""+posicao);


            }
        });
        if (usuarioConectado != null){
            startActivity(new Intent(MainActivity.this, PerfilActivity.class));


        }


        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edtEmail.getText().toString().equals("") && !edtSenha.getText().toString().equals("")){
                    usuario = new Usuarios();
                    usuario.setEmail(edtEmail.getText().toString());
                    usuario.setSenha(edtSenha.getText().toString());
                    validarLogin(usuario);
                }else{
                    Toast.makeText(MainActivity.this, "Há algo errado", Toast.LENGTH_SHORT).show();
                }
            }
        });


        TextView txtCadastro = findViewById(R.id.txtCadastro);
        txtCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaCadastro = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(telaCadastro);
            }
        });

    }


    public void validarLogin(Usuarios usuario){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        Toast.makeText(MainActivity.this, usuario.getSenha(), Toast.LENGTH_LONG);
        autenticacao.signInWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        startActivity(new Intent(MainActivity.this, PerfilActivity.class));
                        Toast.makeText(MainActivity.this, "Login efetuado com sucesso", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this, "Usuário inexistente", Toast.LENGTH_SHORT).show();
                    }
            }
        });

    }

}
