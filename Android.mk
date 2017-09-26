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
LOCAL_STATIC_JAVA_LIBRARIES += android-support-annotations
LOCAL_STATIC_JAVA_LIBRARIES += android-support-v4
LOCAL_STATIC_JAVA_LIBRARIES += ps-auth ps-id apis-mail apic-android activation additionnal mail

LOCAL_PREBUILT_STATIC_JAVA_LIBRARIES := ps-auth:libs/google-play-services-auth-11.0.4.jar
LOCAL_PREBUILT_STATIC_JAVA_LIBRARIES += ps-id:libs/google-play-services-identity-11.0.4.jar
LOCAL_PREBUILT_STATIC_JAVA_LIBRARIES += apis-mail:libs/google-api-services-gmail-v1-rev70-1.22.0.jar
LOCAL_PREBUILT_STATIC_JAVA_LIBRARIES += apic-android:libs/google-api-client-android-1.22.0.jar
LOCAL_PREBUILT_STATIC_JAVA_LIBRARIES += activation:app/libs/activation.jar
LOCAL_PREBUILT_STATIC_JAVA_LIBRARIES += additionnal:app/libs/additionnal.jar
LOCAL_PREBUILT_STATIC_JAVA_LIBRARIES += mail:app/libs/mail.jar

LOCAL_SRC_FILES := $(call all-java-files-under, app/src/main/java faboptions/src/main/java nononsense-filepicker/src/main/java )
LOCAL_RESOURCE_DIR := $(LOCAL_PATH)/app/src/main/res \
    $(LOCAL_PATH)/faboptions/src/main/res \
    $(LOCAL_PATH)/nononsense-filepicker/src/main/res \
    frameworks/support/v7/appcompat/res \
    frameworks/support/v7/cardview/res \
    frameworks/support/design/res

LOCAL_AAPT_FLAGS := --auto-add-overlay
LOCAL_AAPT_FLAGS += --extra-packages android.support.v7.appcompat:android.support.v7.cardview:android.support.design:com.joaquimley.faboptions:com.nononsenseapps.filepicker:com.google.android.gms.play-services-auth:com.google.android.gms.play-services-identity:com.google.api-client.google-api-client-android:com.google.apis.google-api-services-gmail

include $(BUILD_PACKAGE)

include $(CLEAR_VARS)

include $(BUILD_MULTI_PREBUILT)
