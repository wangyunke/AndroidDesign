package com.i.compose

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.graphics.ColorUtils
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import kotlin.math.max
import kotlin.math.roundToInt

@Composable
fun AmbientLightColor(
    title: String,
    color: State<Color>,
    modifier: Modifier = Modifier
) {
    val updateWhileMoving = remember { mutableStateOf(false) }
    val initColor = remember { color }

    ConstraintLayout(modifier = modifier) {
        val (Title, Palette) = createRefs()
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.constrainAs(Title) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
            }
        )
        AmbientLightPalette(
            initColor = initColor.value,
            modifier = Modifier.height(50.dp).fillMaxWidth(0.8f).constrainAs(Palette) {
                top.linkTo(Title.bottom, margin = 20.dp)
                start.linkTo(parent.start, margin = 50.dp)
                end.linkTo(parent.end, margin = 50.dp)
            }
        )
        val Switch = createRef()
        SwitchWidget(
            enable = true,
            name = "ambient_light_update_while_moving",
            value = updateWhileMoving.value,
            onCheckedChange = { updateWhileMoving.value = updateWhileMoving.value.not() },
            modifier = Modifier.constrainAs(Switch) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(Palette.bottom, margin = 40.dp)
            }
        )
    }
}

@Composable
fun AmbientLightPalette(
    initColor: Color?,
    modifier: Modifier = Modifier
) {
    val thumbWidthDP = 40.dp
    val thumbWidthPX = with(LocalDensity.current) { thumbWidthDP.toPx() }
    val paletteWidth = remember { mutableStateOf(0) }
    val paletteHeight = remember { mutableStateOf(0) }

    val offsetX = remember { mutableStateOf(0f) }
    val draggableState = rememberDraggableState {
        val newValue = (offsetX.value + it).coerceIn(0f, (paletteWidth.value - thumbWidthPX))
        if (newValue != offsetX.value) {
            offsetX.value = newValue
            val percent = offsetX.value / (paletteWidth.value - thumbWidthPX)
//            onColorChanged.invoke(percent.percentToHSVColor())
        }/**/
    }
    if (initColor != null && paletteWidth.value > 0) {
        val argb = initColor.toArgb()
        val arr = floatArrayOf(0f, 0f, 0f)
        ColorUtils.RGBToHSL(argb.red, argb.green, argb.blue, arr)
        val h = arr[0]
        offsetX.value = h / 360 * paletteWidth.value
    }
    ConstraintLayout(modifier = modifier.customLayoutModifier(paletteWidth, paletteHeight)) {
        val (Thumb, Background) = createRefs()
        Canvas(
            Modifier.fillMaxSize().constrainAs(Background) {
                linkTo(
                    start = parent.start,
                    top = parent.top,
                    end = parent.end,
                    bottom = parent.bottom
                )
            }
        ) {
            val lineWidth = paletteWidth.value.div(360f)
            repeat(360) {
                val color = Color.hsv(it.toFloat(), 1f, 1f)
                val start = Offset(max((it - 1) * lineWidth, 0f), paletteHeight.value / 2f)
                val end = Offset(it * lineWidth, paletteHeight.value / 2f)
                drawLine(color, start, end, strokeWidth = paletteHeight.value.toFloat())
            }
        }
        Box(
            modifier = Modifier
                .width(thumbWidthDP)
                .fillMaxHeight()
                .constrainAs(Thumb) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
                .offset {
                    if (paletteWidth.value > 0) {
                        val newOffsetX = offsetX.value.roundToInt().coerceIn(
                            0,
                            paletteWidth.value - thumbWidthPX.toInt()
                        )
                        IntOffset(newOffsetX, 0)
                    } else {
                        IntOffset.Zero
                    }
                }
                .draggable(
                    orientation = Orientation.Horizontal,
                    state = draggableState,
                    onDragStopped = { /*onChangedEnd.invoke()*/ }
                )
                .border(5.dp, Color.Gray)
        )
    }
}

@Preview(widthDp = 100, heightDp = 50)
@Composable
fun AmbientLightPalettePreview() {
    AmbientLightPalette(
        Color.Red,
//        onColorChanged = {},
        modifier = Modifier.fillMaxSize(),
//        onChangedEnd = {}
    )
}

@Composable
fun SwitchWidget(
    name: String,
    value: Boolean,
    enable: Boolean,
    onCheckedChange: ((Boolean) -> Unit),
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier = modifier
            .padding(25.dp)
    ) {
        val (Name, Switch) = createRefs()
        Text(
            text = name,
            fontSize = 30.sp,
            modifier = Modifier.constrainAs(Name) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                end.linkTo(Switch.start, margin = 20.dp)
            }
        )
        Switch(
            checked = value,
            enabled = enable,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.constrainAs(Switch) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
            }
        )
    }
}

private fun Float.percentToHSVColor(): Color {
    return Color.hsv(this.times(360), 1f, 1f)
}

private fun Modifier.customLayoutModifier(
    widthState: MutableState<Int>,
    heightState: MutableState<Int>
) =
    this.layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        widthState.value = placeable.width
        heightState.value = placeable.height
        layout(constraints.maxWidth, constraints.maxHeight) {
            placeable.placeRelative(0, 0)
        }
    }
