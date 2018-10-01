package com.example.a20151inf0182.organizeme.Activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;


import com.example.a20151inf0182.organizeme.DAO.ConfiguracaoFirebase;
import com.example.a20151inf0182.organizeme.Entidades.Usuarios;
import com.example.a20151inf0182.organizeme.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.database.DatabaseReference;

import java.util.Date;


public class PerfilActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private FirebaseUser usuarioConectado;
    private MainActivity login;
    private static final String TAG = "PerfilActivity";
    private static final int OK_MENU_ITEM = Menu.FIRST;
    private Usuarios usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);


//
        mDatabase = ConfiguracaoFirebase.getDatabaseReference();
        mAuth = ConfiguracaoFirebase.getFirebaseAuth();
        usuarioConectado = mAuth.getCurrentUser();

        final TextView tvNome = (TextView) findViewById(R.id.txtNome);
        final TextView tvDataNasc = (TextView) findViewById(R.id.txtDataNascimento);
        final TextView tvCurso = (TextView) findViewById(R.id.txtCurso);
        final TextView tvEmail = (TextView) findViewById(R.id.txtEmail);
        final TextView tvSerie = (TextView) findViewById(R.id.txtSerie);
        final Button btnEditar = (Button) findViewById(R.id.btnEditar);


        Intent i = getIntent();
        usuario = (Usuarios) i.getSerializableExtra("Usuario");


        tvNome.setText("Nome: "+usuario.getNome());
        tvEmail.setText("Email: "+usuario.getEmail());
        tvCurso.setText("Curso: "+usuario.getCurso());
        tvDataNasc.setText("Data Nascimento: "+ usuario.getDataNascimento());
        tvSerie.setText("Serie/Ano: "+usuario.getSerie());



        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater li = getLayoutInflater();
                View view = li.inflate(R.layout.editar_dados, null);

                final AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                builder.setCancelable(false);
                builder.setTitle("Atualiza suas informações!");
                builder.setView(R.layout.editar_dados);
                builder.setPositiveButton("Atualizar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText emailAtualizar = (EditText) ((Dialog) dialog).findViewById(R.id.edtEmailAtulizar);
                        EditText cursoAtualizar = (EditText) ((Dialog) dialog).findViewById(R.id.edtCursoAtualizar);
                        EditText dataNascAtualizar = (EditText) ((Dialog) dialog).findViewById(R.id.edtDataNatualizar);
                        EditText serieAtualizar = (EditText) ((Dialog) dialog).findViewById(R.id.edtSerieAtulizar);
                        EditText nomeAtualizar = (EditText) ((Dialog) dialog).findViewById(R.id.edtNomeAtulizar);
                        EditText senhaNova = (EditText) ((Dialog) dialog).findViewById(R.id.edtSenhaNova);
                        EditText senhaAntiga = (EditText) ((Dialog) dialog).findViewById(R.id.edtSenhaAntiga);

                        String email = emailAtualizar.getText().toString();
                        String curso = cursoAtualizar.getText().toString();
                        String dataNasc = dataNascAtualizar.getText().toString();
                        String serie = serieAtualizar.getText().toString();
                        String nome = nomeAtualizar.getText().toString();
                        String novaSenha = senhaNova.getText().toString();
                        String antigaSenha = senhaAntiga.getText().toString();


                        if (usuario.getSenha().equals(antigaSenha)) {
                            if (!email.equals("") &&
                                    !nome.equals("") &&
                                    !curso.equals("") &&
                                    !dataNasc.equals("") &&
                                    !serie.equals("")
                                    ) {

                                mDatabase.child("Usuarios").child(usuario.getId()).child("nome").setValue(nome);
                                mDatabase.child("Usuarios").child(usuario.getId()).child("email").setValue(email);
                                mDatabase.child("Usuarios").child(usuario.getId()).child("curso").setValue(curso);
                                mDatabase.child("Usuarios").child(usuario.getId()).child("dataNascimento").setValue(dataNasc);
                                mDatabase.child("Usuarios").child(usuario.getId()).child("serie").setValue(serie);
                                usuarioConectado.updateEmail(email);
                                usuarioConectado.updatePassword(novaSenha);



                            }
                            else{
                                Toast.makeText(PerfilActivity.this, "Há campos em branco", Toast.LENGTH_SHORT).show();
                            }

                        }
                        else{
                            Toast.makeText(PerfilActivity.this, "Sua senha antiga não é essa!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                final AlertDialog ad = builder.create();

               ad.show();


            }
        });

//        Button btnLogout = (Button) findViewById(R.id.btnLogout);
//        btnLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mAuth.signOut();
//                startActivity(new Intent(PerfilActivity.this, MainActivity.class));
//            }
//        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.opcPerfil:
                Intent i  = new Intent(this, PerfilActivity.class);
                i.putExtra("Usuario", usuario);
                startActivity(i);
                finish();
                break;
            case R.id.opcAddTarefa:
                i = new Intent(this, AddTarefa.class);
                i.putExtra("Usuario", usuario);
                startActivity(i);
                finish();
                break;
            case R.id.opcNotas:
                i = new Intent(this, NotasActivity.class);
                i.putExtra("Usuario", usuario);
                startActivity(i);
                finish();
                break;
            case R.id.opcVerTarefas:
                i = new Intent(this, TarefasActivity.class);
                i.putExtra("Usuario", usuario);
                startActivity(i);
                finish();
                break;
            case R.id.opcAjuda:
                i = new Intent(this, AjudaActivity.class);
                i.putExtra("Usuario", usuario);
                startActivity(i);
                finish();
                break;
            case R.id.opcSair:
                mAuth.signOut();
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);



    }
//    public void btnEditarAction(){
//        btnEditar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(PerfilActivity.this, TesteActivity.class));
//            }
//        });
//    }


}
