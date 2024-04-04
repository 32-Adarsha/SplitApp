package com.example.splitapp.overView

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import com.example.splitapp.ui.theme.green32
import com.example.splitapp.ui.theme.orange32
import com.example.splitapp.ui.theme.white32
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.splitapp.ui.theme.blue32

class GroupViewModel: ViewModel()
{
    var testGroup: MutableState<Map<Color, Float>> = mutableStateOf(
        mapOf(
            green32 to 150.5f,
            orange32 to 20.0f,
            white32 to 113.0f,
            Color.Black to 55.0f,
            Color.Blue to 5.0f
        )
    )
}
@Composable
fun GroupComposable(navController: NavController?, viewModel: GroupViewModel = androidx.lifecycle.viewmodel.compose.viewModel())
 {
    var testGroup by rememberSaveable {
        mutableStateOf(mapOf(
            green32 to 150.5f,
            orange32 to 20.0f,
            white32 to 113.0f,
            Color.Black to 55.0f,
            Color.Blue to 5.0f
        )
        )
    }
    Column (
        modifier = Modifier
            .padding(20.dp)
    ) {
        Text(text = "Groups",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            color = blue32
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn {
            items(testGroup.size) { index ->
                val entry = testGroup.entries.elementAt(index)
                val (color, value) = entry
                val values = "$$value"
                Surface (
                    shape = RoundedCornerShape(20.dp),
                    border = BorderStroke(1.dp, Color.Black),
                    modifier = Modifier.padding(vertical = 5.dp , horizontal = 0.dp),
                    onClick = {navController?.navigate("groupView")}
                ){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                        .padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Surface(

                    ) {
                        Row {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                border = BorderStroke(1.dp, Color.Black),
                                modifier = Modifier.size(50.dp),
                                color = color
                            ) {

                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = "Vatmara",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = blue32
                                )
                                Text(
                                    text = "Owner",
                                    fontWeight = FontWeight.Medium,
                                    fontStyle = FontStyle.Italic,
                                    color = orange32)
                            }
                        }
                    }
                    Text(
                        text = values,
                        fontSize = 25.sp,
                        color = blue32,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )

                }
            }

            }
        }
    }
}



@Preview
@Composable
fun previewGroupComposable(){
    GroupComposable(null)
}