package day_9

import java.io.File
import java.lang.Integer.max
import kotlin.math.abs

const val fileName = "src/main/resources/day_9/input"

fun main(args: Array<String>) {
    println(getTailLocationsNumber())
}

fun getTailLocationsNumber(): Int {
    val lines = readFileAsLinesUsingUseLines(fileName)
    var head = Position(0,0)
    var tails:List<Position> = List(9) { Position(0,0) }
    val locationList: MutableSet<Position> = mutableSetOf()

    lines.forEach { line ->
        var values = line.split(' ')
        for (i in 0 until values[1].toInt()) {
            when(values[0].toCharArray()[0]) {
                'L' -> {
                    head.x--
                }
                'R' -> {
                    head.x++
                }
                'D' -> {
                    head.y--
                }
                'U' -> {
                    head.y++
                }
            }
            for (i in 0 until tails.size) {
                if (i == 0) {
                    if (max(abs(tails[i].x - head.x), abs(tails[i].y - head.y)) > 1) {
                        if (head.x > tails[i].x) {
                            tails[i].x++
                        }
                        if (head.x < tails[i].x) {
                            tails[i].x--
                        }
                        if (head.y > tails[i].y) {
                            tails[i].y++
                        }
                        if (head.y < tails[i].y) {
                            tails[i].y--
                        }
                    }
                } else {
                    if (max(abs(tails[i].x - tails[i - 1].x), abs(tails[i].y - tails[i - 1].y)) > 1) {
                        if (tails[i - 1].x > tails[i].x) {
                            tails[i].x++
                        }
                        if (tails[i - 1].x < tails[i].x) {
                            tails[i].x--
                        }
                        if (tails[i - 1].y > tails[i].y) {
                            tails[i].y++
                        }
                        if (tails[i - 1].y < tails[i].y) {
                            tails[i].y--
                        }
                    }
                }
                if (i == 8) {
                    locationList.add(tails[i].copy())
                }

            }

        }
    }
    return locationList.size
}

data class Position(var x:Int, var y: Int)

fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toList() }
