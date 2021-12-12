import domain.FieldSize
import domain.MineSweeperGame
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MineSweeperGameTest {

    @Test
    fun `가로, 높이, 지뢰 갯수가 주어졌을때 새 게임을 만들 수 있다`() {
        val predictedNumberOfMine = 5
        val fieldSize = FieldSize(3, 5)
        val game = MineSweeperGame.newGame(fieldSize, predictedNumberOfMine)
        val allSlot = game.mineField
            .allSlots()
            .flatten()
        val actualNumberOfMine = allSlot.filter { it.isMine() }.size
        assertThat(actualNumberOfMine).isEqualTo(predictedNumberOfMine)

        val actualNumberOfGround = allSlot.filter { !it.isMine() }.size
        val predictedNumberOfGround = fieldSize.width * fieldSize.height - predictedNumberOfMine
        assertThat(actualNumberOfGround).isEqualTo(predictedNumberOfGround)
    }
}
