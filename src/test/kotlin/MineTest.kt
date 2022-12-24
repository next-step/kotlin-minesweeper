import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.FreeSpec
import minesweeper.domain.Mine

class MineTest : FreeSpec({
    "지뢰 생성" {
        shouldNotThrowAny { Mine() }
    }
})
