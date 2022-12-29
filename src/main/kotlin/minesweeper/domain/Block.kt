package minesweeper.domain

sealed interface Block {
    object LandMine : Block
    class Normal : Block {
        override fun equals(other: Any?): Boolean {
            return this === other
        }

        override fun hashCode(): Int {
            return System.identityHashCode(this)
        }
    }
}
