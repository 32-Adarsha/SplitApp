import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.persistableBundleOf
import androidx.navigation.NavController
import com.example.splitapp.DataLayer.DataModel.Usermodel
import com.example.splitapp.DataLayer.DataModel.addFriend
import com.example.splitapp.DataLayer.DataViewModel.SplitViewModel
import com.example.splitapp.R
import com.example.splitapp.Views.createGroup.AddFriendComposable
import com.example.splitapp.Views.createGroup.IndividualViewComposable
import com.example.splitapp.Views.theme.green32
import com.example.splitapp.Views.theme.orange32
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


@Composable
fun FriendRequestComposable(navController: NavController?, splitViewModel: SplitViewModel? ,onAdd: (Usermodel) -> Unit){


    var search by remember {
        mutableStateOf("")
    }

    var searchedUser: Usermodel? by remember {
        mutableStateOf(null)
    }
    var coroutine = rememberCoroutineScope()

    var invitedPeople:MutableList<Usermodel> by remember {
        mutableStateOf(mutableListOf())
    }

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp)
    ) {
        Column {

            Text(
                text = "Invite Friend",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                fontFamily = FontFamily(Font(R.font.headingfont)),
                fontWeight = FontWeight.SemiBold,
                fontSize = 30.sp,
                color = orange32

            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                value = search,
                onValueChange = { newText ->
                    search = newText
                },
                label = {
                    Text(text = "Email")
                },
                shape = RoundedCornerShape(percent = 20),
                trailingIcon = {
                    Button(
                        onClick = {
                            coroutine.launch {
                                searchedUser =
                                    async { splitViewModel?.searchFriend(search) }.await()
                            }


                        }, shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.padding(horizontal = 8.dp)
                    ) {
                        Text(text = "Search")
                    }
                })
            if (searchedUser != null) {
                Surface(

                ) {

                    Column(

                    ) {

                        Text(
                            text = "Result",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Start,
                            fontFamily = FontFamily(Font(R.font.headingfont)),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 30.sp,
                            color = orange32

                        )
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(70.dp)
                        ) {
                            var c: Color by remember {
                                mutableStateOf(Color.Black)
                            }
                            var content by remember {
                                mutableStateOf("Send Request")
                            }

                            IndividualViewComposable(
                                friendUserName = searchedUser?.username!!,
                                friendName = searchedUser?.first_name!!
                            ) {
                                Button(
                                    onClick = {
                                        onAdd(searchedUser!!)
                                    },
                                    shape = RoundedCornerShape(4.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = c,
                                        contentColor = Color.White
                                    ),
                                ) {
                                    Text("Invite")
                                }
                            }
                        }
                    }
                }
            }

            Text(
                text = "Member",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                textAlign = TextAlign.Start,
                fontFamily = FontFamily(Font(R.font.headingfont)),
                fontWeight = FontWeight.SemiBold,
                fontSize = 30.sp,
                color = orange32,

                )



        }

    }
}


//@Preview
//@Composable
//fun previewAddFriend(){
//    FriendRequestComposable(navController = null, splitViewModel = null )
//}