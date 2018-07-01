package parcel

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecordedSimulation extends Simulation {

	val httpProtocol = http
		.baseURL("http://192.168.99.102")
		.inferHtmlResources()
		.acceptHeader("*/*")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-GB,en;q=0.5")
		.doNotTrackHeader("1")
		.userAgentHeader("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:60.0) Gecko/20100101 Firefox/60.0")

	val headers_0 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_7 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
		"Access-Control-Request-Headers" -> "content-type",
		"Access-Control-Request-Method" -> "POST",
		"Origin" -> "http://192.168.99.102")

	val headers_8 = Map(
		"Accept" -> "application/json, text/plain, */*",
		"Content-Type" -> "application/json;charset=utf-8",
		"Origin" -> "http://192.168.99.102")

    val uri2 = "http://193.174.205.28:8443/ui"
    val uri3 = "http://192.168.99.100:8443/parcel/size"

	val scn = scenario("RecordedSimulation")
		.exec(http("request_0")
			.get("/")
			.headers(headers_0)
			.resources(http("request_1")
			.get("/js/app.js"),
            http("request_2")
			.get(uri2 + "/parcel-option.component.js"),
            http("request_3")
			.get(uri2 + "/parcel-price.component.js"),
            http("request_4")
			.get(uri2 + "/parcel-address.component.js"),
            http("request_5")
			.get(uri2 + "/parcel-size.component.js"),
            http("request_6")
			.get("/images/icon_pakete.png")
			.check(status.is(404))))
		.pause(7)
		.exec(http("request_7")
			.options(uri3 + "")
			.headers(headers_7)
			.resources(http("request_8")
			.post(uri3 + "")
			.headers(headers_8)
			.body(RawFileBody("RecordedSimulation_0008_request.txt"))))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}
