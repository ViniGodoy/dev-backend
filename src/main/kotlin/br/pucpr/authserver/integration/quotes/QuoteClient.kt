package br.pucpr.authserver.integration.quotes

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate

@Component
class QuoteClient {
    fun randomQuote(): Quote? =
        try {
            val client = RestTemplate()
            client.getForObject(
                "https://quote-garden.onrender.com/api/v3/quotes/random",
                QuoteResponse::class.java,
            )?.data?.firstOrNull()
        } catch (error: RestClientException) {
            log.error("Problems accessing the quotes", error)
            throw error
        }


    companion object {
        private val log = LoggerFactory.getLogger(QuoteClient::class.java)
    }

}
