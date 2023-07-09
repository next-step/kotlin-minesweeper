package domain

class Spaces(private val spaces: MutableList<Space>) {

    fun list(): List<Space> {
        return spaces.toList()
    }

    fun chunked(size: Int): List<Spaces> {
        return spaces.chunked(size).map { Spaces(it.toMutableList()) }
    }

    private fun plantMineAt(index: Int) {
        spaces[index] = Space.Mine
    }

    fun plantMinesAt(indices: List<Int>) {
        indices.forEach { plantMineAt(it) }
    }

    companion object {
        fun emptySpaces(size: Int): Spaces {
            return Spaces(
                MutableList(size) { Space.Empty }
            )
        }
    }
}
