package com.example.a20151inf0182.organizeme.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.a20151inf0182.organizeme.DAO.ConfiguracaoFirebase;
import com.example.a20151inf0182.organizeme.Entidades.Tarefas;
import com.example.a20151inf0182.organizeme.Entidades.Usuarios;
import com.example.a20151inf0182.organizeme.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.Inflater;

public class TarefasActivity extends AppCompatActivity {

    private Usuarios usuario;
    private FirebaseAuth mAuth;
    @SuppressLint("ResourceType")
    private DatabaseReference mDatabase;
    private LinearLayout layoutPrincipal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefas);
        Intent i = getIntent();


        usuario = (Usuarios) i.getSerializableExtra("Usuario");
        mDatabase = ConfiguracaoFirebase.getDatabaseReference();

        mDatabase.child("Tarefas").addListenerForSingleValueEvent(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final ArrayList<Tarefas> tempTarefas = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ArrayList<String[]> integrantes = new ArrayList<>();
                    for (DataSnapshot integrante : snapshot.child("integrantes").getChildren()) {
                        integrantes.add(new String[]{


                                        integrante.child("email").getValue().toString(),
                                        integrante.child("fazer").getValue().toString(),
                                        integrante.getKey().toString()


                                }

                        );
                    }
                    boolean usuarioVinculado = false;
                    for (String[] teste : integrantes) {
                        if (teste[0].equals(usuario.getEmail())) {
                            usuarioVinculado = true;
                            break;
                        } else {
                        }
                    }

                    if (snapshot.child("proprietario").getValue().toString().equals(usuario.getEmail()) || usuarioVinculado) {
                        Tarefas tarefa = new Tarefas();
//                        Toast.makeText(TarefasActivity.this, "aqui está adicionando no arrayList", Toast.LENGTH_SHORT).show();
                        tarefa.setIdTarefa(snapshot.getKey().toString());
                        tarefa.setNomeTarefa(snapshot.child("nome").getValue().toString());
                        tarefa.setaFazer(snapshot.child("fazer").getValue().toString());
                        tarefa.setIntegrantes(integrantes);
                        tarefa.setProprietario(snapshot.child("proprietario").getValue().toString());
                        tarefa.setMateria(snapshot.child("materia").getValue().toString());
                        tarefa.setStatus(snapshot.child("status").getValue().toString());
                        tarefa.setTempoEntrega(snapshot.child("tempoEntrega").getValue().toString());
                        tarefa.setTempoPrevisto(snapshot.child("dataCriacao").getValue().toString());
                        tarefa.setSubTarefaProp(snapshot.child("SubTarefa Proprietario").getValue().toString());

                        tempTarefas.add(tarefa);


                    }

                }
                View layoutTarefas = findViewById(R.id.lytTdsTarefas);
                for (int x = 0; x < tempTarefas.size(); x++) {
                    final Tarefas tarefaTemp;
                    tarefaTemp = tempTarefas.get(x);
                    if (!tarefaTemp.getStatus().equals("Em andamento")) {
                        LinearLayout layoutOutraTarefa = new LinearLayout(TarefasActivity.this);
                        layoutOutraTarefa.setOrientation(LinearLayout.VERTICAL);
                        layoutOutraTarefa.setPadding(0, 50, 0, 0);
                        // setar o id do layout onlick abrir a tela de tarefa
                        int id = View.generateViewId();
                        layoutOutraTarefa.setId(id);

                        layoutOutraTarefa.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                        //cria um novo TextView nome da tarefa
                        TextView valueTV = new TextView(TarefasActivity.this);
                        valueTV.setText("✔ Finalizado: " + tarefaTemp.getNomeTarefa());
                        valueTV.setTextSize(Float.parseFloat("18"));
                        //id pra tarefa (5 esta só para teste)
//        valueTV.setId(5);
                        valueTV.setLayoutParams(new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT));


                        TextView txtTempoRestante = new TextView(TarefasActivity.this);
                        txtTempoRestante.setText("Tempo restante: ");
//        txtFazer.setTextSize(Float.parseFloat("18"));
                        //id pra txttarefa (6 esta só para teste)
//        txtFazer.setId(6);
                        txtTempoRestante.setLayoutParams(new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT));

                        //cria um novo TextView o que tem q ser feito
                        TextView txtFazer = new TextView(TarefasActivity.this);
                        txtFazer.setText("O que tem que fazer: " + tarefaTemp.getaFazer());
