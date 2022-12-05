package day_4

import java.io.File

const val fileName = "src/main/resources/day_4/input"

fun main(args: Array<String>) {
    println(getContainingPairs())
    println(getOverlapPairs())
}

fun getContainingPairs(): Int {
    var totalScore = 0
    File(fileName).forEachLine { line ->
        val firstArray = createIntArray(line.split(',')[0])
        val secondArray = createIntArray(line.split(',')[1])
        if (checkIfArrayContainsArray(firstArray, secondArray) || checkIfArrayContainsArray(secondArray, firstArray)) {
            totalScore++
        }
    }

    return totalScore
}

fun getOverlapPairs(): Int {
    var totalScore = 0
    File(fileName).forEachLine { line ->
        val firstArray = createIntArray(line.split(',')[0])
        val secondArray = createIntArray(line.split(',')[1])
        if (checkIfArraysOverlap(firstArray, secondArray)) {
            totalScore++
        }
    }

    return totalScore
}

fun createIntArray(string: String): IntArray {
    val from: Int = string.split('-')[0].toInt()
    val to: Int = string.split('-')[1].toInt() + 1
    val array = IntArray(to - from)
    var i = 0
    var value = from
    for (number in array) {
        array[i++] = value++
    }
    return array
}

fun checkIfArrayContainsArray(firstArray: IntArray, secondArray: IntArray): Boolean {
    for (number in firstArray) {
        if (!secondArray.contains(number)) {
            return false
        }
    }
    return true
}

fun checkIfArraysOverlap(firstArray: IntArray, secondArray: IntArray): Boolean {
    for (number in firstArray) {
        if (secondArray.contains(number)) {
            return true
        }
    }
    return false
}
