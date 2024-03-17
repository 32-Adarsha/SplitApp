package com.example.splitapp.createGroup

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.splitapp.R
import com.example.splitapp.ui.theme.blue32
import com.example.splitapp.ui.theme.green32


@Composable
fun createGroupComposable (){
    var groupName by remember {
        mutableStateOf("")
    }
    var groupDiscription by remember {
        mutableStateOf("")
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp, vertical = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomInputforThis(
            passedValue = groupName,
            onchange = { value -> groupName = value },
            textstyle = TextStyle(fontSize = 35.sp, fontWeight = FontWeight.Bold, color = blue32 , textAlign = TextAlign.Center),
            placeHolder = "Type Group Name"
        )
        Spacer(modifier = Modifier.height(10.dp))
        CustomInputforThis(
            passedValue = groupDiscription,
            onchange = { value -> groupDiscription = value },
            textstyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium, color = blue32 ),
            placeHolder = "Give Group Discription"
        )
    }

}

@Composable
fun CustomInputforThis(passedValue : String , onchange : (String) -> Unit , textstyle : TextStyle ,placeHolder:String){
    var currValue = passedValue
    var isFocused by remember {
        mutableStateOf(false)
    }
    var borderColor = Color.Transparent
    var textContent = ""
    var textstyle : TextStyle = textstyle
    if (currValue.length == 0 && !isFocused)
    {
        textContent = placeHolder
        textstyle = textstyle.copy(color = Color.Gray)
        borderColor = blue32

    }
    else
    {
        textContent = currValue
    }
    Surface(
        shape = RoundedCornerShape(15.dp),
        border = BorderStroke(2.dp, borderColor),
        modifier = Modifier
            .onFocusChanged { isFocused = it.isFocused }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp)
        ) {

            Spacer(modifier = Modifier.width(6.dp))

            BasicTextField(
                value = textContent,
                onValueChange = {it -> onchange(it)},
                singleLine = true,
                textStyle = textstyle,
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .fillMaxWidth()
                    .height(50.dp)

            )
        }
    }
}



@Preview
@Composable
fun PreviewCreateGroupComposable (){
    createGroupComposable()
}