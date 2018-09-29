package com.example.a20151inf0182.organizeme.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.zip.Inflater;

public class TarefasActivity extends AppCompatActivity {

    private Usuarios usuario;
    private FirebaseAuth mAuth;
    @SuppressLint("ResourceType")
    private DatabaseReference mDatabase;
    private LinearLayout layoutPrincipal;
    public ArrayList<Tarefas> tarefas;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefas);
        Intent i = getIntent();

        usuario = (Usuarios) i.getSerializableExtra("Usuario");
        mDatabase = ConfiguracaoFirebase.getDatabaseReference();

        mDatabase.child("Tarefas").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final ArrayList<Tarefas> tempTarefas = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ArrayList<String[]> integrantes = new ArrayList<>();
                    for (DataSnapshot integrante : snapshot.child("integrantes").getChildren()) {
                        integrantes.add(new String[]{

                                        integrante.getKey().toString(),
                                        integrante.child("email").getValue().toString(),
                                        integrante.child("fazer").getValue().toString()

                                }

                        );
                    }
                    boolean usuarioVinculado = false;
                    for (String[] teste : integrantes) {
                        if (teste[1].equals(usuario.getEmail())) {
                            usuarioVinculado = true;
                            break;
                        } else {
                        }
                    }

                    if (snapshot.child("proprietario").getValue().toString().equals(usuario.getEmail()) || usuarioVinculado) {
                        Tarefas tarefa = new Tarefas();
//                        Toast.makeText(TarefasActivity.this, "aqui está adicionando no arrayList", Toast.LENGTH_SHORT).show();
                        tarefa.setNomeTarefa(snapshot.child("nome").getValue().toString());
                        tarefa.setaFazer(snapshot.child("fazer").getValue().toString());
                        tarefa.setIntegrantes(integrantes);
                        tarefa.setProprietario(snapshot.child("proprietario").getValue().toString());
                        tarefa.setMateria(snapshot.child("materia").getValue().toString());
                        tarefa.setStatus(snapshot.child("status").getValue().toString());
                        tarefa.setTempoEntrega(snapshot.child("tempoEntrega").getValue().toString());
                        tarefa.setTempoPrevisto(snapshot.child("tempoPrevisto").getValue().toString());
                        tarefa.setSubTarefaProp(snapshot.child("SubTarefa Proprietario").getValue().toString());

                        tempTarefas.add(tarefa);


                    }

                }
                View layoutTarefas = findViewById(R.id.lytTdsTarefas);
                for (int x = 0; x< tempTarefas.size(); x++) {
                    Tarefas tarefaTemp = new Tarefas();
                    tarefaTemp = tempTarefas.get(x);
//
//
// TextView tvTarefa = (TextView) findViewById(R.id.tvNomeTarefa);
//            TextView tvTempoRestante = (TextView) findViewById(R.id.txtTempoRes);
//            TextView tvFazer = (TextView) findViewById(R.id.txtFazer);

//            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//            df.setLenient(false);
//            Date d1 = null;
//            try {
//                d1 = df.parse(arrTemp.getTempoEntrega().toString());
//            } catch (ParseException e) {
//                e.printStackTrace();
//                Toast.makeText(getApplicationContext(), "A data de entrega da tarefa é inválida", Toast.LENGTH_SHORT).show();
//            }
//            Date d2 = null;
//            try {
//                Date dataAtual = new Date();
//                SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
//                d2 = df.parse(formatador.format(dataAtual));
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//            long dt = (d2.getTime() - d1.getTime()) + 3600000;

//            tvTarefa.setText("Tarefa: "+arrTemp.getNomeTarefa());
//            tvFazer.setText("Definição da tarefa: "+arrTemp.getaFazer());
//            tvFazer.setText("Tempo Restante: "+dt);


                    //pega o layout em que vao tds as tarefas

                    //LinearLayout layout = (LinearLayout) findViewById(R.id.info);


                    //cria um novo layout
                    LinearLayout layoutOutraTarefa = new LinearLayout(TarefasActivity.this);
                    layoutOutraTarefa.setOrientation(LinearLayout.VERTICAL);
                    layoutOutraTarefa.setPadding(0, 50, 0, 0);
                    // setar o id do layout onlick abrir a tela de tarefa
                    // layoutOutraTarefa.setId("nomeDaTarefaAqui");
                    layoutOutraTarefa.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                    //cria um novo TextView nome da tarefa
                    TextView valueTV = new TextView(TarefasActivity.this);
                    valueTV.setText("Tarefa "+tarefaTemp.getNomeTarefa());
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
                    txtFazer.setText("O que tem que fazer: "+tarefaTemp.getaFazer());
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

            }




            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


//
//        ArrayList<Tarefas> tarefasArr =
//        Toast.makeText(TarefasActivity.this, ""+tarefasArr.size(), Toast.LENGTH_SHORT).show();

    }

    public ArrayList<Tarefas> getTarefas() {
        return tarefas;
    }

    public void setTarefas(ArrayList<Tarefas> tarefas) {
        this.tarefas = tarefas;
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
