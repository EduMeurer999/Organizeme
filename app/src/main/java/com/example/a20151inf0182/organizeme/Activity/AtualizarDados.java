package com.example.a20151inf0182.organizeme.Activity;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a20151inf0182.organizeme.DAO.ConfiguracaoFirebase;
import com.example.a20151inf0182.organizeme.Entidades.Usuarios;
import com.example.a20151inf0182.organizeme.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class AtualizarDados extends AppCompatActivity {

    private DatabaseReference Database;
    private FirebaseAuth Auth;
    private FirebaseUser usuarioConectado;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_dados);
        final Usuarios usuario = (Usuarios) getIntent().getSerializableExtra("usuario");
        Database = ConfiguracaoFirebase.getDatabaseReference();
        Auth = ConfiguracaoFirebase.getFirebaseAuth();
        usuarioConectado = Auth.getCurrentUser();

        final EditText edtNome = findViewById(R.id.edtNomeAtualizar);
        final EditText edtCurso = findViewById(R.id.edtCursoAtualizar);
        final EditText edtEmail = findViewById(R.id.edtEmailAtualizar);
        final EditText edtDataNasc = findViewById(R.id.edtDataNascimentoAtualizar);
        final EditText edtSerie = findViewById(R.id.edtSerieAtualizar);
        final EditText edtSenhaNova = findViewById(R.id.edtSenhaAtualizar);

        final Button btnAtualizar = findViewById(R.id.btnAtualizar);
        final Button btnCancelar = findViewById(R.id.btnCancelar);


        edtNome.setText(usuario.getNome());
        edtCurso.setText(usuario.getCurso());
        edtEmail.setText(usuario.getEmail());
        edtDataNasc.setText(usuario.getDataNascimento());
        edtSerie.setText(usuario.getSerie());
        btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                String nome = edtNome.getText().toString();
                String dataNasc = edtDataNasc.getText().toString();
                String curso = edtCurso.getText().toString();
                String serie = edtSerie.getText().toString();
                String senhaNova = edtSenhaNova.getText().toString();




                    if (!email.equals("") &&
                            !nome.equals("") &&
                            !curso.equals("") &&
                            !dataNasc.equals("") &&
                            !serie.equals("") &&
                            !senhaNova.equals("")

                            ) {


                        Database.child("Usuarios").child(usuario.getId()).child("nome").setValue(nome);
                        Database.child("Usuarios").child(usuario.getId()).child("email").setValue(email);
                        Database.child("Usuarios").child(usuario.getId()).child("curso").setValue(curso);
                        Database.child("Usuarios").child(usuario.getId()).child("dataNascimento").setValue(dataNasc);
                        Database.child("Usuarios").child(usuario.getId()).child("serie").setValue(serie);
                        usuarioConectado.updateEmail(email);
                        usuarioConectado.updatePassword(senhaNova);
                        usuario.setEmail(email);
                        usuario.setNome(nome);
                        usuario.setCurso(curso);
                        usuario.setSenha(senhaNova);
                        usuario.setDataNascimento(dataNasc);
                        usuario.setSerie(serie);
                    }



                else{
                        Toast.makeText(AtualizarDados.this, "Há campos em branco", Toast.LENGTH_SHORT).show();
                }
            }

        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AtualizarDados.this, PerfilActivity.class);
                i.putExtra("Usuario",usuario);
                startActivity(i);
                finish();
            }
        });



    }
}
