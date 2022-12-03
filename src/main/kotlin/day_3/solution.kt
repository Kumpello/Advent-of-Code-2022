package day_3

import java.io.File

const val fileName = "src/main/resources/day_3/input"

fun main(args: Array<String>) {
    println(getCommonItemsPoints())
    println(getBadgePoints())
}

fun getCommonItemsPoints(): Int {
    var totalScore = 0
    File(fileName).forEachLine { line ->
        val firstHalf = line.subSequence(0, line.length / 2)
        val secondHalf = line.subSequence(line.length / 2, line.length)
        val commonSign: Char = getCommonItem(firstHalf, secondHalf)
        totalScore += findValue(commonSign)
    }

    return totalScore
}

fun getBadgePoints(): Int {
    var totalScore = 0
    var commonSigns:CharArray = CharArray(0)
    var iter = 0
    File(fileName).forEachLine { line ->
        if (iter == 0) {
            commonSigns = line.toCharArray()
            iter++
        } else if (iter == 1) {
            commonSigns = getCommonItems(commonSigns, line.toCharArray())
            iter++
        } else {
            commonSigns = getCommonItems(commonSigns, line.toCharArray())
            totalScore += findValue(commonSigns[0])
            iter = 0
        }

    }

    return totalScore
}

fun getCommonItem(first: CharSequence, second: CharSequence): Char {
    val iterator = first.iterator()
    var character = ' '
    while (iterator.hasNext()) {
        character = iterator.nextChar()
        if (second.contains(character)) {
            break
        }
    }
    return character
}

fun getCommonItems(first: CharArray, second: CharArray): CharArray {
    val iterator = first.iterator()
    var character: Char
    val commonCharacters = mutableListOf<Char>()
    while (iterator.hasNext()) {
        character = iterator.nextChar()
        if (second.contains(character)) {
            commonCharacters.add(character)
        }
    }
    return commonCharacters.toCharArray()
}

fun findValue(char: Char): Int {
    return if (char.isLowerCase()) {
        char.code - 96
    } else {
        char.code - 38
    }
}


