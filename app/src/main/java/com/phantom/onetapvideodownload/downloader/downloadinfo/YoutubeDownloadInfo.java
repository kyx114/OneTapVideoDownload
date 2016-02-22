package com.phantom.onetapvideodownload.downloader.downloadinfo;

import android.content.Context;
import android.util.Log;

import com.phantom.onetapvideodownload.databasehandlers.DownloadDatabase;

public class YoutubeDownloadInfo implements DownloadInfo {
    private final static String TAG = "YoutubeDownloadInfo";
    private String mParam, mVideoUrl, mDownloadLocation, mFilename;
    private int mItag;
    private long mDatabaseId = -1, mContentLength = -1, mDownloadedLength = -1;
    private Status mStatus;
    private Context mContext;

    public YoutubeDownloadInfo(Context context, String filename, String url, String downloadPath, String param, int itag) {
        mContext = context;
        mFilename = filename;
        mItag = itag;
        mParam = param;
        mDownloadLocation = downloadPath;
        mVideoUrl = url;
        mStatus = Status.Stopped;
    }

    @Override
    public String getFilename() {
        return mFilename;
    }

    @Override
    public String getUrl() {
        return mVideoUrl;
    }

    @Override
    public long getDatabaseId() {
        return mDatabaseId;
    }

    @Override
    public void setDatabaseId(long databaseId) {
        mDatabaseId = databaseId;
    }

    public String getParam() {
        return mParam;
    }

    public int getItag() {
        return mItag;
    }

    @Override
    public String getDownloadLocation() {
        return mDownloadLocation;
    }

    @Override
    public Status getStatus() {
        return mStatus;
    }

    @Override
    public void setStatus(Status status) {
        Log.e(TAG, "Download Status changed from " + mStatus.name() + " to " + status.name());
        mStatus = status;
    }

    @Override
    public long getContentLength() {
        return mContentLength;
    }

    @Override
    public void setContentLength(long contentLength) {
        mContentLength = contentLength;
    }

    @Override
    public long getDownloadedLength() {
        return mDownloadedLength;
    }

    @Override
    public void setDownloadedLength(long downloadedLength) {
        mDownloadedLength = downloadedLength;
    }

    @Override
    public void addDownloadedLength(long additionValue) {
        mDownloadedLength += additionValue;
    }

    @Override
    public Integer getProgress() {
        return (int)((mDownloadedLength*100)/mContentLength);
    }

    @Override
    public void writeToDatabase() {
        DownloadDatabase downloadDatabase = DownloadDatabase.getDatabase(mContext);
        downloadDatabase.addOrUpdateDownload(this);
    }
}
