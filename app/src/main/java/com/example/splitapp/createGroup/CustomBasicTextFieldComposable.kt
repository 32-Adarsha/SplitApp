package com.example.splitapp.createGroup

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.splitapp.ui.theme.blue32
import com.example.splitapp.ui.theme.green32


@Composable
fun CustomBasicTextField(textcontent:String , onchange:(String)->Unit , singleline:Boolean , height:Int , textstyle: TextStyle){
    BasicTextField(
        value = textcontent,
        onValueChange = {it -> onchange(it)},
        singleLine = singleline,
        textStyle = textstyle,
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth()
            .height(height.dp)

    )
}

@Composable
fun CustomInputforThis(passedValue : String , onchange : (String) -> Unit , textstyle : TextStyle ,placeHolder:String ,singleline:Boolean , height:Int){
    var currValue = passedValue
    var isFocused by remember {
        mutableStateOf(false)
    }
    var borderColor =  if (!isFocused) blue32 else green32
    var elevated = if (isFocused) 10 else 5
    var textContent = ""
    var textstyle : TextStyle = textstyle
    if (currValue.length == 0 && !isFocused)
    {
        textContent = placeHolder
        textstyle = textstyle.copy(color = Color.Gray)
    }
    else
    {
        textContent = currValue
    }
    Surface(
        shape = RoundedCornerShape(15.dp),
        border = BorderStroke(1.dp, borderColor),
        shadowElevation = elevated.dp,
        modifier = Modifier
            .onFocusChanged { isFocused = it.isFocused }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 5.dp, vertical = 5.dp)
        ) {

            CustomBasicTextField(
                textcontent = textContent,
                onchange = onchange,
                singleline = singleline,
                height = height,
                textstyle = textstyle,
            )
        }
    }
}


@Preview
@Composable
fun previewThis(){
    CustomInputforThis(
        passedValue = "Name",
        onchange = {} ,
        textstyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Normal, color = blue32),
        placeHolder = "Test" ,
        singleline = true ,
        height = 30
    )
}