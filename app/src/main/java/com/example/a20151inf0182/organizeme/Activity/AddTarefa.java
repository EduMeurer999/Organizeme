package com.example.a20151inf0182.organizeme.Activity;


import android.app.Dialog;
import android.content.DialogInterface;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class AddTarefa extends AppCompatActivity {


    private FirebaseAuth Auth;
    private FirebaseUser usuarioConectado;
    private DatabaseReference Database;
    private Usuarios usuario;
    private Tarefas tarefa;
    TextView tvIntegrantes;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tarefa);
        Database = ConfiguracaoFirebase.getDatabaseReference();
        Auth = ConfiguracaoFirebase.getFirebaseAuth();

        usuario = (Usuarios) getIntent().getSerializableExtra("Usuario");
        tarefa = new Tarefas();

        final ArrayList<String[]> integrantes = new ArrayList<>();
        final EditText edtNomeTarefa = (EditText) findViewById(R.id.edtNomeTarefa);
        final EditText edtMateria = (EditText) findViewById(R.id.edtMateria);
        final EditText edtFazer = (EditText) findViewById(R.id.edtFazer);
        final EditText edtTempoEntrega = (EditText) findViewById(R.id.edtTempoEntrega);
        final EditText edtPropFazer = (EditText) findViewById(R.id.edtTarefaProp);
        final Button btnCadastrarTarefa = (Button) findViewById(R.id.btnCadastrarTarfefa);
        tvIntegrantes = (TextView) findViewById(R.id.tvIntegrantes);

        LayoutInflater li = getLayoutInflater();
        View view = li.inflate(R.layout.alert_dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Integrantes");
        builder.setView(R.layout.alert_dialog);


        //SET posistive Button
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                final EditText edtEmail = (EditText) ((Dialog) dialog).findViewById(R.id.email);
                final EditText fazer = (EditText) ((Dialog) dialog).findViewById(R.id.fazer);
                if (
                        !edtEmail.getText().toString().equals("") &&
                                !edtEmail.getText().toString().equals("")) {

                    Database.child("Usuarios")
                            .addListenerForSingleValueEvent(
                                    new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            boolean existe = false;
                                            for (DataSnapshot snapshot :
                                                    dataSnapshot.getChildren()) {
                                                if (snapshot.child("email").getValue().toString()
                                                        .equals(edtEmail.getText().toString()) && !snapshot.child("email").getValue().toString().equals(usuario.getEmail())) {
                                                    integrantes.add(new String[]{
                                                            edtEmail.getText().toString(),
                                                            fazer.getText().toString(),
                                                            snapshot.getKey().toString()});
                                                    existe = true;

                                                    atualizarTXT(integrantes);
                                                    break;
                                                } else if (usuario.getEmail().equals(edtEmail.getText().toString())) {
                                                    Toast.makeText(getApplicationContext(), "O proprietário já é um integrante", Toast.LENGTH_SHORT).show();
                                                    existe = true;
                                                    break;
                                                }
                                                else if(!snapshot.getValue().toString().equals(edtEmail.getText().toString())){
                                                    existe= false;
                                                }

                                            }
                                            if(!existe){
                                                Toast.makeText(getApplicationContext(), "Usuário Inexistente", Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    }

                            );

                } else {
                    Toast.makeText(getApplicationContext(), "Nenhum campo deve estar em branco", Toast.LENGTH_SHORT).show();
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
        Button btnAddIntegrantes = (Button) findViewById(R.id.btnAddIntegrantes);
        btnAddIntegrantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ad.show();


            }
        });
        Button removerIntegrante = (Button) findViewById(R.id.btnDropIntegrante);
        removerIntegrante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                integrantes.remove(integrantes.size() - 1);
                atualizarTXT(integrantes);
            }
        });

        btnCadastrarTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fazer = edtFazer.getText().toString();
                String nomeTarefa = edtNomeTarefa.getText().toString();
                String dataEntrega = edtTempoEntrega.getText().toString();
                String subTarefaProp = edtPropFazer.getText().toString();
                String materia = edtMateria.getText().toString();


                if (!fazer.equals("")
                        && !nomeTarefa.equals("")
                        && !dataEntrega.equals("")
                        && !subTarefaProp.equals("")
                        && !materia.equals("")) {
                    String status = "Em andamento";

                    String keyPush;
                    DatabaseReference idTarefa = Database.child("Tarefas").push();
                    keyPush = idTarefa.getKey().toString();
                    tarefa.setIdTarefa(keyPush);
                    tarefa.setaFazer(fazer);
                    tarefa.setNomeTarefa(nomeTarefa);
                    tarefa.setTempoEntrega(dataEntrega);
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    Calendar a = Calendar.getInstance();
                    Date data = new Date();
                    a.setTime(data);
                    Date data_atual = a.getTime();
                    tarefa.setTempoPrevisto(format.format(data_atual));
                    tarefa.setSubTarefaProp(subTarefaProp);
                    tarefa.setMateria(materia);
                    tarefa.setIntegrantes(integrantes);

                    Database.child("Tarefas").child(keyPush).child("fazer").setValue(tarefa.getaFazer());
                    Database.child("Tarefas").child(keyPush).child("nome").setValue(tarefa.getNomeTarefa());
                    Database.child("Tarefas").child(keyPush).child("tempoEntrega").setValue(tarefa.getTempoEntrega());
                    Database.child("Tarefas").child(keyPush).child("dataCriacao").setValue(tarefa.getTempoPrevisto());
                    Database.child("Tarefas").child(keyPush).child("SubTarefa Proprietario").setValue(tarefa.getSubTarefaProp());
                    Database.child("Tarefas").child(keyPush).child("materia").setValue(tarefa.getMateria());
                    Database.child("Tarefas").child(keyPush).child("proprietario").setValue(usuario.getEmail());
                    Database.child("Tarefas").child(keyPush).child("status").setValue(status);
                    DatabaseReference integrantes = Database.child("Tarefas").child(keyPush).child("integrantes");

                    for (String[] arrTemp : tarefa.getIntegrantes()) {
                        String keyPushIntegrante;
                        keyPushIntegrante = integrantes.push().getKey().toString();

                        integrantes.child(keyPushIntegrante).child("email").setValue(arrTemp[0]);
                        integrantes.child(keyPushIntegrante).child("fazer").setValue(arrTemp[1]);
                    }
                    Toast.makeText(getApplicationContext(), "Tarefa cadastrada com sucesso!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), TarefasActivity.class);
                    i.putExtra("Usuario",usuario);
                    startActivity(i);
                    finish();

                }
                else{
                    Toast.makeText(getApplicationContext(), "Todos campos devem ser preenchidos", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    public void atualizarTXT(ArrayList<String[]> arr) {
        String texto = "";
        for (String[] tmpArr : arr) {
            texto += tmpArr[0].toString() + "\n";
        }
        tvIntegrantes.setText(texto);
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
                Auth.signOut();
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);


    }

}





