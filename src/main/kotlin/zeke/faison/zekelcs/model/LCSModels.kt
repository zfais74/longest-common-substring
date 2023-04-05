package zeke.faison.zekelcs.model

import jakarta.validation.Valid
import jakarta.validation.constraints.NotEmpty
import org.springframework.http.HttpStatus

data class LongestCommonSubStringRequest(
    @NotEmpty
    @Valid
    val setOfStrings: List<LCSContent>
)

data class LCSContent(
    @NotEmpty
    val value: String
)

data class LongCommonSubStringResponse(
    val lcs: List<LCSContent>
)

class ErrorMessage(
    val status: HttpStatus? = null,
    val message: String? = null
)