package com.i.compose

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

@SuppressLint("UnrememberedMutableState")
@Composable
fun AmbientLightPage() {
    ConstraintLayout(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 50.dp)
            .verticalScroll(rememberScrollState())
    ) {
        val (Title, ColorView, Brightness) = createRefs()
        Text(
            ("ambient_light_title"),
            style = MaterialTheme.typography.titleLarge,
            fontSize = 45.sp,
            modifier = Modifier.constrainAs(Title) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        AmbientLightColor(
            "ambient_light_line_color_title",
            mutableStateOf(Color.Red),
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(200.dp)
                .constrainAs(ColorView) {
                    start.linkTo(parent.start)
                    top.linkTo(Title.bottom, margin = 20.dp)
                }
        )
    }

}