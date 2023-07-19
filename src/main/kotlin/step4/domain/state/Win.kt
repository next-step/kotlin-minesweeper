package step4.domain.state

import step4.domain.Cells

class Win(
    val cells: Cells,
) : Finished()
