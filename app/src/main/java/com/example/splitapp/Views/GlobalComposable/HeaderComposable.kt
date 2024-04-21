package com.example.splitapp.Views.GlobalComposable

import androidx.compose.ui.text.font.Font
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.splitapp.R

@Composable
fun HeaderComposable(title:String){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = title, fontSize = 50.sp , fontWeight = FontWeight.Bold , fontFamily = FontFamily(
            Font(R.font.headingfont)
        ))
    }
}