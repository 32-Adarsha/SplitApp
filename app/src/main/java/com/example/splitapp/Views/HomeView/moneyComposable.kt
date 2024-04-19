package com.example.splitapp.Views.HomeView

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.splitapp.R
import com.example.splitapp.Views.theme.white33
import androidx.compose.ui.text.font.Font
import com.example.splitapp.Views.theme.blue32
import com.example.splitapp.Views.theme.green32
import com.example.splitapp.Views.theme.orange32
import java.util.Locale

@Composable
fun MoneyComposable() {

    Box (
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(horizontal = 30.dp),
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

@Composable
fun MoneyViewer() {
    Column (
        modifier = Modifier.padding(top = 16.dp , bottom = 16.dp)
    ){


        Text(
            text = "Adarsha,",
            modifier = Modifier.padding(start = 16.dp ),
            textAlign = TextAlign.Start,
            fontFamily = FontFamily(Font(R.font.cvfont)),
            fontSize = 30.sp,
            fontWeight = FontWeight.SemiBold,
            color = orange32
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {


            Text(
                text = "Total Owed :",
                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                textAlign = TextAlign.Start,
                fontFamily = FontFamily(Font(R.font.headingfont)),
                fontSize = 40.sp
            )

            Text(
                text = "$ 20.5",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
                    .alpha(0.8f),
                textAlign = TextAlign.End,
                fontFamily = FontFamily(Font(R.font.dmfont)),
                fontSize = 40.sp,
                color = green32,

                )

        }


    }

}


@Preview
@Composable
fun previewMoneyComposable(){
    MoneyComposable()

}

@Preview
@Composable
fun previewMoneyComposable2(){
    MoneyViewer()

}