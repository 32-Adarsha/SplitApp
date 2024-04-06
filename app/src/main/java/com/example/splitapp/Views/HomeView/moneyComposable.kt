package com.example.splitapp.Views.HomeView

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.splitapp.R
import com.example.splitapp.Views.theme.white33

@Composable
fun MoneyComposable() {

    Box (
        modifier = Modifier.fillMaxWidth().height(200.dp).padding(horizontal = 30.dp),
        contentAlignment = Alignment.Center

    ) {



        Image(
            painter = painterResource(id = R.drawable.test),
            contentDescription = stringResource(id = R.string.user_photo),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier

        )

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "$750.0",
                fontSize = 60.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                color = white33,
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.Black,
                        offset = Offset(10f, 10f),
                        blurRadius = 8f
                    )
                )
            )
        }



    }
}


@Preview
@Composable
fun previewMoneyComposable(){
    MoneyComposable()
}