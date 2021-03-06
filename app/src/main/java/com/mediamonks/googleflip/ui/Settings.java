package com.mediamonks.googleflip.ui;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.mediamonks.googleflip.R;
import com.mediamonks.googleflip.data.constants.Fragments;
import com.mediamonks.googleflip.data.constants.IntentKeys;
import com.mediamonks.googleflip.pages.about.AboutActivity;
import com.mediamonks.googleflip.pages.calibration.CalibrationActivity;
import com.mediamonks.googleflip.pages.game_flow.singleplayer.SinglePlayerGameFlowActivity;
import com.mediamonks.googleflip.pages.licenses.LicensesActivity;
import com.mediamonks.googleflip.util.SoundManager;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import temple.core.ui.CustomButton;

/**
 * Settings menu
 */
public class Settings extends FrameLayout {

	@InjectView(R.id.button)
	protected ImageButton _button;
	@InjectView(R.id.outside)
	protected FrameLayout _outside;
	@InjectView(R.id.menu)
	protected LinearLayout _menu;
	@InjectView(R.id.calibrate_button)
	protected CustomButton _calibrateButton;
	@InjectView(R.id.mute_button)
	protected CustomButton _muteButton;
	@InjectView(R.id.about_button)
	protected CustomButton _aboutButton;
	@InjectView(R.id.licenses_button)
	protected CustomButton _licensesButton;
	@InjectView(R.id.fragment_container)
	protected FrameLayout _fragmentContainer;

	public Settings(Context context) {
		super(context);
	}

	public Settings(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public Settings(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		ButterKnife.inject(this);

		_muteButton.setText(getResources().getString(SoundManager.isMuted() ? R.string.sound_on : R.string.sound_off));
	}

	@OnClick(R.id.button)
	protected void onButtonClick() {
		SoundManager.getInstance().play(R.raw.tap);

		showMenu();
	}

	@OnClick(R.id.calibrate_button)
	protected void onCalibrateButtonClick() {
		Intent intent = new Intent(new Intent(getContext(), CalibrationActivity.class));
		intent.putExtra(IntentKeys.FROM, getContext().getClass().getSimpleName());
		getContext().startActivity(intent);

		hideMenu();
	}

	@OnClick(R.id.mute_button)
	protected void onMuteButtonClick() {
		SoundManager.toggleMute();

		_muteButton.setText(getResources().getString(SoundManager.isMuted() ? R.string.sound_on : R.string.sound_off));
	}

	@OnClick(R.id.about_button)
	protected void onAboutButtonClick() {
		Intent intent = new Intent(getContext(), AboutActivity.class);
		getContext().startActivity(intent);

		hideMenu();
	}

	@OnClick(R.id.licenses_button)
	protected void onLicensesButtonClick() {
		Intent intent = new Intent(getContext(), LicensesActivity.class);
		getContext().startActivity(intent);

		hideMenu();
	}

	@OnClick(R.id.outside)
	protected void onOutsideClick() {
		hideMenu();
	}

	private Boolean menuIsVisible() {
		return _menu.getVisibility() == View.VISIBLE;
	}

	private void hideMenu() {
		_menu.setVisibility(View.GONE);
		_outside.setVisibility(View.GONE);
	}

	private void showMenu() {
		_menu.setVisibility(View.VISIBLE);
		_outside.setVisibility(View.VISIBLE);
	}
}
