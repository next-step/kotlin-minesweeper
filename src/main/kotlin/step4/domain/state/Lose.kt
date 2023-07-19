package step4.domain.state

import step4.domain.Cells

class Lose(
    val cells: Cells,
) : Finished()
