import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.splitapp.R
import com.example.splitapp.createGroup.CustomInputforThis
import com.example.splitapp.createGroup.friendListComposable
import com.example.splitapp.ui.theme.blue32
import com.example.splitapp.ui.theme.green32
import com.example.splitapp.ui.theme.white33

val friend:Map<String , Float> = mapOf(
    "Samir" to 15.0f,
    "Aakash" to 16.0f,
    "Suman" to 118.0f,
    "Krishna" to 10.0f,
    "Samir" to 15.0f,
    "Aakasha" to 16.0f,
    "Sumana" to 18.0f,
    "Krishnaa" to 10.0f,
    "Samira" to 15.0f,
    "Aakasha" to 16.0f,
    "Sumana" to 18.0f,
    "Krishnaa" to 10.0f
)

@Composable
fun friendListComposable2(name:String , owes: Float){

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 15.dp, vertical = 5.dp)
                .fillMaxWidth()
                .height(50.dp),

            ) {
            Surface(
                modifier = Modifier.weight(3f)
            ) {
                Row(
                ) {
                    Surface(
                        border = BorderStroke(1.dp, Color.Black),
                        modifier = Modifier.size(50.dp),
                        color = white33,
                        shape = RoundedCornerShape(20.dp),
                    ) {

                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = name,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = blue32
                        )
                        Text(
                            text = "nickname",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = blue32,
                            fontStyle = FontStyle.Italic
                        )
                    }

                }
            }
            Surface(
                modifier = Modifier.weight(2f)
            ) {
                CustomInputforThis(
                    passedValue = "${owes.toString()} $",
                    onchange = {},
                    textstyle = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = blue32,
                        textDirection = TextDirection.Rtl
                    ),
                    placeHolder = "Amount",
                    singleline = true,
                    height = 30
                )


            }
        }

}


@Composable
fun selectedFriend2(){
    Surface (
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
    ){
        LazyColumn(){
            items(com.example.splitapp.createGroup.friend.size){ index ->
                val entry = com.example.splitapp.createGroup.friend.entries.elementAt(index)
                friendListComposable2(name = entry.key , owes = entry.value )
            }
        }
    }
}

@Preview
@Composable
fun PreviewFriend(){
    selectedFriend2()
}

