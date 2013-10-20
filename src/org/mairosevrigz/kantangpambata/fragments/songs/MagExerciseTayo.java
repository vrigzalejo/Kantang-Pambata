package org.mairosevrigz.kantangpambata.fragments.songs;

import org.holoeverywhere.LayoutInflater;
import org.holoeverywhere.app.Fragment;
import org.holoeverywhere.demo.R;
import org.holoeverywhere.widget.Button;
import org.holoeverywhere.widget.SeekBar;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;

public class MagExerciseTayo extends Fragment implements OnClickListener,
		OnTouchListener {

	View v;
	MediaPlayer mp;
	Button bPlayPause, bStop;
	SeekBar seekBar;

	private final Handler handler = new Handler();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.magexercisetayo, container, false);

		initControls();

		return v;
	}

	@Override
	public void onResume() {
		super.onResume();
		getSupportActionBar().setSubtitle("Mag-Exercise Tayo");
	}

	private void initControls() {
		try {

			bPlayPause = (Button) v.findViewById(R.id.bPlayMagExerciseTayo);
			bPlayPause.setOnClickListener(this);

			bStop = (Button) v.findViewById(R.id.bStopMagExerciseTayo);
			bStop.setOnClickListener(this);

			mp = MediaPlayer.create(getActivity(), R.raw.yoyoy_villame_mag_exercise_tayo);

			seekBar = (SeekBar) v.findViewById(R.id.sbMagExerciseTayo);
			seekBar.setMax(mp.getDuration());
			seekBar.setOnTouchListener(this);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onLowMemory() {

		super.onLowMemory();
		seekBar.setProgress(0);
		try {
			mp.stop();
			mp.reset();
			mp = null;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onPause() {

		super.onPause();

		try {
			seekBar.setProgress(0);
			mp.stop();
			mp.reset();
			mp = null;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onDestroy() {

		super.onDestroy();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bPlayMagExerciseTayo:
			buttonClick();
			break;
		case R.id.bStopMagExerciseTayo:
			try {

				mp.stop();
				mp.reset();
				mp = null;
				bPlayPause.setText(getString(R.string.play));
				seekBar.setProgress(0);

			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
	}

	private void buttonClick() {
		try {
			if (mp == null) {
				mp = MediaPlayer.create(getActivity(), R.raw.yoyoy_villame_mag_exercise_tayo);
				if (!mp.isPlaying()) {
					if (bPlayPause.getText() == getString(R.string.play)) {
						bPlayPause.setText(getString(R.string.pause));
						try {
							mp.start();
							playProgress();
						} catch (IllegalStateException e) {
							mp.pause();
						}
					} else {
						bPlayPause.setText(getString(R.string.play));
						mp.pause();
					}
				}
			} else {
				if (bPlayPause.getText() == getString(R.string.play)) {
					bPlayPause.setText(getString(R.string.pause));
					try {
						mp.start();
						playProgress();
					} catch (IllegalStateException e) {
						mp.pause();
					}
				} else {
					bPlayPause.setText(getString(R.string.play));
					mp.pause();

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void playProgress() {
		try {

			seekBar.setProgress(mp.getCurrentPosition());

			if (mp.isPlaying()) {
				Runnable seekBarRun = new Runnable() {

					@Override
					public void run() {
						playProgress();

					}
				};

				handler.postDelayed(seekBarRun, 1000);
			} else {
				mp.pause();
				bPlayPause.setText(getString(R.string.play));
				seekBar.setProgress(mp.getCurrentPosition());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		try {
			seekChange(arg0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private void seekChange(View arg0) {
		try {
			if (mp.isPlaying()) {
				seekBar = (SeekBar) arg0;
				mp.seekTo(seekBar.getProgress());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
