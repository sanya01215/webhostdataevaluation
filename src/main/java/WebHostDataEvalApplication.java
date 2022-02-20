import controller.MainController;

public class WebHostDataEvalApplication {
    public static void main(String[] args) {
        /*
         There are default txt i/o files,
         and input file contains test data,
         you can run it, or change on yours.
         */
        MainController mainController = new MainController();
        mainController.processData("input/input.txt", "output/output.txt");
    }
}
