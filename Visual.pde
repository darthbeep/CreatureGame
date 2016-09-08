//Displays the traits of a given creature
void displayCreatureTraits(Creature c, int x, int y) {
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
PShape displayCreature(Creature c) {
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

  shape.scale(.5);
  return shape;
}
void housebackground() {
  if (enviroment.equals("house")) {
  background(#00ff00);
  fill(#FF00FF);
  rect(200, 100, 200, 400);
  fill(#0000FF);
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
void displayProfile(Creature selected, int startLeft, int startTop) {
  fill(selected.col);
  rect(startLeft, startTop, 200, 185);
  fill(blackOrWhite(selected.col));
  //rect(50, 50, 17, 50);
  textSize(30);
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
}
//Highlight the creature
void selectCreature(Creature c, float x, float y) {
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
void newCreature() {
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
  text("Continue",194 + 8.5*4,465);
  //rect(100, 400, 100+ 8.5*4 - 2.5, 100);
  //rect(400-8.5*4+2.5, 400, 100+8.5*4, 100);
  displayProfile(newGuy, 100, 150);
}
