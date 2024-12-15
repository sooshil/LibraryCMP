package com.sukajee.library.core.presentation

import com.sukajee.library.core.domain.DataError
import library.composeapp.generated.resources.Res
import library.composeapp.generated.resources.disk_full_error
import library.composeapp.generated.resources.no_internet_error
import library.composeapp.generated.resources.request_timeout_error
import library.composeapp.generated.resources.serialization_error
import library.composeapp.generated.resources.server_error
import library.composeapp.generated.resources.too_many_requests_error
import library.composeapp.generated.resources.unknown_error

fun DataError.toUiText(): UiText {
    val stringResource = when (this) {
        DataError.Local.DISK_FULL -> Res.string.disk_full_error
        DataError.Local.UNKNOWN -> Res.string.unknown_error
        DataError.Remote.REQUEST_TIMEOUT -> Res.string.request_timeout_error
        DataError.Remote.TOO_MANY_REQUESTS -> Res.string.too_many_requests_error
        DataError.Remote.SERVER_ERROR -> Res.string.server_error
        DataError.Remote.NO_INTERNET -> Res.string.no_internet_error
        DataError.Remote.SERIALIZATION -> Res.string.serialization_error
        DataError.Remote.UNKNOWN -> Res.string.unknown_error
    }
    return UiText.StringResourceId(stringResource)
}