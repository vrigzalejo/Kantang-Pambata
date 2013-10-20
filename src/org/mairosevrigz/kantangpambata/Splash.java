package org.mairosevrigz.kantangpambata;

import org.holoeverywhere.app.Activity;
import org.holoeverywhere.demo.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class Splash extends Activity {

	@Override
	protected void onCreate(Bundle MailaValenciaRoseSupanVrigzAlejo) {
		// TODO Auto-generated method stub
		super.onCreate(MailaValenciaRoseSupanVrigzAlejo);

		// fullscreen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.splash);

		Thread timer = new Thread() {
			// start the thread
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					Intent openStartingPoint = new Intent(Splash.this,
							DemoActivity.class);
					startActivity(openStartingPoint);
				}
			}
		};

		timer.start();

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

}
