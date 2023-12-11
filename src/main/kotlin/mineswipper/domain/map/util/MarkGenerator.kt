package mineswipper.domain.map.util

import mineswipper.domain.map.Field
import mineswipper.domain.map.Mark
import mineswipper.domain.map.Mine
import mineswipper.domain.map.position.Position

object MarkGenerator {
    fun markGeneration(field: Field, position: Position = Position(0, 0)): Mark {
        val pedal = field.findPedal(position)
        if (pedal.mark != null) return pedal.mark!!

        val aroundPositions = position.getAroundPositions(field.size)
        val count = aroundPositions.positions.count {
            val findPedal = field.findPedal(it)
            findPedal is Mine
        }

        val mark = Mark(count.toString())
        pedal.mark = mark

        aroundPositions.positions.forEach {
            markGeneration(field, it)
        }

        return mark
    }
}
