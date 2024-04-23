package com.example.splitapp.Views.FriendView

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.splitapp.DataLayer.DataModel.Usermodel
import com.example.splitapp.DataLayer.DataViewModel.SplitViewModel
import com.example.splitapp.R
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


@Composable
fun friendViewComposable (navController: NavController , splitViewModel: SplitViewModel){


    var thisTab by remember {
        mutableStateOf(0)
    }
    Column (
        modifier = Modifier.padding(horizontal = 5.dp , vertical = 5.dp)
    ) {
        TabRow(selectedTabIndex = thisTab) {
            Tab(
                selected = thisTab == 0,
                onClick = { thisTab = 0 },
                text = { Text("Friend") }
            )


            Tab(
                selected = thisTab == 1,
                onClick = { thisTab = 1 },
                text = { Text(text = "Friend Request") }
            )
        }

        if (thisTab == 0){
            Text(text = "All Friend")
        }else {
            Text(text = "Friend Request")
        }
    }
    //AddFriend(navController , splitViewModel)


}


@Composable
fun AddFriend(navController: NavController , splitViewModel: SplitViewModel){
    var search by remember {
        mutableStateOf("")
    }
    var searchedUser:Usermodel? by remember {
        mutableStateOf(null)
    }
    var coroutine = rememberCoroutineScope()



    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 15.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = search,
            onValueChange = { newText ->
                search = newText


            },
            label = {
                Text(text = "Email")
            },
            shape = RoundedCornerShape(percent = 20),
            trailingIcon = {
                Surface (
                    onClick = {
                        coroutine.launch {
                            searchedUser = async {   splitViewModel.searchFriend(search)}.await()
                        }
                    }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.search),
                        contentDescription = "Search"
                    )
                }
            } )




    }
}


