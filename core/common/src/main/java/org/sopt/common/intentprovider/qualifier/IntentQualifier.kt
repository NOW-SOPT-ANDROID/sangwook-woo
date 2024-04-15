package org.sopt.common.intentprovider.qualifier

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LOGIN

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MAIN

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SIGNUP