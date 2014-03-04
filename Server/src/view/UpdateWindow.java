/*
 * Updates window with log.txt 
 */
package view;

public class UpdateWindow extends Thread
{
	public Window window;

public UpdateWindow(Window window)
{
	this.window = window;
}
@Override
public void run()
{
	while(true)
	{
		try
		{
			sleep(5000);
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		
		window.getText();
	}
}
}
