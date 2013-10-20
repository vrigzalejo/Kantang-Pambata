package org.mairosevrigz.kantangpambata;



import android.content.DialogInterface;

import android.os.Bundle;

import org.holoeverywhere.addon.AddonSlider;
import org.holoeverywhere.addon.AddonSlider.AddonSliderA;
import org.holoeverywhere.app.Activity;
import org.holoeverywhere.app.Activity.Addons;
import org.holoeverywhere.app.AlertDialog;
import org.holoeverywhere.demo.R;

import org.holoeverywhere.preference.SharedPreferences;
import org.holoeverywhere.slider.SliderMenu;
import org.mairosevrigz.kantangpambata.fragments.songs.Abakada;
import org.mairosevrigz.kantangpambata.fragments.songs.AkoAyMayLobo;
import org.mairosevrigz.kantangpambata.fragments.songs.BahayKubo;
import org.mairosevrigz.kantangpambata.fragments.songs.LeronLeronSinta;
import org.mairosevrigz.kantangpambata.fragments.songs.MagExerciseTayo;
import org.mairosevrigz.kantangpambata.fragments.songs.SaUgoyNgDuyan;
import org.mairosevrigz.kantangpambata.fragments.songs.Saranggola;
import org.mairosevrigz.kantangpambata.fragments.songs.TongTongTong;
import org.mairosevrigz.kantangpambata.widget.DemoThemePicker;

@Addons(Activity.ADDON_SLIDER)
public class DemoActivity extends Activity {
	
	public static final String PREFS_NAME = "MyPrefsFile";

	public AddonSliderA addonSlider() {
		return addon(AddonSlider.class);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final SliderMenu sliderMenu = addonSlider().obtainDefaultSliderMenu(
				R.layout.menu);

		 SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		   boolean dialogShown = settings.getBoolean("dialogShown", false);

		   if (!dialogShown) {
		     // AlertDialog code here
			   new AlertDialog.Builder(this).setTitle("Hi there!")
				.setMessage("Thank you for installing this app!")
				.setNeutralButton("OK", null).show();
			   

		     SharedPreferences.Editor editor = settings.edit();
		     editor.putBoolean("dialogShown", true);
		     editor.commit();    
		   }
		
		sliderMenu.add(R.string.abakada, Abakada.class, SliderMenu.BLUE);
		sliderMenu.add(R.string.ako_ay_may_lobo, AkoAyMayLobo.class,
				SliderMenu.GREEN);
		sliderMenu.add(R.string.bahay_kubo, BahayKubo.class, SliderMenu.ORANGE);
		sliderMenu.add(R.string.leron_leron_sinta, LeronLeronSinta.class,
				SliderMenu.PURPLE);
		sliderMenu.add(R.string.mag_exercise_tayo, MagExerciseTayo.class,
				SliderMenu.BLUE);
		sliderMenu.add(R.string.saranggola_ni_pepe, Saranggola.class,
				SliderMenu.GREEN);
		sliderMenu.add(R.string.sa_ugoy_ng_bayan, SaUgoyNgDuyan.class,
				SliderMenu.ORANGE);
		sliderMenu.add(R.string.tong_tong_tong, TongTongTong.class,
				SliderMenu.PURPLE);

		getSupportActionBar().setTitle(R.string.library_name);

		// We are should provide activity to ThemePicker
		((DemoThemePicker) findViewById(R.id.themePicker)).setActivity(this);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public void onBackPressed() {
		new AlertDialog.Builder(this)
				.setMessage("Do you want to Exit now? =(")
				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								DemoActivity.this.finish();

							}
						}).setNegativeButton("No", null).show();

	}

}
