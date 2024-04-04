package com.example.splitapp.overView

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.splitapp.ui.theme.Pink80
import com.example.splitapp.ui.theme.Purple80
import com.example.splitapp.ui.theme.blue32
import com.example.splitapp.ui.theme.green32
import com.example.splitapp.ui.theme.orange32
import com.example.splitapp.ui.theme.white32
import kotlinx.coroutines.launch



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun Profile(navController: NavController?, navRoute: String)
{

        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        ModalNavigationDrawer(
            drawerContent = {
                ModalDrawerSheet{
                    Icon(
                        painter = painterResource(id = com.example.splitapp.R.drawable.user),
                        contentDescription = "Home",
                        modifier = Modifier.size(90.dp)
                            .size(30.dp)
                        ,
                        tint = green32)
                    Button( onClick = {/**/})
                    {
                        Text(text = "Log Out")
                    }


            } },
            drawerState = drawerState,
            modifier = Modifier.background(green32)
        )
        {
            val showDialogBox = remember { mutableStateOf(false)}
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .padding(vertical = 20.dp),

            )
            {
                Row()
                {
                    Icon(
                        painter = painterResource(id = com.example.splitapp.R.drawable.user),
                        contentDescription = "Home",
                        modifier = Modifier.size(90.dp)
                            .clickable {
                                scope.launch {
                                    drawerState.open()
                                }
                            },
                        tint = white32

                    )
                }
                Text(text = "Hello user",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(vertical = 30.dp))
                Row(
                    modifier = Modifier
                        .padding(vertical = 5.dp)
                        .clickable { navController?.navigate("overview")}
                ) {
                    Text (text = "Home", color = white32)
                }

                Row(
                    modifier = Modifier
                        .padding(vertical = 5.dp)
                        .clickable { /**/}
                ) {
                    Text (text = "Settings", color = white32)
                }

                Row(
                    modifier = Modifier
                        .padding(vertical = 5.dp)
                        .clickable { /**/}
                ) {
                    Text (text = "Help", color = white32)
                }

                Row(
                    modifier = Modifier
                        .clickable { showDialogBox.value = true }
                        .padding(vertical = 5.dp)

                ) {
                    Text (text = "Log Out" , color = white32)
                }
                if (showDialogBox.value)
                {
                    DialogBox(navController = navController, navRoute = "overview")
                }

            }
        }
        }


@Composable
fun DialogBox(navController: NavController?, navRoute: String) {
    //To track whether dialog is shown
    var showDialog by remember {
        mutableStateOf(true)
    }
    if (!showDialog)
        return

    AlertDialog(
        onDismissRequest = { showDialog = false },
        title = { Text(text = "Log Out") },
        text = { Text(text = "Are you sure you want to log out?") },
        confirmButton = {
            Button(onClick = { navController?.navigate("login") }) {
                Text(text = "Yes")
            }
        },
        dismissButton = {
            Button(onClick = { showDialog = false}) {
                Text(text = "No")
            }
        }
    )
}





