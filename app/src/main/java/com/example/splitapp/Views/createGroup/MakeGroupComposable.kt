package com.example.splitapp.Views.createGroup

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.splitapp.DataLayer.DataViewModel.AuthViewModel
import com.example.splitapp.DataLayer.DataViewModel.SplitViewModel
import com.example.splitapp.R
import com.example.splitapp.Views.GlobalComposable.HeaderComposable
import com.example.splitapp.Views.GlobalComposable.TopComposable
import com.example.splitapp.Views.theme.blue32
import com.example.splitapp.Views.theme.orange32
import kotlinx.coroutines.launch

@Composable
fun MakeGroupComposable (
    navController: NavController,
    splitViewModel: SplitViewModel,
    authViewModel: AuthViewModel,
) {
    val thisUser = authViewModel.thisUser.collectAsState()
    val allFriend = splitViewModel.friend.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var member:MutableList<String> by remember {
        mutableStateOf(mutableListOf())
    }


    var selectfriend by remember {
        mutableStateOf(false)
    }



    if (selectfriend){
        Dialog(onDismissRequest = {

        }) {
            Surface(
                modifier = Modifier.padding(horizontal = 5.dp , vertical = 15.dp),
                shape = RoundedCornerShape(20.dp),
                border = BorderStroke(1.dp, Color.Black),

                ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .padding(top = 15.dp, bottom = 5.dp)
                ) {
                    AddFriendComposable(splitViewModel) { friends: MutableList<String> ->
                        run {
                            member = ((member + friends).toMutableList())
                            selectfriend = false
                        }
                    }

                }
            }

        }
    }


Column (

    verticalArrangement = Arrangement.Center,
    modifier = Modifier.padding(15.dp)
) {
    TopComposable(navController = navController)
    HeaderComposable(title = "Create Group")
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = name,
        onValueChange = {name = it },
        label = { Text("Name") }
    )

    OutlinedTextField(
        modifier = Modifier
            .height(120.dp)
            .fillMaxWidth(),
        value = description,
        onValueChange = { description = it },
        label = { Text("Description") }
    )

    Spacer(modifier = Modifier.height(5.dp))

    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(text = "Select Friend",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = blue32
        )
        Surface (
            onClick = { selectfriend = true}

        ){
            Icon(painter = painterResource(id = R.drawable.plus), contentDescription = "Add Friend" ,
                tint = Color.Black,
            )
        }
        



    }
    LazyColumn(
        modifier = Modifier.weight(1f)
    ) {
        items(member.size){index ->
            val user = allFriend.value[member[index]]
            IndividualViewComposable(user?.username!!, user.first_name!!) {
                Surface (
                    onClick = ({
                        run {
                            member = (member - member[index]).toMutableList()
                        }
                    })
                ) {
                    Icon(painter = painterResource(id = R.drawable.trash), contentDescription = "Delete" , modifier = Modifier.size(25.dp) , tint = orange32 )
                }
              }
        }
    }


    Button(onClick = {
           coroutineScope.launch {
               splitViewModel.addGroup(authViewModel.thisUser.value!!.uid , name , description , member)
           }
            navController.popBackStack()
    } ,
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)
    ) {
        Text(text = "Create-Group")
    }

}

}














//@Preview
//@Composable
//fun PreviewMakeComposable(){
//    MakeGroupComposable()
//}