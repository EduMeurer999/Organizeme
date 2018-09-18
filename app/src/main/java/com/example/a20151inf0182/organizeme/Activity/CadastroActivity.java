package com.example.a20151inf0182.organizeme.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a20151inf0182.organizeme.Entidades.Usuarios;
import com.example.a20151inf0182.organizeme.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CadastroActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        final EditText edtNome = (EditText) findViewById(R.id.edtNome);
        final EditText edtEmail = (EditText) findViewById(R.id.edtEmail);
        final EditText edtSenha = (EditText) findViewById(R.id.edtSenha);
        final EditText edtSenhaC = (EditText) findViewById(R.id.edtSenhaC);
        final Button btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
//        EditText edtCurso = (EditText) findViewById(R.id.edtCurso);
//        EditText edtSerie = (EditText) findViewById(R.id.edtSerie);
//        EditText edtIdade = (EditText) findViewById(R.id.edtIdade);


        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = edtNome.getText().toString();
                String email = edtEmail.getText().toString();
                String senha = edtSenha.getText().toString();
                String senhaC = edtSenhaC.getText().toString();
//        String curso = edtCurso.getText().toString();
//        String serie = edtSerie.getText().toString();
//        int idade = Integer.parseInt(edtIdade.getText().toString());


                if (!nome.equals("") && !email.equals("") &&
//                        !curso.equals("") &&
//                        !serie.equals("") &&
//                        !idade.equals(null) &&
                                !senha.equals("") &&
                                !senhaC.equals("")) {

                    if (senha.equals(senhaC)) {
                        Usuarios usuario = new Usuarios();
                        usuario.setNome(nome);
                        usuario.setEmail(email);
                        usuario.setSenha(senha);
//                usuario.setCurso(curso);
//                usuario.setSerie(serie);
//                usuario.setIdade(idade);


                    } else {
                        Toast.makeText(CadastroActivity.this, "As senhas não coincidem", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(CadastroActivity.this, "Todos os campos devem ser preenchidos", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    public void cadastrarUsuario(Usuarios user) {


    }
//    public byte[] encriptarSenha(String senha){
//        try {
//            MessageDigest enc = MessageDigest.getInstance("MD5");
//           byte messageDigest[] = enc.digest(senha.getBytes("UTF-8"));
//            return messageDigest;
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//            return null;
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//            return null;
//        }
//
//  }
}