package minesweeper.domain.common

data class NumberSet(private val numberSet: Set<Int>) : Set<Int> by numberSet {
    companion object {
        fun of(collection: Collection<Int>): NumberSet {
            require(collection.distinct().size == collection.size) { "values must not to be duplicated." }
            return NumberSet(collection.toSet())
        }
    }
}
