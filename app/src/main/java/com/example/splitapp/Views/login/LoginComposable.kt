package com.example.splitapp.Views.login

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.splitapp.DataLayer.DataViewModel.AuthViewModel
import com.example.splitapp.DataLayer.DataViewModel.DataViewModel
import com.example.splitapp.DataLayer.DataViewModel.SplitViewModel
import com.example.splitapp.R
import com.example.splitapp.Views.theme.green32
import com.example.splitapp.Views.theme.orange32
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun LoginComposable(navController: NavController?, authViewModel: AuthViewModel, testModle:DataViewModel, splitViewModel: SplitViewModel) {
    var corontine = rememberCoroutineScope()
    var error = splitViewModel.error.collectAsState().value
    val thisUser = authViewModel.thisUser.collectAsState()
    var loading = splitViewModel.loading.collectAsState().value
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }


    var errorMessage by remember {
        mutableStateOf("")
    }

    if (thisUser.value != null){
        corontine.launch {splitViewModel.requestGroupRequest(thisUser.value!!.uid)  }
        navController?.navigate("overView")
    }

    if(loading){
        Dialog(onDismissRequest = { /*TODO*/ }) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
        }
    }



    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo",
                modifier = Modifier.size(300.dp)
            )
            Text(
                text = "SPLIT",
                fontSize = 35.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = email,
                onValueChange = { newText ->
                    email = newText

                },
                label = {
                    Text(text = "Username")
                },
                shape = RoundedCornerShape(percent = 20),
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = password,
                onValueChange = { newText ->
                    password = newText

                },
                visualTransformation =  PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                label = {
                    Text(text = "Password")
                },
                placeholder = { Text(text = "Type password here") },
                shape = RoundedCornerShape(percent = 20),
            )
            Spacer(modifier = Modifier.height(10.dp))
            if (error != null){
                Text(text = error , color = orange32)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {

                    authViewModel.signin(email, password)
                },
            ) {
                Text(text = "Sign In")
            }
            Surface (
                onClick = {navController?.navigate("register")}
            ){
                Text(text = "New! Register your account" , textDecoration = TextDecoration.Underline)
            }
        }


    }




}



@Composable
fun CustomInput(passedValue : String , onchange : (String) -> Unit, svgId :Int){
    var currValue = passedValue
    var isFocused by remember {
        mutableStateOf(false)
    }
    val borderColor = if (isFocused) green32 else Color.Black
    Surface(
        shape = RoundedCornerShape(15.dp),
        border = BorderStroke(2.dp, borderColor),
        modifier = Modifier
            .onFocusChanged { isFocused = it.isFocused }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(15.dp)
        ) {
            Icon(
                painter = painterResource(id = svgId),
                contentDescription = "Logo",
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))

            BasicTextField(
                value = currValue,
                onValueChange = {it -> onchange(it)},
                singleLine = true,
                textStyle = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Normal),
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth()
                    .height(30.dp)

            )
        }
    }
}


