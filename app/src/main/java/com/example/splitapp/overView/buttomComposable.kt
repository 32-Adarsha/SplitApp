package com.example.splitapp.overView

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.splitapp.R
import com.example.splitapp.ui.theme.green32
import com.example.splitapp.ui.theme.white32


@Composable
fun buttomComposable(){
    Surface {
        Box(
            modifier = Modifier.height(100.dp)
        ){
            Surface(

            ) {
                Icon(
                    painter = painterResource(id = R.drawable.bnav),
                    contentDescription = "navBar",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(1000.dp)
                )
            }
            Row (
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = "Home" ,
                    modifier = Modifier.size(40.dp),
                    tint = white32
                )
                Icon(
                    painter = painterResource(id = R.drawable.friend),
                    contentDescription = "Friends",
                    modifier = Modifier.size(50.dp),
                    tint = white32)
            }
        }
    }
}


@Preview(name = "buttom")
@Composable
fun preview2(){
    buttomComposable()
}