package com.example.a20151inf0182.organizeme.Activity;


import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.a20151inf0182.organizeme.DAO.ConfiguracaoFirebase;
import com.example.a20151inf0182.organizeme.Entidades.Usuarios;
import com.example.a20151inf0182.organizeme.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class tarefaActivity extends AppCompatActivity {

    private Button btnArtigos;
    private Usuarios usuario;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefa);
        Intent i = getIntent();
        usuario = (Usuarios) i.getSerializableExtra("Usuario");
        //teste
        btnArtigos = (Button) findViewById(R.id.btnArtigos);
        btnArtigos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txtTarefa = (TextView)findViewById(R.id.txtNomeTarefa);
                String sTarefa=String.valueOf(txtTarefa.getText().toString());
                String pesquisa = sTarefa.replace(" ", "+");
                Uri uriUrl = Uri.parse("https://scholar.google.com.br/scholar?hl=pt-BR&as_sdt=0%2C5&q="+pesquisa+"&oq=");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        });
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
                break;
            case R.id.opcAddTarefa:
                i = new Intent(this, AddTarefa.class);
                i.putExtra("Usuario", usuario);
                startActivity(i);
                break;
            case R.id.opcNotas:
                i = new Intent(this, NotasActivity.class);
                i.putExtra("Usuario", usuario);
                startActivity(i);
                break;
            case R.id.opcVerTarefas:
                i = new Intent(this, TarefasActivity.class);
                i.putExtra("Usuario", usuario);
                startActivity(i);
                break;
            case R.id.opcAjuda:
                i = new Intent(this, AjudaActivity.class);
                i.putExtra("Usuario", usuario);
                startActivity(i);
                break;
            case R.id.opcSair:
                mAuth.signOut();
                startActivity(new Intent(this, MainActivity.class));
                break;

        }
        return super.onOptionsItemSelected(item);



    }
}
