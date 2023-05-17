import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import org.lokinotes.platform

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Kanban board (Experimental)",
        state = rememberWindowState(width = 1050.dp, height = 700.dp),
    ) {
        MaterialTheme {
            KanbanBoard(fakeCards())
        }
    }
}
