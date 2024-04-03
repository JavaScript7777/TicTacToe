import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;

   // when the AI is doing the learning mode DO NOT hit end hit enter for a safe exit.
   // regenerating brain takes ~30 mins on FCPS computer
   // main method line 106

public class TicTacToe {
   public static byte[] board = {0, 0, 0, 0, 0, 0, 0, 0, 0};
   public static char[] key = {' ', 'O', 'X'};
   public static byte piece = 1;
   public static volatile boolean go = true;
   public static int getLineCount(File f) {
      try {
         Scanner s = new Scanner(f);
         int ans = 0;
         while (s.hasNext()) {
            s.nextLine();
            ans++;
         }
         s.close();
         return ans;
      } catch (Exception e) {
         throw new RuntimeException("Cannot access file (00). " + e.getMessage());
      }
   }
   public static String getLine(File f, int line) {
      try {
         Scanner l = new Scanner(f);
         for (int i = 1; i<line; i++) {
            l.nextLine();
         }
         String ans = l.nextLine();
         l.close();
         return ans;
      } catch (Exception e) {
         throw new RuntimeException("Cannot access file (01)." + e.getMessage());
      }
   }
   public static void turn(int x, int y) {
      switch (x) {
         case 1, 2, 3 -> x--;
         default -> throw new RuntimeException("Out of bounds x (03).");
      }
      switch (y) {
         case 1, 2, 3 -> y--;
         default -> throw new RuntimeException("Out of bounds y (04).");
      }
      switch (board[((y)*3)+(x)]) {
         case 0 -> board[((y)*3)+(x)] = piece;
         default -> throw new RuntimeException("Spot taken (02).");
      }
   }
   public static void playerMove() {
      while (true) {
         try {
            turn(getNum("X: "), getNum("Y: "));
            return;
         } catch (Exception e) {
            System.out.println(e.getMessage());
         }
      }
   }
   public static int getNum(String text) {
      System.out.print(text);
      try {
         return new Scanner(System.in).nextInt();
      } catch (Exception e) {
         System.out.println("Enter an int.");
         return getNum(text);
      }
   }
   public static void AIMove() {
      File g = new File("Files/Brain/" + board[0] + "" + board[1] + "" + board[2] + "" + board[3] + "" + board[4] + "" + board[5] + "" + board[6] + "" + board[7] + "" + board[8]);
      char[] s = getLine(g, 1+((int)(Math.random()*getLineCount(g)))).toCharArray();
      if (getLineCount(g) > 1) {
         try {
            FileWriter o = new FileWriter("Files/" + piece);
            o.write(g.toString()+"\n");
            o.write(new String(s)+"\n");
            o.close();
         } catch (Exception e) {
            throw new RuntimeException("Error loading file (05)");
         }
      }
      for (int i = 0; i<9; i++) {
         board[i] = Byte.parseByte(s[i]+"");
      }
   }
   public static void print() {
      System.out.println("1  ->   " + key[board[0]] + " | " + key[board[1]] + " | " + key[board[2]] + "\n       ---|---|---");
      System.out.println("2  ->   " + key[board[3]] + " | " + key[board[4]] + " | " + key[board[5]] + "\n       ---|---|---");
      System.out.println("3  ->   " + key[board[6]] + " | " + key[board[7]] + " | " + key[board[8]] + "");
      System.out.println("        1   2   3\n");
   }
   public static boolean isWon() {
      return (board[0]==board[1]&&board[1]==board[2]&&board[2]!=0)||(board[3]==board[4]&&board[4]==board[5]&&board[5]!=0)||(board[6]==board[7]&&board[7]==board[8]&&board[8]!=0)||(board[0]==board[3]&&board[3]==board[6]&&board[6]!=0)||(board[1]==board[4]&&board[4]==board[7]&&board[7]!=0)||(board[2]==board[5]&&board[5]==board[8]&&board[8]!=0)||(board[0]==board[4]&&board[4]==board[8]&&board[8]!=0)||(board[2]==board[4]&&board[4]==board[6]&&board[6]!=0);
   }
   public static byte whoWon() {
      return (board[0]==board[1]&&board[1]==board[2]&&board[2]!=0)? board[0]:(board[3]==board[4]&&board[4]==board[5]&&board[5]!=0)? board[3]:(board[6]==board[7]&&board[7]==board[8]&&board[8]!=0)? board[6]:(board[0]==board[3]&&board[3]==board[6]&&board[6]!=0)? board[0]:(board[1]==board[4]&&board[4]==board[7]&&board[7]!=0)? board[1]:(board[2]==board[5]&&board[5]==board[8]&&board[8]!=0)? board[2]:(board[0]==board[4]&&board[4]==board[8]&&board[8]!=0)? board[0]:(board[2]==board[4]&&board[4]==board[6]&&board[6]!=0)? board[2]:0;
   }
   public static boolean isTie() {
      return !isWon()&&(board[0] != 0 && board[1] != 0 && board[2] != 0 && board[3] != 0 && board[4] != 0 && board[5] != 0 && board[6] != 0 && board[7] != 0 && board[8] != 0);
   }
   public static void main(String[] args) {
   
   //                // this is to regenerate the brain folder, prints percent done
   //             try {
   //                char[] a = {'0', '0', '0', '0', '0', '0', '0', '0', '0'};
   //                int i = 0;
   //                ki: while (true) {
   //                   i++;
   //                   System.out.println(((int)((i*100)/196.83))/100.0);
   //                   if (validate(a)) {
   //                      boolean g = false;
   //                      FileWriter q = new FileWriter("Files/perm");
   //                      int[] p = {0, 0, 0};
   //                      for (int j = 0; j<9; j++) {
   //                         p[Byte.parseByte(a[j]+"")]++;
   //                      }
   //                      char k = (p[1]==p[2])? '1':'2';
   //                      for (int j = 0; j<9; j++) {
   //                         switch (a[j]) {
   //                            case '0':
   //                               a[j] = k;
   //                               q.write(new String(a) + "\n");
   //                               g = !validate(a)||g;
   //                               a[j] = '0';
   //                         }
   //                      }
   //                      q.close();
   //                      
   //                      FileWriter l = new FileWriter("Files/Brain/" + new String(a));
   //                      Scanner d = new Scanner(new File("Files/perm"));
   //                      while (d.hasNext()) {
   //                         String j = d.nextLine();
   //                         l.write((!g||!validate(j.toCharArray()))? j+"\n":"");
   //                      }
   //                      l.close();
   //                      d.close();
   //                   }
   //                   switch (a[8]) {
   //                      default:
   //                         a[8]++;
   //                         break;
   //                      case '2':
   //                         a[8] = '0';
   //                         switch (a[7]) {
   //                            default:
   //                               a[7]++;
   //                               break;
   //                            case '2':
   //                               a[7] = '0';
   //                               switch (a[6]) {
   //                                  default:
   //                                     a[6]++;
   //                                     break;
   //                                  case '2':
   //                                     a[6] = '0';
   //                                     switch (a[5]) {
   //                                        default:
   //                                           a[5]++;
   //                                           break;
   //                                        case '2':
   //                                           a[5] = '0';
   //                                           switch (a[4]) {
   //                                              default:
   //                                                 a[4]++;
   //                                                 break;
   //                                              case '2':
   //                                                 a[4] = '0';
   //                                                 switch (a[3]) {
   //                                                    default:
   //                                                       a[3]++;
   //                                                       break;
   //                                                    case '2':
   //                                                       a[3] = '0';
   //                                                       switch (a[2]) {
   //                                                          default:
   //                                                             a[2]++;
   //                                                             break;
   //                                                          case '2':
   //                                                             a[2] = '0';
   //                                                             switch (a[1]) {
   //                                                                default:
   //                                                                   a[1]++;
   //                                                                   break;
   //                                                                case '2':
   //                                                                   a[1] = '0';
   //                                                                   switch (a[0]) {
   //                                                                      default:
   //                                                                         a[0]++;
   //                                                                         break;
   //                                                                      case '2':
   //                                                                         break ki;
   //                                                                   }
   //                                                             }
   //                                                       }
   //                                                 }
   //                                           }
   //                                     }
   //                               }
   //                         }
   //                   }
   //                }
   //             } catch (Exception e) {
   //                System.out.println("ff " + e.getMessage());
   //             }
   
            
            // this is to have the ai play itself prints 0 won if tie
         Runnable r = 
            () -> {
               new Scanner(System.in).nextLine();
               go = false;
            };
         new Thread(r).start(); // for a safe exit from program just hit enter into the console wont break files
            
         while (go) {
            while (!isWon() && !isTie()) {
               //print();
               AIMove();
               piece = switch (piece) {
                  case 2 -> 1;
                  default -> 2;
                  };
            }
            //print();
            System.out.println(whoWon() + " won.");
            if (!isTie()) {
               learn();
            }
            reset();
         }
         System.exit(0);
   
//             // This is to play the AI no reading for winner user goes first with O's
//          while (!isWon() && !isTie()) {
//             print();
//             switch (piece) {
//                case 2 -> AIMove();
//                case 1 -> playerMove();
//             }
//             piece = switch (piece) {
//                case 2 -> 1;
//                default -> 2;
//                };
//          }
//          print();
   }
   public static void learn() {
      try {
         Scanner s = new Scanner(new File("Files/" + piece));
         File f = new File(s.nextLine());
         String st = s.nextLine();
         s.close();
         Scanner sc = new Scanner(f);
         FileWriter fi = new FileWriter("Files/perm");
         while (sc.hasNext()) {
            String str = sc.nextLine();
            if (!str.equals(st)) {
               fi.write(str + "\n");
            }
         }
         fi.close();
         sc.close();
         Scanner sca = new Scanner(new File("Files/perm"));
         FileWriter fil = new FileWriter(f);
         while (sca.hasNext()) {
            fil.write(sca.nextLine() + "\n");
         }
         fil.close();
         sca.close();
         System.out.println("learned");
      } catch (Exception e) {
         throw new RuntimeException("learning error (06)");
      }
   }
   public static boolean validate(char[] s) {
      int[] a = {0, 0, 0};
      for (int i = 0; i<9; i++) {
         byte k = Byte.parseByte(s[i]+"");
         a[k]++;
         board[i] = k;
      }
      return !isWon()&&(a[0]!=0)&&((a[1]==a[2])||(a[2]+1==a[1]));
   }
   public static void reset() {
      piece = 1;
      board = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
   }
}