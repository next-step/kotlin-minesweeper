package mine

import org.junit.jupiter.api.BeforeEach

class GameTest {
    private lateinit var board: Board

    @BeforeEach
    fun setup() {
        board = Board.createBoard(Width(10), Height(10), Mine(10))
    }

    // @Test
    // fun `지뢰인 셀을 클릭한다`() {
    //     val status = board.clicked(position)
    //
    //     Assertions.assertThat(position).isEqualTo(null)
    // }
    //
    // @Test
    // fun `주변의 지뢰가 없는 셀을 클릭한다`() {
    //     val position = Position(1, 1)
    //     val status = board.clicked(position)
    //
    //     Assertions.assertThat(position).isEqualTo(null)
    // }
    //
    // @Test
    // fun `주변에 지뢰가 있는 셀을 클릭한다`() {
    //     val position = Position(1, 1)
    //     val status = board.clicked(position)
    //
    //     Assertions.assertThat(position).isEqualTo(null)
    // }
}
