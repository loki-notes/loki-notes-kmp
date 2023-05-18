import androidx.compose.material.MaterialTheme
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Kanban board (Experimental)",
        state = rememberWindowState(width = 1050.dp, height = 700.dp),
    ) {
        MaterialTheme {
            KanbanGroupScreen(KanbanViewModel())
        }
    }
}
