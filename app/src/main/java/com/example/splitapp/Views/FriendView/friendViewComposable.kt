package com.example.splitapp.Views.FriendView

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
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
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.splitapp.DataLayer.DataModel.Usermodel
import com.example.splitapp.DataLayer.DataViewModel.SplitViewModel
import com.example.splitapp.R
import com.example.splitapp.Views.HomeView.BottomComposable
import com.example.splitapp.Views.HomeView.topComposable
import com.example.splitapp.Views.createGroup.IndividualViewComposable
import com.example.splitapp.Views.theme.orange32
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


@Composable
fun friendViewComposable (navController: NavController , splitViewModel: SplitViewModel){

    var allGroupRequest = splitViewModel?.allGroupRequest?.collectAsState()?.value
    var allNewFriend = splitViewModel.allNewFriend.collectAsState()?.value
    var thisTab by remember {
        mutableStateOf(1)
    }
    var corontine  = rememberCoroutineScope()
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Surface (
                    onClick = {navController.popBackStack()},
                    color = Color.Transparent
                ) {
                    Icon(painter = painterResource(id = R.drawable.backarrow) , contentDescription = "back" )
                }
            }
        },
        bottomBar = {
            BottomComposable(navController , "friendRequest")
        },
    ) {innerpadding ->
        Column(
            modifier = Modifier
                .padding(innerpadding)
                .padding(horizontal = 15.dp)
        ) {
            TabRow(selectedTabIndex = thisTab) {
                Tab(
                    selected = thisTab == 0,
                    onClick = { thisTab = 0 },
                    text = { Text("Group Request") }
                )


                Tab(
                    selected = thisTab == 1,
                    onClick = { thisTab = 1 },
                    text = { Text(text = "Friends") }
                )
            }

            if (thisTab == 0) {
                LazyColumn {
                    var arr = allGroupRequest?.toList()
                    items(arr!!.size){index ->
                        var (id , groupM) = arr[index]
                        if(groupM != null) {

                                Row (
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp, vertical = 8.dp)
                                ){
                                    Column {
                                        Text(
                                            text = "${groupM.name}",
                                            modifier = Modifier,
                                            textAlign = TextAlign.Start,
                                            fontFamily = FontFamily(Font(R.font.headingfont)),
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = 30.sp,
                                            color = orange32

                                        )
                                        Text(
                                            text = "${splitViewModel.getFriendFromId(groupM.owner!!)?.username}",
                                            modifier = Modifier,
                                            textAlign = TextAlign.Start,
                                            fontFamily = FontFamily(Font(R.font.cvfont)),
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = 20.sp,
                                            color = orange32

                                        )
                                    }
                                    Row (
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                    ) {
                                        Button(onClick = {
                                            corontine.launch {
                                                splitViewModel.acceptRequest(id)

                                            }
                                        },
                                            shape = RoundedCornerShape(5.dp)) {
                                            Text(text = "Accept")
                                        }
                                        Button(onClick = { corontine.launch {
                                            splitViewModel.removeRequest(id)
                                        } },shape = RoundedCornerShape(5.dp) ,
                                            modifier = Modifier.padding(start = 5.dp)) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.trash),
                                                contentDescription = "delete",
                                                modifier = Modifier.size(25.dp),

                                            )
                                        }


                                    }
                                }


                        }
                    }
                }
            } else {


                var newList = allNewFriend?.toList()
                LazyColumn {
                    items(newList?.size!!){ index ->
                        var (id , model) = newList[index]
                        IndividualViewComposable(friendUserName = model?.username!!, friendName =model?.first_name!! ) {

                        }
                    }
                }

            }
        }
    }
}




