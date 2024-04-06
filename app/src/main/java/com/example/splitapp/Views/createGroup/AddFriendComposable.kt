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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.splitapp.DataLayer.DataModel.Friend
import com.example.splitapp.DataLayer.DataViewModel.SplitViewModel
import com.example.splitapp.Views.login.CustomInput
import com.example.splitapp.Views.theme.blue32
import com.example.splitapp.Views.theme.white33
import com.example.splitapp.R
import com.example.splitapp.Views.theme.green32





@Composable
fun AddFriendComposable(viewModel: SplitViewModel ,onAdd:(MutableList<Friend>)-> Unit ) {
    var search by remember {
        mutableStateOf("")
    }
    val friends by viewModel.friend.collectAsState()

        Column(
            modifier = Modifier
                .height(600.dp)

        ) {
            CustomInput(
                passedValue = search,
                onchange = { value -> search = value },
                svgId = R.drawable.search)
            Spacer(modifier = Modifier.height(5.dp))
            selectedFriend(friends , onAdd)

        }


}


@Composable
fun selectedFriend( friends: List<Friend>, onAdd:(MutableList<Friend>)-> Unit){
    var friendToAdd:MutableList<Friend> by remember {
        mutableStateOf(mutableListOf())
    }
    Surface (
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
    ){
        Column(
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {

                items(friends.size) { index ->
                    var isChecked by remember {
                        mutableStateOf(false)
                    }
                    var BorderColor by remember {
                        mutableStateOf(Color.Transparent)
                    }
                    var color by remember {
                        mutableStateOf(Color.Gray)
                    }
                    Surface(
                        modifier = Modifier.padding(vertical = 5.dp),
                        border = BorderStroke(1.dp, BorderColor),
                        shape = RoundedCornerShape(20.dp),
                        onClick = {
                            isChecked = !isChecked
                            if (isChecked) {
                                BorderColor = green32
                                friendToAdd.add(friends[index])
                            } else {
                                BorderColor = Color.Transparent
                                friendToAdd.remove(friends[index])
                            }

                        }
                    ) {
                        val entry = friends[index]
                        IndividualViewComposable(friend = entry){
                            Icon(painter = painterResource(id = R.drawable.check), contentDescription = "Delete", modifier = Modifier.size(25.dp) , tint = BorderColor )
                        }
                    }
                }

            }
            Button(onClick = {
                onAdd(friendToAdd)
            },
                modifier = Modifier.fillMaxWidth()) {
                Text(text = "Add Friend")
            }

        }
    }
}

//@Preview
//@Composable
//fun PreviewselectedFriend(){
//    selectedFriend()
//}
//
//
//@Preview
//@Composable
//fun PreviewfriendComposable(){
//    AddFriendComposable()
//}