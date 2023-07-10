package domain

sealed interface Space {
    object Mine : Space
    object Empty : Space
}
