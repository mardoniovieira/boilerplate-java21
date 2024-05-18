package br.com.boilerplate.java21.presentation.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApiErrorMessage {

    UNKNOWN_ERROR("api.exception.message.unknown-error"),
    CUSTOM_NOT_FOUND("api.exception.message.custom-not-found"),
    CUSTOM_BAD_REQUEST("api.exception.message.custom-bad-request");

    private final String property;
}
