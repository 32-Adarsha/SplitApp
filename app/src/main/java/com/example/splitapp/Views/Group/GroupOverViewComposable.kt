package com.example.splitapp.Views.Group


import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.splitapp.DataLayer.DataModel.GroupId
import com.example.splitapp.DataLayer.DataModel.GroupModel
import com.example.splitapp.DataLayer.DataModel.Usermodel
import com.example.splitapp.DataLayer.DataViewModel.SplitViewModel
import com.example.splitapp.R
import com.example.splitapp.Views.GlobalComposable.HeaderComposable
import com.example.splitapp.Views.GlobalComposable.TopComposable
import com.example.splitapp.Views.HomeView.BottomComposable
import com.example.splitapp.Views.HomeView.GroupComposable
import com.example.splitapp.Views.HomeView.MoneyViewer
import com.example.splitapp.Views.HomeView.topComposable
import com.example.splitapp.Views.theme.blue32
import com.example.splitapp.Views.theme.green32
import com.example.splitapp.Views.theme.orange32
import com.example.splitapp.Views.theme.white33


@SuppressLint("SuspiciousIndentation")
@Composable
fun GroupOverViewComposable (navController: NavController? , groupId: String?, splitViewModel: SplitViewModel) {
        val thisGroup:GroupModel? = splitViewModel.getGroupFromID(groupId!!)
        val owedInGroup: Map<String, Float>? = splitViewModel.getGroupOwed(groupId!!)
        Scaffold(
            topBar = {
                TopComposable(navController)
            },
            bottomBar = {
                BottomComposable(navController = navController , path = "makeTransaction/${thisGroup?.id}")
            },
        ) { innerPadding ->
            Column (
                modifier = Modifier
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                HeaderComposable(title = thisGroup?.name!!)
                Text(
                    text = "Member",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.cvfont)),
                    color = orange32,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
                GroupMidTopComposable(splitViewModel , thisGroup.member!! , owedInGroup!!)
                Text(
                    text = "Transaction",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.cvfont)),
                    color = orange32,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
                Box(
                    modifier = Modifier
                        .background(Color.Transparent),
                ) {
                    LogViewComposable(splitViewModel , groupId )
                }
            }

        }
    }







@Composable
fun GroupMidTopComposable(splitViewModel: SplitViewModel , member:List<String> ,oMap:Map<String , Float>){

    LazyRow(
        modifier = Modifier.padding(horizontal = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){


        if (member != null) {
            items(member.size){index ->
                val entry:Usermodel? = splitViewModel.getUserFromId(member[index])
                friendViewComposable(name = entry?.username , oMap[entry?.id]!!)
            }
        }
    }

}

@Composable
fun friendViewComposable(name:String? , owes:Float = 0f){
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
        Text(text = name!!, fontSize =  20.sp , fontWeight = FontWeight.Bold , color = blue32 , fontFamily = FontFamily(Font(R.font.headingfont)))
        Text(text = "$$owes" , fontSize =  15.sp , fontWeight = FontWeight.Bold , color = green32)
    }
}








