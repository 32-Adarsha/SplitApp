package com.example.splitapp.Views.Transaction

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.splitapp.DataLayer.DataModel.Usermodel
import com.example.splitapp.DataLayer.DataViewModel.AuthViewModel
import com.example.splitapp.DataLayer.DataViewModel.SplitViewModel
import com.example.splitapp.R
import com.example.splitapp.Views.GlobalComposable.TopComposable
import com.example.splitapp.Views.createGroup.AddFriendComposable
import com.example.splitapp.Views.createGroup.IndividualViewComposable



import com.example.splitapp.Views.theme.blue32
import com.example.splitapp.Views.theme.green32
import com.example.splitapp.Views.theme.orange32
import kotlinx.coroutines.launch


enum class Transaction {
    SettleUp,
    DivideExpense
}
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun mainTransactionComposable(navController: NavController,splitViewModel: SplitViewModel , id:String ,authViewModel: AuthViewModel) {
    var owner = authViewModel.thisUser
    var coroutineScope = rememberCoroutineScope()
    var typeTransaction: Transaction? by remember {
        mutableStateOf(null)
    }
    var member: Map<String ,Float> by remember {
        mutableStateOf(mutableMapOf())
    }

    var name by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    var isSlectingFriend: Boolean by remember {
        mutableStateOf(false)
    }


    var total by remember {
        mutableStateOf("")
    }

    var memberChoice = splitViewModel.getGroupMember(id)?.toMutableList()
    memberChoice?.remove(owner.value!!.uid)


    var isSettle = if (typeTransaction == Transaction.SettleUp) true else false
    var isDivide = if (typeTransaction == Transaction.DivideExpense) true else false


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        TopComposable(navController)
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {

            Surface(
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, blue32),
                onClick = {
                    typeTransaction = Transaction.SettleUp
                },
                shadowElevation = if (isSettle) 5.dp else 0.dp,
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
            ) {
                TransactionOption(optName = "Settle", selected = isSettle, Weight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.width(10.dp))


            Surface(
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, blue32),
                onClick = {
                    typeTransaction = Transaction.DivideExpense
                },
                shadowElevation = if (isDivide) 10.dp else 0.dp,
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
            ) {
                TransactionOption(optName = "Divide", selected = isDivide, Weight = FontWeight.Bold)
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") }
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") }
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = total,
            onValueChange = { total = it },
            label = { Text("Total") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        )

        Spacer(modifier = Modifier.height(10.dp))


        Button(
            onClick = {
                isSlectingFriend = !isSlectingFriend
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(3.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Friend Involved", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Icon(painter = painterResource(id = R.drawable.plus), contentDescription = "add")
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
        Surface(
            modifier = Modifier.weight(1f)
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                var mToList = member.toList()
                items(mToList.size) { index ->
                    var (key, value2) = mToList[index]
                    val thisUser = splitViewModel.getUserFromId(key)
                    IndividualViewComposable(thisUser?.username!!, thisUser?.first_name!!) {
                        Surface(

                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                var thisVaue by remember {
                                    mutableStateOf("")
                                }
                                OutlinedTextField(
                                    value = thisVaue,
                                    onValueChange = {
                                        val temPmap = member.toMutableMap()
                                        thisVaue = it
                                        if (thisVaue != ""){

                                        temPmap[key] = thisVaue.toFloat()
                                        } else {
                                            temPmap[key] =0f
                                        }
                                        member = temPmap.toMap()
                                                    },
                                    modifier = Modifier.width(100.dp),
                                    textStyle = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp,
                                        lineHeight = 30.sp
                                    ),
                                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Surface(
                                    onClick = ({
                                        run {
                                            var tempMap = member.toMutableMap()
                                            tempMap.remove(key)
                                            member = tempMap.toMap()
                                        }
                                    }),
                                    border = BorderStroke(1.dp, Color.Black),
                                    shape = RoundedCornerShape(5.dp),

                                    ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.trash),
                                        contentDescription = "Delete",
                                        modifier = Modifier
                                            .size(25.dp)
                                            .background(
                                                orange32
                                            ),
                                        tint = Color.White,

                                        )
                                }
                            }

                        }
                    }
                }
            }
        }


        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                coroutineScope.launch {
                    splitViewModel.addGroupLog(id,owner.value!!.uid ,member,name,total.toFloat(),description , isSettle)
                }

                navController.popBackStack()
            },
            shape = RoundedCornerShape(3.dp),

            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "Post Transaction", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }
    }

        if (isSlectingFriend) {
            Dialog(onDismissRequest = { /*TODO*/ }) {

                Surface(
                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 15.dp),
                    shape = RoundedCornerShape(20.dp),
                    border = BorderStroke(1.dp, Color.Black),

                    ) {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 15.dp)
                            .padding(top = 15.dp, bottom = 5.dp)
                    ) {
                        if (memberChoice != null) {
                            AddFriendComposable(splitViewModel , memberChoice) { friends: MutableList<String> ->
                                run {
                                    var tempMap = member.toMutableMap()
                                    for(indivi in friends){
                                        tempMap[indivi] = 0f
                                    }
                                    member = tempMap.toMap()
                    //
                                    isSlectingFriend = !isSlectingFriend
                                }
                            }
                        }
                    }


                }


            }

        }
    }








    @Composable
    fun TransactionOption(optName: String, selected: Boolean, Weight: FontWeight) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(5.dp)
        ) {

            Icon(
                painter = painterResource(id = R.drawable.check),
                contentDescription = "Checked",
                tint = if (selected) green32 else Color.Gray,
                modifier = Modifier.size(25.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = optName,
                fontSize = 20.sp,
                fontWeight = Weight,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )


        }
    }





//@Preview
//@Composable
//fun PreviewMainTransactionComposable(){
//    mainTransactionComposable()
//}