package es.santyarbo.mymovies.domain.model

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

sealed interface Error {
    class Server(val code: Int) : Error
    object Connectivity : Error
    class Unknown(val message: String) : Error
}

fun Throwable.toError(): Error = when(this) {
    is IOException -> Error.Connectivity
    is HttpException -> Error.Server(code())
    else -> Error.Unknown(message ?: "")
}

inline fun <T> tryCall(action: () -> T): Either<Error, T> = try {
    action().right()
} catch (e: Exception) {
    e.toError().left()
}