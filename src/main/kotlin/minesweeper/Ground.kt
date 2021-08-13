package minesweeper

class Ground(height: Int, vertical: Int) {
    init {
        require(height > MINIMUM_SIZE && vertical > MINIMUM_SIZE)
    }

    companion object {
        const val MINIMUM_SIZE = 0
    }
}