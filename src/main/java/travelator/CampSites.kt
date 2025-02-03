package travelator

fun Set<CampSite>.sitesInRegion(
    countryISO: String,
    region: String
): Set<CampSite> =
    filter { site: CampSite ->
        site.isIn(countryISO, region)
    }.toSet()

fun CampSite.isIn(countryISO: String, region: String? = null) =
    when (region) {
        null -> countryCode == countryISO
        else -> countryCode == countryISO &&
                region.equals(this.region, ignoreCase = true)
    }
