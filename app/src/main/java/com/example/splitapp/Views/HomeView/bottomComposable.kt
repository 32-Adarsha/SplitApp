package com.example.splitapp.Views.HomeView

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.splitapp.R
import com.example.splitapp.Views.theme.orange32
import com.example.splitapp.Views.theme.white32
import com.example.splitapp.Views.theme.white33

@Composable
fun BottomComposable (navController: NavController? , path:String? ){
    BottomAppBar(
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.primary,
        modifier = Modifier.background(color = Color.Black)
    ) {
        Box (
            contentAlignment = Alignment.Center,


        ) {

            FloatComposable2(navController = navController, navRoute = path!! )
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
                    tint = white32
                )
            }
        }

    }
}


@Composable
fun FloatComposable2 (navController: NavController?, navRoute: String) {
    FloatingActionButton(
        modifier = Modifier
            .size(60.dp),
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

@Preview
@Composable
fun PreviewBottomComposable(){
    BottomComposable(null , "tes")
}