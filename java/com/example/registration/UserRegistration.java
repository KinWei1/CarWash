package com.example.registration;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.content.Intent;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class UserRegistration extends AppCompatActivity {
    private EditText emailID, password, cfpassword, phoneNo;
    private Button btnSignUp;
    private FirebaseAuth mFirebaseAuth;
    private ProgressDialog loadingBar;

    FirebaseDatabase userinfo = FirebaseDatabase.getInstance();
    DatabaseReference ref = userinfo.getReference("userinfo");

    public final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9+._%-+]{1,256}" +
                    "@" +
                    "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" +
                    "(" +
                    "." +
                    "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" +
                    ")+"
    );



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailID = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);
        cfpassword = (EditText) findViewById(R.id.editText3);
        phoneNo = (EditText) findViewById(R.id.editText6);
        btnSignUp = (Button) findViewById(R.id.button);
        loadingBar = new ProgressDialog(this);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userinfo = FirebaseDatabase.getInstance();
                ref = userinfo.getReference("UserInfo");

                String email = emailID.getText().toString();
                String phoneNumber = phoneNo.getText().toString();
                String pwd = password.getText().toString();
                String pwd2 = cfpassword.getText().toString();

                String id = ref.push().getKey();

                UserInfo userInfo = new UserInfo(email, phoneNumber, pwd);

                ref.child(id).setValue(userInfo);

                if(email.isEmpty()){
                    emailID.setError("Please enter your E-mail");
                    emailID.requestFocus();
                }
                else if(phoneNumber.isEmpty()){
                    phoneNo.setError("Please enter your phone number");
                    phoneNo.requestFocus();
                }
                else if(pwd.isEmpty()){
                    password.setError("Please enter your password");
                    password.requestFocus();
                }
                else  if(pwd2.isEmpty()){
                    cfpassword.setError("Please confirm your password");
                    cfpassword.requestFocus();
                }
                else if(!pwd.equals(pwd2))
                {
                    Toast.makeText(UserRegistration.this, "Password do not match", Toast.LENGTH_SHORT).show();
                }
                else if(!EMAIL_ADDRESS_PATTERN.matcher(email).matches()){
                    Toast.makeText(UserRegistration.this,"Invalid Email Address",Toast.LENGTH_SHORT).show();
                }

                else {
                    loadingBar.setTitle("Creating New Account");
                    loadingBar.setMessage("Please wait for a moment");
                    loadingBar.show();
                    loadingBar.setCanceledOnTouchOutside(true);

                    mFirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(UserRegistration.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                startActivity(new Intent(UserRegistration.this,MainActivity.class));
                                loadingBar.dismiss();
                            }
                            else {
                                Toast.makeText(UserRegistration.this,"E-mail ",Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                        }
                    });
                }

            }
        });
    }
}