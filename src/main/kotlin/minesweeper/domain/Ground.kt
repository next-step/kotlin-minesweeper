package minesweeper.domain

class Ground(val height: Int, val vertical: Int) {

    init {
        require(height > MINIMUM_SIZE && vertical > MINIMUM_SIZE) { "가로와 세로는 자연수만 가능합니다. : $height, $vertical" }
    }

    companion object {
        const val MINIMUM_SIZE = 0
    }
}