package com.example.splitapp.Views.Group

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
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
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.splitapp.DataLayer.DataModel.GroupLog
import com.example.splitapp.DataLayer.DataModel.Usermodel
import com.example.splitapp.DataLayer.DataViewModel.SplitViewModel
import com.example.splitapp.Views.theme.blue32
import com.example.splitapp.Views.theme.orange32
import com.example.splitapp.Views.theme.white33


@Composable
fun LogViewComposable(splitViewModel: SplitViewModel , id:String){
    var allLog  = splitViewModel.allGroupLog.collectAsState().value[id]
    Surface (

        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp)
            .padding(top = 10.dp)
    ){

        LazyColumn(){

            if (allLog != null) {
                items(allLog.size){ index ->
                    LogCardComposable(allLog[index] ,splitViewModel)
                }
            }
        }

    }
}



@Composable
fun LogCardComposable(gLog:GroupLog , splitViewModel: SplitViewModel){
    var user = splitViewModel.getUserFromId(gLog.owner!!)
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
                        text = gLog.name!!,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = blue32
                    )
                    Text(
                        text = "${user!!.username} posted $${gLog.total}",
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
                            .padding(vertical = 10.dp),
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(1.dp, Color.Black),
                    ) {
                        Column (
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp, vertical = 2.dp)
                        ) {
                            Text(text = "${user?.username?.uppercase()} lend" , fontWeight = FontWeight.Medium , fontSize = 22.sp)
                            gLog.involved!!.forEach { (id,owed) ->
                                var oUser = splitViewModel.getUserFromId(id)
                                Row (
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(text = oUser!!.username!! , fontSize = 20.sp , fontWeight = FontWeight.Medium)
                                    Text(text = "$owed" , fontSize = 18.sp , fontWeight = FontWeight.Bold)
                                }
                            }
                        }

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
