package Variables;

public class Variables {

    // understanding the different variable types
    String A = "this is a string";
    boolean result = true;
    char capitalC = 'C';
    byte b = 100;
    short s = 10000;
    int i = 100000;


    // The number 26, in decimal
    int decimalValue = 26;


    //  The number 26, in hexadecimal
    int hexadecimalValue = 0x1a;

    // The number 26, in binary
    int binaryValue = 0b11010;
    double d1 = 123.4;


    // same value as d1, but in scientific notation
    double d2 = 1.234e2;
    float f1  = 123.4f;


    long creditCardNumber = 1234_5678_9012_3456L;
    long socialSecurityNumber = 999_99_9999L;
    float pi =  3.14_15F;
    long hexBytes = 0xFF_EC_DE_5E;
    long hexWords = 0xCAFE_BABE;
    long maxLong = 0x7fff_ffff_ffff_ffffL;
    byte nybbles = 0b0010_0101;
    long bytes = 0b11010010_01101001_10010100_10010010;


    // Invalid: cannot put underscores
// adjacent to a decimal point
 //   float pi1 = 3_.1415F;


    // Invalid: cannot put underscores
// adjacent to a decimal point
   // float pi2 = 3._1415F;


    // Invalid: cannot put under
    // scores
// prior to an L suffix
  //  long socialSecurityNumber1 = 999_99_9999_L;



    // OK (decimal literal)
    int x1 = 5_2;


    // Invalid: cannot put underscores
// At the end of a literal
  //  int x2 = 52_;


    // OK (decimal literal)
    int x3 = 5_______2;

    // Invalid: cannot put underscores
// in the 0x radix prefix
  //  int x4 = 0_x52;


    // Invalid: cannot put underscores
// at the beginning of a number
    //int x5 = 0x_52;
    // OK (hexadecimal literal)
    int x6 = 0x5_2;


    // Invalid: cannot put underscores
// at the end of a number
   // int x7 = 0x52_;

}
