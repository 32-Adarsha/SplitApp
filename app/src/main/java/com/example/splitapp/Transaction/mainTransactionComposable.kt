package com.example.splitapp.Transaction

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Button
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
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.splitapp.R
import com.example.splitapp.createGroup.CustomInputforThis
import com.example.splitapp.createGroup.Label
import com.example.splitapp.createGroup.SelectTopComposable
import com.example.splitapp.ui.theme.blue32
import com.example.splitapp.ui.theme.green32
import selectedFriend2


enum class Transaction {
    SettleUp,
    DivideExpense
}
@Composable
fun mainTransactionComposable(){
    var typeTransaction : Transaction? by remember {
        mutableStateOf(null)
    }
    var amount by remember {
        mutableStateOf(0f)
    }

    var isSettle = if(typeTransaction == Transaction.SettleUp) true else false
    var isDivide = if(typeTransaction == Transaction.DivideExpense) true else false
    Column (modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)
    ) {
        SelectTopComposable()
        Spacer(modifier = Modifier.height(10.dp))
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()){

            Surface (
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, blue32),
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
            ) {
                TransactionOption(optName = "Settle", selected = isSettle , Weight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.width(10.dp))


            Surface(
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, blue32),
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
            ) {
                TransactionOption(optName = "Divide", selected = true , Weight = FontWeight.Bold)
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
        TransactionAmount(amount = amount.toString() , {it -> amount = it.toFloat()})
        Spacer(modifier = Modifier.height(10.dp))


        Button(onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth()) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "Friend List" , fontWeight = FontWeight.Bold , fontSize = 20.sp)
                Icon(painter = painterResource(id = R.drawable.plus), contentDescription = "add" )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
        Surface (
            modifier = Modifier.weight(1f)
        ) {
            selectedFriend2()
        }

        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)) {
            Text(text = "Post Transaction" , fontWeight = FontWeight.Bold , fontSize = 20.sp)


        }


    }


}

@Composable
fun TransactionOption(optName:String , selected:Boolean , Weight: FontWeight){
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(5.dp)
    ) {

        Icon(
            painter = painterResource(id = R.drawable.check),
            contentDescription = "Checked",
            tint = if(selected) green32 else Color.Gray,
            modifier = Modifier.size(25.dp)
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(text = optName , fontSize = 20.sp , fontWeight = Weight, modifier = Modifier.weight(1f) , textAlign = TextAlign.Center)
        

    }
}


@Composable
fun TransactionAmount(amount:String , onchange : (String) -> Unit){
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ){
        Surface (
            modifier = Modifier.weight(1f)
        ){
            Label(labelName = "Total : ", font_size = 25, weight = FontWeight.Bold)
        }
        Surface (
            modifier = Modifier.weight(2f)
        ) {
            CustomInputforThis(
                passedValue = amount,
                onchange = {},
                textstyle = TextStyle(
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = blue32,
                    textDirection = TextDirection.Rtl
                ),
                placeHolder = "Amount",
                singleline = true,
                height = 40
            )
        }


    }
}

@Composable
fun DistributeEqually(){
    var disEqually by remember {
        mutableStateOf(true)
    }
    Surface(
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(2.dp,  if (disEqually) green32 else blue32),
        modifier = Modifier
            .height(50.dp)
            .width(200.dp)
    ){
    TransactionOption(optName = "Equally", selected = disEqually , Weight = FontWeight.Bold)}
}



@Preview
@Composable
fun PreviewMainTransactionComposable(){
    mainTransactionComposable()
}