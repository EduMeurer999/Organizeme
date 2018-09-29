package com.example.a20151inf0182.organizeme.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.a20151inf0182.organizeme.DAO.ConfiguracaoFirebase;
import com.example.a20151inf0182.organizeme.Entidades.Usuarios;
import com.example.a20151inf0182.organizeme.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class TarefasActivity extends AppCompatActivity {

    private Usuarios usuario;
    private FirebaseAuth mAuth;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefas);
        Intent i = getIntent();
        usuario = (Usuarios) i.getSerializableExtra("Usuario");



        //pega o layout em que vao tds as tarefas
        View layoutTarefas =  findViewById(R.id.lytTdsTarefas);
        //LinearLayout layout = (LinearLayout) findViewById(R.id.info);


        //cria um novo layout
        LinearLayout layoutOutraTarefa = new LinearLayout(this);
        layoutOutraTarefa.setOrientation(LinearLayout.VERTICAL);
        layoutOutraTarefa.setPadding(0,50,0,0);
        // setar o id do layout onlick abrir a tela de tarefa
        // layoutOutraTarefa.setId("nomeDaTarefaAqui");
        layoutOutraTarefa.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        //cria um novo TextView nome da tarefa
        TextView valueTV = new TextView(this);
        valueTV.setText("Tarefa Y");
        valueTV.setTextSize(Float.parseFloat("18"));
        //id pra tarefa (5 esta só para teste)
//        valueTV.setId(5);
        valueTV.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        //cria barra de progresso
        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.background);
        ProgressBar barraProgresso =  new ProgressBar(this, null, R.style.Widget_AppCompat_ProgressBar_Horizontal);
        //id aqui
        //barraProgresso.setId("");
        barraProgresso.setProgress(50);
        barraProgresso.setMax(100);
        barraProgresso.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
        barraProgresso.setProgressDrawable(drawable);

        TextView txtTempoRestante = new TextView(this);
        txtTempoRestante.setText("Tempo restante:");
//        txtFazer.setTextSize(Float.parseFloat("18"));
        //id pra txttarefa (6 esta só para teste)
//        txtFazer.setId(6);
        txtTempoRestante.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));


        //cria um novo TextView o que tem q ser feito
        TextView txtFazer = new TextView(this);
        txtFazer.setText("O que tem que fazer:");
//        txtFazer.setTextSize(Float.parseFloat("18"));
        //id pra txttarefa (6 esta só para teste)
//        txtFazer.setId(6);
        txtFazer.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));



        //add no layout das tarefas
        ((LinearLayout) layoutTarefas).addView(layoutOutraTarefa);
        ((LinearLayout) layoutOutraTarefa).addView(valueTV);
        ((LinearLayout) layoutOutraTarefa).addView(barraProgresso);
        ((LinearLayout) layoutOutraTarefa).addView(txtTempoRestante);
        ((LinearLayout) layoutOutraTarefa).addView(txtFazer);

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
