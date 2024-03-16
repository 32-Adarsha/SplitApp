package com.example.splitapp.overView

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import com.example.splitapp.ui.theme.green32
import com.example.splitapp.ui.theme.orange32
import com.example.splitapp.ui.theme.white32
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp


@Composable
fun GroupComposable() {
    val testGroup: Map<Color, Float> = mapOf(
        green32 to 1.5f,
        orange32 to 2.0f,
        white32 to 3.0f
    )
    Column (
        modifier = Modifier.padding(20.dp)
    ) {
        Text(text = "Groups",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn {
            items(testGroup.size) { index ->
                val entry = testGroup.entries.elementAt(index)
                val (color, value) = entry
                Surface (
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(2.dp, green32),
                    modifier = Modifier.padding(vertical = 5.dp , horizontal = 0.dp)
                ){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(1.dp, Color.Black),
                        modifier = Modifier.size(50.dp),
                        color = color
                    ) {

                    }
                    Text(
                        text = value.toString(),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
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
    GroupComposable()
}