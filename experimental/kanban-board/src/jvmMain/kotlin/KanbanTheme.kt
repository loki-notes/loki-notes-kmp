import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

object KanbanTheme {
    val colors: KanbanColor
        @Composable get() = LocalKanbanColor.current
}

val LocalKanbanColor = compositionLocalOf { KanbanColor() }

class KanbanColor(
    val background: Background = Background(),
    val foreground: Foreground = Foreground(),
    val text: Text = Text(),
) {
    class Background {
        val base: Color = Color(0xFFF7F7F7)
        val primary: Color = Color(0xFFFFFFFF)
    }

    class Foreground {
        val placeholder: Color = Color(0xFF82868B)
        val positive: Color = Color(0xFF62B6A4)
        val border: Color = Color(0xFFEEEEEF)
    }

    class Text {
        val title: Color = Color(0xFF112033)
        val description: Color = Color(0xFF82868B)
        val accent: Color = Color(0xFF365DFF)
    }
}
