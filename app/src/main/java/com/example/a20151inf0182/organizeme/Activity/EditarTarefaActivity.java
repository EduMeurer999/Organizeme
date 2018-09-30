package com.example.a20151inf0182.organizeme.Activity;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.example.a20151inf0182.organizeme.R;

import java.util.ArrayList;

public class EditarTarefaActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_tarefa);
        final ArrayList<String[]> integrantes = new ArrayList<>();
        final EditText edtNomeTarefa = (EditText) findViewById(R.id.edtNomeTarefa);
        final EditText edtMateria = (EditText) findViewById(R.id.edtMateria);
        final EditText edtFazer = (EditText) findViewById(R.id.edtFazer);
        final EditText edtTempoEntrega = (EditText) findViewById(R.id.edtTempoEntrega);
        final EditText edtTempoPrevisto = (EditText) findViewById(R.id.edtTempoPrevisto);
        final EditText edtPropFazer = (EditText) findViewById(R.id.edtTarefaProp);
        final Button btnCadastrarTarefa = (Button) findViewById(R.id.btnEditarTarefa);

    }
}
