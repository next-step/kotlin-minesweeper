package domain

class MinesWeeper(val boards: List<Board>) {

    fun calculateCount() {
        boards.forEach {
            if (it.cell is Basic) {
                checkAround(it.location, it.cell)
            }
        }
    }

    private fun checkAround(location: Location, basic: Basic) {
        val count = Around.values().map {
            val y = location.y + it.y
            val x = location.x + it.x
            boards.firstOrNull { board -> board.location.isSame(y, x) }
        }.count { board -> board != null && board.cell is Mine }
        basic.addCount(count)
    }

    companion object {
        fun of(height: Int, width: Int, count: Int): MinesWeeper {

            require((height * width) > count) { COUNT_EXCEPTION }

            val mines = MineGenerator.create(height, width, count)
            val boards = (LOCATION_START_NUM until height)
                .flatMap { y ->
                    (LOCATION_START_NUM until width)
                        .map { x ->
                            val location = Location(y, x)
                            Board(location, getCell(mines, location))
                        }
                }
            return MinesWeeper(boards)
        }

        private fun getCell(mines: List<Location>, location: Location): Cell {
            if (mines.contains(location)) return Mine
            return Basic()
        }

        private const val COUNT_EXCEPTION = "지뢰수가 지뢰찾기게임의 칸수보다 적어야합니다."
        private const val LOCATION_START_NUM = 0
    }
}
