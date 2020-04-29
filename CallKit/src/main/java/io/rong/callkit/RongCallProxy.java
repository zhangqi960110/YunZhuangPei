package io.rong.callkit;

import android.util.Log;
import android.view.SurfaceView;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import io.rong.calllib.IRongCallListener;
import io.rong.calllib.RongCallCommon;
import io.rong.calllib.RongCallSession;
import io.rong.common.RLog;

/**
 * Created by jiangecho on 2016/10/27.
 */

public class RongCallProxy implements IRongCallListener {

    private static final String TAG = "RongCallProxy";
    private IRongCallListener mCallListener;
    private Queue<CallDisconnectedInfo> mCachedCallQueue;
    private static RongCallProxy mInstance;

    private RongCallProxy() {
        mCachedCallQueue = new LinkedBlockingQueue<>();
    }

    private IRongCallListener mAppCallListener;

    public void setAppCallListener(IRongCallListener listener) {
        Log.d(TAG, "setAppCallListener: " + listener);
        this.mAppCallListener = listener;
    }


    public static synchronized RongCallProxy getInstance() {
        if (mInstance == null) {
            mInstance = new RongCallProxy();
        }
        return mInstance;
    }

    public void setCallListener(IRongCallListener listener) {
        RLog.d(TAG, "setCallListener listener = " + listener);
        this.mCallListener = listener;
        if (listener != null) {
            CallDisconnectedInfo callDisconnectedInfo = mCachedCallQueue.poll();
            if (callDisconnectedInfo != null) {
                listener.onCallDisconnected(callDisconnectedInfo.mCallSession, callDisconnectedInfo.mReason);
            }
        }
    }

    @Override
    public void onCallOutgoing(RongCallSession callSession, SurfaceView localVideo) {
        if (mCallListener != null) {
            mCallListener.onCallOutgoing(callSession, localVideo);
        }
        if(mAppCallListener != null) {
            mAppCallListener.onCallOutgoing(callSession, localVideo);
        }
    }

    @Override
    public void onCallConnected(RongCallSession callSession, SurfaceView localVideo) {
        if (mCallListener != null) {
            mCallListener.onCallConnected(callSession, localVideo);
        }
        if (mAppCallListener != null){
            mAppCallListener.onCallConnected(callSession,localVideo);
        }
    }

    @Override
    public void onCallDisconnected(RongCallSession callSession, RongCallCommon.CallDisconnectedReason reason) {
        RLog.d(TAG, "RongCallProxy onCallDisconnected mCallListener = " + mCallListener);
        if (mCallListener != null) {
            mCallListener.onCallDisconnected(callSession, reason);
        } else {
            mCachedCallQueue.offer(new CallDisconnectedInfo(callSession, reason));
        }

        if (mAppCallListener != null) {
            mAppCallListener.onCallDisconnected(callSession, reason);
        } else {
            mCachedCallQueue.offer(new CallDisconnectedInfo(callSession, reason));
        }
    }

    @Override
    public void onRemoteUserRinging(String userId) {
        if (mCallListener != null) {
            mCallListener.onRemoteUserRinging(userId);
        }
        if (mAppCallListener != null){
            mAppCallListener.onRemoteUserRinging(userId);
        }
    }

    @Override
    public void onRemoteUserJoined(String userId, RongCallCommon.CallMediaType mediaType, SurfaceView remoteVideo) {
        if (mCallListener != null) {
            mCallListener.onRemoteUserJoined(userId, mediaType, remoteVideo);
        }
        if (mAppCallListener != null){
            mAppCallListener.onRemoteUserJoined(userId,mediaType,remoteVideo);
        }
    }

    @Override
    public void onRemoteUserInvited(String userId, RongCallCommon.CallMediaType mediaType) {
        if (mCallListener != null) {
            mCallListener.onRemoteUserInvited(userId, mediaType);
        }
        if (mAppCallListener != null){
            mAppCallListener.onRemoteUserInvited(userId,mediaType);
        }
    }

    @Override
    public void onRemoteUserLeft(String userId, RongCallCommon.CallDisconnectedReason reason) {
        if (mCallListener != null) {
            mCallListener.onRemoteUserLeft(userId, reason);
        }
        if (mAppCallListener != null){
            mAppCallListener.onRemoteUserLeft(userId,reason);
        }
    }

    @Override
    public void onMediaTypeChanged(String userId, RongCallCommon.CallMediaType mediaType, SurfaceView video) {
        if (mCallListener != null) {
            mCallListener.onMediaTypeChanged(userId, mediaType, video);
        }
        if (mAppCallListener != null){
            mAppCallListener.onMediaTypeChanged(userId,mediaType,video);
        }
    }

    @Override
    public void onError(RongCallCommon.CallErrorCode errorCode) {
        if (mCallListener != null) {
            mCallListener.onError(errorCode);
        }
        if (mAppCallListener != null){
            mAppCallListener.onError(errorCode);
        }
    }

    @Override
    public void onRemoteCameraDisabled(String userId, boolean disabled) {
        if (mCallListener != null) {
            mCallListener.onRemoteCameraDisabled(userId, disabled);
        }
        if (mAppCallListener != null){
            mAppCallListener.onRemoteCameraDisabled(userId,disabled);
        }
    }

    private static class CallDisconnectedInfo {
        RongCallSession mCallSession;
        RongCallCommon.CallDisconnectedReason mReason;

        public CallDisconnectedInfo(RongCallSession callSession, RongCallCommon.CallDisconnectedReason reason) {
            this.mCallSession = callSession;
            this.mReason = reason;
        }
    }
}
