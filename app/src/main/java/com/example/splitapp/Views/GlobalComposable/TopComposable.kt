package com.example.splitapp.Views.GlobalComposable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.splitapp.R
import com.example.splitapp.Views.theme.orange32


@Composable
fun TopComposable(navController: NavController?) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Surface(
                onClick = { navController?.popBackStack() }
            ) {

                Icon(

                    painter = painterResource(id = R.drawable.backarrow),
                    contentDescription = "backArrow",
                    modifier = Modifier.size(30.dp)
                )
            }
            Surface(
                onClick = {  }
            ) {

                Icon(

                    painter = painterResource(id = R.drawable.outlinesetting),
                    contentDescription = "backArrow",
                    modifier = Modifier.size(30.dp),
                    tint = orange32
                )
            }
        }
    }


@Preview
@Composable
fun previewTopComposable(){
    TopComposable(null)
}