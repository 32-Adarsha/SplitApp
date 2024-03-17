package com.example.splitapp.createGroup

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.splitapp.Group.friendViewComposable
import com.example.splitapp.login.CustomInput
import com.example.splitapp.ui.theme.blue32
import com.example.splitapp.ui.theme.white33
import com.example.splitapp.R
import com.example.splitapp.ui.theme.green32

val friend:Map<String , Float> = mapOf(
    "Samir" to 5.0f,
    "Aakash" to 6.0f,
    "Suman" to 8.0f,
    "Krishna" to 0.0f,
    "Samir" to 5.0f,
    "Aakasha" to 6.0f,
    "Sumana" to 8.0f,
    "Krishnaa" to 0.0f,
    "Samira" to 5.0f,
    "Aakasha" to 6.0f,
    "Sumana" to 8.0f,
    "Krishnaa" to 0.0f
)

@Composable
fun AddFriendComposable(){
    var search by remember {
        mutableStateOf("")
    }
    Surface (
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp, vertical = 10.dp)
        ){
            CustomInput(passedValue = search, onchange ={value -> search =value} , svgId = R.drawable.search)
            Spacer(modifier = Modifier.height(5.dp))
            selectedFriend()

        }
    }
}

@Composable
fun friendListComposable(name:String){
    var isChecked by remember {
        mutableStateOf(false)
    }
    var checkedColor = if (isChecked) green32 else Color.Gray
    Surface (
        onClick = {isChecked = !isChecked}
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 15.dp, vertical = 5.dp)
                .fillMaxWidth()
                .height(50.dp),

            ) {
            Row {
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
                        text = name,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = blue32
                    )
                    Text(
                        text = "nickname",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = blue32,
                        fontStyle = FontStyle.Italic
                    )
                }

            }

            Icon(
                painter = painterResource(id = R.drawable.check),
                contentDescription = "checkBox",
                modifier = Modifier.size(15.dp),
                tint = checkedColor,
            )


        }
    }
}
@Composable
fun selectedFriend(){
    Surface (
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
    ){
        LazyColumn(){
            items(friend.size){index ->
                val entry = friend.entries.elementAt(index)
                friendListComposable(name = entry.key )
            }
        }
    }
}

@Preview
@Composable
fun PreviewselectedFriend(){
    selectedFriend()
}

@Preview
@Composable
fun PreviewfriendViewComposable(){
    friendListComposable(name = "Adarsha")
}

@Preview
@Composable
fun PreviewfriendComposable(){
    AddFriendComposable()
}