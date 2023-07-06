package domain

class MinesWeeper(val boards: Map<Location, Cell>) {

    fun calculateCount() {
        boards.keys.forEach {
            val cell = boards[it]
            if (cell is Basic) {
                checkAround(it, cell)
            }
        }
    }

    private fun checkAround(location: Location, basic: Basic) {
        aroundList.flatMap { height ->
            val y = location.y + height
            aroundList.map { width ->
                val x = location.x + width
                if (!location.isSame(x, y) && boards[Location(y, x)] is Mine) {
                    basic.addCount()
                }
            }
        }
    }

    companion object {
        fun of(height: Int, width: Int, count: Int): MinesWeeper {

            require((height * width) > count) { COUNT_EXCEPTION }

            val mines = MineGenerator.create(height, width, count)
            val boards = (LOCATION_START_NUM until height)
                .flatMap { y ->
                    (LOCATION_START_NUM until width)
                        .map { x ->
                            Location(y, x)
                        }
                }.associateWith { getCell(mines, it) }
            return MinesWeeper(boards)
        }

        private fun getCell(mines: List<Location>, location: Location): Cell {
            if (mines.contains(location)) return Mine
            return Basic()
        }

        private val aroundList = listOf(-1, 0, 1)
        private const val COUNT_EXCEPTION = "지뢰수가 지뢰찾기게임의 칸수보다 적어야합니다."
        private const val LOCATION_START_NUM = 0
    }
}
