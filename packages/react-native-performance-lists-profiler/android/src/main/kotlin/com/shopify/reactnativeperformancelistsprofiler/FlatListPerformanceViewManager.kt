package com.shopify.reactnativeperformancelistsprofiler

import android.view.ViewGroup
import com.facebook.react.common.MapBuilder
import com.facebook.react.module.annotations.ReactModule
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.views.view.ReactViewGroup
import com.facebook.react.views.view.ReactViewManager

@ReactModule(name = FlatListPerformanceViewManager.REACT_CLASS)
class FlatListPerformanceViewManager: ReactViewManager() {
    companion object {
        const val REACT_CLASS = "FlatListPerformanceView"
    }

    override fun getExportedCustomDirectEventTypeConstants(): Map<String, Any> {
        return MapBuilder.builder<String, Any>()
                .put(
                        "onInteractive",
                        MapBuilder.of(
                                "registrationName", "onInteractive")
                )
                .put(
                        "onBlankAreaEvent",
                        MapBuilder.of(
                                "registrationName", "onBlankAreaEvent")
                )
                .build()
    }

    override fun getName(): String {
        return REACT_CLASS
    }

    override fun createViewInstance(context: ThemedReactContext): ReactViewGroup {
        return BlankAreaView(context).apply {
            getCells = {
                if (scrollView == null) {
                    emptyArray()
                } else {
                    ((scrollView as ViewGroup).getChildAt(0) as ViewGroup)
                            .getChildren()
                }
            }
        }
    }
}
