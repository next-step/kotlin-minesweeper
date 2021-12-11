import domain.FieldSize
import domain.MineSweeperGame
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MineSweeperGameTest {

    @Test
    fun `가로, 높이, 지뢰 갯수가 주어졌을때 새 게임을 만들 수 있다`() {
        val predictedNumberOfMine = 5
        val width = 3
        val height = 5
        val game = MineSweeperGame.newGame(FieldSize(3, 5), predictedNumberOfMine)
        val allSlot = game.mines
            .allSlots()
            .flatten()
        val actualNumberOfMine = allSlot
            .filter { it.isMine() }
            .size
        assertThat(actualNumberOfMine).isEqualTo(predictedNumberOfMine)

        val actualNumberOfGround = allSlot
            .filter { !it.isMine() }
            .size
        val predictedNumberOfGround = width * height - predictedNumberOfMine
        assertThat(actualNumberOfGround).isEqualTo(predictedNumberOfGround)
    }
}
