import domain.CoordinateNotFoundException
import domain.MineBoard
import dto.MineBoardDto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MineBoardDtoKtTest {

    @Test
    fun `Dto 변환 시 지뢰찾기판에 좌표가 없는 경우 예외를 반환한다`() {
        val mineBoard = MineBoard(emptyMap())
        val result = assertThrows<CoordinateNotFoundException> { MineBoardDto(mineBoard) }
        assertThat(result.message).isEqualTo("좌표가 존재하지 않습니다. coordinates: []")
    }
}
