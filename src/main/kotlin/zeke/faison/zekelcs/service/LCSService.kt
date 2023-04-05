package zeke.faison.zekelcs.service

import org.springframework.stereotype.Service
import zeke.faison.zekelcs.model.LCSContent

@Service
class LCSService {
    fun areAllStringsAreUnique(strings: List<LCSContent>): Boolean = strings.size == strings.distinct().size

    fun findLongestCommonString(strings: List<LCSContent>): String {
        // not the most efficient algorithm and probably overcomplicated, but it gets the job done
        var longestCommonSubString = ""
        for(stringIndex in strings.indices ) {
            for(comparisonStringIndex in stringIndex + 1 until strings.size) {
                val allSubStrings = with(strings[stringIndex].value) {
                 indices.asSequence().flatMap { stringSize ->
                     windowedSequence(length - stringSize)
                 }
                }.toList()
                val longestMatchedSubString = allSubStrings.filter { strings[comparisonStringIndex].value.contains(it) }.maxBy { it.length }
                longestCommonSubString = if (longestMatchedSubString.length > longestCommonSubString.length) longestMatchedSubString else longestMatchedSubString
            }
        }
        return longestCommonSubString
    }

}