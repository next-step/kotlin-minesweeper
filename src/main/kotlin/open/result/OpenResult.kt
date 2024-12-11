package open.result

import map.Map

sealed class OpenResult {
    data object MineExploded : OpenResult()

    data object InvalidPosition : OpenResult()

    data class Success(
        val map: Map,
    ) : OpenResult()
}
