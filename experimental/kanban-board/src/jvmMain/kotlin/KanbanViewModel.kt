import androidx.compose.foundation.MutatorMutex
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import domain.KanbanGroup
import domain.fakeData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.jetbrains.skiko.MainUIDispatcher

class KanbanViewModel {
    private val _state: MutableState<ViewState> = mutableStateOf(ViewState())
    val state: State<ViewState> = _state

    private val scope = CoroutineScope(MainUIDispatcher + SupervisorJob())

    private val mutatorMutex: MutatorMutex = MutatorMutex()

    fun moveCardTo(card: KanbanGroup.Item, toGroup: String) {
        scope.launch {
            moveCardToMutex(card = card, toGroup = toGroup)
        }
    }

    private suspend fun moveCardToMutex(card: KanbanGroup.Item, toGroup: String) =
        mutatorMutex.mutate {
            if (card.group == toGroup) return@mutate

            val updatedCard = card.copy(group = toGroup)
            val newKanbanGroups = _state.value.kanbanGroups
                .map { group ->
                    when (group.name) {
                        card.group -> {
                            group.copy(items = group.items.filter { it.title != card.title })
                        }

                        toGroup -> group.copy(items = group.items.plus(updatedCard))
                        else -> group
                    }
                }

            _state.value = _state.value.copy(kanbanGroups = newKanbanGroups)
        }
}

data class ViewState(
    val kanbanGroups: List<KanbanGroup> = fakeData(),
)
