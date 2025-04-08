package travelator.handlers

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import dev.forkhandles.result4k.Failure
import dev.forkhandles.result4k.Success
import dev.forkhandles.result4k.map
import dev.forkhandles.result4k.recover
import travelator.*
import travelator.http.Request
import travelator.http.Response
import java.net.HttpURLConnection

class CustomerRegistrationHandler(
    private val registration: IRegisterCustomers
) {
    private val objectMapper = ObjectMapper()

    fun handle(request: Request): Response =
        try {
            val data = objectMapper.readValue(
                request.body,
                RegistrationData::class.java
            )

            registration.registerToo(data)
                .map { value ->
                    Response(
                        HttpURLConnection.HTTP_CREATED,
                        objectMapper.writeValueAsString(value)
                    )
                }
                .recover { reason -> reason.toResponse() }
        } catch (x: JsonProcessingException) {
            Response(HttpURLConnection.HTTP_BAD_REQUEST)
        } catch (x: ExcludedException) {
            Response(HttpURLConnection.HTTP_FORBIDDEN)
        } catch (x: DuplicateException) {
            Response(HttpURLConnection.HTTP_CONFLICT)
        } catch (x: Exception) {
            Response(HttpURLConnection.HTTP_INTERNAL_ERROR)
        }
}

private fun RegistrationProblem.toResponse() = when (this) {
    is Duplicate -> Response(HttpURLConnection.HTTP_CONFLICT)
    is Excluded -> Response(HttpURLConnection.HTTP_FORBIDDEN)
}
