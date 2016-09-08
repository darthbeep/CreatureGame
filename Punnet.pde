String hold = "fr";
//Main function for punnet square generation
void generatePunnetSquare(Creature m, Creature f, int x, int y, String stat) {
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
PShape GridLines() {
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
void GridText(Creature m, Creature f, int x, int y, String bType, boolean m1, boolean m2, boolean f1, boolean f2, boolean isDom) {
  PFont fn = loadFont("Monospaced-30.vlw");
  textFont(fn);
  fill(0);
  fill(m.col);
  text(returnPunnetValue(bType, m1), 64.5 + x, 37.5 + y);
  text(returnPunnetValue(bType, m2), 114.5 + x, 37.5 + y);
  if (bType.equals("ha ") || bType.equals("ex ") || bType.equals("ag ")) {
  if (!((m1)||(!f1))) text(returnPunnetValue(bType, m1), 59 + x, 87.5 + y);
  if (!((m1)||(!f2))) text(returnPunnetValue(bType, m1), 59 + x, 137.5 + y);
  if (!((m2)||(!f1))) text(returnPunnetValue(bType, m2), 109 + x, 87.5 + y);
  if (!((m2)||(!f2))) text(returnPunnetValue(bType, m2), 109 + x, 137.5 + y);
  if (((m1)||(!f1))) text(returnPunnetValue(bType, m1), 74 + x, 87.5 + y);
  if (((m1)||(!f2))) text(returnPunnetValue(bType, m1), 74 + x, 137.5 + y);
  if (((m2)||(!f1))) text(returnPunnetValue(bType, m2), 124 + x, 87.5 + y);
  if (((m2)||(!f2))) text(returnPunnetValue(bType, m2), 124 + x, 137.5 + y);
  }
  else {
  if (!((!m1)&&(f1))) text(returnPunnetValue(bType, m1), 59 + x, 87.5 + y);
  if (!((!m1)&&(f2))) text(returnPunnetValue(bType, m1), 59 + x, 137.5 + y);
  if (!((!m2)&&(f1))) text(returnPunnetValue(bType, m2), 109 + x, 87.5 + y);
  if (!((!m2)&&(f2))) text(returnPunnetValue(bType, m2), 109 + x, 137.5 + y);
  if (((!m1)&&(f1))) text(returnPunnetValue(bType, m1), 74 + x, 87.5 + y);
  if (((!m1)&&(f2))) text(returnPunnetValue(bType, m1), 74 + x, 137.5 + y);
  if (((!m2)&&(f1))) text(returnPunnetValue(bType, m2), 124 + x, 87.5 + y);
  if (((!m2)&&(f2))) text(returnPunnetValue(bType, m2), 124 + x, 137.5 + y);
  }
  fill(f.col);
  text(returnPunnetValue(bType, f1), 14.5 + x, 87.5 + y);
  text(returnPunnetValue(bType, f2), 14.5 + x, 137.5 + y);
  if (bType.equals("ha ")  || bType.equals("ex ")  || bType.equals("ag ")) {
  if (!((!m1)&&(!f1))) text(returnPunnetValue(bType, f1), 74 + x, 87.5 + y);
  if (!((!m2)&&(!f1))) text(returnPunnetValue(bType, f1), 124 + x, 87.5 + y);
  if (!((!m1)&&(!f2))) text(returnPunnetValue(bType, f2), 74 + x, 137.5 + y);
  if (!((!m2)&&(!f2))) text(returnPunnetValue(bType, f2), 124 + x, 137.5 + y);
  if (((!m1)&&(!f1))) text(returnPunnetValue(bType, f1), 59 + x, 87.5 + y);
  if (((!m2)&&(!f1))) text(returnPunnetValue(bType, f1), 109 + x, 87.5 + y);
  if (((!m1)&&(!f2))) text(returnPunnetValue(bType, f2), 59 + x, 137.5 + y);
  if (((!m2)&&(!f2))) text(returnPunnetValue(bType, f2), 109 + x, 137.5 + y);
  }
  else {
    if (!((!m1)&&(f1))) text(returnPunnetValue(bType, f1), 74 + x, 87.5 + y);
  if (!((!m2)&&(f1))) text(returnPunnetValue(bType, f1), 124 + x, 87.5 + y);
  if (!((!m1)&&(f2))) text(returnPunnetValue(bType, f2), 74 + x, 137.5 + y);
  if (!((!m2)&&(f2))) text(returnPunnetValue(bType, f2), 124 + x, 137.5 + y);
  if (((!m1)&&(f1))) text(returnPunnetValue(bType, f1), 59 + x, 87.5 + y);
  if (((!m2)&&(f1))) text(returnPunnetValue(bType, f1), 109 + x, 87.5 + y);
  if (((!m1)&&(f2))) text(returnPunnetValue(bType, f2), 59 + x, 137.5 + y);
  if (((!m2)&&(f2))) text(returnPunnetValue(bType, f2), 109 + x, 137.5 + y);
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
void breedScreen(Creature m, Creature f) {
  //friendliness happiness expensiveness
  //speed agility attractiveness
  shape(m.shape, 50, 25);
  fill(f.col);
  text("Categories",250, 37.5);
  fill(m.col);
  rect(200, 50, 400, 300);
  shape(f.shape, 50, 100);
  fill(f.col);
  textSize(30);
  //fill(0);
  text("Friendliness", 250, 50+37.5);
  text("Happiness", 250, 100+37.5);
  text("Expensiveness", 250, 150+37.5);
  text("Speed", 250, 200+37.5);
  text("Agility", 250, 250+37.5);
  text("Attractiveness", 250, 300+37.5);
  generatePunnetSquare(m, f, 25, 200, hold);
  for (int i = 0; i < 6; i++)line(200, 62.5+37.5 +i*50, 600, 62.5+37.5 + i*50);
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
