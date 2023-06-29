package map.position.selector

import map.position.Position
import kotlin.random.Random
import kotlin.random.nextInt

// 런타임에 해당 클래스를 사용하는 MineMap 클래스의
// 너비나 높이가 변경되는 요구사항이 있는 경우에는
// 생성자에서 받은 yRange, xRange 가 동기화가 안 될 우려가 있어서
// select 메소드 에서 파라미터로 받으면 제일 좋겠지만
// 그러면 인터페이스를 변경/관리 하기가 어려워질것같다
class RandomMinePositionSelector(
    private val yRange: IntRange,
    private val xRange: IntRange,
) : MinePositionSelector {

    override fun select(): Position {
        val y = Random.nextInt(yRange)
        val x = Random.nextInt(xRange)
        return Position(y, x)
    }
}
