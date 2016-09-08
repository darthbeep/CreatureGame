//Returns random boolean
boolean randomBoolean() {
  if ((int) random(2) == 0)
    return true;
  else
    return false;
}
//Returns a random letter
String randomLetter() {
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
String randomVowel() {
  int letter = (int) random(6);
  if (letter == 0) return "A";
  if (letter == 1) return "E";
  if (letter == 2) return "I";
  if (letter == 3) return "O";
  if (letter == 4) return "U";
  if (letter == 5) return "Y";
  else return " ";
}

String randomName() {
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
boolean oneOfTwo(boolean x, boolean y) {
  if (randomBoolean()) return x;
  else return y;
}

//Returns a number similar to the average of two numbers
int mutatedNumber(int x, int y) {
  int z = (x + y)/2;
  int z2 = (int) (random(100) - 50);
  return z + z2;
}
//Returns a value to be displayed on the punnet square
String returnPunnetValue(String s, boolean b) {
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
boolean xor(boolean a, boolean b) {
  return ((a || b) && !(a && b));
}
color blackOrWhite(color c) {
  float  r = red(c);
  float g = green(c);
  float b = blue(c);
  float all = r + g +b;

  //System.out.println(all);
  if (all < 250 && r < 150 && b < 150 && g < 150) return 255;
  else return 0;
}
int getCreatureValue(Creature youreGoingToGetSold) {
  int price = 0;
  price += youreGoingToGetSold.getFriendliness();
  price += youreGoingToGetSold.getHappiness();
  price += youreGoingToGetSold.getExpensiveness();
  price += youreGoingToGetSold.getSpeed();
  price += youreGoingToGetSold.getAgility();
  price += youreGoingToGetSold.getAttractiveness();
  return price;
}
