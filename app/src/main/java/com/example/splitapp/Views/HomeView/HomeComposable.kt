package com.example.splitapp.Views.HomeView
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.splitapp.DataLayer.DataViewModel.SplitViewModel
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HomeComposable(navController: NavController? , splitViewModel: SplitViewModel) {

    val coroutineScope = rememberCoroutineScope()
    coroutineScope.launch {
        splitViewModel.fetchUserGroup()
    }
    Scaffold(
        topBar = {
            topComposable()
        },
        bottomBar = {
            BottomComposable(navController , "createGroup")
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            MoneyComposable()
            GroupComposable(navController = navController ,splitViewModel)

        }
    }
}

//@Preview
//@Composable
//fun PreviewScaffold(){
//    HomeComposable(null)
//}
