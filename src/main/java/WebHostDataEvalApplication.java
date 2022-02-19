import service.parse.ParserFacade;

public class WebHostDataEvalApplication {
    public static void main(String[] args) {
        ParserFacade parserFacade = new ParserFacade();
        parserFacade.parseData("input/input.txt","output/output.txt");
    }
}
