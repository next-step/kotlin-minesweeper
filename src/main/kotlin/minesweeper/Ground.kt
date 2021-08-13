package minesweeper

class Ground(height: Int) {
    init {
        require(height > MINIMUM_HEIGHT)
    }

    companion object {
        const val MINIMUM_HEIGHT = 0;
    }
}