package domain

import androidx.compose.ui.graphics.Color


data class KanbanGroup(
    val name: String,
    val color: Color,
    val items: List<Item>,
) {
    data class Item(
        val group: String,
        val title: String,
        val description: String?,
        val tags: List<Tag>,
    )

    data class Tag(
        val name: String,
        val backgroundColor: Color,
        val foregroundColor: Color,
    )
}

fun fakeData(): List<KanbanGroup> {
    val uxTag = KanbanGroup.Tag(
        name = "UX",
        backgroundColor = Color(0xFFEA9836).copy(alpha = 0.2f),
        foregroundColor = Color(0xFFEA9836)
    )

    val uiTag = KanbanGroup.Tag(
        name = "UI",
        backgroundColor = Color(0xFF7D62EB).copy(alpha = 0.2f),
        foregroundColor = Color(0xFF7D62EB)
    )

    val boardingTag = KanbanGroup.Tag(
        name = "Boarding",
        backgroundColor = Color(0xFFE1807C).copy(alpha = 0.2f),
        foregroundColor = Color(0xFFE1807C)
    )


    return listOf(
        KanbanGroup(
            name = "To-do",
            color = Color(0xFF375DFF), items = listOf(
                KanbanGroup.Item(
                    group = "To-do",
                    title = "First design concept",
                    description = "Create a concept based on te research and insights gathered during the discovery phase of the project...",
                    tags = listOf(uiTag)
                ),
                KanbanGroup.Item(
                    group = "To-do",
                    title = "Design library",
                    description = "Create a concept based on te research and insights gathered during the discovery phase of the project...",
                    tags = listOf(uiTag)
                ),
                KanbanGroup.Item(
                    group = "To-do",
                    title = "Wireframing",
                    description = "Create low-fidelity designs that outline the basic structure and layout of the product or service...",
                    tags = listOf(uxTag)
                ),
            )
        ),
        KanbanGroup(
            name = "In progress", color = Color(0xFFFFBC38), items = listOf(
                KanbanGroup.Item(
                    group = "In progress",
                    title = "Customer Journey Mapping",
                    description = "Identify the key touchpoints and pain points in the customer journey, and to develop strategies to improve...",
                    tags = listOf(uxTag)
                ),
                KanbanGroup.Item(
                    group = "In progress",
                    title = "Personal development",
                    description = "Create user personas based on the research data to represent different user groups and their characteristics...",
                    tags = listOf(uxTag)
                ),
            )
        ),
        KanbanGroup(
            name = "Need Review", color = Color(0xFF365DFF), items = listOf(
                KanbanGroup.Item(
                    group = "Need Review",
                    title = "Competitor research",
                    description = "Create a concept based on te research and insights gathered during the discovery phase of the project...",
                    tags = listOf(uxTag)
                ),
            )
        ),
        KanbanGroup(
            name = "Done", color = Color(0xFF10BF98), items = listOf(
                KanbanGroup.Item(
                    group = "Done",
                    title = "Branding, visual identity",
                    description = "Create a concept based on te research and insights gathered during the discovery phase of the project...",
                    tags = listOf(boardingTag)
                ),
                KanbanGroup.Item(
                    group = "Done",
                    title = "Marketing materials",
                    description = "Create a concept based on te research and insights gathered during the discovery phase of the project...",
                    tags = listOf(boardingTag)
                ),
            )
        ),
    )
}
