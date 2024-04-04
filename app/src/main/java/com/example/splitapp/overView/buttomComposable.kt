package com.example.splitapp.overView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
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
import com.example.splitapp.ui.theme.orange32
import com.example.splitapp.ui.theme.white32
import com.example.splitapp.ui.theme.white33


@Composable
fun BComposable(navController: NavController? , navRoute : String){
    Box (
        contentAlignment = Alignment.Center
    ) {
        BarComposable()
        FloatComposable(navController = navController , navRoute = navRoute)
    }
}
@Composable
fun BarComposable(){
    Surface {
        Box(
            modifier = Modifier
                .height(80.dp)
                .background(green32)
        ){
            Row (
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
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

@Composable
fun FloatComposable(navController: NavController? , navRoute: String) {
    FloatingActionButton(
        modifier = Modifier
            .size(70.dp),
        onClick = {navController?.navigate(navRoute) },
        elevation = FloatingActionButtonDefaults.elevation(5.dp, 5.dp, 5.dp, 5.dp),
        shape = CircleShape,
        containerColor = white33
    ) {
       Icon(painter = painterResource(id = R.drawable.plus), contentDescription = "add",
           modifier = Modifier.size(25.dp),
           tint = orange32     )
    }
}

@Preview(name = "buttom")
@Composable
fun preview2(){

}