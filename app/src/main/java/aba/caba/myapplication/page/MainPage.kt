package aba.caba.myapplication.page

import aba.caba.myapplication.data.Card
import aba.caba.myapplication.data.SortWay
import aba.caba.myapplication.data.Stack
import aba.caba.myapplication.data.Theme
import aba.caba.myapplication.ui.theme.black
import aba.caba.myapplication.ui.theme.blue
import aba.caba.myapplication.ui.theme.gray
import aba.caba.myapplication.ui.theme.white
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

data class ThemeEditor(val name : String, val theme : Theme, val themeIndex : Int?)

@Composable
fun MainPage(themes : SnapshotStateList<Theme>, openTheme : (Theme) -> Unit) {
    var cardOpened by remember { mutableStateOf<ThemeEditor?>(null) }
    Box(Modifier.fillMaxSize()) {
        Column(Modifier.padding(24.dp)) {
            Row {
                Text(text = "Стопки", style = MaterialTheme.typography.h1)
                Spacer(modifier = Modifier.weight(1f))
                AddButton {
                    cardOpened = ThemeEditor("Создать стопку", Theme("", SortWay.Filter, Stack(listOf())), null)
                }
                //ThemeEditor(text = "Создание стопки", theme = Theme("", Stack(listOf())), pushTheme = pushTheme)
            }
            Spacer(modifier = Modifier.height(16.dp))
            for ((index, theme) in themes.withIndex()) {
                Spacer(modifier = Modifier.height(20.dp))
                Theme(theme, {
                    openTheme(theme)
                }) {
                    cardOpened = ThemeEditor("Редактировать стопку", theme, index)
                }
            }
        }
        cardOpened?.let { themeEditor ->
            var theme by remember(themeEditor) { mutableStateOf(themeEditor.theme) }
            Column(
                Modifier
                    .fillMaxSize()
                    .background(Color(0x33000000))
            ) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .clickable {
                            if (themeEditor.themeIndex == null) {
                                themes.add(theme)
                            } else {
                                themes[themeEditor.themeIndex] = theme
                            }
                            cardOpened = null
                        }
                )
                ThemeEditor(themeEditor.name, theme) {
                    theme = it
                }
            }
        }
    }
}

@Composable
fun Theme(theme: Theme, onClick : () -> Unit, editTheme : () -> Unit) {
    Row {
        Box(modifier =
            Modifier
                .height(28.dp)
                .width(2.dp)
                .background(
                    Brush.linearGradient(0f to Color(0xFF7CD0FF), 1f to Color(0xFFBD7EEE)),
                    RoundedCornerShape(2.dp)
                ).clickable(onClick = onClick)
        )
        Spacer(modifier = Modifier.width(8.dp).clickable(onClick = onClick))
        Text(text = theme.name, style = MaterialTheme.typography.h3, modifier = Modifier.clickable(onClick = onClick))
        Spacer(modifier = Modifier.weight(1f).clickable(onClick = onClick))
        PenButton(editTheme)
    }
}

@Composable
fun ColumnScope.ThemeEditor(name : String, theme : Theme, pushTheme: (Theme) -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .background(white, RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
            .weight(4f)
            .padding(16.dp)
    ) {
        Text(text = name, style = MaterialTheme.typography.h2)
        Spacer(modifier = Modifier.height(16.dp))
        NamedTextField(vertical = false, name = "Название", value = theme.name) { newName ->
            pushTheme(theme.copy(name = newName))
        }
        Spacer(modifier = Modifier.height(16.dp))
        SelectSortWay(theme.sortWay) {
            pushTheme(theme.copy(sortWay = it))
        }
        Spacer(modifier = Modifier.height(16.dp))
        val state = rememberScrollState()
        Column(
            Modifier
                .weight(1f)
                .verticalScroll(state)) {
            for ((index, card) in theme.stack.cards.withIndex()) {
                val updateCard: (Card) -> Unit = {
                    pushTheme(theme.copy(stack = theme.stack.withCard(index, it)))
                }
                Column(
                    Modifier
                        .fillMaxWidth()
                        .background(gray, RoundedCornerShape(12.dp))
                        .padding(16.dp)
                ) {
                    NamedTextField(vertical = true, name = "Термин", value = card.key) {
                        updateCard(card.copy(key = it))
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    NamedTextField(vertical = true, name = "Определение", value = card.value) {
                        updateCard(card.copy(value = it))
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .background(blue, RoundedCornerShape(12.dp))
                .height(48.dp)
                .fillMaxWidth()
                .clickable {
                    pushTheme(theme.copy(stack = Stack(theme.stack.cards + listOf(Card("", "")))))
                },
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Добавить карточку", style = MaterialTheme.typography.h4, color = white)
        }
    }
}

@Composable
fun SelectSortWay(way : SortWay, setSortWay: (SortWay) -> Unit) {
    Row {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.height(32.dp).padding(horizontal = 4.dp)
        ) {
            Text(text = "Тип тестирования", style = MaterialTheme.typography.h4)
        }
        Box(
            Modifier
                .fillMaxWidth()
                .height(32.dp)
                .border(2.dp, black, RoundedCornerShape(12.dp))
                .clip(RoundedCornerShape(12.dp))
                .clickable {
                    setSortWay(SortWay.values()[(way.ordinal + 1) % SortWay.values().size])
                },
            contentAlignment = Alignment.CenterStart
        ) {
            Text(way.text, color = black, modifier = Modifier.padding(start = 8.dp))
        }
    }
}
