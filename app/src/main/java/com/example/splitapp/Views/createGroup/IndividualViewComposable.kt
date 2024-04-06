package com.example.splitapp.Views.createGroup

import android.util.Log
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
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
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
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.splitapp.DataLayer.DataModel.Friend
import com.example.splitapp.Views.theme.blue32
import com.example.splitapp.Views.theme.white33
import com.example.splitapp.R
import com.example.splitapp.Views.Group.LogCardComposable
import com.example.splitapp.Views.Group.friend
import com.example.splitapp.Views.theme.green32
import com.example.splitapp.Views.theme.orange32

@Composable
fun IndividualViewComposable(friend: Friend , side: @Composable () -> Unit){
    var color by remember {
        mutableStateOf(Color.Gray)
    }
    Surface(
        modifier = Modifier.padding(3.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(vertical = 5.dp)
                .fillMaxWidth()
                .height(50.dp),
        ) {
            Row (
                horizontalArrangement = Arrangement.SpaceBetween
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
                        text = friend.name,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = blue32
                    )
                    Text(
                        text = friend.nickname,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = blue32,
                        fontStyle = FontStyle.Italic
                    )
                }


            }

            side()

        }
    }
}



@Preview
@Composable
fun PreviewIndComposable(){
    var color by remember {
        mutableStateOf(Color.Gray)
    }
    IndividualViewComposable(friend = Friend("Adarsha" , "Kira")) {
        Icon(painter = painterResource(id = R.drawable.check), contentDescription = "" , modifier = Modifier.size(50.dp))
    }
}