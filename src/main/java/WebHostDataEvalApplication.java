import controller.MainController;

public class WebHostDataEvalApplication {
    public static void main(String[] args) {
      MainController mainController = new MainController();
        mainController.processData("input/input.txt","output/output.txt");
    }
}
