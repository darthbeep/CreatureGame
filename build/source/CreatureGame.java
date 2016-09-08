import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class CreatureGame extends PApplet {

//Creature c1, c2;
//Creature[] clist = new Creature[6];
ArrayList<Creature> creatures = new ArrayList();
int selectedNum = -1;
int breedNum = -1;
Creature mBreed, fBreed;
boolean once = true;
boolean selling = false;
public void setup() {
  
  background(255);
  PFont fn = loadFont("Monospaced-30.vlw");
  textFont(fn);
  setupReader();
  //c1 = new Creature();
  //c2 = new Creature();
  //generatePunnetSquare(c1, c2);
  //String s[] = PFont.list();
  //for (int i = 0; i < s.length; i++) System.out.println(s[i]);
  /*clist[0] = new Creature();
  clist[1] = new Creature();
  clist[2] = new Creature(clist[0], clist[1]);
  clist[3] = new Creature(clist[0], clist[1]);
  clist[4] = new Creature(clist[2], clist[3]);
  clist[5] = new Creature(clist[2], clist[3]);*/
  //for (int i = 0; i < 5; i++) creatures.add(new Creature());
  //housebackground();
}

public void draw() {
  background(255);
  //generatePunnetSquare(c1, c2, mouseX, mouseY, "ha");
  //breedScreen(c1, c2);
  /*shape(clist[0].shape, 100, 100);
  shape(clist[1].shape, 300, 100);
  shape(clist[2].shape, 100, 300);
  shape(clist[3].shape, 300, 300);
  shape(clist[4].shape, 100, 500);
  shape(clist[5].shape, 300, 500);*/
  setupHouse();
  //newCreature();
  //storeSettings();
}
public void mouseClicked() {
  clickCreature();
}
public void keyPressed() {
  //save();
}
public class Creature {

  //Non stat features
  String name;
  boolean gender; //false for male, true for female
  String geneticDisease1, geneticDisease2; //If they match, the creature has a genetic disease
  PShape shape;

  //Regular stats
  double friendliness; //75%
  double happiness; //75%
  double expensiveness; //50%
  double speed; //50%
  double agility; //25%
  double attractiveness; //25%

  //Biological stats
  boolean friendliness1, friendliness2; //25%, dominant
  boolean happiness1, happiness2; //25%, recessive
  boolean expensiveness1, expensiveness2; //50%, recessive
  boolean speed1, speed2; //50%, dominant
  boolean agility1, agility2; //75%, recessive
  boolean attractiveness1, attractiveness2; //75%, dominent

  //Color deciders
  int red;
  int green;
  int blue;
  int col;

  //coordinates
  float x, y;

  //Selected and breeding
  boolean selected = false;
  boolean breeding = false;

  //Creature setup
  //Random Creature
  public Creature() {
    randomCreature();
  }
  //Child creature
  public Creature(Creature mother, Creature father) {
    randomStats();
    randomChildStats(mother, father);
  }

  //Read a creature from data.txt
public Creature(String line) {
  //System.out.println("The creation starts");
  String[]  stats = new String[25];
  int j = 0;
  for (int i = 0; i < 25; i++) stats[i] = "";
  for (int i = 0; i < line.length(); i++) {
    if (line.substring(i, i+1).equals(" ")) {
      //System.out.println(stats[j]);
      j++;
    }
    else stats[j] += line.substring(i, i+1);
  }
  //System.out.println("The creation gets somewhere.");
  name = stats[0];
  gender = Boolean.parseBoolean(stats[1]);
  geneticDisease1 = stats[2];
  geneticDisease2 = stats[3];
  //System.out.println("The creation marches on");
  friendliness = Double.parseDouble(stats[4]);
  happiness = Double.parseDouble(stats[5]);
  expensiveness = Double.parseDouble(stats[6]);
  speed = Double.parseDouble(stats[7]);
  agility = Double.parseDouble(stats[8]);
  attractiveness = Double.parseDouble(stats[9]);
  //System.out.println("The valient creation continues");
  friendliness1 = Boolean.parseBoolean(stats[10]);
  happiness1 = Boolean.parseBoolean(stats[11]);
  expensiveness1 = Boolean.parseBoolean(stats[12]);
  speed1 = Boolean.parseBoolean(stats[13]);
  agility1 = Boolean.parseBoolean(stats[14]);
  attractiveness1= Boolean.parseBoolean(stats[15]);
  friendliness2 = Boolean.parseBoolean(stats[16]);
  happiness2 = Boolean.parseBoolean(stats[17]);
  expensiveness2 = Boolean.parseBoolean(stats[18]);
  speed2 = Boolean.parseBoolean(stats[19]);
  agility2 = Boolean.parseBoolean(stats[20]);
  attractiveness2 = Boolean.parseBoolean(stats[21]);
  //System.out.println("The creation's goal is in sight");
  //System.out.println(stats[24]);
  red = (int) Double.parseDouble(stats[22]);
  green = (int) Double.parseDouble(stats[23]);
  blue = (int) Double.parseDouble(stats[24]);
  //System.out.println("The creation grows weary");
  x = random(450) + 50;
  y = random(450) + 50;
  col = color(red, green, blue);
  shape = displayCreature(this);
  //System.out.println("The creation's task is complete");
}

  //Random creature creation
  public void randomCreature() {
    randomStats();
    randomBioStats();
    randomDisease();
    randomColor();
    shape = displayCreature(this);
    gender = randomBoolean();
  }

  //Create child of two creatures
  public void randomChildStats(Creature m, Creature f) {
    gender = randomBoolean();
    //Bio stats
    friendliness1 = oneOfTwo(m.friendliness1, m.friendliness2);
    friendliness2 = oneOfTwo(f.friendliness1, f.friendliness2);
    happiness1 = oneOfTwo(m.happiness1, m.happiness2);
    happiness2 = oneOfTwo(f.happiness1, f.happiness2);
    expensiveness1 = oneOfTwo(m.expensiveness1, m.expensiveness2);
    expensiveness2 = oneOfTwo(f.expensiveness1, f.expensiveness2);
    speed1 = oneOfTwo(m.speed1, m.speed2);
    speed2 = oneOfTwo(f.speed1, f.speed2);
    agility1 = oneOfTwo(m.agility1, m.agility2);
    agility2 = oneOfTwo(f.agility1, f.agility2);
    attractiveness1 = oneOfTwo(m.attractiveness1, m.attractiveness2);
    attractiveness2 = oneOfTwo(f.attractiveness1, f.attractiveness2);

    //Genetic disease
    if (randomBoolean()) geneticDisease1 = m.geneticDisease1;
    else geneticDisease1 = m.geneticDisease2;
    if (randomBoolean()) geneticDisease2 = f.geneticDisease1;
    else geneticDisease2 = f.geneticDisease2;

    //Color
    red = mutatedNumber(m.red, f.red);
    green = mutatedNumber(m.green, f.green);
    blue = mutatedNumber(m.blue, f.blue);
    col = color(red, green, blue);

    shape = displayCreature(this);
    //System.out.println(geneticDisease1+", "+geneticDisease2);
  }

  //Randomize regular stats
  public void randomStats() {
    //Stats:
    //friendliness happiness expensiveness
    //speed agility attractiveness
    friendliness = random(50);
    happiness = random(50);
    expensiveness = random(50);
    speed = random(50);
    agility = random(50);
    attractiveness = random(50);

    //Coordinates
    x = random(450) + 50;
    y = random(450) + 50;

    //Name
    name = randomName();
  }

  //Randomize biological stats
  public void randomBioStats() {
    friendliness1 = randomBoolean();
    happiness1 = randomBoolean();
    expensiveness1 = randomBoolean();
    speed1 = randomBoolean();
    agility1 = randomBoolean();
    attractiveness1 = randomBoolean();
    friendliness2 = randomBoolean();
    happiness2 = randomBoolean();
    expensiveness2 = randomBoolean();
    speed2 = randomBoolean();
    agility2 = randomBoolean();
    attractiveness2 = randomBoolean();
  }

  //Random genetic disease genes
  public void randomDisease() {
    geneticDisease1 = randomLetter();
    geneticDisease2 = randomLetter();
    while (geneticDisease1.equals(geneticDisease2)) {
      geneticDisease2 = randomLetter();
    }
  }

  //Random color
  public void randomColor() {
    red = (int) random(255);
    green = (int) random(255);
    blue = (int) random(255);
    col = color(red, green, blue);
  }

  //Retrieve stats in usable form
  public double getFriendliness() {
    if (friendliness1 || friendliness2)
      return friendliness*.75f + 25;
    else
      return friendliness *.75f;
  }
  public double getHappiness() {
    if (happiness1 && happiness2)
      return happiness*.75f + 25;
    else
      return happiness *.75f;
  }
  public double getExpensiveness() {
    if (expensiveness1 && expensiveness2)
      return expensiveness*.50f + 50;
    else
      return expensiveness *.50f;
  }
  public double getSpeed() {
    if (speed1 || speed2)
      return friendliness*.50f + 50;
    else
      return friendliness *.50f;
  }
  public double getAgility() {
    if (agility1 && agility2)
      return expensiveness*.25f + 75;
    else
      return expensiveness *.25f;
  }
  public double getAttractiveness() {
    if (attractiveness1 || attractiveness2)
      return attractiveness*.25f + 75;
    else
      return attractiveness *.25f;
  }

  //Allows the house coordinate to change
  public void moveHouseCoords() {
    if (random(100) < 1) x++;
    if (random(100) < 1) x--;
    if (random(100) < 1) y++;
    if (random(100) < 1) y--;
  }

  public void box() {
    if (selected) {
      /*noFill();
      boolean fr = friendliness1 || friendliness2;
      boolean ha = happiness1 && happiness2;
      if ((!fr && ha)) rect(x+10, y+10, 60, 50);
      else if ((!fr && !ha) || (fr && ha)) rect(x+5, y+5, 65, 55);
      else rect (x, y, 70, 60);*/
      selectCreature(this, x, y);

    }
  }
}
String enviroment = "house";
public void setupHouse() {
  //enviroment = "house";

  if (enviroment.equals("house")) housebackground();
  else if (enviroment.equals("breed")) {
    breedScreen(mBreed, fBreed);
  }
  else if (enviroment.equals("new")) newCreature();
}
//Lets you learn about a creature by mousing over it.
public void profile() {
  if (enviroment.equals("house")) {
    textSize(30);
    int startTop = 0;
    int startLeft = 0;
    Creature selected = null;
    for (int i = 0; i < creatures.size(); i++) {
      int a = mouseX;
      int b = mouseY;
      startTop = b-200;
      if (startTop < 0) startTop = 0;
      if (a < 400) startLeft = a;
      else startLeft = a-200;
      float c = creatures.get(i).x;
      float d = creatures.get(i).y;
      //selected = null;
      if (a > c && a < c + 70 && b > d && b < d + 60) {
        selected = creatures.get(i);
      }
      creatures.get(i).box();
    }
    if (selected != null) {
      displayProfile(selected, startLeft, startTop);
      //friendliness happiness expensiveness
      //speed agility attractiveness
      //selected.selected = true;
      /*fill(selected.col);
      rect(startLeft, startTop, 200, 185);
      fill(0);
      //rect(50, 50, 17, 50);
      text(selected.name, startLeft + 100 - 8.5*(selected.name.length()), startTop+30);
      textSize(20);
      int shift = 20;
      if (selected.gender) text("Female", startLeft + 66, startTop + 50);
      else text("Male", startLeft + 78, startTop + 50);
      text("Friendliness", startLeft+5, startTop + 60 + shift);
      text(((int) selected.getFriendliness()) + "", startLeft + 172.5, startTop+60 + shift);
      text("Happiness", startLeft+5, startTop + 80 + shift);
      text(((int) selected.getHappiness()) + "", startLeft + 172.5, startTop+80 + shift);
      text("Expensiveness", startLeft+5, startTop + 100 + shift);
      text(((int) selected.getExpensiveness()) + "", startLeft + 172.5, startTop+100 + shift);
      text("Speed", startLeft+5, startTop + 120 + shift);
      text(((int) selected.getSpeed()) + "", startLeft + 172.5, startTop+120 + shift);
      text("Agility", startLeft+5, startTop + 140 + shift);
      text(((int) selected.getAgility()) + "", startLeft + 172.5, startTop+140 + shift);
      text("Attractiveness", startLeft+5, startTop + 160 + shift);
      text(((int) selected.getAttractiveness()) + "", startLeft + 172.5, startTop+160 + shift);
    */}
  }
}
//When the creature is clicked
public void clickCreature() {
  //Save button
  if (mouseX > 510 && mouseX < 575 && mouseY > 555 && mouseY < 595) {
    fill(0xffdd123c);
    rect(510, 555, 65, 40);
    delay(100);
    save();
  }
  //If the breed button has been clicked
  if (enviroment.equals("breed")) {
    //The cancel button
    if (mouseX > 350 && mouseX < 350 + 150 && mouseY > 425 && mouseY < 525) {
      breedNum = -1;
      selectedNum = -1;
      enviroment = "house";
    }
    //calculates the price
    int breedCost = (getCreatureValue(mBreed) + getCreatureValue(fBreed))/2;
    //The breed creatures button
    if (mouseX > 100 && mouseX < 300 && mouseY > 425 && mouseY < 525 && money-breedCost >= 0) {
      breedNum = -1;
      selectedNum = -1;
      enviroment = "new";
      creatures.add(new Creature(mBreed, fBreed));
      money -= (getCreatureValue(mBreed) + getCreatureValue(fBreed))/2;
      newCreature();
    }
  }
  else if (enviroment.equals("house") && selling) {
    Creature youreGoingToGetSold = null;
    int terminated = -1;
    for (int i = 0; i <creatures.size();i++) {
      float a = mouseX;
      float b = mouseY;
      float c = creatures.get(i).x;
      float d = creatures.get(i).y;
      if (a > c && a < c + 70 && b > d && b < d + 60) {
        youreGoingToGetSold = creatures.get(i);
        terminated = i;
      }
    }
    if (youreGoingToGetSold != null) {
      int price = getCreatureValue(youreGoingToGetSold);
      /*price += youreGoingToGetSold.getFriendliness();
      price += youreGoingToGetSold.getHappiness();
      price += youreGoingToGetSold.getExpensiveness();
      price += youreGoingToGetSold.getSpeed();
      price += youreGoingToGetSold.getAgility();
      price += youreGoingToGetSold.getAttractiveness();*/
      money += price;
      creatures.remove(terminated);
    }
    else {
      selling = false;
    }
  }
  //If the enviroment is house and you aren't selling stuff
  else if (enviroment.equals("house") && !selling) {
    //Click the sell button
    if (mouseX > 500 && mouseX < 590 && mouseY > 5 && mouseY < 45) {
      selling = true;
    }
    else {
      selling = false;
    }
    //If looking for someone to breed with
    if (breedNum != -1) {
      for (int i = 0; i < creatures.size(); i++) {
        int a = mouseX;
        int b = mouseY;
        float c = creatures.get(i).x;
        float d = creatures.get(i).y;
        //selected = null;
        creatures.get(i).selected = false;
        if (a > c && a < c + 70 && b > d && b < d + 60) {
          selectedNum = i;
        }
      }
      if (selectedNum != -1) {
        mBreed = creatures.get(breedNum);
        fBreed = creatures.get(selectedNum);
        if (xor(mBreed.gender, fBreed.gender)) {
          enviroment = "breed";
          //System.out.print("help");
          //breedScreen(m, f);
        }
      }
    }
    //If someone has been selected
    if (selectedNum != -1) {
      breedNum = -1;
      creatures.get(selectedNum).breeding = false;
      //for (int i = 0; i < creatures.size(); i++) {
        int a = mouseX;
        int b = mouseY;
        float c = creatures.get(selectedNum).x;
        float d = creatures.get(selectedNum).y;
        //selected = null;
        //creatures.get(i).selected = false;
        if (a > c && a < c + 70 && b > d + 60 && b < d + 60 + 18) breedNum=selectedNum;
      //}
      //rect(c, d, 20, 20);
      if (breedNum != -1)  {
        creatures.get(selectedNum).breeding = true;
      }
      if (breedNum == -1) selectedNum = -1;
    }
    //If no one has been selected
    if (selectedNum == -1) {
      selectedNum = -1;
      breedNum = -1;
      for (int i = 0; i < creatures.size(); i++) {
        int a = mouseX;
        int b = mouseY;
        float c = creatures.get(i).x;
        float d = creatures.get(i).y;
        //selected = null;
        creatures.get(i).selected = false;
        if (a > c && a < c + 70 && b > d && b < d + 60) {
          selectedNum = i;
        }
        //if (a > c && a < c + 70 && b > d + 60 && b < d + 60 + 18) rect(20, 20, 20, 20);
      }
      if (selectedNum != -1) {
        /*System.out.println(red(creatures.get(selectedNum).col));
        System.out.println(green(creatures.get(selectedNum).col));
        System.out.println(blue(creatures.get(selectedNum).col));*/
        creatures.get(selectedNum).selected = true;
      }
    }
  }
  else if (enviroment.equals("new")) {
    if (mouseX > 100 && mouseX < 500 && mouseY > 400 && mouseY < 500) {
      enviroment = "house";
      //System.out.println(1);
    }
  }


}
//Returns random boolean
public boolean randomBoolean() {
  if ((int) random(2) == 0)
    return true;
  else
    return false;
}
//Returns a random letter
public String randomLetter() {
  int letter = (int) random(26);
  if (letter == 0) return "A";
  else if (letter == 1) return "B";
  else if (letter == 2) return "C";
  else if (letter == 3) return "D";
  else if (letter == 4) return "E";
  else if (letter == 5) return "F";
  else if (letter == 6) return "G";
  else if (letter == 7) return "H";
  else if (letter == 8) return "I";
  else if (letter == 9) return "J";
  else if (letter == 10) return "K";
  else if (letter == 11) return "L";
  else if (letter == 12) return "M";
  else if (letter == 13) return "N";
  else if (letter == 14) return "O";
  else if (letter == 15) return "P";
  else if (letter == 16) return "Q";
  else if (letter == 17) return "R";
  else if (letter == 18) return "S";
  else if (letter == 19) return "T";
  else if (letter == 20) return "U";
  else if (letter == 21) return "V";
  else if (letter == 22) return "W";
  else if (letter == 23) return "X";
  else if (letter == 24) return "Y";
  else if (letter == 25) return "Z";
  else return " ";
}

//Returns a random vowel
public String randomVowel() {
  int letter = (int) random(6);
  if (letter == 0) return "A";
  if (letter == 1) return "E";
  if (letter == 2) return "I";
  if (letter == 3) return "O";
  if (letter == 4) return "U";
  if (letter == 5) return "Y";
  else return " ";
}

public String randomName() {
  String s = "";
  int len = 1+(int) random(6);
  for (int i = 0; i < len; i++) {
    if (random(3) < 1) {
      s += randomVowel();
    }
    else {
      s += randomLetter();
    }
  }
  return s;
}

//Returns a boolean randomly chose from two booleans
public boolean oneOfTwo(boolean x, boolean y) {
  if (randomBoolean()) return x;
  else return y;
}

//Returns a number similar to the average of two numbers
public int mutatedNumber(int x, int y) {
  int z = (x + y)/2;
  int z2 = (int) (random(100) - 50);
  return z + z2;
}
//Returns a value to be displayed on the punnet square
public String returnPunnetValue(String s, boolean b) {
  //friendliness happiness expensiveness
  //speed agility attractiveness
  if (s.equals("fr")) {
    if (b) return "F";
    else return "f";
  }
  if (s.equals("ha")) {
    if (b) return "H";
    else return "h";
  }
  if (s.equals("ex")) {
    if (b) return "E";
    else return "e";
  }
  if (s.equals("sp")) {
    if (b) return "S";
    else return "s";
  }
  if (s.equals("ag")) {
    if (b) return "A";
    else return "a";
  }
  if (s.equals("at")) {
    if (b) return "T";
    else return "t";
  }
  else return "";
}
public boolean xor(boolean a, boolean b) {
  return ((a || b) && !(a && b));
}
public int blackOrWhite(int c) {
  float  r = red(c);
  float g = green(c);
  float b = blue(c);
  float all = r + g +b;

  //System.out.println(all);
  if (all < 250 && r < 150 && b < 150 && g < 150) return 255;
  else return 0;
}
public int getCreatureValue(Creature youreGoingToGetSold) {
  int price = 0;
  price += youreGoingToGetSold.getFriendliness();
  price += youreGoingToGetSold.getHappiness();
  price += youreGoingToGetSold.getExpensiveness();
  price += youreGoingToGetSold.getSpeed();
  price += youreGoingToGetSold.getAgility();
  price += youreGoingToGetSold.getAttractiveness();
  return price;
}
String hold = "fr";
//Main function for punnet square generation
public void generatePunnetSquare(Creature m, Creature f, int x, int y, String stat) {
  //friendliness happiness expensiveness
  //speed agility attractiveness
  PShape p = GridLines();
  shape(p, x, y);
  if (stat.equals("fr")) {
    boolean m1 = m.friendliness1 || m.friendliness2;
    boolean m2 = m.friendliness1 && m.friendliness2;
    boolean f1 = f.friendliness1 || f.friendliness2;
    boolean f2 = f.friendliness1 && f.friendliness2;
    GridText(m, f, x, y, stat, m1, m2, f1, f2, true);
  }
  else if (stat.equals("ha")) {
    boolean m1 = !m.happiness1 || !m.happiness2;
    boolean m2 = !m.happiness1 && !m.happiness2;
    boolean f1 = !f.happiness1 || !f.happiness2;
    boolean f2 = !f.happiness1 && !f.happiness2;
    GridText(m, f, x, y, stat, m1, m2, f1, f2, false);
  }
  else if (stat.equals("ex")) {
    boolean m1 = !m.expensiveness1 || !m.expensiveness2;
    boolean m2 = !m.expensiveness1 && !m.expensiveness2;
    boolean f1 = !f.expensiveness1 || !f.expensiveness2;
    boolean f2 = !f.expensiveness1 && !f.expensiveness2;
    GridText(m, f, x, y, stat, m1, m2, f1, f2, false);
  }
  else if (stat.equals("sp")) {
    boolean m1 = m.speed1 || m.speed2;
    boolean m2 = m.speed1 && m.speed2;
    boolean f1 = f.speed1 || f.speed2;
    boolean f2 = f.speed1 && f.speed2;
    GridText(m, f, x, y, stat, m1, m2, f1, f2, true);
  }
  else if (stat.equals("ag")) {
    boolean m1 = !m.agility1 || !m.agility2;
    boolean m2 = !m.agility1 && !m.agility2;
    boolean f1 = !f.agility1 || !f.agility2;
    boolean f2 = !f.agility1 && !f.agility2;
    GridText(m, f, x, y, stat, m1, m2, f1, f2, false);
  }
  else if (stat.equals("at")) {
    boolean m1 = m.attractiveness1 || m.attractiveness2;
    boolean m2 = m.attractiveness1 && m.attractiveness2;
    boolean f1 = f.attractiveness1 || f.attractiveness2;
    boolean f2 = f.attractiveness1 && f.attractiveness2;
    GridText(m, f, x, y, stat, m1, m2, f1, f2, true);
  }
}

//Punnet square outline
public PShape GridLines() {
  PShape grid = createShape(GROUP);
  PShape[] gLine = new PShape[4];
  for (int i = 0; i < 4; i++) {
    if (i < 2)
      gLine[i] = createShape(LINE, i*50 +50, 0, i* 50 +50, 150);
    else
    gLine[i] = createShape(LINE, 0, i* 50-50, 150, i*50-50);
    grid.addChild(gLine[i]);
  }
  //m1.
  //m1.text();
  return grid;
}
//Text for grid
public void GridText(Creature m, Creature f, int x, int y, String bType, boolean m1, boolean m2, boolean f1, boolean f2, boolean isDom) {
  PFont fn = loadFont("Monospaced-30.vlw");
  textFont(fn);
  fill(0);
  fill(m.col);
  text(returnPunnetValue(bType, m1), 64.5f + x, 37.5f + y);
  text(returnPunnetValue(bType, m2), 114.5f + x, 37.5f + y);
  if (bType.equals("ha ") || bType.equals("ex ") || bType.equals("ag ")) {
  if (!((m1)||(!f1))) text(returnPunnetValue(bType, m1), 59 + x, 87.5f + y);
  if (!((m1)||(!f2))) text(returnPunnetValue(bType, m1), 59 + x, 137.5f + y);
  if (!((m2)||(!f1))) text(returnPunnetValue(bType, m2), 109 + x, 87.5f + y);
  if (!((m2)||(!f2))) text(returnPunnetValue(bType, m2), 109 + x, 137.5f + y);
  if (((m1)||(!f1))) text(returnPunnetValue(bType, m1), 74 + x, 87.5f + y);
  if (((m1)||(!f2))) text(returnPunnetValue(bType, m1), 74 + x, 137.5f + y);
  if (((m2)||(!f1))) text(returnPunnetValue(bType, m2), 124 + x, 87.5f + y);
  if (((m2)||(!f2))) text(returnPunnetValue(bType, m2), 124 + x, 137.5f + y);
  }
  else {
  if (!((!m1)&&(f1))) text(returnPunnetValue(bType, m1), 59 + x, 87.5f + y);
  if (!((!m1)&&(f2))) text(returnPunnetValue(bType, m1), 59 + x, 137.5f + y);
  if (!((!m2)&&(f1))) text(returnPunnetValue(bType, m2), 109 + x, 87.5f + y);
  if (!((!m2)&&(f2))) text(returnPunnetValue(bType, m2), 109 + x, 137.5f + y);
  if (((!m1)&&(f1))) text(returnPunnetValue(bType, m1), 74 + x, 87.5f + y);
  if (((!m1)&&(f2))) text(returnPunnetValue(bType, m1), 74 + x, 137.5f + y);
  if (((!m2)&&(f1))) text(returnPunnetValue(bType, m2), 124 + x, 87.5f + y);
  if (((!m2)&&(f2))) text(returnPunnetValue(bType, m2), 124 + x, 137.5f + y);
  }
  fill(f.col);
  text(returnPunnetValue(bType, f1), 14.5f + x, 87.5f + y);
  text(returnPunnetValue(bType, f2), 14.5f + x, 137.5f + y);
  if (bType.equals("ha ")  || bType.equals("ex ")  || bType.equals("ag ")) {
  if (!((!m1)&&(!f1))) text(returnPunnetValue(bType, f1), 74 + x, 87.5f + y);
  if (!((!m2)&&(!f1))) text(returnPunnetValue(bType, f1), 124 + x, 87.5f + y);
  if (!((!m1)&&(!f2))) text(returnPunnetValue(bType, f2), 74 + x, 137.5f + y);
  if (!((!m2)&&(!f2))) text(returnPunnetValue(bType, f2), 124 + x, 137.5f + y);
  if (((!m1)&&(!f1))) text(returnPunnetValue(bType, f1), 59 + x, 87.5f + y);
  if (((!m2)&&(!f1))) text(returnPunnetValue(bType, f1), 109 + x, 87.5f + y);
  if (((!m1)&&(!f2))) text(returnPunnetValue(bType, f2), 59 + x, 137.5f + y);
  if (((!m2)&&(!f2))) text(returnPunnetValue(bType, f2), 109 + x, 137.5f + y);
  }
  else {
    if (!((!m1)&&(f1))) text(returnPunnetValue(bType, f1), 74 + x, 87.5f + y);
  if (!((!m2)&&(f1))) text(returnPunnetValue(bType, f1), 124 + x, 87.5f + y);
  if (!((!m1)&&(f2))) text(returnPunnetValue(bType, f2), 74 + x, 137.5f + y);
  if (!((!m2)&&(f2))) text(returnPunnetValue(bType, f2), 124 + x, 137.5f + y);
  if (((!m1)&&(f1))) text(returnPunnetValue(bType, f1), 59 + x, 87.5f + y);
  if (((!m2)&&(f1))) text(returnPunnetValue(bType, f1), 109 + x, 87.5f + y);
  if (((!m1)&&(f2))) text(returnPunnetValue(bType, f2), 59 + x, 137.5f + y);
  if (((!m2)&&(f2))) text(returnPunnetValue(bType, f2), 109 + x, 137.5f + y);
  }
  if (once && bType.equals("ha")) {
    once = false;
    /*System.out.println(m.happiness1);
    System.out.println(m.happiness2);
    System.out.println(f.happiness1);
    System.out.println(f.happiness2);
    System.out.println(m1+ " " + m2 + " " + f1 + " " + f2);*/
  }

}
//Shows the percent of being happy
//A screen for breeding 2 Creatures
public void breedScreen(Creature m, Creature f) {
  //friendliness happiness expensiveness
  //speed agility attractiveness
  shape(m.shape, 50, 25);
  fill(f.col);
  text("Categories",250, 37.5f);
  fill(m.col);
  rect(200, 50, 400, 300);
  shape(f.shape, 50, 100);
  fill(f.col);
  textSize(30);
  //fill(0);
  text("Friendliness", 250, 50+37.5f);
  text("Happiness", 250, 100+37.5f);
  text("Expensiveness", 250, 150+37.5f);
  text("Speed", 250, 200+37.5f);
  text("Agility", 250, 250+37.5f);
  text("Attractiveness", 250, 300+37.5f);
  generatePunnetSquare(m, f, 25, 200, hold);
  for (int i = 0; i < 6; i++)line(200, 62.5f+37.5f +i*50, 600, 62.5f+37.5f + i*50);
  //Change the punnet square
  if (mouseX > 200 && mouseX < 600) {
    if (mouseY >50 && mouseY < 100) hold = "fr";
    if (mouseY >100 && mouseY < 150) hold = "ha";
    if (mouseY >150 && mouseY < 200) hold = "ex";
    if (mouseY >200 && mouseY < 250) hold = "sp";
    if (mouseY >250 && mouseY < 300) hold = "ag";
    if (mouseY >300 && mouseY < 350) hold = "at";
  }
  fill(f.col);
  rect(100, 425, 200, 100);
  rect(350, 425, 150, 100);
  fill(m.col);
  textSize(16);
  text("BREED CREATURES", 125, 475);
  int price = 0;
  price += getCreatureValue(m);
  price += getCreatureValue(f);
  /*price += m.getFriendliness();
  price += m.getHappiness();
  price += m.getExpensiveness();
  price += m.getSpeed();
  price += m.getAgility();
  price += m.getAttractiveness();
  price += f.getFriendliness();
  price += f.getHappiness();
  price += f.getExpensiveness();
  price += f.getSpeed();
  price += f.getAgility();
  price += f.getAttractiveness();*/
  price = price/2;
  if (money-price >= 0) text("This will cost $" + price, 110, 500);
  else {
    textSize(10);
    text("You don't have enough money",115,500);
  }
  textSize(16);
  text("Cancel", 400, 475);
}
//This file is about storing stuff in the save file and making the in game store work
//I think that's funny
//You should too
int money;
//Reads saved stuff
public void setupReader() {
  /*BufferedReader reader;
  String line;
  reader = createReader("data.txt");*/
  String[] load = loadStrings("data/data.txt");
  String line;
  try {
    //line = reader.readLine();
    line = load[0];
    money = Integer.parseInt(line);
    //line = reader.readLine();
    line = load[1];
    int times = Integer.parseInt(line);
    for (int i = 0; i < times; i ++) {
      //System.out.println(times);
      //line = reader.readLine();
      line = load[2 + i];
      //System.out.println(line);
      creatures.add(new Creature(line));
      //System.out.println(creatures.get(i).name);
    }
  }
  catch (Exception e) {
    System.out.println("I have no idea what I'm doing.");
    line = "600";
  }
  //System.out.println(line);
  //money = Integer.parseInt(line);
}
//Save stuff
public void save() {
    String[] saveString = new String[2 + creatures.size()];
    saveString[0] = money + "";
    saveString[1] = creatures.size() + "";
    for (int i = 0; i < creatures.size(); i++) {
      String thisLine = "";
      Creature saved = creatures.get(i);
      thisLine += saved.name + " " + saved.gender + " ";
      thisLine += saved.geneticDisease1 + " " + saved.geneticDisease2 + " ";
      thisLine += saved.friendliness + " " + saved.happiness + " " + saved.expensiveness + " ";
      thisLine += saved.speed + " " + saved.agility + " " + saved.attractiveness + " ";
      thisLine += saved.friendliness1 + " " + saved.happiness1 + " " + saved.expensiveness1 + " ";
      thisLine += saved.speed1 + " " + saved.agility1 + " " + saved.attractiveness1 + " ";
      thisLine += saved.friendliness2 + " " + saved.happiness2 + " " + saved.expensiveness2 + " ";
      thisLine += saved.speed2 + " " + saved.agility2 + " " + saved.attractiveness2 + " ";
      thisLine += ((int) red(saved.col)) + " " + ((int) green(saved.col)) + " " + ((int) blue(saved.col));
      saveString[2 + i] = thisLine;
    }
    /*PrintWriter output;
    output = createWriter("data.txt");
    output.print(saveString);
    output.flush();
    output.close();*/
    saveStrings("data/data.txt", saveString);
}

//Set up all the store stuff
public void storeSettings( ) {
  //System.out.println("ayy lmao");
  displayMoney();
  savebutton();
  sellButton();
}

//Display amount of money on the screen
public void displayMoney( ) {
  fill(0);
  textSize(20);
  text("Money: $" + money,10,585);
}
//You press this to save :D
public void savebutton() {
    fill(0xff888888);
    rect(510, 555, 65, 40);
    fill(blackOrWhite(0xff888888));
    textSize(20);
    text("Save", 520, 585);
}
//The button that lets you sell stuff
public void sellButton( ) {
  if (enviroment.equals("house")) {
    if (!selling) fill(80, 231, 18);
    else fill(color(14, 135, 32));
    rect(500, 5, 90, 40);
    fill(0);
    textSize(20);
    text("Sell",520,35);
  }
}
//Generates the store
public void setupShop( ) {

}
//Shows how much a creature is worth
public void showMoney( ) {
  if (enviroment.equals("house") && selling) {
    Creature youreGoingToGetSold = null;
    for (int i = 0; i < creatures.size(); i++) {
      float a = mouseX;
      float b = mouseY;
      float c = creatures.get(i).x;
      float d = creatures.get(i).y;
      if (a > c && a < c + 70 && b > d && b < d + 60) {
        youreGoingToGetSold = creatures.get(i);
      }
    }
    if (youreGoingToGetSold != null) {
      int price = getCreatureValue(youreGoingToGetSold);
      /*price += youreGoingToGetSold.getFriendliness();
      price += youreGoingToGetSold.getHappiness();
      price += youreGoingToGetSold.getExpensiveness();
      price += youreGoingToGetSold.getSpeed();
      price += youreGoingToGetSold.getAgility();
      price += youreGoingToGetSold.getAttractiveness();*/
      String value = "$"+price;
      float startTop = mouseY-10;
      float startLeft = mouseX;
      textSize(20);
      fill(youreGoingToGetSold.col);
      rect(startLeft,startTop, 8.5f*value.length() + 30, 40);
      fill(blackOrWhite(youreGoingToGetSold.col));
      text(value,10+startLeft,startTop+30);
    }
  }
}
//Displays the traits of a given creature
public void displayCreatureTraits(Creature c, int x, int y) {
  //friendliness happiness expensiveness
  //speed agility attractiveness
  fill(c.col);

  text(c.friendliness1 + "", x, y);
  text(c.happiness1 + "", x, y + 20);
  text(c.expensiveness1 + "", x, y + 40);
  text(c.speed1 + "", x, y + 60);
  text(c.agility1 + "", x, y + 80);
  text(c.attractiveness1 + "", x, y + 100);
  text(c.friendliness2 + "", x + 100, y);
  text(c.happiness2 + "", x + 100, y + 20);
  text(c.expensiveness2 + "", x + 100, y + 40);
  text(c.speed2 + "", x + 100, y + 60);
  text(c.agility2 + "", x + 100, y + 80);
  text(c.attractiveness2 + "", x + 100, y + 100);

  text(c.geneticDisease1, x, y + 120);
  text(c.geneticDisease2, x+100, y + 120);
}

//Displays the creature as the player would see them
public PShape displayCreature(Creature c) {
  PShape shape;
  //Setup
  //friendliness happiness expensiveness
  //speed agility attractiveness
  boolean fr = c.friendliness1 || c.friendliness2;
  boolean ha = c.happiness1 && c.happiness2;
  boolean ex = c.expensiveness1 && c.expensiveness2;
  boolean sp = c.speed1 || c.speed2;
  boolean ag = c.agility1 && c.agility2;
  boolean at = c.attractiveness1 || c.attractiveness2;
  shape = createShape(GROUP);
  PShape head = null;
  PShape lEar = null;
  PShape rEar = null;
  PShape body = null;
  PShape[] leg = new PShape[4];
  PShape tail = null;
  PShape lSpike = null;
  PShape rSpike = null;
  fill(c.col);
  //Head and ears
  if (fr) {
    head = createShape(ELLIPSE, 30, 30, 40, 40);
    if (ha) {
      lEar = createShape(TRIANGLE, 10, 10, 20, 10, 10, 20);
      rEar = createShape(TRIANGLE, 50, 10, 40, 10, 50, 20);
    }
    else {
      lEar = createShape(TRIANGLE, 0, 0, 20, 10, 10, 20);
      rEar = createShape(TRIANGLE, 60, 0, 40, 10, 50, 20);
    }
  }
  else {
    head = createShape(ELLIPSE, 35, 35, 30, 30);

    if (ha) {
      lEar = createShape(TRIANGLE, 20, 20, 30, 20, 20, 30);
      rEar = createShape(TRIANGLE, 50, 20, 40, 20, 50, 30);
    }
    else {
      lEar = createShape(TRIANGLE, 10, 10, 30, 20, 20, 30);
      rEar = createShape(TRIANGLE, 60, 10, 40, 20, 50, 30);
    }
  }
  //Body
  if (at) {
    body = createShape(RECT, 40,40,80,40);
  }
  else {
    body = createShape(RECT,40,40,80,20);
  }
  //Legs
  int a = 0;
  if (!sp) a = 10;
  for (int i = 0; i < 4; i++) {
    leg[i] = createShape(RECT, 40 + a + 20*i, 60, 10, 60);
  }
  //Tail
  if (ag) {
    tail = createShape(TRIANGLE, 120, 60, 140, 60, 140, 40);
  }
  else {
    tail = createShape(TRIANGLE, 120, 40, 140, 60, 140, 40);
  }
  //Spikes
  if (ex) {
    lSpike = createShape(TRIANGLE, 60, 40, 70, 30, 80, 40);
    rSpike = createShape(TRIANGLE, 90, 40, 100, 30, 110, 40);
  }
  else {
    lSpike = createShape(RECT, 60, 30, 10, 10);
    rSpike = createShape(RECT, 90, 30, 10, 10);
  }
  //End result
  shape.addChild(head);
  shape.addChild(lEar);
  shape.addChild(rEar);
  for (int i = 0; i < 4; i++) shape.addChild(leg[i]);
  shape.addChild(body);
  shape.addChild(tail);
  shape.addChild(lSpike);
  shape.addChild(rSpike);

  shape.scale(.5f);
  return shape;
}
public void housebackground() {
  if (enviroment.equals("house")) {
  background(0xff00ff00);
  fill(0xffFF00FF);
  rect(200, 100, 200, 400);
  fill(0xff0000FF);
  rect(0, 500, 600, 100);
  for (int i = 0; i < creatures.size(); i++) {
    creatures.get(i).moveHouseCoords();
    shape(creatures.get(i).shape, creatures.get(i).x, creatures.get(i).y);
  }
  storeSettings();
  if (!selling) profile();
  else showMoney();
}
}
public void displayProfile(Creature selected, int startLeft, int startTop) {
  fill(selected.col);
  rect(startLeft, startTop, 200, 185);
  fill(blackOrWhite(selected.col));
  //rect(50, 50, 17, 50);
  textSize(30);
  text(selected.name, startLeft + 100 - 8.5f*(selected.name.length()), startTop+30);
  textSize(20);
  int shift = 20;
  if (selected.gender) text("Female", startLeft + 66, startTop + 50);
  else text("Male", startLeft + 78, startTop + 50);
  text("Friendliness", startLeft+5, startTop + 60 + shift);
  text(((int) selected.getFriendliness()) + "", startLeft + 172.5f, startTop+60 + shift);
  text("Happiness", startLeft+5, startTop + 80 + shift);
  text(((int) selected.getHappiness()) + "", startLeft + 172.5f, startTop+80 + shift);
  text("Expensiveness", startLeft+5, startTop + 100 + shift);
  text(((int) selected.getExpensiveness()) + "", startLeft + 172.5f, startTop+100 + shift);
  text("Speed", startLeft+5, startTop + 120 + shift);
  text(((int) selected.getSpeed()) + "", startLeft + 172.5f, startTop+120 + shift);
  text("Agility", startLeft+5, startTop + 140 + shift);
  text(((int) selected.getAgility()) + "", startLeft + 172.5f, startTop+140 + shift);
  text("Attractiveness", startLeft+5, startTop + 160 + shift);
  text(((int) selected.getAttractiveness()) + "", startLeft + 172.5f, startTop+160 + shift);
}
//Highlight the creature
public void selectCreature(Creature c, float x, float y) {
  noFill();
  if (c.breeding) stroke(228, 8, 206);
  boolean fr = c.friendliness1 || c.friendliness2;
  boolean ha = c.happiness1 && c.happiness2;
  float top = 0;
  float left = 0;
  float he = 0;
  float wi = 0;
  if ((!fr && ha)) {
    left = x+10;
    wi = 60;
    top = y + 10;
    he = 50;
  }//rect(x+10, y+10, 60, 50);
  else if ((!fr && !ha) || (fr && ha)) {
    left = x + 5;
    top = y + 5;
    he = 55;
    wi = 65;
  }//rect(x+5, y+5, 65, 55);
  else {
    left = x;
    top = y;
    wi = 70;
    he = 60;
  }//(x, y, 70, 60);
  rect(left, top, wi, he);
  stroke(0);
  fill(c.col);
  rect(left, top+he, wi, 20);
  fill(0);
  textSize(18);
  text("BREED",left + wi/8,top+he+18);
}
public void newCreature() {
  Creature newGuy = creatures.get(creatures.size()-1);
  //scale(2);
  boolean fr = newGuy.friendliness1 || newGuy.friendliness2;
  boolean ha = newGuy.happiness1 && newGuy.happiness2;
  float left, top;

  if ((!fr && ha)) {
    left = 120;
    top = 200;
  }//rect(x+10, y+10, 60, 50);
  else if ((!fr && !ha) || (fr && ha)) {
    left = 130;
    top = 195;
  }//rect(x+5, y+5, 65, 55);
  else {
    left = 140;
    top = 190;
  }//(x, y, 70, 60);

  PShape newGuyShape = new PShape();
  newGuyShape.addChild(newGuy.shape);
  newGuyShape.scale(2);
  shape(newGuyShape, 500-140 ,top);
  //newGuyShape.scale(.5);

  //scale(1);
  fill(newGuy.col);
  textSize(30);
  text("You bred a new creature!",100,100);
  rect(100, 400, 400, 100);
  //textSize(30);
  fill(blackOrWhite(newGuy.col));
  text("Continue",194 + 8.5f*4,465);
  //rect(100, 400, 100+ 8.5*4 - 2.5, 100);
  //rect(400-8.5*4+2.5, 400, 100+8.5*4, 100);
  displayProfile(newGuy, 100, 150);
}
  public void settings() {  size(600, 600); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "CreatureGame" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
