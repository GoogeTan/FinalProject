package aba.caba.myapplication.page

import aba.caba.myapplication.ui.theme.MyApplicationTheme
import aba.caba.myapplication.ui.theme.black
import aba.caba.myapplication.ui.theme.white
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun NamedTextField(vertical : Boolean, name : String, value : String, setValue : (String) -> Unit) {
    val container: @Composable (@Composable () -> Unit) -> Unit = if (!vertical) {
        { body ->
            Row {
                body()
            }
        }
    } else { body ->
        Column {
            body()
        }
    }
    container {
        Box(contentAlignment = Alignment.Center, modifier = Modifier
            .height(32.dp)
            .padding(horizontal = 4.dp)) {
            Text(text = name, style = MaterialTheme.typography.h4)
        }
        Box(
            Modifier
                .fillMaxWidth()
                .height(32.dp)
                .border(2.dp, black, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.CenterStart
        ) {
            BasicTextField(
                value = value,
                onValueChange = setValue,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
            )
        }
    }
}

@Preview
@Composable
fun NamedPreview() {
    MyApplicationTheme {
        Column(Modifier.background(white)) {
            NamedTextField(vertical = true, name = "Поле ввода", value = "", setValue = {})
            Spacer(modifier = Modifier.height(16.dp))
            NamedTextField(vertical = false, name = "Поле ввода", value = "", setValue = {})
        }
    }
}