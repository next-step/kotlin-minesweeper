import domain.MineGenerator
import domain.MineState
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.types.shouldBeInstanceOf

class MineGeneratorTest : StringSpec({

    "생성된 지뢰가 반환된다" {
        val mineGenerator = object : MineGenerator(5, 50) {
            override fun isMine(): Boolean = true
        }
        mineGenerator.generate().shouldBeInstanceOf<MineState>()
    }
})
