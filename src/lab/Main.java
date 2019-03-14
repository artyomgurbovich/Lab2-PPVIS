package lab;

import lab.controller.Controller;
import lab.model.Model;
import lab.view.View;

public class Main {
	public static void main(String[] args) {
		Model model = new Model();
		Controller controller = new Controller(model);
		View view = new View(controller);
		view.begin();
	}
}