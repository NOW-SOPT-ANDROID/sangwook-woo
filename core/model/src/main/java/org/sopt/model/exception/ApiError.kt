package org.sopt.model.exception

data class ApiError(
    override val message: String,
): Exception()