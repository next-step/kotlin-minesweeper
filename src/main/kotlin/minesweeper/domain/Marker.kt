package minesweeper.domain

class Marker(val height: Int, val vertical: Int) {

    init {
        require(height > MINIMUM_SIZE && vertical > MINIMUM_SIZE) { "가로와 세로는 자연수만 가능합니다. : $height, $vertical" }
    }

    fun generateAllGround(): MutableList<List<String>> {
        val markers = mutableListOf<List<String>>()

        (START_POSITION until height).map {
            val newMarker = mutableListOf<String>()

            generateRowGround(newMarker)

            markers.add(newMarker)
        }

        return markers
    }

    private fun generateRowGround(newMarker: MutableList<String>) {
        (START_POSITION until vertical).map {
            newMarker.add(MARKER_EXPRESSION)
        }
    }

    companion object {
        private const val MINIMUM_SIZE = 0
        private const val START_POSITION = 0
        private const val MARKER_EXPRESSION = "C "
    }
}