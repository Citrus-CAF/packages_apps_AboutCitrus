LOCAL_PATH := $(call my-dir)

LOCAL_MODULE_TAGS := optional

include $(CLEAR_VARS)

LOCAL_PACKAGE_NAME := AboutCitrus
LOCAL_CERTIFICATE := platform
LOCAL_PROGUARD_ENABLED := disabled
LOCAL_MANIFEST_FILE := app/src/main/AndroidManifest.xml

LOCAL_STATIC_JAVA_LIBRARIES := android-support-v7-appcompat
LOCAL_STATIC_JAVA_LIBRARIES += android-support-v7-cardview
LOCAL_STATIC_JAVA_LIBRARIES += android-support-design

LOCAL_PREBUILT_STATIC_JAVA_LIBRARIES := rebound:app/libs/facebook-rebound.jar
LOCAL_PREBUILT_STATIC_JAVA_LIBRARIES += tastytoast:app/libs/tastytoast.jar
LOCAL_PREBUILT_STATIC_JAVA_LIBRARIES += faboptions:app/libs/faboptions.jar

LOCAL_SRC_FILES := $(call all-java-files-under, app/src/main)
LOCAL_RESOURCE_DIR := $(LOCAL_PATH)/app/src/main/res \
    frameworks/support/v7/appcompat/res \
    frameworks/support/v7/cardview/res \
    frameworks/support/design/res

LOCAL_AAPT_FLAGS := --auto-add-overlay
LOCAL_AAPT_FLAGS += --extra-packages android.support.v7.appcompat:android.support.v7.cardview:android.support.design

include $(BUILD_PACKAGE)

include $(CLEAR_VARS)

include $(BUILD_MULTI_PREBUILT)
