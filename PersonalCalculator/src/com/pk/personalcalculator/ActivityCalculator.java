package com.pk.personalcalculator;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ActivityCalculator extends Activity
{
	private SharedPreferences prefs;
	private int selectedTheme;
	
	// Fonts used to Google Now theme.
	Typeface robotoThin;
	Typeface robotoBoldCondensed;
	
	// Buttons...
	ImageButton btnExpand;
	Button btnClear;
	Button btnDelete;
	Button btnEqual;
	Button btn0;
	Button btn1;
	Button btn2;
	Button btn3;
	Button btn4;
	Button btn5;
	Button btn6;
	Button btn7;
	Button btn8;
	Button btn9;
	Button btnLeftP;
	Button btnRightP;
	Button btnDivide;
	Button btnMultiply;
	Button btnMinus;
	Button btnPlus;
	Button btnDot;
	Button btnSwitch;
	
	// The text view form
	TextView textInput;
	
	//String builder to build the equation
	StringBuilder textString = new StringBuilder();
	
	//Resource strings
	String plusSign;
	String minusSign;
	String multiplySign;
	String divideSign;
	String leftParSign;
	String rightParSign;
	
	MenuItem mItemDebug;
	
	// Called when activity starts
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calculator);
		prefs = getSharedPreferences("PersonalCalculatorPreferences", 0);
		selectedTheme = prefs.getInt("Theme", 0);
		initializeUI();
		initializeSigns();
		//lockdown();
	}
	
	// Create ActionBar menu options
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.action_menu, menu);
		
		this.mItemDebug = menu.findItem(R.id.action_debug);
		
		// If on debug mode, let us debug!
		if (ActivityMain.DebugMode)
			mItemDebug.setVisible(true);
		else
			mItemDebug.setVisible(false);
		
		return true;
	}
	
	// Select what to do once ActionBar option is touched.
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.action_manage:
				Intent manageIntent = new Intent(ActivityCalculator.this, ActivityManage.class);
				startActivity(manageIntent);
				return true;
			case R.id.action_settings:
				Intent settingsIntent = new Intent(ActivityCalculator.this, ActivitySettings.class);
				startActivity(settingsIntent);
				return true;
			case R.id.action_debug:
				startActivity(new Intent(ActivityCalculator.this, ActivityDebug.class));
			default:
				
				return super.onOptionsItemSelected(item);
		}
	}
	
	// Initialize all UI objects and set theme if needed
	public void initializeUI()
	{
		robotoThin = Typeface.createFromAsset(getAssets(), "Roboto-Thin.ttf");
		robotoBoldCondensed = Typeface.createFromAsset(getAssets(), "Roboto-BoldCondensed.ttf");
		
		btnExpand = (ImageButton) findViewById(R.id.btnExpand);
		btnClear = (Button) findViewById(R.id.btnClear);
		btnDelete = (Button) findViewById(R.id.btnDelete);
		btnEqual = (Button) findViewById(R.id.btnEqual);
		btn0 = (Button) findViewById(R.id.btn0);
		btn1 = (Button) findViewById(R.id.btn1);
		btn2 = (Button) findViewById(R.id.btn2);
		btn3 = (Button) findViewById(R.id.btn3);
		btn4 = (Button) findViewById(R.id.btn4);
		btn5 = (Button) findViewById(R.id.btn5);
		btn6 = (Button) findViewById(R.id.btn6);
		btn7 = (Button) findViewById(R.id.btn7);
		btn8 = (Button) findViewById(R.id.btn8);
		btn9 = (Button) findViewById(R.id.btn9);
		btnLeftP = (Button) findViewById(R.id.btnLeftP);
		btnRightP = (Button) findViewById(R.id.btnRightP);
		btnDivide = (Button) findViewById(R.id.btnDivide);
		btnMultiply = (Button) findViewById(R.id.btnMultiply);
		btnMinus = (Button) findViewById(R.id.btnMinus);
		btnPlus = (Button) findViewById(R.id.btnPlus);
		btnDot = (Button) findViewById(R.id.btnDot);
		btnSwitch = (Button) findViewById(R.id.btnSwitch);
		textInput = (TextView) findViewById(R.id.Input);
		
		textInput.setText("");
		
		setCalculatorTheme(selectedTheme);
	}

	
	// Lock down the system
	public void lockdown()
	{
		View disableStatusBar = new View(ActivityCalculator.this);
		
		WindowManager.LayoutParams handleParams = new WindowManager.LayoutParams(LayoutParams.MATCH_PARENT, 50,
		// This allows the view to be displayed over the status bar
		WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
		// this is to keep button presses going to the background window
		WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
		// this is to enable the notification to receive touch events
		WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
		// Draws over status bar
		WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN, PixelFormat.TRANSLUCENT);
		
		handleParams.gravity = Gravity.TOP;
		getWindow().addContentView(disableStatusBar, handleParams);
	}
	
	// Set calculator theme
	public void setCalculatorTheme(int theme)
	{
		if (theme == 1)
		{
			// Google Now theme
			textInput.setTextSize(R.dimen.theme2_input);
			btnExpand.setBackgroundResource(R.drawable.border_selector);
			btnDelete.setBackgroundResource(R.drawable.item_selector);
			btnEqual.setBackgroundResource(R.drawable.item_selector);
			btnSwitch.setBackgroundResource(R.drawable.item_selector);
			btnClear.setBackgroundResource(R.drawable.item_selector);
			btnLeftP.setBackgroundResource(R.drawable.item_selector);
			btnRightP.setBackgroundResource(R.drawable.item_selector);
			btnDivide.setBackgroundResource(R.drawable.item_selector);
			btnMultiply.setBackgroundResource(R.drawable.item_selector);
			btnMinus.setBackgroundResource(R.drawable.item_selector);
			btnPlus.setBackgroundResource(R.drawable.item_selector);
			btnDot.setBackgroundResource(R.drawable.item_selector);
			btn0.setBackgroundResource(R.drawable.item_selector);
			btn1.setBackgroundResource(R.drawable.item_selector);
			btn2.setBackgroundResource(R.drawable.item_selector);
			btn3.setBackgroundResource(R.drawable.item_selector);
			btn4.setBackgroundResource(R.drawable.item_selector);
			btn5.setBackgroundResource(R.drawable.item_selector);
			btn6.setBackgroundResource(R.drawable.item_selector);
			btn7.setBackgroundResource(R.drawable.item_selector);
			btn8.setBackgroundResource(R.drawable.item_selector);
			btn9.setBackgroundResource(R.drawable.item_selector);
			
			btnClear.setTypeface(robotoBoldCondensed);
			btn0.setTypeface(robotoThin);
			btn1.setTypeface(robotoThin);
			btn2.setTypeface(robotoThin);
			btn3.setTypeface(robotoThin);
			btn4.setTypeface(robotoThin);
			btn5.setTypeface(robotoThin);
			btn6.setTypeface(robotoThin);
			btn7.setTypeface(robotoThin);
			btn8.setTypeface(robotoThin);
			btn9.setTypeface(robotoThin);
			textInput.setTypeface(robotoThin);
		}
		
	}

