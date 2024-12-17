package view

import domain.MineSweeperMap

class DefaultDrawPolicy : DrawPolicy {
    override fun draw(
        map: MineSweeperMap,
        index: Int,
    ) {
        val y = index / map.getWidth()
        val x = index % map.getWidth()
        when (map.isOpen(x, y)) {
            false -> print("C ")
            true -> if (map.isMine(x, y)) print("* ") else print("${map.getMineAroundCount(index)} ")
        }

        if (index % map.getWidth() == map.getWidth() - 1) {
            println()
        }
    }
}
