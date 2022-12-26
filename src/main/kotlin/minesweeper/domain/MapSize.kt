package minesweeper.domain

data class MapSize(val width: Int, val height: Int) {

    init {
        require(width >= MIN_MAP_SIZE && height >= MIN_MAP_SIZE) { "width & height should be greater or equal than $MIN_MAP_SIZE" }
    }

    companion object {
        private const val MIN_MAP_SIZE = 1
    }
}
