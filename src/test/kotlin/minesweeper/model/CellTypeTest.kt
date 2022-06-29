package minesweeper.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

@DisplayName("셀 타입 테스트")
class CellTypeTest {

    @Test
    fun `지뢰인지 판단하는 기능이 정상 동작`() {
        // given, when, then
        assertAll(
            "determining whether object is MINE",
            { assertThat(CellType.MINE.isMine()).isTrue },
            { assertThat(CellType.NON_MINE.isMine()).isFalse },
        )
    }
}
