package com.example.a20151inf0182.organizeme.Activity;

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
import android.widget.ListView;
import android.widget.ProgressBar;


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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefas);
        Intent i = getIntent();
        usuario = (Usuarios) i.getSerializableExtra("Usuario");

//list view ou details
        ListView listview = (ListView) findViewById(R.id.listview);
        String[] dados = new String[] { "Cupcake", "Donut", "Eclair", "Froyo", "Gingerbread",
                "Honeycomb", "Ice Cream Sandwich", "Jelly Bean",
                "KitKat", "Lollipop", "Marshmallow", "Nougat" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dados);
        listview.setAdapter(adapter);

//barra de progresso
        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.background);
        ProgressBar mProgress = (ProgressBar) findViewById(R.id.progressbar1);
        mProgress.setProgress(75);   // Main Progress
//        mProgress.setSecondaryProgress(50); // Secondary Progress
        mProgress.setMax(100); // Maximum Progress
        mProgress.setProgressDrawable(drawable);


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
