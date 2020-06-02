package com.wangyi.musicplayer;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.audiofx.Visualizer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private VisualizerView mVisualizerView;
    private MediaPlayer mMediaPlayer;
    private Visualizer mVisualizer;
    private SeekBar mSeekBar;
    private TextView mCurrentTime;
    private TextView mTotalTime;
    private Handler mHandler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            mSeekBar.setProgress(mMediaPlayer.getCurrentPosition());
            mCurrentTime.setText(String.valueOf(mMediaPlayer.getCurrentPosition() / 1000));
            mTotalTime.setText(String.valueOf(mMediaPlayer.getDuration() / 1000));
            mHandler.postDelayed(runnable, 1000);
        }
    };

    private List<Float> data1 = new ArrayList<>();
    private List<Float> data2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSeekBar = findViewById(R.id.seek_bar);
        mCurrentTime = findViewById(R.id.current_time);
        mTotalTime = findViewById(R.id.tv_duration);
        mVisualizerView = findViewById(R.id.mVisualizerView);

        try {
            initMusic();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initMusic() throws IOException {
        mMediaPlayer = new MediaPlayer();
        initVisualizer();
        AssetFileDescriptor fileDescriptor = getAssets().openFd("music_1.mp3");
        mMediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(),
                fileDescriptor.getStartOffset(), fileDescriptor.getLength());
        mMediaPlayer.setScreenOnWhilePlaying(true);
        mMediaPlayer.prepare();
        mSeekBar.setMax(mMediaPlayer.getDuration());
        mMediaPlayer.start();
        mHandler.post(runnable);
    }

    /**
     * 初始化可视化
     */
    private void initVisualizer() {
        mVisualizer = new Visualizer(mMediaPlayer.getAudioSessionId());
        //设置采样率
        mVisualizer.setCaptureSize(Visualizer.getCaptureSizeRange()[1]);
        mVisualizer.setDataCaptureListener(new Visualizer.OnDataCaptureListener() {
            @Override
            public void onWaveFormDataCapture(Visualizer visualizer, byte[] waveform,
                                              int samplingRate) {

            }

            @Override
            public void onFftDataCapture(Visualizer visualizer, byte[] fft,
                                         int samplingRate) {
                byte[] model = new byte[fft.length / 2 + 1];
                model[0] = (byte) Math.abs(fft[1]);
                int j = 1;
                int size = Math.min(fft.length, 200);
                for (int i = 2; i < size; ) {
                    model[j] = (byte) Math.hypot(fft[i], fft[i + 1]);
                    i += 2;
                    j++;
                }

                //设置音频数据到控件
                mVisualizerView.setVisualizer(model);
            }
        }, Visualizer.getMaxCaptureRate() / 2, false, true);
        mVisualizer.setEnabled(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMediaPlayer.release();
        mVisualizer.setEnabled(false);
        if (mVisualizer != null) {
            mVisualizer.release();
        }
    }
}
