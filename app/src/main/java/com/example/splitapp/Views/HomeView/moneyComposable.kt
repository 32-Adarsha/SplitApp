package com.example.splitapp.Views.HomeView

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.splitapp.DataLayer.DataViewModel.SplitViewModel
import com.example.splitapp.R
import com.example.splitapp.Views.theme.green32
import com.example.splitapp.Views.theme.orange32
import com.example.splitapp.Views.theme.white33



@Composable
fun MoneyViewer(splitViewModel: SplitViewModel) {
    var totalOwed = 0f
    splitViewModel.allOwed.collectAsState().value.forEach { (id, nextmap) ->

        nextmap.forEach { (id,value) ->
            totalOwed += value
        }
    }
    var owed = 0f

    var c = Color.Gray

    if (totalOwed > 0f){
        owed = totalOwed
        c = green32
    } else if (totalOwed == 0f) {
        owed = totalOwed
    } else {
        owed = totalOwed * -1
        c = orange32
    }


    Column (
        modifier = Modifier.padding(bottom = 16.dp)
    ){



        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Owed",
                modifier = Modifier
                    .padding(start = 20.dp, end = 16.dp),

                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.headingfont)),
                fontSize = 70.sp,

                )

                Text(
                    text = "$ $owed",
                    modifier = Modifier
                        .padding(start = 15.dp, end = 16.dp),

                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily(Font(R.font.headingfont)),
                    fontSize = 70.sp,
                    color = c,
                )

        }


    }

}




//@Preview
//@Composable
//fun previewMoneyComposable2(){
//    MoneyViewer()
//
//}