package com.example.splitapp.Views.HomeView

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.splitapp.R

@Composable
fun topComposable(){
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 13.dp, vertical = 15.dp)
    ){
            Icon(painter = painterResource(id = R.drawable.outlinesetting), contentDescription = "Setting",modifier = Modifier
                .size(34.dp)
                .clip(CircleShape),)

        Image(
            painter = painterResource(id = R.drawable.user),
            contentDescription = stringResource(id = R.string.user_photo),
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .size(34.dp)
                .clip(CircleShape)
        )
    }
}

@Preview
@Composable
fun preview(){
    topComposable()
}