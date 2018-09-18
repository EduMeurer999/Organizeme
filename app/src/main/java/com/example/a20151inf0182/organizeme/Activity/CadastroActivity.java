package com.example.a20151inf0182.organizeme.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        EditText edtNome = (EditText) findViewById(R.id.edtNome);
        EditText edtEmail = (EditText) findViewById(R.id.edtEmail);
        EditText edtSenha = (EditText) findViewById(R.id.edtSenha);
        EditText edtSenhaC = (EditText) findViewById(R.id.edtSenhaC);
        String nome = edtNome.getText().toString();
        String email = edtEmail.getText().toString();
        String senha = edtSenha.getText().toString();
        String senhaC = edtSenhaC.getText().toString();
        if(!nome.equals("") && !email.equals("") && !senha.equals("") && senhaC.equals("")){
            if(senha.equals(senhaC)){
                Usuarios usuario = new Usuarios();
                usuario.setEmail(email);
                usuario.setSenha(senha);
                usuario.setNome(nome);
                cadastrarUsuario(usuario);



            }
            else{
                Toast.makeText(CadastroActivity.this, "As senhas não coincidem", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(CadastroActivity.this, "Todos os campos deverão ser preenchidos", Toast.LENGTH_SHORT).show();
        }



    }

    public void cadastrarUsuario(Usuarios user){


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