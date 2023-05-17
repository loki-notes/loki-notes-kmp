import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun KanbanBoard(columns: List<List<KanbanCard>>) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyRow {
            items(columns) { cards ->
                LazyColumn {
                    items(cards) { card ->
                        BoardItem(card.title)
                    }
                }
            }
        }
    }
}

@Composable
fun ColumnName() {

}

@Composable
fun KanbanColumn() {

}

@Composable
fun BoardItem(title: String) {
    Box(modifier = Modifier.width(150.dp)) {
        Text(text = title)
    }
}

//@Preview
//@Composable
//private fun KanbanBoardPreview() {
//    KanbanBoard(fakeCards())
//}
//
//@Preview
//@Composable
//private fun TextPreview() {
//    MaterialTheme {
//        Box(modifier = Modifier.background(color = Color.White)) {
//            Text(text = "Test")
//        }
//    }
//}

@Preview
@Composable
private fun TextPreview() {
//    MaterialTheme {
    Box(modifier = Modifier.background(color = Color.White)) {
        Text(text = "Test")
    }
//    }
}


data class KanbanCard(
    val title: String,
)

fun fakeCards(): List<List<KanbanCard>> {
    return listOf(
        // To-do
        listOf(
            KanbanCard(title = "Task #1"),
            KanbanCard(title = "Task #2"),
            KanbanCard(title = "Task #3"),
            KanbanCard(title = "Task #4"),
        ),
        // In progress
        listOf(
            KanbanCard(title = "Task #5"),
        ),
        // Done
        listOf(
            KanbanCard(title = "Task #6"),
            KanbanCard(title = "Task #7"),
            KanbanCard(title = "Task #8"),
            KanbanCard(title = "Task #9"),
            KanbanCard(title = "Task #10"),
            KanbanCard(title = "Task #11"),
        )
    )
}
