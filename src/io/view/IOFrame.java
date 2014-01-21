package io.view;

import javax.swing.JFrame;
import io.Controller.IOController;
/**
 * Frame for the IO Project
 * @author plar4927
 *version 1.0 12/13/2013
 */
public class IOFrame extends JFrame
{

	/**
	 * IO panel for the frame and the IO Controller for the frame
	 */
	private IOController baseController;
	private IOPanel basePanel;
	
	/**
	 * constructor for the IOFrame
	 * @param baseController
	 */
	public IOFrame(IOController baseController)
	{
		this.baseController = baseController;
		basePanel = new IOPanel(baseController);
		
		setupFrame();
	}
	
	/**
	 * sets up the frame size and loads the content panel
	 */
	private void setupFrame()
	{
		this.setContentPane(basePanel);
		this.setSize(450,450);
		this.setVisible(true);
	}
}
