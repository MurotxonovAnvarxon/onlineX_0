package com.example.onlinex_0.presentor.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.onlinex_0.data.common.UserUiCommon

@Composable
fun UserItem(
    userUICommon: UserUiCommon,
    click: (String, String) -> Unit,
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable {
                click(userUICommon.uuId, userUICommon.name)
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
//            Image(
//                modifier = Modifier
//                    .size(50.dp)
//                    .padding(12.dp) ,
//                painter = painterResource(id = R.drawable.ic_launcher_foreground),
//                contentDescription = ""
//            )
            Text(
                modifier = Modifier
                    .padding(start = 5.dp),
                text = userUICommon.name,

                style = TextStyle(
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserItemPreview() {
    UserItem(UserUiCommon("", "")) { id, name -> }
}