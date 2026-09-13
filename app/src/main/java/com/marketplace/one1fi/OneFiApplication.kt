package com.marketplace.one1fi

import android.app.Application
import coil3.ImageLoader
import coil3.SingletonImageLoader
import coil3.network.okhttp.OkHttpNetworkFetcherFactory
import dagger.hilt.android.HiltAndroidApp
import okhttp3.OkHttpClient

/**
 * If the existing 1Fi app already has an `@HiltAndroidApp` Application class,
 * do NOT add a second one — Android allows only one, declared in the manifest's
 * `<application android:name=...>`. This file is only needed if the host
 * project isn't on Hilt yet.
 */
@HiltAndroidApp
class OneFiApplication : Application(), SingletonImageLoader.Factory {

    // Wikimedia (and some other hosts) reject requests without a descriptive
    // User-Agent — Coil's default one gets a 403. Only needed while product
    // images point at wikimedia.org; drop this once real product images are used.
    override fun newImageLoader(context: android.content.Context): ImageLoader =
        ImageLoader.Builder(context)
            .components {
                add(
                    OkHttpNetworkFetcherFactory(
                        callFactory = {
                            OkHttpClient.Builder()
                                .addInterceptor { chain ->
                                    chain.proceed(
                                        chain.request().newBuilder()
                                            .header(
                                                "User-Agent",
                                                "OneFiMarketplaceApp/1.0 (contact@onefi.in)"
                                            )
                                            .build()
                                    )
                                }
                                .build()
                        }
                    )
                )
            }
            .build()
}
