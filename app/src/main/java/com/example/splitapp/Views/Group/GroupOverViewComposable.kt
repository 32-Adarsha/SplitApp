package com.example.splitapp.Views.Group

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.splitapp.DataLayer.DataViewModel.SplitViewModel
import com.example.splitapp.R
import com.example.splitapp.Views.HomeView.BottomComposable
import com.example.splitapp.Views.login.LoginComposable

import com.example.splitapp.Views.theme.blue32
import com.example.splitapp.Views.theme.green32
import com.example.splitapp.Views.theme.orange32
import com.example.splitapp.Views.theme.white33
import kotlinx.coroutines.flow.collect


val friend:Map<String , Float> = mapOf(
    "Samir" to 5.0f,
    "Aakash" to 6.0f,
    "Suman" to 8.0f,
    "Krishna" to 0.0f
)


@Composable
fun GroupOverViewComposable (navController: NavController? , name: String, viewModel: SplitViewModel , id:Int) {
    val allG by viewModel.allGroup.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 10.dp)
        ) {
            GroupTopComposable(navController ,name = allG[id].name)
            Spacer(modifier = Modifier.height(50.dp))
            GroupMidTopComposable(viewModel , id)
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Transaction",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                color = blue32,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(Color.Transparent),
                ) {
                LogViewComposable(viewModel , id)
            }
            BottomComposable(navController = navController , path = "makeTransaction")
        }

        



    }

}

@Composable
fun GroupTopComposable(navController: NavController?, name:String){
    Row (
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface (
            onClick ={
                navController?.popBackStack()
            }
        ){
            Icon(painter = painterResource(id = R.drawable.backarrow), contentDescription = "backArrow")

        }
       Surface(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Text(text = name ,
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }


    }
}


@Composable
fun GroupMidTopComposable(viewModel: SplitViewModel , id: Int){
    val group by viewModel.allGroup.collectAsState()
    LazyRow(
        modifier = Modifier.padding(horizontal = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        item {
            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                Surface (
                    border = BorderStroke(1.dp, Color.Black),
                    modifier = Modifier
                        .size(70.dp),
                    color = white33,
                    shape = CircleShape
                ) {
                    Icon(painter = painterResource(id = R.drawable.plus), contentDescription = "Add",
                        modifier = Modifier.padding(20.dp), tint = blue32
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                Text(text = "Add" , fontSize =  20.sp , fontWeight = FontWeight.Bold , color = orange32)


            }
        }
        val f = group[id].member
        items(f.size){index ->
            val entry = f[index]
            friendViewComposable(entry.name)
        }
    }

}

@Composable
fun friendViewComposable(name:String , owes:Float = 0f){
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 10.dp)
    ) {
        Surface (
            border = BorderStroke(1.dp, Color.Black),
            modifier = Modifier.size(70.dp),
            color = white33,
            shape = CircleShape
        ) {

        }
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = name, fontSize =  20.sp , fontWeight = FontWeight.Bold , color = blue32)
//        Text(text = "$${owes.toString()}" , fontSize =  15.sp , fontWeight = FontWeight.Bold , color = green32)
    }
}







// Only Preview After This



//@Preview(name = "Friend")
//@Composable
//fun PreviewfriendView(){
//    friendViewComposable(name = "Adarsha", owes = 5.0f)
//}

//@Preview(name = "MidComposable")
//@Composable
//fun PreviewGroupMidComposable(){
//
//    GroupMidTopComposable( friend)
//}
//

//@Preview
//@Composable
//fun previewGroupOverViewComposable (){
//    GroupOverViewComposable(null,"Vatmara" , null , 0)
//}