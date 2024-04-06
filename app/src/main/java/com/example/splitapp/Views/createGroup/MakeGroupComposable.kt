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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.splitapp.DataLayer.DataModel.Friend
import com.example.splitapp.DataLayer.DataModel.GroupModel
import com.example.splitapp.DataLayer.DataViewModel.SplitViewModel
import com.example.splitapp.R
import com.example.splitapp.Views.theme.blue32
import com.example.splitapp.Views.theme.orange32

@Composable
fun MakeGroupComposable (
    navController: NavController,
    viewModel: SplitViewModel
) {

    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var member:MutableList<Friend> by remember {
        mutableStateOf(mutableListOf())
    }

    var selectfriend by remember {
        mutableStateOf(false)
    }

    var count by remember {
        mutableIntStateOf(0)
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
                    AddFriendComposable(viewModel) { friends: MutableList<Friend> ->
                        run {
                            member = (member + friends).toMutableList()
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
    SelectTopComposable()

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
            IndividualViewComposable(friend = member[index]) {
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
                     var group = GroupModel(name , description , member)
                     viewModel.addGroup(group)
                     viewModel.logCount()
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



@Composable
fun SelectTopComposable() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Icon(
            painter = painterResource(id = R.drawable.backarrow),
            contentDescription = "backArrow",
            modifier = Modifier.size(30.dp))
    }
}










//@Preview
//@Composable
//fun PreviewMakeComposable(){
//    MakeGroupComposable()
//}