//Obtains the string resources for use with buttonClick
public void initializeSigns()
{
	plusSign = getResources().getString(R.string.plus);
	minusSign = getResources().getString(R.string.minus);
	multiplySign = getResources().getString(R.string.multiply);
	divideSign = getResources().getString(R.string.divide);
	leftParSign = getResources().getString(R.string.leftPar);
	rightParSign = getResources().getString(R.string.rightPar);
}
	
	// This method will determine what will happen when a button is pressed.
	public void buttonClick(View v)
	{
		switch (v.getId())
		{
			case R.id.btn0:
				textString.append(0);
				textInput.setText(textString.toString());
				break;
			case R.id.btn1:
				textString.append(1);
				textInput.setText(textString.toString());
				break;
			case R.id.btn2:
				textString.append(2);
				textInput.setText(textString.toString());
				break;
			case R.id.btn3:
				textString.append(3);
				textInput.setText(textString.toString());
				break;
			case R.id.btn4:
				textString.append(4);
				textInput.setText(textString.toString());
				break;
			case R.id.btn5:
				textString.append(5);
				textInput.setText(textString.toString());
				break;
			case R.id.btn6:
				textString.append(6);
				textInput.setText(textString.toString());
				break;
			case R.id.btn7:
				textString.append(7);
				textInput.setText(textString.toString());
				break;
			case R.id.btn8:
				textString.append(8);
				textInput.setText(textString.toString());
				break;
			case R.id.btn9:
				textString.append(9);
				textInput.setText(textString.toString());
				break;
			case R.id.btnPlus:
			{
				if (!(textInput.getText().toString().isEmpty())){
					Character lastChar = Character.valueOf(textString.charAt(textInput.length() - 1));
					if (lastChar.toString().equals(leftParSign) ||lastChar.toString().equals(plusSign) 
					|| lastChar.toString().equals(minusSign) || lastChar.toString().equals(multiplySign) 
					|| lastChar.toString().equals(divideSign))
					{
						textString.deleteCharAt(textString.length() - 1);
					}					
					textString.append(plusSign);
					textInput.setText(textString.toString());
					
				}
				break;
			}
			case R.id.btnMinus:
			{
				if (!(textInput.getText().toString().isEmpty())){
					Character lastChar = Character.valueOf(textString.charAt(textInput.length() - 1));
					if (lastChar.toString().equals(leftParSign) || lastChar.toString().equals(plusSign) 
					|| lastChar.toString().equals(minusSign)|| lastChar.toString().equals(multiplySign) 
					|| lastChar.toString().equals(divideSign))
					{
						textString.deleteCharAt(textString.length() - 1);
					}
					textString.append(minusSign);
					textInput.setText(textString.toString());
					
				}
				break;
			}
			case R.id.btnMultiply:
			{
				if(!(textInput.getText().toString().isEmpty())){
					Character lastChar = Character.valueOf(textString.charAt(textInput.length() - 1));
					if (lastChar.toString().equals(leftParSign) || lastChar.toString().equals(plusSign) 
					|| lastChar.toString().equals(minusSign) || lastChar.toString().equals(multiplySign)
					|| lastChar.toString().equals(divideSign))
					{
						textString.deleteCharAt(textString.length() - 1);
					}
					textString.append(multiplySign);
					textInput.setText(textString.toString());
				}
				break;
			}
			case R.id.btnDivide:
			{
				if (!(textInput.getText().toString().isEmpty())){
					Character lastChar = Character.valueOf(textString.charAt(textInput.length() - 1));
					if (lastChar.toString().equals(leftParSign) || lastChar.toString().equals(plusSign) 
					|| lastChar.toString().equals(minusSign) || lastChar.toString().equals(multiplySign)
					|| lastChar.toString().equals(divideSign))
					{
						textString.deleteCharAt(textString.length() - 1);
					}
					textString.append(divideSign);
					textInput.setText(textString.toString());
				}
				break;
			}
			case R.id.btnLeftP:
				textString.append(getResources().getString(R.string.leftPar));
				textInput.setText(textString.toString());
				break;
			case R.id.btnRightP:
			{
				if ((textInput.getText().toString().contains("(")))
				{
					Character lastChar = Character.valueOf(textString.charAt(textInput.length() - 1));
					if (lastChar.toString().equals(plusSign) || lastChar.toString().equals(minusSign) 
					|| lastChar.toString().equals(multiplySign) || lastChar.toString().equals(divideSign))
					{
						textString.deleteCharAt(textString.length() - 1);
					}
					textString.append(rightParSign);
					textInput.setText(textString.toString());
				}
				break;
			}
			case R.id.btnClear:
				textString.setLength(0);
				textInput.setText("");
				break;
			case R.id.btnDelete:
				if (!(textInput.getText().toString().isEmpty()))
				{
					textString.deleteCharAt(textString.length() - 1);
					textInput.setText(textString.toString());
				}
				break;
			case R.id.btnEqual:
				solve(); // Stub method; incomplete but skeletally functional.
				break;
			case R.id.btnSwitch: // Code for the additive inverse of a number goes
									// here.;
				break;
			case R.id.btnDot:
				if (!textInput.getText().toString().contains("."))
				{
					textString.append(".");
					textInput.setText(textString.toString());
				}
				break;
		}
	}
	
	// The method will take the text view "Input", analyze it, and construct an
	// equation that the machine will solve;
	// The resulting solution will be set in the text view afterwards.
	public void solve()
	{
		// Code for solve goes here
	}
}