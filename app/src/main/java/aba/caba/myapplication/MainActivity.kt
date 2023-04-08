package aba.caba.myapplication

import aba.caba.myapplication.data.Card
import aba.caba.myapplication.data.SortWay
import aba.caba.myapplication.data.Stack
import aba.caba.myapplication.data.Theme
import aba.caba.myapplication.page.MainPage
import aba.caba.myapplication.page.Test
import aba.caba.myapplication.ui.theme.MyApplicationTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                var openedTheme by remember { mutableStateOf<Theme?>(Theme("Phasal verbs", SortWay.Cycle, Stack(
                    listOf(
                        Card("go", "went"),
                        Card("build", "build"),
                        Card("aba", "caba")
                    )
                )
                )) }
                val map = remember { mutableStateListOf<Theme>() }
                if (openedTheme == null) {
                    MainPage(themes = map) {
                        openedTheme = it
                    }
                } else {
                    Test(openedTheme!!) {
                        openedTheme = null
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}