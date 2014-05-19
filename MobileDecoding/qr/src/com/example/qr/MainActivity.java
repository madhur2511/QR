package com.example.qr;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private final String TAG = "QRQRQRQRQR";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}


	public void scan(View v){
		
		Intent qrDroid = new Intent("la.droid.qr.scan");
        qrDroid.putExtra( "la.droid.qr.complete" , true);
        try { 
        	startActivityForResult(qrDroid, 0); 
        }catch (ActivityNotFoundException activity) {
        	Toast.makeText(getApplicationContext(), "Please download the QRDroid Services application to use this feature", Toast.LENGTH_LONG).show();
        }
	}
	
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	   super.onActivityResult(requestCode, resultCode, data);
	
	   if(data != null && data.getExtras()!=null )
	   {
		   String content = data.getExtras().getString("la.droid.qr.result");
		   
		   String equation = makeEquation(content);
		   
		   TextView contentField = (TextView)findViewById(R.id.equationSaysView);
		   contentField.setText("The Equation is: ");
		   
		   TextView equationField = (TextView)findViewById(R.id.scannedView);
		   equationField.setText(equation);
		 
	   }
    }
    
    
    private String makeEquation(String content)
    {
    	String equation = "";
    	String[] lhsAndRhs = content.split("="); 		//always even number of ";" on LHS and RHS
    	
    	String[] lhs = lhsAndRhs[0].split(";");
    	String[] rhs = lhsAndRhs[1].split(";");
    	    	
    	
    	for(int i=0;i<lhs.length;++i){	
    		String lhs_term = lhs[i];
    		
    		if( lhs_term.length() > 0 ){
    			
	    		if(lhs_term.equals("-"))
	    			equation = equation.concat(" " + lhs_term + " ");
	    		
	    		else if(lhs_term.equals("+") && i!=0)
	    			equation = equation.concat(" " + lhs_term + " ");
	    		
	    		else if(lhs_term.equals("+") && i==0)
	    			continue;
	    		
	    		else{
	    			String[] numAndDen = lhs_term.split("/");
	    			
	    			if(numAndDen[0].split("\\+|-").length > 1){
	    				equation = equation.concat("(" + numAndDen[0] + ")");
	    			}else{
	    				equation = equation.concat(numAndDen[0]);
	    			}
	    			
	    			if(numAndDen[1].equals("1"))
	    				continue;
	    			else if(numAndDen[1].split("\\+|-").length > 1){
	    				equation = equation.concat("/(" + numAndDen[1] + ")");
	    			}
	    			else
	    				equation = equation.concat("/" + numAndDen[1]);
	    		}
    		}
    	}
    	
    	equation = equation.concat(" = ");
    	
		for(int i=0;i<rhs.length;++i){	
    		String rhs_term = rhs[i];
    		
    		if( rhs_term.length() > 0 ){
    			
	    		if(rhs_term.equals("-"))
	    			equation = equation.concat(" " + rhs_term + " ");
	    		
	    		else if(rhs_term.equals("+") && i!=1)
	    			equation = equation.concat(" " + rhs_term + " ");
	    		
	    		else if(rhs_term.equals("+") && i==1)
	    			continue;
  
    			else{
	    			String[] numAndDen = rhs_term.split("/");
	    			
	    			if(numAndDen[0].split("\\+|-").length > 1){
	    				equation = equation.concat("(" + numAndDen[0] + ")");
	    			}else{
	    				equation = equation.concat(numAndDen[0]);
	    			}
	    			
	    			if(numAndDen[1].equals("1"))
	    				continue;
	    			else if(numAndDen[1].split("\\+|-").length > 1){
	    				equation = equation.concat("/(" + numAndDen[1] + ")");
	    			}
	    			else
	    				equation = equation.concat("/" + numAndDen[1]);
	    		}
    		}
    	}
    	
    	return equation;
    }
    
    
//    public void process(View v){
//    	
//    	TextView contentField = (TextView)findViewById(R.id.scannedView);
//		String content = (String) contentField.getText(); 
//    	boolean valid = false;
//		
//    	content = content.trim();
//    	String[] arr = content.split(":");
//    	
//    	if(arr[0] != ""  && arr.length == 2 && arr[1] != "" && !(arr[0].equals("http"))){
//	    	String typeOfProblem = arr[0];
//	    	String problem = arr[1];
//	    	
//	    	if(typeOfProblem.equals("HTU")){
//	    		String[] operands = problem.split("\\+");		// "+" is a special character in Java Reg-Ex
//	    		
//	    		try{
//	    			Integer.parseInt(operands[0]);
//	    			Integer.parseInt(operands[1]);
//	    		}
//	    		catch(Exception e){}
//	    		
//	    		if(operands.length == 2){
//	    			valid = true;
//	    			Intent htu = new Intent(this,HTUActivity.class);
//	        		htu.putExtra("QRContent", problem);
//	        		startActivity(htu);
//	    		}
//	    	}
//    	}
//    	
//    	if(valid == false){
//    		Toast.makeText(getApplicationContext(), "Not a valid problem", Toast.LENGTH_LONG).show();
//    	}
//    }
}