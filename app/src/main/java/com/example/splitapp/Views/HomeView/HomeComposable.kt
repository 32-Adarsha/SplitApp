package com.example.splitapp.Views.HomeView
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.magnifier
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.splitapp.DataLayer.DataViewModel.SplitViewModel
import com.example.splitapp.R
import com.example.splitapp.Views.theme.orange32
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HomeComposable(navController: NavController? , splitViewModel: SplitViewModel) {
    var thisUser = splitViewModel.thisUser.collectAsState().value?.uid
    var user = splitViewModel.getFriendFromId(thisUser!!)
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
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = "${user?.username},",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 17.dp),
                textAlign = TextAlign.Start,
                fontFamily = FontFamily(Font(R.font.headingfont)),
                fontWeight = FontWeight.SemiBold,
                fontSize = 30.sp,
                color = orange32

            )
            Divider(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp))
            MoneyViewer(splitViewModel)
            GroupComposable(navController = navController ,splitViewModel)

        }
    }
}




