package com.lh1169835.movietracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        //Authentication providers
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build())

        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            //TODO need to create logo for the setLogo() method
            //.setLogo()
            .build()
        signInLauncher.launch(signInIntent)

    }

    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ){ res ->
        this.onSignInResult(res)
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult){
        val response = result.idpResponse
        if(result.resultCode == RESULT_OK){
            //Successfully signed in
            val user = FirebaseAuth.getInstance().currentUser
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("user",user)
            startActivity(intent)
        }else{
            //Sign in failed. If the response is null user cancelled the sign in.
            //Otherwise check response.getError().getErrorCode() and handle the error
            Toast.makeText(this,"Sign In Failed",Toast.LENGTH_LONG).show()
            startActivity(Intent(this,SignInActivity::class.java))
        }
    }
}