package rrff.fast_shop.view.component

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun SwipeToRevealLogin(drawableRes: Int) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val scope = rememberCoroutineScope()
        val screenHeight = maxHeight
        val screenHeightPx = with(LocalDensity.current) { screenHeight.toPx() }

        val offsetY = remember { Animatable(0f) }

        Image(
            painter = painterResource(id = drawableRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .offset { IntOffset(0, offsetY.value.roundToInt()) }
                .pointerInput(Unit) {
                    detectVerticalDragGestures(
                        onDragEnd = {
                            val shouldOpen = offsetY.value < -screenHeightPx * 0.4f
                            scope.launch {
                                if (shouldOpen) {
                                    offsetY.animateTo(-screenHeightPx)
                                } else {
                                    offsetY.animateTo(0f)
                                }
                            }
                        },
                        onVerticalDrag = { change, dragAmount ->
                            scope.launch {
                                val newOffset = offsetY.value + dragAmount
                                offsetY.snapTo(newOffset.coerceIn(-screenHeightPx, 0f))
                            }
                        }
                    )
                }
        )
    }
}