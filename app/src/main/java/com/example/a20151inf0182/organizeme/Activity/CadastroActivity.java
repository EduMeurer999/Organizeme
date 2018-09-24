package com.example.a20151inf0182.organizeme.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
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
import com.google.firebase.database.FirebaseDatabase;
import com.example.a20151inf0182.organizeme.Activity.MainActivity;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class CadastroActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

// ...

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

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
                final String nome = edtNome.getText().toString();
                String email = edtEmail.getText().toString();
                String senha = edtSenha.getText().toString();
                String senhaC = edtSenhaC.getText().toString();
//        String curso = edtCurso.getText().toString();
//        int serie = Integer.parseInt(edtSerie.getText().toString());
//        int idade = Integer.parseInt(edtIdade.getText().toString());


                if (!nome.equals("") && !email.equals("") &&
//                        !curso.equals("") &&
//                        !serie.equals("") &&
//                        !idade.equals(null) &&
                        !senha.equals("") &&
                        !senhaC.equals("")) {

                    if (senha.equals(senhaC)) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            Toast.makeText(CadastroActivity.this, "Há algum usuário conectado o momento! Por favor desconecte para continuar",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // No user is signed in

                            Usuarios usuarioCadastro = new Usuarios();
                            usuarioCadastro.setNome(nome);
                            usuarioCadastro.setEmail(email);
                            usuarioCadastro.setSenha(senha);
//                            usuarioCadastro.setCurso(curso);
//                            usuarioCadastro.setSerie(serie);
//                            usuarioCadastro.setIdade(idade);

                            String emailCadastro = usuarioCadastro.getEmail().replace("@","()");
                            int posicaoArroba = usuarioCadastro.getEmail().indexOf("@");


                            DatabaseReference usuarioAtual = mDatabase.child("Usuarios").child(emailCadastro);
                            usuarioAtual.child("nome").setValue(usuarioCadastro.getNome());
                            usuarioAtual.child("email").setValue(usuarioCadastro.getEmail());


//                            usuarioAtual.child("curso").setValue(usuarioCadastro.getCurso());
//                            usuarioAtual.child("serie").setValue(usuarioCadastro.getSerie());
//                            usuarioAtual.child("idade").setValue(usuarioCadastro.getIdade());



                            mAuth.createUserWithEmailAndPassword(usuarioCadastro.getEmail(), usuarioCadastro.getSenha())
                                    .addOnCompleteListener(CadastroActivity.this, new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                // Sign in success, update UI with the signed-in user's information
                                                Toast.makeText(CadastroActivity.this, "Cadastro realizado com sucesso!",
                                                        Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(CadastroActivity.this, MainActivity.class));


                                            } else {
                                                // If sign in fails, display a message to the user.
                                                Toast.makeText(CadastroActivity.this, "Authentication failed.",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }
                    } else {
                        Toast.makeText(CadastroActivity.this, "As senhas não coincidem", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(CadastroActivity.this, "Todos os campos devem ser preenchidos", Toast.LENGTH_SHORT).show();
                }

            }
        });


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