//        txtFazer.setTextSize(Float.parseFloat("18"));
                        //id pra txttarefa (6 esta só para teste)
//        txtFazer.setId(6);
                        txtFazer.setLayoutParams(new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT));


                        //add no layout das tarefas
                        ((LinearLayout) layoutTarefas).addView(layoutOutraTarefa);
                        ((LinearLayout) layoutOutraTarefa).addView(valueTV);
                        ((LinearLayout) layoutOutraTarefa).addView(txtTempoRestante);
                        ((LinearLayout) layoutOutraTarefa).addView(txtFazer);
                        findViewById(id).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent a = new Intent(TarefasActivity.this, tarefaActivity.class);
                                a.putExtra("tarefa", tarefaTemp);
                                a.putExtra("Usuario", usuario);
                                startActivity(a);
                            }
                        });
                    } else {
                        LinearLayout layoutOutraTarefa = new LinearLayout(TarefasActivity.this);
                        layoutOutraTarefa.setOrientation(LinearLayout.VERTICAL);
                        layoutOutraTarefa.setPadding(0, 50, 0, 0);
                        // setar o id do layout onlick abrir a tela de tarefa
                        int id = View.generateViewId();
                        layoutOutraTarefa.setId(id);

                        layoutOutraTarefa.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                        //cria um novo TextView nome da tarefa
                        TextView valueTV = new TextView(TarefasActivity.this);
                        valueTV.setText(tarefaTemp.getNomeTarefa());
                        valueTV.setTextSize(Float.parseFloat("18"));
                        //id pra tarefa (5 esta só para teste)
//        valueTV.setId(5);
                        valueTV.setLayoutParams(new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT));

                        //cria barra de progresso
                        Resources res = getResources();
                        Drawable drawable = res.getDrawable(R.drawable.background);
                        ProgressBar barraProgresso = new ProgressBar(TarefasActivity.this, null, R.style.Widget_AppCompat_ProgressBar_Horizontal);
                        //id aqui
                        //barraProgresso.setId("");

//                        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
//                        Date dataMaxima = null;
//                        Date dataAtual = null;
//                        Long dataMa = null;
//                        Long dataA = null ;
//                        try {
//                            dataAtual = new Date(System.currentTimeMillis());
//                            dataA = dataAtual.getTime();
//                            dataMaxima =  formato.parse(tarefaTemp.getTempoEntrega());
//                            dataMa = dataMaxima.getTime();
//
//                        } catch (ParseException e) {
//                            e.printStackTrace();
//                        }





                        barraProgresso.setProgress(50);
                        barraProgresso.setMax(100);
                        barraProgresso.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                        barraProgresso.setProgressDrawable(drawable);

                        TextView txtTempoRestante = new TextView(TarefasActivity.this);
                        txtTempoRestante.setText("Tempo restante: ");
//        txtFazer.setTextSize(Float.parseFloat("18"));
                        //id pra txttarefa (6 esta só para teste)
//        txtFazer.setId(6);
                        txtTempoRestante.setLayoutParams(new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT));

                        //cria um novo TextView o que tem q ser feito
                        TextView txtFazer = new TextView(TarefasActivity.this);
                        txtFazer.setText("O que tem que fazer: " + tarefaTemp.getaFazer());
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
                        findViewById(id).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent a = new Intent(TarefasActivity.this, tarefaActivity.class);
                                a.putExtra("tarefa", tarefaTemp);
                                a.putExtra("Usuario", usuario);
                                startActivity(a);
                            }
                        });
                    }
                }


            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


//
//        ArrayList<Tarefas> tarefasArr =
//        Toast.makeText(TarefasActivity.this, ""+tarefasArr.size(), Toast.LENGTH_SHORT).show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.opcPerfil:
                Intent i = new Intent(this, PerfilActivity.class);
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
}
