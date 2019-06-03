package com.example.lab_gsb;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    EditText nom_, prenom_, email_, password_, confirmPassword_;
    DataHelper dbcenter;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        dbcenter = new DataHelper(this);

        InitializeFields();
    }

    private void InitializeFields() {
        nom_ = findViewById(R.id.editText_nom);
        prenom_ = findViewById(R.id.editText_prenom);
        email_ = findViewById(R.id.editText_email);
        password_ = findViewById(R.id.editText_password);
        confirmPassword_ = findViewById(R.id.editText_confirmPassword);
    }

    public void UserSignUp(View view) {
        String nom = nom_.getText().toString();
        String prenom = prenom_.getText().toString();
        String email = email_.getText().toString();
        String password = password_.getText().toString();
        String confirmPassword = confirmPassword_.getText().toString();

        if(nom.equals("") || nom.trim().isEmpty() || prenom.equals("") || prenom.trim().isEmpty() || email.equals("") || email.trim().isEmpty() ||
            password.equals("") || password.trim().isEmpty() || confirmPassword.equals("") || confirmPassword.trim().isEmpty()) {

            Toast.makeText(SignupActivity.this, "DATA needed", Toast.LENGTH_LONG).show();
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(SignupActivity.this, "email needed", Toast.LENGTH_LONG).show();
        }else if (!confirmPassword.equals(password)){
            Toast.makeText(SignupActivity.this, "bad password", Toast.LENGTH_LONG).show();
        }
        else{
            SQLiteDatabase query = dbcenter.getReadableDatabase();
            cursor = query.rawQuery("SELECT idVis FROM Visiteur WHERE email = '"+ email + "'", null);
            cursor.moveToFirst();
            if(cursor.getCount() > 0) {
                Toast.makeText(getApplicationContext(), "email" + email + "account already exist", Toast.LENGTH_LONG).show();
            }
            else{
                query.execSQL("INSERT INTO Visiteur (nom, prenom, email, password) VALUES ('" +
                        nom +"','" +
                        prenom +"','" +
                        email +"','" +
                        password +"')");
                Toast.makeText(getApplicationContext(), "account register",Toast.LENGTH_LONG).show();

                SendUserToLoginActivity();
            }
        }

        // SendUserToLoginActivity();
    }

    private void SendUserToLoginActivity() {
        Intent LoginIntent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(LoginIntent);
    }
}
