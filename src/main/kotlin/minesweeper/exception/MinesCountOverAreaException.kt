package minesweeper.exception

class MinesCountOverAreaException(private val minesCount: Int, private val area: Int) : RuntimeException() {

    override val message: String = MESSAGE.format(minesCount, area)

    companion object {
        private const val MESSAGE = "'%s'는 area 의 크기(%s)를 넘었습니다."
    }
}
