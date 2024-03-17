package com.example.splitapp.Group

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.example.splitapp.R
import com.example.splitapp.login.LoginComposable
import com.example.splitapp.overView.BComposable
import com.example.splitapp.ui.theme.blue32
import com.example.splitapp.ui.theme.green32
import com.example.splitapp.ui.theme.orange32


val friend:Map<String , Float> = mapOf(
    "Samir" to 5.0f,
    "Aakash" to 6.0f,
    "Suman" to 8.0f,
    "Krishna" to 0.0f
)


@Composable
fun GroupOverViewComposable (navController: NavController? ,groupTitle:String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 10.dp)
        ) {
            GroupTopComposable(name = "Vatmara")
            Spacer(modifier = Modifier.height(50.dp))
            GroupMidTopComposable(friend = friend)
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Transaction",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                color = blue32,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            LogViewComposable()
        }
        BComposable()

    }

}

@Composable
fun GroupTopComposable(name:String){
    Row (
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(painter = painterResource(id = R.drawable.backarrow), contentDescription = "backArrow")
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
fun GroupMidTopComposable(friend:Map<String,Float>){
    LazyRow(){
        items(friend.size){index ->
            val entry = friend.entries.elementAt(index)
            friendViewComposable(name = entry.key, owes = entry.value)
        }
    }

}

@Composable
fun friendViewComposable(name:String , owes:Float){
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 5.dp)
    ) {
        Surface (
            modifier = Modifier.size(80.dp),
            color = orange32,
            shape = CircleShape
        ) {

        }

        Text(text = name, fontSize =  20.sp , fontWeight = FontWeight.Bold , color = blue32)
        Text(text = "$${owes.toString()}" , fontSize =  15.sp , fontWeight = FontWeight.Bold , color = green32)
    }
}







// Only Preview After This



@Preview(name = "Friend")
@Composable
fun PreviewfriendView(){
    friendViewComposable(name = "Adarsha", owes = 5.0f)
}

@Preview(name = "MidComposable")
@Composable
fun PreviewGroupMidComposable(){

    GroupMidTopComposable( friend)
}


@Preview
@Composable
fun previewGroupOverViewComposable (){
    GroupOverViewComposable(null,"Vatmara")
}