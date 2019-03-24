// Charalampos Mpoloudakis
// 1115201500103
// Compilers - Homework 1 - Part 1

// Exception thrown when we encounter unexpected symbol at any grammar rule.
public class ParseError extends Exception {

    private String info;

    public ParseError(String info) {
        this.info = info;
    }

    public ParseError() {
        this.info = "No additional info.";
    }

    public String getMessage() {
        return "Parse Error: " + this.info;
    }
}