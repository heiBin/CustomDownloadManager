package com.duoyi.providers.ui.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


import com.duoyi.downloadmanager.R;
import com.duoyi.downloadmanager.abstracthttp.HttpInstance;
import com.duoyi.downloadmanager.downloads.DownloadService;
import com.duoyi.downloadmanager.manager.DownloadManager;
import com.duoyi.providers.downloadhttp.DownloadHttpCreat;
import com.duoyi.providers.ui.DownloadList;

public class DownloadProviderActivity extends AppCompatActivity implements
	OnClickListener {
    @SuppressWarnings("unused")
    private static final String TAG = DownloadProviderActivity.class.getName();

    private BroadcastReceiver mReceiver;

    EditText mUrlInputEditText;
    Button mStartDownloadButton;
    DownloadManager mDownloadManager;
    Button mShowDownloadListButton;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.main);
	HttpInstance.getInstance().setHttpCreate(new DownloadHttpCreat());
	mDownloadManager = new DownloadManager(getContentResolver(),
		getPackageName());
	buildComponents();
//	startDownloadService();

	mReceiver = new BroadcastReceiver() {

	    @Override
	    public void onReceive(Context context, Intent intent) {
		showDownloadList();
	    }
	};

	registerReceiver(mReceiver, new IntentFilter(
		DownloadManager.ACTION_NOTIFICATION_CLICKED));
    }

    @Override
    protected void onDestroy() {
	unregisterReceiver(mReceiver);
	super.onDestroy();
    }

    private void buildComponents() {
	mUrlInputEditText = (EditText) findViewById(R.id.url_input_edittext);
	mStartDownloadButton = (Button) findViewById(R.id.start_download_button);
	mShowDownloadListButton = (Button) findViewById(R.id.show_download_list_button);

	mStartDownloadButton.setOnClickListener(this);
	mShowDownloadListButton.setOnClickListener(this);

	mUrlInputEditText.setText("http://common.duoyiservice.com/package/170215/69a6ef/com.jiajiu.youxin-wanxin-release.apk");
    }

    private void startDownloadService() {
	Intent intent = new Intent();
	intent.setClass(this, DownloadService.class);
	startService(intent);
    }

    @Override
    public void onClick(View v) {
	int id = v.getId();
	switch (id) {
	case R.id.start_download_button:
	    startDownload();
	    break;
	case R.id.show_download_list_button:
	    showDownloadList();
	    break;
	default:
	    break;
	}
    }

    private void showDownloadList() {
	Intent intent = new Intent();
	intent.setClass(this, DownloadList.class);
	startActivity(intent);
    }

    private void startDownload() {
	String url = mUrlInputEditText.getText().toString();
	Uri srcUri = Uri.parse(url);
	DownloadManager.Request request = new DownloadManager.Request(srcUri);
	request.setDestinationInExternalPublicDir(
		Environment.DIRECTORY_DOWNLOADS, "/");
	request.setDescription("问道");
	request.setShowRunningNotification(true);
	mDownloadManager.enqueue(request);
    }
}