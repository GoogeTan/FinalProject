package aba.caba.myapplication.page

import aba.caba.myapplication.data.Theme
import aba.caba.myapplication.ui.theme.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Test(theme : Theme, goBack : () -> Unit) {
    var provider by remember(theme) { mutableStateOf(theme.sortWay.provider(theme)) }
    var shown by remember(provider) { mutableStateOf(true) }
    Column(Modifier.padding(24.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            BackArrow {
                goBack()
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text("Заучивание", style = MaterialTheme.typography.h1)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Spacer(modifier = Modifier.weight(1f))
            for (i in provider.good) {
                val color = when (i) {
                    true -> green
                    false -> red
                    null -> gray
                }
                Spacer(modifier = Modifier.width(2.5.dp))
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .background(color, RoundedCornerShape(1000.dp))
                )
                Spacer(modifier = Modifier.width(2.5.dp))
            }
            Spacer(modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(8.dp))
        if (shown) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(128.dp)
                    .background(gray, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(provider.card.key, style = MaterialTheme.typography.h2)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(blue, RoundedCornerShape(12.dp))
                    .clickable { shown = false },
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Показать ответ", color = white, )
            }
        } else {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(128.dp)
                    .background(gray, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center) {
                Text(provider.card.value, style = MaterialTheme.typography.h2)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(green, RoundedCornerShape(12.dp))
                    .clickable {
                        provider = provider.submitAnswer(true) ?: return@clickable goBack()
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Совпало", color = white, )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(red, RoundedCornerShape(12.dp))
                    .clickable {
                        provider = provider.submitAnswer(false) ?: return@clickable goBack()
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Не совпало", color = white, )
            }
        }
    }
}