package xyz.mommde.synctis.untis.legacy.methods

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import xyz.mommde.synctis.untis.serializer.WebUntisLocalDateSerializer

@Serializable
data class WebUntisLegacySchoolyearResponse(
    val id: Int,
    val name: String,
    @Serializable(WebUntisLocalDateSerializer::class)
    val startDate: LocalDate,
    @Serializable(WebUntisLocalDateSerializer::class)
    val endDate: LocalDate,
)

internal object WebUntisLegacyGetCurrentSchoolyearMethod : WebUntisLegacyEmptyRequestMethod<WebUntisLegacySchoolyearResponse>("getCurrentSchoolyear")
