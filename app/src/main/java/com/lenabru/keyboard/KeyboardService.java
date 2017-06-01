package com.lenabru.keyboard;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener;
import android.media.AudioManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * Created by Lena Brusilovski on 29-May 2017.
 */

public class KeyboardService
		extends InputMethodService
		implements OnKeyboardActionListener {

	private static final String TAG = KeyboardService.class.getSimpleName();

	private static final int DISPLAY_HISTORY = 1000;

	private static final int CALCULATE = 1001;

	private static final int PLUS = 0;

	private static final int MINUS = 1;

	private static final int MULT = 2;

	private static final int DIV = 3;

	private KeyboardView kv;

	private Keyboard keyboard;

	private ListView historyListView;

	private boolean candidatesViewShown;

	private HistoryAdapter adapter;

	private AudioManager am;

	private HistoryController historyController;

	@Override
	public void onCreate() {
		super.onCreate();
		adapter = new HistoryAdapter(this);
		am = (AudioManager) getSystemService(AUDIO_SERVICE);
		historyController = new HistoryController();
	}

	@Override
	public View onCreateInputView() {
		kv = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard, null);
		keyboard = new Keyboard(this, R.xml.keys);
		kv.setKeyboard(keyboard);
		kv.setOnKeyboardActionListener(this);
		kv.setPreviewEnabled(false);
		return kv;
	}

	@Override
	public View onCreateCandidatesView() {
		setupHistoryList();
		return historyListView;
	}

	private void setupHistoryList() {
		historyListView = (ListView) getLayoutInflater().inflate(R.layout.history_list, null);
		historyListView.setAdapter(adapter);
		historyListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				InputConnection ic = getCurrentInputConnection();
				HistoryLine historyLine = adapter.getItem(position);
				ic.deleteSurroundingText(10000, 10000);
				ic.commitText(historyController.getResultString(historyLine), 1);
				historyController.applyHistory(historyLine);
			}
		});
		displayHistory(false);
	}

	private void displayHistory(boolean display) {
		candidatesViewShown = display;
		AnimatorListenerAdapter listener = new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				super.onAnimationEnd(animation);
				setCandidatesViewShown(candidatesViewShown);
			}
		};
		historyListView.animate().translationY(display ? 0 : historyListView.getHeight()).setListener(listener);
	}

	@Override
	public void onPress(int primaryCode) {
		am.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD);
	}

	@Override
	public void onRelease(int primaryCode) {

	}

	@Override
	public void onKey(int primaryCode, int[] keyCodes) {
		InputConnection ic = getCurrentInputConnection();
		switch (primaryCode) {
			case DISPLAY_HISTORY:
				displayHistory(!candidatesViewShown);
				break;
			case CALCULATE:
				if (candidatesViewShown) {
					return;
				}
				HistoryLine historyLine = historyController.calculate();
				if (historyLine != null) {
					adapter.add(historyLine);
					adapter.notifyDataSetChanged();
					ic.deleteSurroundingText(10000, 10000);
					ic.commitText(historyController.getResultString(historyLine), 1);
				}
				break;
			case PLUS:
			case MINUS:
			case DIV:
			case MULT:
				if (candidatesViewShown) {
					return;
				}
				Operation op = Operation.get(primaryCode);
				historyController.setOperation(primaryCode);
				ic.commitText(" " + op.type + " ", 1);
				break;
			default:
				if (candidatesViewShown) {
					return;
				}
				char code = (char) primaryCode;
				historyController.appendKeyCode(String.valueOf(code));
				ic.commitText(String.valueOf(code), 1);
		}
	}

	@Override
	public void onText(CharSequence text) {
		Log.d(TAG, "text:" + text);
	}

	@Override
	public void swipeLeft() {

	}

	@Override
	public void swipeRight() {

	}

	@Override
	public void swipeDown() {

	}

	@Override
	public void swipeUp() {

	}
}
