package com.example.splitapp.Views.HomeView

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.splitapp.DataLayer.DataViewModel.SplitViewModel
import com.example.splitapp.R
import com.example.splitapp.Views.theme.blue32
import com.example.splitapp.Views.theme.green32
import com.example.splitapp.Views.theme.orange32
import java.util.Locale


@Composable
fun GroupComposable(
    navController: NavController?,
    splitViewModel: SplitViewModel) {

    val groupModel by splitViewModel.allGroup.collectAsState()
    val count by splitViewModel.count.collectAsState()

    Column (
        modifier = Modifier
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = "Groups,",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(R.font.cvfont)),
            fontWeight = FontWeight.SemiBold,
            fontSize = 30.sp,
            color = orange32

            )
        Divider(modifier = Modifier.padding(vertical = 5.dp))
        Spacer(modifier = Modifier.height(10.dp))
        if (groupModel.isNotEmpty()) {
            LazyColumn {
                items(groupModel.toList()){(key, group) ->
                    Surface(
                        onClick = {
                        navController?.navigate("groupView/${group.id}")
                        },
                        modifier = Modifier.padding(top = 5.dp , bottom = 5.dp)
                    ) {
                        val userModel = splitViewModel.getUserFromId(group.owner!!)
                        var owed = splitViewModel.getTotalOwedInGroup(group.id!!)
                    OutlinedCardExample( group.name!! , userModel?.username!!,owed , 40 )
                    }

                }
            }
        }
    }
}

@Composable
fun OutlinedCardExample( name:String , owner:String ,owesamount:Float , moneyFontSize:Int) {
    var owes = owesamount
    var color: Color = if (owes == 0f) {
        Color.Gray
    } else if (owes < 0f) {
        owes = -1 * owes
        orange32
    } else {
        green32
    }
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, Color.Black),

        ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column (
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = name,
                    modifier = Modifier
                        .padding(top = 16.dp , start = 16.dp , end = 16.dp),
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily(Font(R.font.headingfont)),
                    fontSize = 40.sp,

                    )
                Text(
                    text = "Owner -> ${owner.capitalize(Locale.getDefault())}",
                    modifier = Modifier.padding( start = 16.dp , bottom = 16.dp , end = 16.dp),
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily(Font(R.font.cvfont)),
                    fontSize = 23.sp
                )

            }
            Text(
                text = "$$owes",
                modifier = Modifier.fillMaxWidth()
                    .padding( start = 16.dp , end = 16.dp),

                textAlign = TextAlign.End,
                fontFamily = FontFamily(Font(R.font.dmfont)),
                fontSize = moneyFontSize.sp,
                color = color,


                )

        }

    }
}

//@Preview
//@Composable
//fun previewOutline(){
//    GroupCard(null)
//}