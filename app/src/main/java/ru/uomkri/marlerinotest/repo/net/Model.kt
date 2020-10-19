package ru.uomkri.marlerinotest.repo.net

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
class RssResponseWrapper @JvmOverloads constructor(
    @field: Element(name = "channel")
    var channel: RssResponse? = null
)

@Root(name = "channel", strict = false)
class RssResponse @JvmOverloads constructor(
    @field: ElementList(inline = true)
    var itemList: List<RssItem>? = null
)

@Root(name = "item", strict = false)
class RssItem @JvmOverloads constructor(
    @field: Element(name = "title")
    var title: String = "",
    @field: Element(name = "description")
    var description: String = "",
    @field: Element(name = "link")
    var link: String = "",
    @field: Element(name = "enclosure")
    var enclosureUrl: Enclosure? = null,
    @field: Element(name = "pubDate")
    var pubDate: String = ""
)

@Root(name = "enclosure", strict = false)
class Enclosure @JvmOverloads constructor(
    @field: Attribute(name = "url")
    var url: String = ""
)
