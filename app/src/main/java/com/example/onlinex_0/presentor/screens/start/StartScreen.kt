package com.example.onlinex_0.presentor.screens.start

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import com.example.onlinex_0.R

class StartScreen : AndroidScreen(
) {
    @Composable
    override fun Content() {

        Log.d("AAA", "Content: Starts")
        val vm: StartViewModelImpl = getViewModel<StartViewModelImpl>()
        StartScreenContent(
            uiState = vm.uiState.collectAsState(),
            onEventDispatchers = vm::onEventDispatcher
        )


    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreenContent(
    uiState: State<StartContract.UIState>,
    onEventDispatchers: (StartContract.Intent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(bottom = 30.dp),
        verticalArrangement = Arrangement.Center
    ) {
//        Image(
//            painter = painterResource(id = R.drawable.ic_launcher_background),
//            contentDescription = null,
//            modifier = Modifier
//                .size(96.dp)
//                .align(Alignment.CenterHorizontally)
//        )
//
//        Text(
//            text = "Welcome",
//            style = TextStyle(
//                fontSize = 24.sp,
//                fontWeight = FontWeight(500),
//                color = Color(0xFFADADAD),
//                textAlign = TextAlign.Center,
//            ),
//            modifier = Modifier
//                .padding(top = 16.dp)
//                .align(Alignment.CenterHorizontally)
//        )
var name by remember {
    mutableStateOf("")
}
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            placeholder = {
                Text(text = "Enter your name", color = Color.Black)
            },
            value = name,
            onValueChange = {
                name=it
            },
            textStyle = TextStyle(color = Color.Black),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black,
                textColor = Color.Black
            )
        )

        Box(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(size = 4.dp)
                )
                .fillMaxWidth()
                .padding(horizontal = 50.dp)
                .height(54.dp)
                .clickable {
                    onEventDispatchers(StartContract.Intent.SaveName(name))
                },
        ) {
            Text(
                text = "ENTER",
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Medium,
                    fontSize = 22.sp
                ),
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun StartScreenContentPreview() {
//    StartScreenContent(StartContract.UIState()) {}
//}

