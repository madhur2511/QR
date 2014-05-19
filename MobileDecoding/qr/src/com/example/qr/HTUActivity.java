package com.example.qr;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class HTUActivity extends Activity {

	ArrayList<Integer> operand1 = null;
	ArrayList<Integer> operand2 = null;
	private String problem = null;
	int level = 0;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_htu);
		operand1 = new ArrayList<Integer>();
		operand2 = new ArrayList<Integer>();
		
		Bundle extras = getIntent().getExtras();
		if(extras != null)
		{
			problem = extras.getString("QRContent");
			TextView tv = (TextView)findViewById(R.id.problemView);
			tv.setText(problem);
			
			elementarize(problem);
			recognizeLevel(problem);
		}
		
	}
	
	
	public void elementarize(String problem) 		// The problem is of the form xxx + yyy
	{
		String [] operands = problem.split("\\+");
		int number;
	
		number = Integer.parseInt(operands[0]);
		while (number > 0) {
		    operand1.add(number % 10);
		    number = number / 10;
		}
		
		number = Integer.parseInt(operands[1]);
		while (number > 0) {
		    operand2.add(number % 10);
		    number = number / 10;
		}
	}
	
	
	public int recognizeLevel(String problem){
		
		return 0;
	}
	
	public void similar(View v)
	{
		
	}
	
	public void lower(View v)
	{
		
	}
	
	public void higher(View v)
	{
		
	}
	
}