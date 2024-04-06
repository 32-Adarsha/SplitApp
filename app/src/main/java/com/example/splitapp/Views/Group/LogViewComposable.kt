package com.example.splitapp.Views.Group

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.splitapp.DataLayer.DataViewModel.SplitViewModel
import com.example.splitapp.Views.theme.blue32
import com.example.splitapp.Views.theme.orange32
import com.example.splitapp.Views.theme.white33


@Composable
fun LogViewComposable(viewModel: SplitViewModel , id:Int){
    val allGroup by viewModel.allGroup.collectAsState()
    Surface (

        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp)
            .padding(top = 10.dp)
    ){

        LazyColumn(){
            val myItems = allGroup[id].log
            items(myItems.size){index ->
                LogCardComposable(myItems[index].name)
            }
        }

    }
}



@Composable
fun LogCardComposable(name:String){
    var expanded by remember {
        mutableStateOf(false)
    }
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp)
                .animateContentSize(
                    animationSpec = tween(
                        delayMillis = 300,
                        easing = LinearOutSlowInEasing
                    )
                )
        ) {
            Column {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, Color.Black),
                    modifier = Modifier.size(50.dp),
                    color = white33,
                    onClick = { expanded = !expanded }
                ) {

                }
                Spacer(modifier = Modifier.width(10.dp))
                Column(

                ) {
                    Text(
                        text = name,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = blue32
                    )
                    Text(
                        text = "Adarsha $100",
                        fontWeight = FontWeight.Medium,
                        fontStyle = FontStyle.Italic,
                        color = orange32
                    )
                }
            }

                if (expanded){
                    Surface (
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .padding(vertical = 10.dp),
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(1.dp, Color.Black),
                    ) {


                    }
                }

        }
    }

}


//
//@Composable
//@Preview
//fun PreviewLogViewComposable(){
//    //LogViewComposable()
//}
//@Preview
//@Composable
//fun PreviewLogCardComposable(){
//    LogCardComposable()
//}
