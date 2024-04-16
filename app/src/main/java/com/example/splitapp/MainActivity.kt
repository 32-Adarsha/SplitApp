package com.example.splitapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.splitapp.DataLayer.DataViewModel.AllFriendViewModel
import com.example.splitapp.DataLayer.DataViewModel.AuthViewModel
import com.example.splitapp.DataLayer.DataViewModel.DataViewModel
import com.example.splitapp.DataLayer.DataViewModel.SplitViewModel
import com.example.splitapp.Views.Group.GroupOverViewComposable

import com.example.splitapp.Views.login.LoginComposable
import com.example.splitapp.Views.HomeView.HomeComposable
import com.example.splitapp.Views.SignUpCompose
import com.example.splitapp.Views.Transaction.mainTransactionComposable
import com.example.splitapp.Views.createGroup.MakeGroupComposable


import com.example.splitapp.Views.theme.SplitAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: SplitViewModel by viewModels()
    private val dataViewModel:DataViewModel by viewModels()
    private val authViewModel: AuthViewModel by viewModels()
    private val allFriendViewModel:AllFriendViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SplitAppTheme {


                val thisId by viewModel.viewGroupDetail.collectAsState()
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "auth" ){
                    navigation(
                        startDestination = "login",
                        route  = "auth"
                    ){
                        composable("register"){
                            SignUpCompose(navController = navController , authViewModel , dataViewModel , allFriendViewModel)
                        }
                        composable("login"){
                            LoginComposable(navController = navController , authViewModel , dataViewModel)
                        }
                        composable("forgetPassword"){

                        }
                    }

                    navigation(
                        startDestination = "overView",
                        route = "userView"
                    ){
                        composable("overView"){
                                HomeComposable(navController= navController , viewModel)
                        }
                        composable("createGroup"){
                                MakeGroupComposable(navController , viewModel)
                        }

                    }
                    navigation (
                        startDestination = "group",
                        route = "groupView"
                    ){
                        composable("group"){
                            GroupOverViewComposable(navController = navController , "Vatmara" , viewModel ,thisId )
                        }
                        composable("makeTransaction"){
                            mainTransactionComposable(navController ,viewModel , thisId)
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