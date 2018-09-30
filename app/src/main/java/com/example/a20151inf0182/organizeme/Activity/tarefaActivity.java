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
import com.example.a20151inf0182.organizeme.Entidades.Tarefas;
import com.example.a20151inf0182.organizeme.Entidades.Usuarios;
import com.example.a20151inf0182.organizeme.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class tarefaActivity extends AppCompatActivity {

    private Usuarios usuario;
    private FirebaseAuth mAuth;
    private Tarefas tarefa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefa);
        Intent i = getIntent();
        final TextView txtTarefa = (TextView)findViewById(R.id.txtNomeTarefa);
        final TextView txtDefinicao = (TextView)findViewById(R.id.txtDefinicao);
        final TextView txtDiaEntrega = (TextView) findViewById(R.id.txtTempoEntrega);
        final TextView txtDiaPrevisto = (TextView) findViewById(R.id.txtTempoPrevisto);
        final TextView txtMateria = (TextView) findViewById(R.id.txtMateria);
        final Button btnArtigos = (Button) findViewById(R.id.btnArtigos);
        final Button btnEditar = (Button) findViewById(R.id.btnEditar);
        final Button btnFinalizar = (Button) findViewById(R.id.btnFinalizar);

        tarefa = (Tarefas) i.getSerializableExtra("tarefa");
        usuario = (Usuarios) i.getSerializableExtra("Usuario");

        txtMateria.setText("Materia: "+ tarefa.getMateria());
        txtDefinicao.setText("Definição da tarefa: "+tarefa.getaFazer());
        txtDiaEntrega.setText("Data da entrega: "+tarefa.getTempoEntrega());
        txtDiaPrevisto.setText("Data Prevista: "+tarefa.getTempoPrevisto());




        btnArtigos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sTarefa=String.valueOf(txtTarefa.getText().toString());
                String pesquisa = sTarefa.replace(" ", "+");
                Uri uriUrl = Uri.parse("https://scholar.google.com.br/scholar?hl=pt-BR&as_sdt=0%2C5&q="+pesquisa+"&oq=");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);

//                String sDefinicao=String.valueOf(txtDefinicao.getText().toString());
//                String var1 = sDefinicao.substring(21,sDefinicao.length());
//                String pesquisa2 = var1.replace(" ", "+");
//                Uri uriUrl2 = Uri.parse("https://scholar.google.com.br/scholar?hl=pt-BR&as_sdt=0%2C5&q="+pesquisa2+"&oq=");
//                Intent launchBrowser2 = new Intent(Intent.ACTION_VIEW, uriUrl2);
//                startActivity(launchBrowser2);
            }
        }) ;


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
