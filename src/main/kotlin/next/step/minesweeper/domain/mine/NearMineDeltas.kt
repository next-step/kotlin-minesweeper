package next.step.minesweeper.domain.mine

import next.step.minesweeper.domain.position.Delta

object NearMineDeltas {

    val deltas: List<Delta> = listOf(
        Delta(-1, -1),
        Delta(0, -1),
        Delta(1, -1),
        Delta(-1, 0),
        Delta(1, 0),
        Delta(-1, 1),
        Delta(0, 1),
        Delta(1, 1)
    )
}
