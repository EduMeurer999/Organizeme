package com.example.a20151inf0182.organizeme.DAO;



import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by 20151inf0115 on 03/09/2018.
 */

public class ConfiguracaoFirebase{

    private static DatabaseReference referenciaFirebase;
    private static FirebaseAuth autenticacao;


    public static DatabaseReference getFirebase(){
        if(referenciaFirebase==null){
            referenciaFirebase= FirebaseDatabase.getInstance().getReference();
        }
        return referenciaFirebase;
    }
    public static FirebaseAuth getFirebaseAutenticacao(){
        if(autenticacao==null){
            autenticacao=FirebaseAuth.getInstance();
        }
        return autenticacao;
    }
}
