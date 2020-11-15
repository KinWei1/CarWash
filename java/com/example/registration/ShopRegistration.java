package com.example.registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class ShopRegistration extends AppCompatActivity {
    private EditText emailID, password, cfpassword, shopName, shopAdr, phoneNo;
    private Button btnSignUp;
    private FirebaseAuth mFirebaseAuth;
    private ProgressDialog loadingBar;

    FirebaseDatabase shopuserinfo = FirebaseDatabase.getInstance();
    DatabaseReference ref = shopuserinfo.getReference("shopuserinfo");

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
        setContentView(R.layout.activity_shop_registration);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailID = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        cfpassword = findViewById(R.id.editText3);
        shopName = findViewById(R.id.editText4);
        shopAdr = findViewById(R.id.editText5);
        phoneNo = findViewById(R.id.editText6);
        btnSignUp = findViewById(R.id.button);
        loadingBar = new ProgressDialog(this);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shopuserinfo = FirebaseDatabase.getInstance();
                ref = shopuserinfo.getReference("ShopInfo");

                String email = emailID.getText().toString();
                String pwd = password.getText().toString();
                String pwd2 = cfpassword.getText().toString();
                String SName = shopName.getText().toString();
                String SAdr = shopAdr.getText().toString();
                String phoneNumber = phoneNo.getText().toString();

                String id = ref.push().getKey();


                ShopInfo getShopInfo = new ShopInfo(email, pwd, phoneNumber, SName, SAdr);

                ref.child(id).setValue(getShopInfo);

                if(email.isEmpty()){
                    emailID.setError("Please enter your E-mail");
                    emailID.requestFocus();
                }
                else if(phoneNumber.isEmpty()){
                    phoneNo.setError("Please enter your phone number");
                    phoneNo.requestFocus();
                }
                else  if(SName.isEmpty()){
                    shopName.setError("Please enter your Shop Name");
                    shopName.requestFocus();
                }
                else  if(SAdr.isEmpty()){
                    shopAdr.setError("Please enter your Shop Address");
                    shopAdr.requestFocus();
                }
                else  if(pwd.isEmpty()){
                    password.setError("Please enter your password");
                    password.requestFocus();
                }
                else  if(pwd2.isEmpty()){
                    cfpassword.setError("Please confirm your password");
                    cfpassword.requestFocus();
                }
                else  if(!pwd.equals(pwd2))
                {
                    Toast.makeText(ShopRegistration.this, "Password do not match", Toast.LENGTH_SHORT).show();
                }
                else  if(email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(ShopRegistration.this,"Please enter your E-mail and Password",Toast.LENGTH_SHORT).show();
                }
                else  if(SName.isEmpty() && SAdr.isEmpty()){
                    Toast.makeText(ShopRegistration.this,"Please enter the information for your shop",Toast.LENGTH_SHORT).show();
                }
                else if(!EMAIL_ADDRESS_PATTERN.matcher(email).matches()){
                    Toast.makeText(ShopRegistration.this,"Invalid Email Address",Toast.LENGTH_SHORT).show();
                }
                else {
                    loadingBar.setTitle("Creating New Account");
                    loadingBar.setMessage("Please wait for a moment");
                    loadingBar.show();
                    loadingBar.setCanceledOnTouchOutside(true);

                    mFirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(ShopRegistration.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                startActivity(new Intent(ShopRegistration.this,MainActivity.class));
                                loadingBar.dismiss();
                            }
                            else {
                                Toast.makeText(ShopRegistration.this,"SignUp Unsuccessful, Please Try Again",Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                        }
                    });
                }
            }
        });
    }
}
