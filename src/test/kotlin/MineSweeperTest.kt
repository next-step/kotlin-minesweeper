import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class MineSweeperTest {
    @ParameterizedTest
    @ValueSource(strings = ["0", "-10", "46", "100"])
    fun `너비는 1부터 30까지의 숫자만 입력 가능하다`(number: String) {
        shouldThrow<IllegalArgumentException> {
            PositiveNumber.of(number)
        }.also {
            it.message shouldBe "1부터 30까지 입력가능합니다"
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [0, -10, 46, 100])
    fun `너비와 1부터 30까지의 숫자만 입력 가능하다`(number: Int) {
        shouldThrow<IllegalArgumentException> {
            PositiveNumber(number)
        }.also {
            it.message shouldBe "1부터 30까지 입력가능합니다"
        }
    }

    @Test
    fun `좌표값을 비교 할 수 있다`() { 
        val coordinate1 = Coordinate(axisX = 10, axisY = 15)
        val coordinate2 = Coordinate(axisX = 10, axisY = 15)
        coordinate1 shouldBeEqual coordinate2
    }

    @Test 
    fun `좌표값 중 하나를 지뢰를 설치 할 수 있다`() {
        val coordinate = Coordinate(axisX = 10, axisY = 15)
        val cell = Cell(coordinate, true)

        cell.hasMine shouldBe true
        cell.coordinate shouldBe coordinate
    }

    @Test
    fun `보드는 넓이 x 높이 만큼의 크기로 생성된다`() {
        val board = Board(10, 10)
        board.coordinates.size shouldBe 100
    }

    @Test
    fun `보드의 넓이와 높이느 30보다 클 수 없다`() {
        shouldThrow<IllegalArgumentException> {
            Board(31, 35)
        }.also {
            it.message shouldBe "1부터 30까지 입력가능합니다"
        }
    }

    @Test
    fun `해당 특정 좌표에 지뢰를 만들면 지뢰인지 확인해볼수 있다`() {
        val coordinate = Coordinate(axisX = 1, axisY = 1)
        val cell = Cell(coordinate, true)
        cell.hasMine shouldBe true
    }

    @Test
    fun `지뢰 없이 셀을 만들면 지뢰의 숫자는 0이다`() {
        val board = Board(10, 10)
        board.makeCell()
        board.countMines() shouldBe 0
    }

    @Test
    fun `지뢰를 포함한 셀을 만들면 지뢰의 숫자를 셀수 있다`() {
        val board = Board(10, 10)
        val mineCount = 10
        board.makeCell(mineCount)
        board.countMines() shouldBe 10
    }

    @Test
    fun `보드 전체 의 셀 보다 많은 지뢰를 만들 수 없다`() {
        val board = Board(5, 5)
        val mineCount = 50
        shouldThrow<IllegalArgumentException> {
            board.makeCell(mineCount)
        }.also {
            it.message shouldBe "지뢰의 개수가 전체 셀 보다 많을 수 없습니다"
        }

    }

    @Test
    fun `보드 크기 안의 셀을 찾을 수 있다`() {
        val board = Board(10, 10)
        board.makeCell(0)
        val cell = board.findCell(5, 5)
        cell.coordinate shouldBe Coordinate(axisX = 5, axisY = 5)
    }

    @Test
    fun `보드에 없는 좌표를 찾으려고 하면 에러가 발생한다`() {
        val board = Board(10, 10)
        board.makeCell(0)
        shouldThrow<IllegalArgumentException> {
            board.findCell(15, 15)
        }.also {
            it.message shouldBe "15, 15 좌표는 없습니다"
        }
    }
}

private const val BOARD_MAX_SIZE = 30

class Board(
    width: Int,
    height: Int,
) {
    val coordinates: List<Coordinate>

    init {
        val positiveWidth = PositiveNumber(width)
        val positiveHeight = PositiveNumber(height)
        coordinates = createCoordinate(positiveWidth.number, positiveHeight.number)
    }

    var cells: List<Cell> = listOf()
        private set

    private fun createCoordinate(width: Int, height: Int): List<Coordinate> {
        return (0 until height).flatMap { y -> (0 until width).map { x -> Coordinate(x, y) } }
    }

    fun makeCell(mineCount: Int = 0) {
        val mineCoordinates = getMineCoordinate(mineCount)
        cells = coordinates.map { checkMine(mineCoordinates.contains(it), it) }
    }

    private fun getMineCoordinate(mineCount: Int): List<Coordinate> {
        require(mineCount <= coordinates.size) { "지뢰의 개수가 전체 셀 보다 많을 수 없습니다" }
        return coordinates.shuffled().take(mineCount)
    }

    private fun checkMine(isContain: Boolean, coordinate: Coordinate): Cell {
        if (isContain) {
            return Cell(coordinate, true)
        }
        return Cell(coordinate, false)
    }

    fun countMines(): Int {
        return cells.filter { it.hasMine }.size
    }

    fun findCell(x: Int, y: Int): Cell {
        return cells.find { it.isItCoordinate(Coordinate(x, y)) } ?: throw  IllegalArgumentException("$x, $y 좌표는 없습니다" )
    }
}


data class Cell(
    val coordinate: Coordinate,
    val hasMine: Boolean = false
) {
    fun isItCoordinate(coordinate: Coordinate): Boolean {
        return this.coordinate.itIt(coordinate)
    }
}

data class Coordinate(val axisX: Int, val axisY: Int) {
    fun itIt(coordinate: Coordinate): Boolean {
        return this == coordinate
    }
}


data class PositiveNumber(val number: Int) {

    init {
        require(number in MIN_NUMBER..MAX_NUMBER) { "1부터 30까지 입력가능합니다" }
    }

    companion object {
        fun of(number: String): PositiveNumber {
            return PositiveNumber(number.toIntOrNull() ?: throw IllegalArgumentException("숫자가 유효하지 않습니다"))
        }

        private const val MIN_NUMBER = 1
        private const val MAX_NUMBER = BOARD_MAX_SIZE
    }
}

//class MineBoard(width: Int, height: Int, ) {
//    init {
//        require( width in 1..30 && height in 1..30 ) {}
//
//    }
//}



