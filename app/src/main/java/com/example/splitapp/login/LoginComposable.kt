package com.example.splitapp.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.splitapp.R

@Composable
fun LoginComposable(navController: NavController?) {
    var username by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
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
            CustomInput(passedValue = username, onchange = {value -> username = value} , R.drawable.user)
            Spacer(modifier = Modifier.height(10.dp))
            CustomInput(passedValue = password, onchange = {value -> password = value} , R.drawable.oval)
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = { /*TODO*/ },
            ) {
                Text(text = "Sign In")
            }
        }


    }




}



@Composable
fun CustomInput(passedValue : String , onchange : (String) -> Unit, svgId :Int){
    var currValue = passedValue
    Surface(
        shape = RoundedCornerShape(15.dp),
        border = BorderStroke(2.dp, Color.Black),
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
                    .padding(10.dp)
                    .fillMaxWidth()
                    .height(26.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewLogin(){

    LoginComposable(null)
}
