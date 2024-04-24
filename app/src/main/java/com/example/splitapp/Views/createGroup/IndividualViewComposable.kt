package com.example.splitapp.Views.createGroup

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.splitapp.R
import com.example.splitapp.Views.theme.blue32
import com.example.splitapp.Views.theme.orange32
import com.example.splitapp.Views.theme.white33


@Composable
fun IndividualViewComposable(friendUserName: String , friendName:String, side: @Composable () -> Unit){
    var color by remember {
        mutableStateOf(Color.Gray)
    }
    Surface(
        modifier = Modifier.padding(3.dp),
        color = Color.Transparent,
        shape = RoundedCornerShape(5.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(vertical = 5.dp , horizontal = 5.dp)
                .fillMaxWidth()

        ) {
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Surface(
                    border = BorderStroke(1.dp, Color.Black),
                    modifier = Modifier.size(50.dp),
                    color = white33,
                    shape = RoundedCornerShape(20.dp),
                ) {

                }
                Spacer(modifier = Modifier.width(10.dp))
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "$friendUserName",
                        modifier = Modifier,
                        textAlign = TextAlign.Start,
                        fontFamily = FontFamily(Font(R.font.headingfont)),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 30.sp,
                        color = orange32

                    )
                    Text(
                        text = friendName,
                        modifier = Modifier,
                        textAlign = TextAlign.Start,
                        fontFamily = FontFamily(Font(R.font.cvfont)),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        color = orange32

                    )
                }


            }

            side()

        }
    }
}



//@Preview
//@Composable
//fun PreviewIndComposable(){
//    var color by remember {
//        mutableStateOf(Color.Gray)
//    }
//    IndividualViewComposable(friend = Friend("Adarsha" , "Kira")) {
//        Icon(painter = painterResource(id = R.drawable.check), contentDescription = "" , modifier = Modifier.size(50.dp))
//    }
//}