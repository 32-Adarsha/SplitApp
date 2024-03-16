package com.example.splitapp.overView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.magnifier
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.splitapp.R
import com.example.splitapp.ui.theme.orange32

@Composable
fun topComposable(){
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ){
        Surface (

        ){
            Icon(
                painter = painterResource(
                    id = R.drawable.setting,
                ),
                tint = orange32,
                contentDescription = "setting",
                modifier = Modifier
                    .size(34.dp)

            )
        }
        Surface (){
            Icon(
                painter = painterResource(id = R.drawable.user),
                contentDescription = "user profile" ,
                modifier = Modifier
                    .size(30.dp))
        }
    }
}

@Preview
@Composable
fun preview(){
    topComposable()
}