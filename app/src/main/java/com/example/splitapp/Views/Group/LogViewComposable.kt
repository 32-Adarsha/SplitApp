package com.example.splitapp.Views.Group

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.splitapp.DataLayer.DataModel.GroupLog
import com.example.splitapp.DataLayer.DataModel.Usermodel
import com.example.splitapp.DataLayer.DataViewModel.SplitViewModel
import com.example.splitapp.R
import com.example.splitapp.Views.HomeView.OutlinedCardExample
import com.example.splitapp.Views.theme.green32
import com.example.splitapp.Views.theme.orange32
import java.util.Locale


@Composable
fun LogViewComposable(splitViewModel: SplitViewModel , id:String){
    var allLog  = splitViewModel.allGroupLog.collectAsState().value[id]
    Surface (

        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp)
            .padding(top = 10.dp)
    ){

        LazyColumn(){

            if (allLog != null) {
                items(allLog.size) { index ->
                    var owner = splitViewModel.getUserFromId(allLog[index].owner!!)
                    Surface (
                        modifier = Modifier.padding(vertical = 5.dp)
                    ) {
                        IndividualLogView( allLog[index] , owner?.username!! , splitViewModel)
                    }
                }
            }
        }

    }
}

@Composable
fun IndividualLogView( groupLog:GroupLog , owner:String , splitViewModel: SplitViewModel) {
    val name = groupLog.name
    val owes = groupLog.total
    var color: Color = if (owes == 0f) {
        Color.Gray
    } else if (owes!! < 0f) {
        orange32
    } else {
        green32
    }

    var exapanded by remember {
        mutableStateOf(false)
    }
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, Color.Black),

        ) {
        Surface (
            onClick = ({exapanded = !exapanded})
        ) {


            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = name!!,
                        modifier = Modifier
                            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(Font(R.font.headingfont)),
                        fontSize = 40.sp,

                        )
                    Text(
                        text = "By -> ${owner.capitalize(Locale.getDefault())}",
                        modifier = Modifier.padding(start = 16.dp, bottom = 16.dp, end = 16.dp),
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(Font(R.font.cvfont)),
                        fontSize = 23.sp
                    )

                }
                Text(
                    text = "$$owes",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp)
                        .alpha(0.7f),
                    textAlign = TextAlign.End,
                    fontFamily = FontFamily(Font(R.font.dmfont)),
                    fontSize = 35.sp,
                    color = color,
                    )

            }
        }
        if (exapanded) {
            Divider(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp))
            LazyColumn (
                modifier = Modifier.fillMaxWidth().height(100.dp)
            ) {
                var logItems = groupLog.involved?.toList()
                items(logItems!!.size) {index ->
                    var (userId , value) = logItems[index]
                    var userName = splitViewModel.getUserFromId(userId)!!.username
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text(
                            text = "${userName}",
                            modifier = Modifier.padding(start = 16.dp, bottom = 16.dp, end = 16.dp),
                            textAlign = TextAlign.Center,
                            fontFamily = FontFamily(Font(R.font.tekofont)),
                            fontSize = 23.sp
                        )
                        Text(
                            text = "$value",
                            modifier = Modifier.padding(start = 16.dp, bottom = 16.dp, end = 16.dp),
                            textAlign = TextAlign.Center,
                            fontFamily = FontFamily(Font(R.font.cvfont)),
                            fontSize = 23.sp
                        )
                    }
                }
            }

        }

    }
}



