import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import domain.KanbanGroup
import domain.fakeData

class KanbanViewModel {
    private val _state: MutableState<ViewState> = mutableStateOf(ViewState())
    val state: State<ViewState> = _state

    fun moveCardTo(card: KanbanGroup.Item, toGroup: String) {
        val updatedCard = card.copy(group = toGroup)
        val newKanbanGroups = _state.value.kanbanGroups
            .map { group ->
                // Remove card from group
                if (group.name == card.group) {
                    group.copy(items = group.items.filter { it.title != card.title })
                } else {
                    group
                }
            }
            .map { group ->
                // Add card to new group
                if (group.name == toGroup) {
                    group.copy(items = group.items.plus(updatedCard))
                } else {
                    group
                }
            }

        _state.value = _state.value.copy(kanbanGroups = newKanbanGroups)
    }
}

data class ViewState(
    val kanbanGroups: List<KanbanGroup> = fakeData(),
)
