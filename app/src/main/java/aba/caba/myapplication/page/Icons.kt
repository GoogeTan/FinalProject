package aba.caba.myapplication.page

import aba.caba.myapplication.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun AddButton(callback : () -> Unit) {
    Image(
        painter = painterResource(id = R.drawable.plus),
        contentDescription = null,
        modifier = Modifier
            .size(24.dp)
            .clickable(onClick = callback)
    )
}

@Composable
fun PenButton(callback : () -> Unit) {
    Image(
        painter = painterResource(id = R.drawable.pen),
        contentDescription = null,
        modifier = Modifier
            .size(20.dp)
            .clickable(onClick = callback)
    )
}

@Composable
fun BackArrow(callback : () -> Unit) {
    Image(
        painter = painterResource(id = R.drawable.back),
        contentDescription = null,
        modifier = Modifier
            .size(20.dp)
            .clickable(onClick = callback)
    )
}