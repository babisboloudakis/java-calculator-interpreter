// Charalampos Mpoloudakis
// 1115201500103
// Compilers - Homework 1 - Part 1
import java.io.IOException;
import java.io.InputStream;

public class CalculatorInterpreter {

  // Stream from which we are going to receive our data.
  private InputStream inputStream;
  // Contains the next character on our input stream.
  private int lookahead;

  public CalculatorInterpreter(InputStream stream) throws IOException {
    this.inputStream = stream;
    cleanWhitespace();
    this.lookahead = inputStream.read();
  }

  public static void main(String[] args) {
    try {
      CalculatorInterpreter interpreter = new CalculatorInterpreter(System.in);
      interpreter.parse();
      System.out.println("Success!");
    } 
    catch (ParseError e) {
      System.out.println(e.getMessage());
    } 
    catch (IOException e) {
      System.out.println(e.getMessage());
    }   
  }

  public void parse() throws IOException, ParseError {
    // Our starting rule is expr().
    expr();
  }

  // Used to skip whitespace from input stream in order to allow
  // more flexible input expressions.
  private void cleanWhitespace() throws IOException {
    while (lookahead == ' ') {
      lookahead = inputStream.read();
    }
  }

  // Try to consume the next input symbol. On success throw the current lookahead
  // and read the next one from the InputStream.
  private void consume(int symbol) throws IOException, ParseError {
    if ( this.lookahead != symbol ) {
      throw new ParseError("Expected " + symbol + " but got " + this.lookahead);
    }
    this.lookahead = this.inputStream.read();
    cleanWhitespace();
  }

  // expr -> term expr2
  //       |  empty
  private void expr() throws ParseError, IOException {
    // Parse error
    if (lookahead == '^'
     || lookahead == -1
     || lookahead == '\n'
     || lookahead == '&'
     || lookahead == ')') {
       throw new ParseError( "expr() got " + lookahead);
    }
    term();
    expr2();
  }

  // expr2 -> ^ term expr2
  //       |  empty
  private void expr2() throws ParseError, IOException {
    // empty
    if (lookahead == -1
     || lookahead == '\n'
     || lookahead == ')') {
      return;
    }
    // Parse error
    if (lookahead == '&'
     || (lookahead >= '0' && lookahead <= '9')) {
      throw new ParseError("expr2() got " + lookahead);
    }
    consume('^');
    term();
    expr2();
  }

  // term -> factor term2
  private void term() throws ParseError, IOException {
    // factor term2
    if ((lookahead >= '0' && lookahead <= '9')
     || lookahead == '(') {
      factor();
      term2();
      return;
    }
    throw new ParseError("term() got " + lookahead);
  }

  // term2 -> & factor term2
  //        | empty
  private void term2() throws ParseError, IOException {
    if (lookahead == '&') {
      consume('&');
      factor();
      term2();
      return;
    }
    if (lookahead == '^'
     || lookahead == -1
     || lookahead == '\n'
     || lookahead == ')') {
      return;
    }
    throw new ParseError("term2() got " + lookahead);
  }

  // factor -> num
  //         | ( expr )
  private void factor() throws ParseError, IOException {
    // ( expr )
    if (lookahead == '(' ) {
      consume('(');
      expr();
      consume(')');
      return;
    }
    // num
    if (lookahead >= '0' && lookahead <= '9') {
      num();
      return;
    }
    throw new ParseError("factor() got " + lookahead);
  }

  // num -> 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9
  private void num() throws ParseError, IOException {
    if (lookahead >= '0' && lookahead <= '9') {
      consume(lookahead);
    } else {
      throw new ParseError("num() got " + lookahead);
    }
  }

}
