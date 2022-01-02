import domain.FieldSize
import domain.MineField
import domain.MineSweeperGame
import domain.Point
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MineSweeperGameTest {

    @Test
    fun `가로, 높이, 지뢰 갯수가 주어졌을때 새 게임을 만들 수 있다`() {
        val predictedNumberOfMine = 5
        val fieldSize = FieldSize(3, 5)
        val game = MineSweeperGame.newGame(fieldSize, predictedNumberOfMine)
        val allSlot = game
            .allSlots()
        val actualNumberOfMine = allSlot.filter { it.isMine() }.size
        assertThat(actualNumberOfMine).isEqualTo(predictedNumberOfMine)
        val actualNumberOfGround = allSlot.filter { !it.isMine() }.size
        val predictedNumberOfGround = fieldSize.width * fieldSize.height - predictedNumberOfMine
        assertThat(actualNumberOfGround).isEqualTo(predictedNumberOfGround)
    }

    @Test
    fun `MineSweeperGame이 주어졌을때 특정 slot이 Mine인지 확인 가능하다`() {
        val mineField = MineField.createByIndexs(setOf(Point(0, 0)), FieldSize(5, 1))

        val game = MineSweeperGame(mineField)

        val actualNotMineSlot = game.checkIsMineAt(Point(1, 0))
        assertThat(actualNotMineSlot).isFalse

        val actualMineSlot = game.checkIsMineAt(Point(0, 0))
        assertThat(actualMineSlot).isTrue
    }

    @Test
    fun `MineSweeperGame이 주어졌을때 모든 Ground가 check되었는지 확인 가능하다`() {
        val mineField = MineField.createByIndexs(setOf(Point(0, 0), Point(3, 1), Point(4, 1)), FieldSize(5, 2))
        val game = MineSweeperGame(mineField)

        val actualNotAllCheckedResult = game.isAllGroundChecked()
        assertThat(actualNotAllCheckedResult).isFalse

        game.checkIsMineAt(Point(1, 0))
        val actualAllCheckedResult = game.isAllGroundChecked()
        assertThat(actualAllCheckedResult).isTrue
    }
}
