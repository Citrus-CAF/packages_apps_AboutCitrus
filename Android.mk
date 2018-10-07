LOCAL_PATH := $(call my-dir)

LOCAL_MODULE_TAGS := optional

include $(CLEAR_VARS)

LOCAL_PACKAGE_NAME := AboutCitrus
LOCAL_PROGUARD_ENABLED := disabled
LOCAL_MANIFEST_FILE := app/src/main/AndroidManifest.xml
LOCAL_SDK_VERSION := current

LOCAL_STATIC_ANDROID_LIBRARIES := android-support-v7-appcompat
LOCAL_STATIC_ANDROID_LIBRARIES += android-support-v7-cardview
LOCAL_STATIC_ANDROID_LIBRARIES += android-support-design
LOCAL_STATIC_ANDROID_LIBRARIES += android-support-annotations
LOCAL_STATIC_ANDROID_LIBRARIES +=android-support-v4

LOCAL_SRC_FILES := $(call all-java-files-under, app/src/main/java faboptions/src/main/java )
LOCAL_RESOURCE_DIR := $(LOCAL_PATH)/app/src/main/res \
                      $(LOCAL_PATH)/faboptions/src/main/res

LOCAL_USE_AAPT2 := true
LOCAL_AAPT_FLAGS := --auto-add-overlay
LOCAL_AAPT_FLAGS += --extra-packages com.joaquimley.faboptions

include $(BUILD_PACKAGE)

include $(CLEAR_VARS)

include $(BUILD_MULTI_PREBUILT)
