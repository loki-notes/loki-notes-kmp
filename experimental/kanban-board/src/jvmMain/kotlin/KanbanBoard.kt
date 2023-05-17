import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import domain.KanbanGroup

@Composable
fun KanbanGroupScreen(viewModel: KanbanViewModel) {
    val state by viewModel.state

    KanbanBoard(state) { toGroup, card ->
        viewModel.moveCardTo(card, toGroup)
    }
}

@Composable
fun KanbanBoard(state: ViewState, onUpdateItems: (String, KanbanGroup.Item) -> Unit) {
    DraggableContent {
        Box(modifier = Modifier.fillMaxSize().background(KanbanTheme.colors.background.base)) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(state.kanbanGroups) { group ->
                    Column(modifier = Modifier.width(250.dp)) {
                        ColumnName(group.name, group.color)

                        NewTaskButton { }

                        KanbanColumn(group, onUpdateItems)
                    }
                }
            }
        }
    }
}

@Composable
fun ColumnName(title: String, color: Color) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Canvas(modifier = Modifier.size(size = 8.dp)) {
            drawCircle(color = color)
        }

        Text(text = title)
    }
}

@Composable
fun KanbanColumn(group: KanbanGroup, onUpdateItems: (String, KanbanGroup.Item) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(group.items) { card ->
            DragTarget(dataToDrop = card) {
                BoardItem(card)
            }
        }
        item {
            DropTarget<KanbanGroup.Item> { isInBound, data ->
                if (data != null) {
                    onUpdateItems(group.name, data)
                }
                DragAndDropPlaceholder(isActiveArea = isInBound)
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BoardItem(item: KanbanGroup.Item) {
    Column(
        modifier = Modifier.width(250.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(
                2.dp,
                color = KanbanTheme.colors.foreground.border,
                shape = RoundedCornerShape(8.dp)
            )
            .background(KanbanTheme.colors.background.primary)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FlowRow {
            item.tags.forEach {
                Text(
                    text = it.name,
                    color = it.foregroundColor,
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(it.backgroundColor)
                        .padding(vertical = 8.dp, horizontal = 16.dp),
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Text(
            text = item.title,
            color = KanbanTheme.colors.text.title,
            fontWeight = FontWeight.SemiBold
        )

        if (item.description != null) {
            Text(text = item.description, color = KanbanTheme.colors.text.description)
        }
    }
}

@Composable
fun NewTaskButton(onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
            .height(40.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(
                2.dp,
                color = KanbanTheme.colors.foreground.border,
                shape = RoundedCornerShape(8.dp)
            )
            .background(KanbanTheme.colors.background.primary)
    ) {
        Icon(
            imageVector = Icons.Rounded.Add,
            contentDescription = null,
            tint = KanbanTheme.colors.text.accent
        )

        Spacer(Modifier.width(8.dp))

        Text(
            "Add new task",
            color = KanbanTheme.colors.text.accent,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun DragAndDropPlaceholder(modifier: Modifier = Modifier, isActiveArea: Boolean) {
    val stroke = Stroke(
        width = 2f, pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    )

    val accentColor = with(KanbanTheme.colors.foreground) {
        if (isActiveArea) positive else placeholder
    }

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Canvas(Modifier.fillMaxWidth().height(72.dp)) {
            drawRoundRect(
                color = accentColor,
                style = stroke,
                cornerRadius = CornerRadius(x = 4.dp.toPx(), 4.dp.toPx())
            )
        }

        Text("Move here", color = accentColor)
    }
}
