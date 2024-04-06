package com.example.splitapp.Views.HomeView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.splitapp.DataLayer.DataViewModel.SplitViewModel

@Composable
fun HomeComposable(navController: NavController? , viewModel: SplitViewModel) {

    Scaffold(
        topBar = {
            topComposable()
        },
        bottomBar = {
            BottomComposable(navController)
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            MoneyComposable()
            GroupComposable(navController = navController ,viewModel)
        }
    }
}

//@Preview
//@Composable
//fun PreviewScaffold(){
//    HomeComposable(null)
//}
