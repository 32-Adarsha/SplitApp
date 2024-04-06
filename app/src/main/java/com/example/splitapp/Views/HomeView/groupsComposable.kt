package com.example.splitapp.Views.HomeView

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import com.example.splitapp.Views.theme.green32
import com.example.splitapp.Views.theme.orange32
import com.example.splitapp.Views.theme.white32
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.splitapp.DataLayer.DataModel.GroupModel
import com.example.splitapp.DataLayer.DataViewModel.SplitViewModel
import com.example.splitapp.Views.theme.blue32


@Composable
fun GroupComposable(
    navController: NavController?,
    viewModel: SplitViewModel) {
    val groupModel by viewModel.allGroup.collectAsState()
    val count by viewModel.count.collectAsState()
    Column (
        modifier = Modifier
            .padding(horizontal = 20.dp)
    ) {
        Text(text = "Groups",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = blue32
        )
        Divider(modifier = Modifier.padding(vertical = 5.dp))
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Count $count")
        if (groupModel.isNotEmpty()) {
            LazyColumn {
                items(groupModel.size) { index ->

                    Surface(
                        onClick = {
                            Log.e("T"  , "$index")
                            viewModel.selectedGroup(index)
                            navController?.navigate("groupView") }
                    ) {
                        GroupCard(navController,groupModel[index].name,groupModel[index].owes)
                    }

                }
            }
        }
    }
}

@Composable
fun GroupCard(navController: NavController? , name:String ,owes:Float) {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),

        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(vertical = 5.dp)
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, Color.Black),
                    modifier = Modifier.size(70.dp),
                    color = green32
                ) {

                }
                Spacer(modifier = Modifier.width(20.dp))
                Column {
                    Text(
                        text = name,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = orange32,
                    )
                    Text(
                        text = "Owner",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,

                        color = Color.Gray)
                }
            }

            Text(
                text = "$$owes",
                fontSize = 35.sp,
                color = green32,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 10.dp)
            )


        }

        }
    }


//@Preview
//@Composable
//fun previewOutline(){
//    GroupCard(null)
//}