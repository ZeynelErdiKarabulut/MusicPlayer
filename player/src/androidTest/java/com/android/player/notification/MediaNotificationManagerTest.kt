package com.android.player.notification

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ServiceTestRule
import com.android.player.service.PlayerService
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MediaNotificationManagerTest {

    @get:Rule
    val mServiceRule: ServiceTestRule = ServiceTestRule()
    lateinit var notificationManager: MediaNotificationManager

    @Before
    fun setUp() {
        val intent = Intent(ApplicationProvider.getApplicationContext<Context>(), PlayerService::class.java)
        val binder = mServiceRule.bindService(intent)
        val service: PlayerService = (binder as PlayerService.LocalBinder).service
        notificationManager = MediaNotificationManager(service)
    }

    @Test
    fun testStartNotification() {
        notificationManager.createMediaNotification()
        assertTrue(notificationManager.mStarted)
    }

    @Test
    fun testStopNotification() {
        notificationManager.stopForegroundPlayerService()
        assertTrue(!notificationManager.mStarted)
    }
}