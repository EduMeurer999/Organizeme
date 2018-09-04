package com.example.a20151inf0182.organizeme.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.a20151inf0182.organizeme.R;

public class PerfilActivity extends AppCompatActivity {


    TextView txtNome = (TextView)findViewById(R.id.txtNome);
    TextView txtEmail = (TextView)findViewById(R.id.txtEmail);
    TextView txtCurso = (TextView)findViewById(R.id.txtCurso);
    TextView txtSerie = (TextView)findViewById(R.id.txtSerie);
    TextView txtIdade = (TextView)findViewById(R.id.txtIdade);
    TextView[] TXTs = new TextView[5];
    EditText[] EDTs = new EditText[5];

    Button btnEditar = (Button)findViewById(R.id.btnEditar);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        TXTs[0] = txtNome;
        TXTs[1] = txtEmail;
        TXTs[2] = txtCurso;
        TXTs[3] = txtSerie;
        TXTs[4] = txtIdade;






    }
    public void btnEditarAction(){
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewSwitcher switcher = (ViewSwitcher) findViewById(R.id.swEditar);
                switcher.showNext(); //or switcher.showPrevious();
                TextView myTV = (TextView) switcher.findViewById(R.id.);
                myTV.setText("value");
            }
        });
    }
}
