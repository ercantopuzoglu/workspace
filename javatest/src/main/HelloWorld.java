package main;

//import java.io.ObjectInputStream.GetField;

/*
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.peer.TextComponentPeer;


import java.util.Timer;
import java.util.TimerTask;
import java.lang.Thread;
import java.net.Socket;
import java.net.SocketAddress;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.DataInputStream;



class BaslangicFrame extends JFrame{
	
	  private JLabel label     = new JLabel("Naber Týkla");    
	  private JButton button   = new JButton("týkla");
	  
	  private Socket skt;
	  private DataOutputStream out;
	  private DataInputStream in;
	
	  
	 //SocketAddress adress= new SocketAddress();
	 // BufferedReader in = new BufferedReader(new InputStreamReader(skt.getInputStream()));
	  
	  class MouseOnclikEvent extends MouseAdapter
	  {int counter;
	  	MouseOnclikEvent()
	  	{
		  counter=5;
	  		System.out.println("new MouseOnclikEvent");
	  	}
		  public void mouseClicked(MouseEvent evt) 
			{
			 // if (evt.getSource())
				JOptionPane.showMessageDialog(null,"Týkladinýz...");				
				button.setText("Tiklandi:"+counter++);
				System.gc();
			}
		  public void mousePressed(MouseEvent evt) {
			  button.setText("mousePressed:"+counter++);
		    }

		  
	  }

	  class BizimThread implements Runnable
	  {
		  public void run()
		  {
			  while(true)
			  {
				
				  try
				  {					  
					  if ((skt==null)||(!skt.isConnected()))
					  {
						  Thread.sleep(1000);
					  }else if((in.available()>0))
					  {
						  System.out.println(in.readLine());					  
					  }else
					  {
						  Thread.sleep(10);
					  }
						  
				  }catch (Exception e)
				  {					
					  System.out.println("Thread :"+e.getMessage());  
				  }

				  
			  }
		  }
	  }
	  

	  class onTimerloop extends  TimerTask
	  {int Counter;
		  onTimerloop()
		  {
			  Counter=0;  
		  }
		  public void run()
		  {int i;
			  try
			  {
			  System.out.println("Timer :"+Counter++);
			  //i=1/0;
				  if ((skt==null)||(!skt.isConnected()))
				  {				  
					 
					  System.out.println("socket is close");
					  ConnectTry();
				  }
				  else
				  {
					  
					  out.writeUTF("Hello from\r\n");//+ skt.getLocalSocketAddress());
					  
					 // 
				  }
					  
				  			         

			  }
			  catch(Exception e)
			  {
				//  ConnectTry();
				  skt=null;
				  System.out.println("Error Timer:"+e.getMessage());
			  }
		  }
	  }
	  
	  public void ConnectTry()
	  {
		  try
		  {
			  System.out.println("new Socketing...");
			  in= null;
			  out=null;
			  skt=null;
			  
			  System.gc();			  
			  skt 	  = new Socket("127.0.0.1", 6565);
			  OutputStream outToServer  = skt.getOutputStream();
			  InputStream inFromServer  = skt.getInputStream();			  
			  out     = new DataOutputStream(outToServer);			  
	          in      = new DataInputStream(inFromServer);
	          
	          //System.gc();
	          
	          System.out.println("Socketed:"+skt.getLocalSocketAddress());
		  }catch (Exception e)
		  {
			  System.out.println("new Socket err:"+e.getMessage());
		  }
  
	  }
	  
	  BaslangicFrame()
	  {
		  setTitle("Merhabalar...");
		  MouseOnclikEvent mevent=new MouseOnclikEvent();		  
		  onTimerloop  timerloop =new onTimerloop();
		  		  
		  ConnectTry();
		  Timer tmtick =new Timer();		  
		  Thread mthread =new Thread(new BizimThread(),"naber");
		  mthread.start();
		  
		  Dimension d = new Dimension(400,400);
		  getContentPane().setPreferredSize(d);
		  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
		  setLocation(500, 200);
 
		  getContentPane().setLayout(null);
		
		  tmtick.schedule(timerloop,1000,1000);
		  
		  button.addMouseListener(mevent);

		  button.setBounds(100, 50, 200, 25);
		  label .setBounds(100, 0, 100, 25);




		  getContentPane().add(label);    
		  getContentPane().add(button);

		// JOptionPane.showMessageDialog(null,"Merhaba");

		 pack();
		setResizable(false);
		setVisible(true);
	}
}		


public class HelloWorld 
{	
	public static void main(String[] args) 
    {	  
	  new BaslangicFrame();  
    }
  
}