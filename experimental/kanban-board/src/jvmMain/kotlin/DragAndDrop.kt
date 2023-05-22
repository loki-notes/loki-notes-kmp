import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

@Composable
fun DraggableContent(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
) {
    val state = remember { CardDragState() }

    CompositionLocalProvider(
        LocalCardDragState provides state
    ) {
        Box(modifier = modifier.fillMaxSize()) {
            content()

            if (state.isDragging) {
                var targetSize by remember { mutableStateOf(IntSize.Zero) }

                Box(modifier = Modifier
                    .graphicsLayer {
                        val offset = (state.position + state.offset)

                        alpha = if (targetSize == IntSize.Zero) 0f else .66f

                        translationX = offset.x.minus(targetSize.width / 2)
                        translationY = offset.y.minus(targetSize.height / 2)
                    }
                    .onGloballyPositioned {
                        targetSize = it.size
                    }
                ) {
                    state.composable?.invoke()
                }
            }
        }
    }
}

@Composable
fun <T> DragTarget(
    modifier: Modifier = Modifier,
    data: T,
    key: (T?) -> Boolean,
    placeholder: @Composable (() -> Unit)? = null,
    content: @Composable (() -> Unit),
) {
    var currentPosition by remember { mutableStateOf(Offset.Zero) }
    var currentSize by remember { mutableStateOf(IntSize.Zero) }
    val currentState = LocalCardDragState.current

    Box(
        modifier = modifier
            .onGloballyPositioned {
                currentPosition = it.localToWindow(Offset.Zero)
                currentSize = it.size
            }
            .pointerInput(Unit) {
                detectDragGestures(onDragStart = {
                    currentState.data = data
                    currentState.isDragging = true
                    currentState.position = currentPosition + it
                    currentState.composable = content
                    currentState.size = currentSize
                }, onDrag = { change, dragAmount ->
                    change.consume()
                    currentState.offset += dragAmount
                }, onDragEnd = {
                    currentState.isDragging = false
                    currentState.offset = Offset.Zero
                }, onDragCancel = {
                    currentState.offset = Offset.Zero
                    currentState.isDragging = false
                })
            }
    ) {
        if (currentState.isDragging && key(currentState.data as? T?) && placeholder != null) {
            Box(
                modifier = Modifier.size(currentState.size.width.dp, currentState.size.height.dp)
            ) {
                placeholder()
            }
        } else {
            content()
        }
    }
}

@Composable
fun <T> DropTarget(
    modifier: Modifier = Modifier,
    content: @Composable (BoxScope.(isInBound: Boolean, data: T?) -> Unit),
) {
    val dragState = LocalCardDragState.current
    val dragPosition = dragState.position
    val dragOffset = dragState.offset
    var isCurrentDropTarget by remember { mutableStateOf(false) }

    Box(modifier = modifier.onGloballyPositioned {
        it.boundsInWindow()
            .let { rect ->
                isCurrentDropTarget = rect.contains(dragPosition + dragOffset)
            }
    }) {
        val data = if (isCurrentDropTarget && !dragState.isDragging) {
            dragState.data as? T?
        } else {
            null
        }

        content(isCurrentDropTarget, data)
    }
}

class CardDragState {
    var data by mutableStateOf<Any?>(null)
    var isDragging: Boolean by mutableStateOf(false)
    var position by mutableStateOf(Offset.Zero)
    var offset by mutableStateOf(Offset.Zero)
    var size by mutableStateOf(IntSize.Zero)
    var composable by mutableStateOf<(@Composable () -> Unit)?>(null)
}

val LocalCardDragState = compositionLocalOf { CardDragState() }
