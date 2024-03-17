package com.example.splitapp.createGroup

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.splitapp.R
import com.example.splitapp.ui.theme.blue32
import com.example.splitapp.ui.theme.green32
import com.example.splitapp.ui.theme.white33


@Composable
fun CreateGroupComposable (){
    var groupName by remember {
        mutableStateOf("")
    }
    var groupDiscription by remember {
        mutableStateOf("")
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp, vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        SelectTopComposable()
        //Custom Input Field For Giving Group Name
        //Label
        Label(labelName = "Group Name", font_size = 18, weight = FontWeight.Bold )
        CustomInputforThis(
            passedValue = groupName,
            onchange = { value -> groupName = value },
            textstyle = TextStyle(fontSize = 25.sp, fontWeight = FontWeight.Bold, color = blue32),
            placeHolder = "Type Group Name",
            singleline = true,
            height = 38
        )

        //Spacer to give space between the Fields
        Spacer(modifier = Modifier.height(10.dp))



        //Custom Input field for Giving Group Description
        //Label
        Label(labelName = "Discription", font_size = 18, weight = FontWeight.Bold )
        CustomInputforThis(
            passedValue = groupDiscription,
            onchange = { value -> groupDiscription = value },
            textstyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Normal, color = blue32 ),
            placeHolder = "Give Group Discription",
            singleline = false,
            height = 100
        )

        Spacer(modifier = Modifier.height(10.dp))
        Label(labelName = "Select Member", font_size = 18, weight = FontWeight.Bold )
        Box (
            modifier = Modifier.weight(1f)
        ) {
            AddFriendComposable()
        }
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth().height(50.dp).padding(top = 5.dp),
            ) {
            Text(text = "Create Group")
        }



    }

}
@Composable
fun SelectTopComposable() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp).padding(top = 5.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Icon(
            painter = painterResource(id = R.drawable.backarrow),
            contentDescription = "backArrow",
            modifier = Modifier.size(30.dp))
    }
}

@Composable
fun Label(labelName:String , font_size : Int , weight: FontWeight){
    Text(
        text = labelName.uppercase(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 5.dp),
        fontWeight = weight,
        fontSize =  font_size.sp )
}







@Preview
@Composable
fun PreviewCreateGroupComposable (){
    CreateGroupComposable()
}

//@Preview
//@Composable
//fun PreviewAddFriendComposable(){
//    AddFriendComposable()
//}