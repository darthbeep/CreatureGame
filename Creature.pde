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
  color col;

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
  void randomCreature() {
    randomStats();
    randomBioStats();
    randomDisease();
    randomColor();
    shape = displayCreature(this);
    gender = randomBoolean();
  }

  //Create child of two creatures
  void randomChildStats(Creature m, Creature f) {
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
  void randomStats() {
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
  void randomBioStats() {
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
  void randomDisease() {
    geneticDisease1 = randomLetter();
    geneticDisease2 = randomLetter();
    while (geneticDisease1.equals(geneticDisease2)) {
      geneticDisease2 = randomLetter();
    }
  }

  //Random color
  void randomColor() {
    red = (int) random(255);
    green = (int) random(255);
    blue = (int) random(255);
    col = color(red, green, blue);
  }

  //Retrieve stats in usable form
  double getFriendliness() {
    if (friendliness1 || friendliness2)
      return friendliness*.75 + 25;
    else
      return friendliness *.75;
  }
  double getHappiness() {
    if (happiness1 && happiness2)
      return happiness*.75 + 25;
    else
      return happiness *.75;
  }
  double getExpensiveness() {
    if (expensiveness1 && expensiveness2)
      return expensiveness*.50 + 50;
    else
      return expensiveness *.50;
  }
  double getSpeed() {
    if (speed1 || speed2)
      return friendliness*.50 + 50;
    else
      return friendliness *.50;
  }
  double getAgility() {
    if (agility1 && agility2)
      return expensiveness*.25 + 75;
    else
      return expensiveness *.25;
  }
  double getAttractiveness() {
    if (attractiveness1 || attractiveness2)
      return attractiveness*.25 + 75;
    else
      return attractiveness *.25;
  }

  //Allows the house coordinate to change
  void moveHouseCoords() {
    if (random(100) < 1) x++;
    if (random(100) < 1) x--;
    if (random(100) < 1) y++;
    if (random(100) < 1) y--;
  }

  void box() {
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
