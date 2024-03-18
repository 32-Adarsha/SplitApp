package com.example.splitapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.splitapp.Group.GroupOverViewComposable
import com.example.splitapp.Transaction.mainTransactionComposable
import com.example.splitapp.createGroup.CreateGroupComposable
import com.example.splitapp.login.LoginComposable
import com.example.splitapp.overView.overView
import com.example.splitapp.ui.theme.SplitAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SplitAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "auth" ){
                    navigation(
                        startDestination = "login",
                        route  = "auth"
                    ){
                        composable("register"){

                        }
                        composable("login"){
                            LoginComposable(navController = navController)
                        }
                        composable("forgetPassword"){

                        }
                    }

                    navigation(
                        startDestination = "overView",
                        route = "userView"
                    ){
                        composable("overView"){
                                overView(navController = navController)
                        }
                        composable("createGroup"){
                                CreateGroupComposable()
                        }

                    }
                    navigation (
                        startDestination = "group",
                        route = "groupView"
                    ){
                        composable("group"){
                            GroupOverViewComposable(navController = navController , "Vatmara")
                        }
                        composable("makeTransaction"){
                            mainTransactionComposable()
                        }
                    }
                }
            }
        }
    }
}


@Composable
inline fun <reified T: ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
   val navGraphRout = destination.parent?.route?: return viewModel()
   val parentEntry = remember(this) {
       navController.getBackStackEntry(navGraphRout)
   }
    return viewModel(parentEntry)
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SplitAppTheme {
        Greeting("Android")
    }
}