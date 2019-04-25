package lab.view;

import lab.controller.Controller;
import lab.view.window.MainWindow;

public class View {
	
	private Controller controller;
	private MainWindow mainWindow;
	
	public View(Controller controller) {
		this.controller = controller;
	}
	
	public void begin() {
		mainWindow = new MainWindow(controller);
		controller.setMainWindow(mainWindow);
		mainWindow.CreateUI();
	}
}