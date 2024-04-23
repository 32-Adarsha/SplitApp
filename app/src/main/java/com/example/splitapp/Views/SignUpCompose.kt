package com.example.splitapp.Views

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.splitapp.DataLayer.DataModel.Usermodel
import com.example.splitapp.DataLayer.DataViewModel.AllFriendViewModel
import com.example.splitapp.DataLayer.DataViewModel.AuthViewModel
import com.example.splitapp.DataLayer.DataViewModel.DataViewModel
import com.example.splitapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch
import java.util.UUID


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SignUpCompose(navController: NavController? , authViewModel: AuthViewModel? , dataViewModel: DataViewModel?) {
    val coroutineScope = rememberCoroutineScope()
    val thisUser by authViewModel?.thisUser!!.collectAsState()
    var email by remember {
         mutableStateOf("")
    }
    var username by remember {
        mutableStateOf("")
    }
    var first_name by remember {
        mutableStateOf("")
    }
    var last_name by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var logedIn by remember {
        mutableStateOf(false)
    }

    if(thisUser != null){
        coroutineScope.launch {
            var userObj = Usermodel(thisUser!!.uid,email,null,null ,username, first_name, last_name)
            dataViewModel?.addSelf(thisUser!!.uid, userObj)
            navController?.navigate("login")
        }
    }



    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = 10.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo",
                modifier = Modifier.size(300.dp)
            )

        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(bottom = 30.dp)
        ) {
            Text(
                text = "Sign Up",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                color = MaterialTheme.colorScheme.primary,
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = email,
                onValueChange = { newText ->
                    email = newText
                },
                label = {
                    Text(text = "Email")
                },
                shape = RoundedCornerShape(percent = 20),
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = username,
                onValueChange = { newText ->
                    username = newText
                },
                label = {
                    Text(text = "Username")
                },
                shape = RoundedCornerShape(percent = 20),
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = first_name,
                onValueChange = { newText ->
                    first_name = newText
                },
                label = {
                    Text(text = "First Name")
                },
                shape = RoundedCornerShape(percent = 20),
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = last_name,
                onValueChange = { newText ->
                    last_name = newText
                },
                label = {
                    Text(text = "Last Name")
                },
                shape = RoundedCornerShape(percent = 20),
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = password,
                onValueChange = { newText ->
                    password = newText
                },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                label = {
                    Text(text = "Password")
                },
                placeholder = { Text(text = "Type password here") },
                shape = RoundedCornerShape(percent = 20),
            )
            Button(onClick = {
                    authViewModel?.signup(email, password)

//                    if (user != null) {
//                        var userObj= Usermodel(email , username ,first_name , last_name)
//                        dataViewModel?.addSelf(user.uid , userObj)
//                        Log.e("User", user.uid)
//                        navController?.navigate("login")
//                    } else {
//                        Toast.makeText(
//                            context,
//                            "Sign up failed",
//                            Toast.LENGTH_LONG
//                        ).show()
//                    }
//                    allFriendViewModel?.seeAllFriend()

            }, shape = RoundedCornerShape(percent = 20), modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)) {
                Text(text = "Sign Up")
            }

            Surface(
                onClick = {navController?.navigate("login")}
            ) {
                Text(text = "Login", textDecoration = TextDecoration.Underline)
            }
        }
    }
}

@Preview
@Composable
fun PreviewSignUPCompose() {
    SignUpCompose(null ,null  , null)
}