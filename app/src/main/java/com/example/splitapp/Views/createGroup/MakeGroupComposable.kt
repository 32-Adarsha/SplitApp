package com.example.splitapp.Views.createGroup

import FriendRequestComposable
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
import com.example.splitapp.DataLayer.DataModel.GroupModel
import com.example.splitapp.DataLayer.DataModel.Usermodel
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

    val coroutineScope = rememberCoroutineScope()
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var member:MutableSet<Usermodel> by remember {
        mutableStateOf(mutableSetOf())
    }





Column (

    verticalArrangement = Arrangement.SpaceBetween,
    modifier = Modifier.padding(15.dp).fillMaxHeight().fillMaxWidth()
) {
    Column {
        TopComposable(navController = navController)
        HeaderComposable(title = "Create Group")
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") }
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") }
        )
        FriendRequestComposable(
            navController = navController,
            splitViewModel = splitViewModel
        ) { friends: Usermodel ->
            run {
                member = ((member + friends).toMutableSet())
            }
        }

        Spacer(modifier = Modifier.height(5.dp))

        LazyColumn {
            var allUser = member.toList()
            items(allUser.size) { index ->
                var user = allUser[index]
                IndividualViewComposable(
                    friendUserName = user.username!!,
                    friendName = user.first_name!!
                ) {
                    Surface(
                        onClick = {
                            member = ((member - user).toMutableSet())
                        },
                        color = Color.Transparent
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.trash),
                            contentDescription = "Trash",
                            Modifier.size(20.dp)
                        )
                    }
                }

            }
        }




    }


    Button(onClick = {
        coroutineScope.launch {
            splitViewModel.addGroup(authViewModel.thisUser.value!!.uid, name, description, member.toList())
        }
        navController.popBackStack()
    },
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